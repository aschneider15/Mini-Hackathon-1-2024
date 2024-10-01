package Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main 
{
    private static int currentQuestionIndex = 0;  // Track the current question
    private static int score = 0;                 // Track the user's score
    private static JPanel[] questionPanels;       // Store the panels for each question
    private static CardLayout cardLayout;         // CardLayout for switching between panels

    public static void main(String[] args) 
    {
        Quiz dumQuiz = new Quiz();
        int totalQuestions = dumQuiz.getContent().length;
        
        // Create the board
        JFrame frame = new JFrame("Trivia");
        frame.setSize(1200, 900);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout); // Panel to hold all the question panels
        
        questionPanels = new JPanel[totalQuestions];
        
        for (int q = 0; q < totalQuestions; q++) 
        {
            MultipleChoice mc = dumQuiz.getContent()[q];
            JPanel questionPanel = new JPanel(new BorderLayout());

            JLabel questionLabel = new JLabel();
            questionLabel.setForeground(Color.black);
            questionLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Question size
            questionLabel.setHorizontalAlignment(JLabel.CENTER);
            questionLabel.setText(mc.getQuestion()); // Set question text
            questionLabel.setOpaque(true);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(2, 2));

            JButton[] buttons = new JButton[4];
            String[] buttonLabels = mc.getAnswers(); // Get the possible answers

            for (int i = 0; i < 4; i++)
             {
                buttons[i] = new JButton(buttonLabels[i]);
                buttons[i].setFont(new Font("Arial", Font.BOLD, 40)); // Possible answer size
                buttons[i].setFocusable(false);
                buttons[i].setBackground(Color.lightGray);
                buttons[i].setForeground(Color.black);

                int correctAnswerIndex = mc.getCorrectAnswer(); // Store the correct answer index

                buttons[i].addActionListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e) 
                    {
                        JButton sourceButton = (JButton) e.getSource();

                        // Check if the selected answer is correct
                        if (mc.getAnswers()[correctAnswerIndex].equals(sourceButton.getText())) 
                        {
                            sourceButton.setBackground(Color.green); // Highlight correct answer
                            JOptionPane.showMessageDialog(frame, "Correct!");
                            score++; // Increment score if correct
                        } else 
                        {
                            sourceButton.setBackground(Color.red); // Highlight wrong answer
                            JOptionPane.showMessageDialog(frame, "Wrong! The correct answer was: " + mc.getAnswers()[correctAnswerIndex]);
                        }
                        
                        // Disable all buttons after selection
                        for (JButton button : buttons) 
                        {
                            button.setEnabled(false);
                        }

                        // Add "Next" button to go to the next question
                        JButton nextButton = new JButton("Next");
                        nextButton.setFont(new Font("Arial", Font.BOLD, 36));
                        nextButton.setFocusable(false);
                        nextButton.addActionListener(new ActionListener() 
                        {
                            public void actionPerformed(ActionEvent e) 
                            {
                                goToNextQuestion(mainPanel, totalQuestions, frame);
                            }
                        });

                        questionPanel.add(nextButton, BorderLayout.SOUTH); // Add Next button at the bottom
                        frame.revalidate(); // Refresh frame to show new component
                    }
                });

                buttonPanel.add(buttons[i]);
            }

            questionPanel.add(questionLabel, BorderLayout.NORTH);
            questionPanel.add(buttonPanel, BorderLayout.CENTER);

            // Add question panel to the array
            questionPanels[q] = questionPanel;
            mainPanel.add(questionPanel, "question" + q);
        }

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Function to go to the next question or end the quiz
    private static void goToNextQuestion(JPanel mainPanel, int totalQuestions, JFrame frame) 
    {
        currentQuestionIndex++;

        if (currentQuestionIndex < totalQuestions) 
        {
            cardLayout.show(mainPanel, "question" + currentQuestionIndex);
        } else {
            showEndScreen(frame);
        }
    }

    // Function to display the end screen with the final score
    private static void showEndScreen(JFrame frame) 
    {
        JPanel endPanel = new JPanel(new BorderLayout());
        JLabel endLabel = new JLabel("End of Quiz! Your score: " + score);
        endLabel.setFont(new Font("Arial", Font.BOLD, 40));
        endLabel.setHorizontalAlignment(JLabel.CENTER);
        endPanel.add(endLabel, BorderLayout.CENTER);

        frame.getContentPane().removeAll();  // Clear all components from the frame
        frame.add(endPanel);                 // Add the end screen panel
        frame.revalidate();
        frame.repaint();                     // Repaint the frame to show the new content
    }
}


/*
 * public class TicTacToe
 * {
 * int boardWidth = 600;
 * int boardHeight = 650;
 * 
 * JFrame frame = new JFrame("TicTacToe");
 * JLabel textLabel = new JLabel();
 * JPanel textPanel = new JPanel();
 * JPanel boardPanel = new JPanel();
 * 
 * JButton[][] board = new JButton[3][3];
 * String playerX = "x";
 * String playerO = "O";
 * String currentPlayer = playerX;
 * 
 * boolean gameOver = false;
 * 
 * TicTacToe()
 * {
 * frame.setVisible(true);
 * frame.setSize(boardWidth, boardHeight);
 * frame.setLocationRelativeTo(null);
 * frame.setResizable(false);
 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 * frame.setLayout(new BorderLayout());
 * 
 * // Debugged
 * textLabel.setBackground(Color.darkGray);
 * textLabel.setForeground(Color.white);
 * textLabel.setFont(new Font("Arial", Font.BOLD, 50));
 * textLabel.setHorizontalAlignment(JLabel.CENTER);
 * 
 * textLabel.setText("Tic-Tac-Toe");
 * textLabel.setOpaque(true);
 * 
 * // Debugged
 * textPanel.setLayout(new BorderLayout());
 * textPanel.add(textLabel);
 * frame.add(textPanel, BorderLayout.NORTH);
 * 
 * // Debugged
 * boardPanel.setLayout(new GridLayout(3, 3));
 * boardPanel.setBackground(Color.darkGray);
 * frame.add(boardPanel);
 * 
 * for (int r = 0; r < 3; r++)
 * {
 * for (int c = 0; c < 3; c++)
 * {
 * JButton tile = new JButton();
 * board[r][c] = tile;
 * boardPanel.add(tile);
 * 
 * tile.setBackground(Color.darkGray);
 * tile.setForeground(Color.white);
 * tile.setFont(new Font("Arial", Font.BOLD, 120));
 * tile.setFocusable(false);
 * 
 * tile.addActionListener(new ActionListener()
 * {
 * public void actionPerformed(ActionEvent e)
 * {
 * if (gameOver) return;
 * JButton tile = (JButton) e.getSource();
 * if (tile.getText() == "")
 * {
 * tile.setText(currentPlayer);
 * checkWinner();
 * if (!gameOver)
 * {
 * currentPlayer = currentPlayer == playerX ? playerO : playerX;
 * textLabel.setText(currentPlayer + "'s turn.");
 * }
 * }
 * }
 * void setWinner(JButton tile)
 * {
 * tile.setForeground(Color.green);
 * tile.setBackground(Color.gray);
 * textLabel.setText(currentPlayer + " is the winner!");
 * }
 * });
 * }
 * }
 * }
 * }
 * JPanel buttonPanel = new JPanel();
buttonPanel.setLayout(new GridLayout(2, 2));
JButton[] buttons = new JButton[4];
String[] buttonLabels = mc.getAnswers(); // Implement answers to this String
for (int i = 0; i < 4; i++) {
    buttons[i] = new JButton(buttonLabels[i]);
    buttons[i].setFont(new Font("Arial",

 */