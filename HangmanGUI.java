import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class HangmanGUI implements ActionListener {

    // GUI Components
    private JFrame frame;
    private JButton submitButton , restartButton;
    private JLabel label , underscoresLabel , progressLabel;
    private JTextField guessTextField;
    private JRadioButton computer , human ;
    private ImageIcon hangmanImage ,case0, case1, case2, case3, case4, case5, case6;
    private Font font , underscoresFont;
    
    // Game Variables
    private String word;
    private int wrongGuess , attemptsLeft;
    private StringBuilder underscoreString , progressString;
    private char guessedChar;
    private boolean selectComputer;

    ArrayList<Character> guessedLetters = new ArrayList<>();
    ArrayList<Character> underscores = new ArrayList<>();

    
    public HangmanGUI(){
        // Get user's selection of opponent 
        humanOrComputer();

        frame = new JFrame();
        label = new JLabel();
        underscoresLabel = new JLabel();
        progressLabel = new JLabel();
        submitButton = new JButton("Submit");
        restartButton = new JButton("Restart");
        guessTextField = new JTextField();
        font = new Font("Arial", Font.BOLD, 24);
        underscoresFont = new Font("Arial", Font.BOLD, 30);

        // Load the images 
        hangmanImage =  new ImageIcon("Hangman.png");
        case0 =  new ImageIcon("Case 0.png");
        case1 =  new ImageIcon("Case 1.png");
        case2 =  new ImageIcon("Case 2.png");
        case3 =  new ImageIcon("Case 3.png");
        case4 =  new ImageIcon("Case 4.png");
        case5 =  new ImageIcon("Case 5.png");
        case6 =  new ImageIcon("Case 6.png");

        // Game Variables
        if(selectComputer){
            word=randomWord();
        }
        else{
            word = JOptionPane.showInputDialog("Enter Word to Guess: ").toUpperCase();

        }
        for (int i = 0; i < word.length(); i++) {
            underscores.add('_');
        }
        underscoreString = new StringBuilder();
        for (Character c : underscores) {
            underscoreString.append(c).append(' '); 
        }
        progressString = new StringBuilder();
        for (Character c : guessedLetters) {
            progressString.append(c).append(','); 
        }
                
        wrongGuess=0;
        attemptsLeft=6;

        //Setting the text and image labels
        label.setIcon(case0);
        label.setText("Enter Your Guess: ");
        label.setFont(font);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setIconTextGap(20);
        label.setBounds(0, 0, 539, 320); 

        //Setting the underscorses label
        underscoresLabel.setText(underscoreString.toString().trim());
        underscoresLabel.setHorizontalAlignment(JLabel.CENTER);
        underscoresLabel.setBounds(0, 320, 539, 40);
        underscoresLabel.setFont(underscoresFont); 

        //Setting The game progress Label
        progressLabel.setText("<html>Attempts Left: " + attemptsLeft + "<br>Guessed Letters: " + progressString + "</html>"); // <br> line breaker using html because JLabel doesnt support multu-line text 
        progressLabel.setHorizontalAlignment(JLabel.CENTER);
        progressLabel.setBounds(0, 440, 539, 60);
        progressLabel.setFont(font); 
        

        //Set the user's guess text field
        guessTextField.setBounds(180, 390, 80, 30); 
        guessTextField.setFont(font);
        
        //Set the Buttons
        submitButton.setBounds(270,390,80,30);
        submitButton.addActionListener(this);
        restartButton.setBounds(220,510,80,30);
        restartButton.addActionListener(this);

        // Set the Frame
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frame.setResizable(false);
        frame.setLayout(null); 
        frame.setSize(539,600);
        frame.setTitle("Hangman"); 
        frame.setIconImage(hangmanImage.getImage());
        frame.getContentPane().setBackground(new Color(135, 206, 235));
        frame.add(label);
        frame.add(underscoresLabel);
        frame.add(progressLabel);
        frame.add(guessTextField);
        frame.add(submitButton);
        frame.add(restartButton);
        frame.setVisible(true);   
    }
    
    public static void main(String[] args){
        new HangmanGUI();
    }

    public void humanOrComputer(){
        // Set the radio buttons
        computer = new JRadioButton("Computer");
        human= new JRadioButton("Human");
        ButtonGroup group = new ButtonGroup(); // to select only 1 item
        group.add(computer);
        group.add(human);
       
        // Set the Jpanel Dialog
        Object[] options = {"OK"};
        int selection = JOptionPane.showOptionDialog(
            frame,
            new Object[]{"Choose Your Opponent:", computer, human},
            "Game Mode Selection",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (selection == 0) {
            if (computer.isSelected()) {
                selectComputer = true;
            } else if (human.isSelected()) {
                selectComputer = false;
            } else {
                selectComputer = true; // Default to computer if no selection is made
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Submit Button
        if(e.getSource()==submitButton){
            // Check validity of input
            String guess =guessTextField.getText().toUpperCase();
            if (guess.length() != 1) {
                JOptionPane.showMessageDialog(null,"Invalid! Please make sure to enter a single letter.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return;
            }
            guessedChar = guess.charAt(0);
            if (guessedLetters.contains(guessedChar)) {
                JOptionPane.showMessageDialog(null,"Invalid! You already guessed this letter before. Try again.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return;
            }
            guessedLetters.add(guessedChar);
               
               
            // Check if there is a matching letter in word
            if (word.indexOf(guessedChar) >= 0) {
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guessedChar) {
                        underscores.set(i, guessedChar);
                    }
                }
            } else {
                wrongGuess++;
                attemptsLeft--;
                drawHangMan(wrongGuess);
            }

            // Update the underscores label
            updateUnderscoresLabel();
            // Update progress string
            updateProgressLabel();

            // Check for win or loss
            if (!underscores.contains('_')) {
                JOptionPane.showMessageDialog(frame, "Congratulations! YOU WIN!! \nThe word was: " + word, "Game Over", JOptionPane.INFORMATION_MESSAGE);
                //frame.dispose(); 
            } else if (attemptsLeft == 0) {
                JOptionPane.showMessageDialog(frame, "Game Over!! The word was: " + word, "Game Over", JOptionPane.ERROR_MESSAGE);
                //frame.dispose(); 
            }

            guessTextField.setText("");
        }

        // Restart Button
        if(e.getSource()==restartButton){
            restartGame();;
        }
        
    }

    // Helper method to update the underscores label
    private void updateUnderscoresLabel() {
        underscoreString.setLength(0); 
        for (Character c : underscores) {
            underscoreString.append(c).append(' ');
        }
        underscoresLabel.setText(underscoreString.toString().trim());
    }

    // Helper method to update the progress lavbel
    private void updateProgressLabel() {
        progressString.setLength(0); 
        for (Character c : guessedLetters) {
            if (!progressString.toString().contains(c.toString())){
                progressString.append(c).append(", ");
            }
            
        }
    
        if (progressString.length() > 0) {
            progressString.setLength(progressString.length() - 2);
        }
    
        progressLabel.setText("<html>Attempts Left: " + attemptsLeft + "<br>Guessed Letters: " + progressString + "</html>");
    }
   
    private void restartGame() {
        // Inform User that game has been restarted
        JOptionPane.showMessageDialog(frame, "Game has been Restarted!", "Game Restarted", JOptionPane.INFORMATION_MESSAGE);
        
        humanOrComputer();
        // Reset game variables
        //Clear the Arrays
        guessedLetters.clear();
        underscores.clear();

        // Reset Game Variables
        if (selectComputer) {
            word = randomWord();
        } else {
            word = JOptionPane.showInputDialog("Enter Word to Guess: ").toUpperCase();
        }
        for (int i = 0; i < word.length(); i++) {
            underscores.add('_');
        }
        underscoreString = new StringBuilder();
        for (Character c : underscores) {
            underscoreString.append(c).append(' ');
        }
        progressString = new StringBuilder();
        for (Character c : guessedLetters) {
            progressString.append(c).append(','); 
        }
        
        wrongGuess = 0;
        attemptsLeft = 6;

        // Reset GUI components
        label.setIcon(case0); 
        underscoresLabel.setText(underscoreString.toString().trim());
        progressLabel.setText("<html>Attempts Left: " + attemptsLeft + "<br>Guessed Letters: " + progressString +"</html>");
        guessTextField.setText("");
 
    }


    //Method for generation a random word for Computer Opponent
    public static String randomWord(){
        List<String> words = Arrays.asList(
            "pizza", "cherry", "honey", "keyboard", "laptop", "tissue", 
            "orange", "tea", "book", "fan", "hangman", "fire", "tangerine", 
            "eraser", "cardigan", "clock", "rice", "banana", "ring", "key", 
            "pants", "flag", "free", "palestine", "notebook", "creative", "tree"
         );
         
         // Generate a random index
         Random random = new Random();
         int randomIndex = random.nextInt(25);
 
         // Pick the random word
         String randomWord = words.get(randomIndex);
 
         return randomWord.toUpperCase();
     }

    // Method for displaying the 6 chances in Hangman
    public void drawHangMan(int wrongGuesses) {
        switch (wrongGuesses) {
            case 0 -> label.setIcon(case0);
            case 1 -> label.setIcon(case1);
            case 2 -> label.setIcon(case2);
            case 3 -> label.setIcon(case3);
            case 4 -> label.setIcon(case4);
            case 5 -> label.setIcon(case5);
            case 6 -> label.setIcon(case6);
            default -> System.out.println();
        }
    }
}
