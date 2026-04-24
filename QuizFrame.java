import javax.swing.*;

public class QuizFrame {

    JFrame frame;
    JLabel questionLabel;
    JRadioButton[] options;
    JButton nextBtn;
    ButtonGroup group;

    QuizService service;

    public QuizFrame() {
        service = new QuizService();

        frame = new JFrame("Quiz Application");
        frame.setSize(500, 400);
        frame.setLayout(null);

        questionLabel = new JLabel();
        questionLabel.setBounds(50, 30, 400, 30);

        options = new JRadioButton[4];
        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setBounds(50, 80 + (i * 30), 300, 30);
            group.add(options[i]);
            frame.add(options[i]);
        }

        nextBtn = new JButton("Next");
        nextBtn.setBounds(200, 250, 100, 30);

        nextBtn.addActionListener(e -> nextQuestion());

        frame.add(questionLabel);
        frame.add(nextBtn);

        loadQuestion();

        // Timer
        new Timer(10000, e -> nextQuestion()).start();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void loadQuestion() {
        Question q = service.getCurrentQuestion();
        questionLabel.setText(q.question);

        for (int i = 0; i < 4; i++) {
            options[i].setText(q.options[i]);
        }

        group.clearSelection();
    }

    void nextQuestion() {
        String selected = null;

        for (JRadioButton btn : options) {
            if (btn.isSelected()) {
                selected = btn.getText();
            }
        }

        service.checkAnswer(selected);

        if (service.hasNext()) {
            loadQuestion();
        } else {
            JOptionPane.showMessageDialog(frame, "Score: " + service.getScore());
            System.exit(0);
        }
    }
}