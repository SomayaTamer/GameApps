 import java.util.*;

 public class Hangman{
     
     public static void play(Scanner scanner) {
 
         while (true){
             
             System.out.println("\t\t\t\t\t\t***** Welcome to Hangman Game *****\"");
             System.out.print("1) Play\n2) How to play\n3) Exit\nEnter your choice (1-3): ");
             String choice= scanner.nextLine().trim();
             
             // Check for empty input
             if (choice.isEmpty()) {
                 continue;
             }
             
             if (choice.charAt(0)=='1'){
                 System.out.print("\n1) Computer Opponent\n2) Friend Opponent\nEnter your choice (1-2): ");
                 String option = scanner.nextLine();
                
                 if (option.charAt(0)=='1'){
                     String computerWord= randomWord();
                     playHangman(scanner, computerWord);
 
                 }
                
                 else if(option.charAt(0)=='2'){
                     // The word to guess
                     System.out.print("\nEnter word to guess: ");
                     String word = scanner.nextLine().toUpperCase();
                     // Clear screen by printing many lines
                     for (int i = 0; i < 50; i++) {
                         System.out.println();
                     }
                     playHangman(scanner, word);
                 }
                
                 else{
                     System.out.print("Invalid! Please enter a valid choice (1-2)");
                 }
 
             }
             else if (choice.charAt(0)=='2'){
                 System.out.println("\nGame Rules:");
                 System.out.println("Hangman is a word-guessing game where the goal is to figure out a hidden word by guessing one letter at a time.\n" +
                 "For every incorrect guess, a part of the hangman figure (head, body, arms, legs) is drawn.\n"+
                 "The player wins by guessing all the letters before the figure is fully drawn.\nRepeated guesses and invalid inputs (e.g., numbers or multiple letters) are not allowed.\n"+
                 "The game is lost if the figure is completed (6 chances) before the word is guessed.\n");
             }
             else if (choice.charAt(0)=='3'){
                 System.out.println("\t\t\t\t\t\t***** program Exited *****");
                 break;
             }
             else{
                 System.out.println("Invalid! Please enter a valid choice (1-3)");
             }
         }
     }
 
     // Method for displaying the 6 chances in Hangman
     static String drawHangMan(int wrongGuesses){
         return switch(wrongGuesses){
 
             case 0 ->   """
                         ----.
                             |
                             |
                             |
                             ===
                         """;
 
             case 1 ->   """
                         ----.
                         O   |
                             |
                             |
                             ===
                         """;
                         
             case 2 ->   """
                         ----.
                         O   |
                         |   |
                             |
                             ===
                         """;
             case 3 ->   """
                         ----.
                         O   |
                        /|   |
                             |
                             ===
                         """;
             case 4 ->   """
                         ----.
                         O   |
                        /|\\  |
                             |
                             ===
                         """;
             case 5 ->   """
                         ----.
                         O   |
                        /|\\  |
                        /    |
                             ===
                         """;
             case 6 ->   """
                         ----.
                         O   |
                        /|\\  |
                        / \\  |
                             ===
                         """;
             
             default ->"";
         };
     }
 
 
     //Method for generation a random word for Computer Opponent
     public static String randomWord(){
         List<String> words = new ArrayList<String>();
         words.add("pizza");                             //0
         words.add("cherry");                           //1
         words.add("honey");                           //2
         words.add("keyboard");                       //3
         words.add("laptop");                        //4
         words.add("tissue");                       //5
         words.add("orange");                      //6
         words.add("tea");                        //7
         words.add("book");                      //8
         words.add("fan");                      //9
         words.add("hangman");                 //10
         words.add("fire");                   //11
         words.add("tangerine");             //12
         words.add("eraser");               //13
         words.add("cardigan");            //14
         words.add("clock");              //15
         words.add("rice");              //16
         words.add("banana");           //17
         words.add("ring");            //18
         words.add("key");            //19
         words.add("pants");         //20
         words.add("flag");         //21
         words.add("free");        //22
         words.add("palestine");  //23
         words.add("notebook");  //24
         words.add("creative"); //25
         words.add("tree");    //26
         
         // Generate a random index
         Random random = new Random();
         int randomIndex = random.nextInt(25);
 
         // Pick the random word
         String randomWord = words.get(randomIndex);
 
         return randomWord.toUpperCase();
     }
 
     // Method For playing hangman
     public static void playHangman(Scanner scanner, String word ) {
 
         // Array for the underscores
         ArrayList<Character> underscores = new ArrayList<>();
         for (int i = 0; i < word.length(); i++) {
             underscores.add('_');
         }
 
         // User guessing
         int attemptsLeft = 6;
         int wrongGuesses = 0;
         ArrayList<Character> guessedLetters = new ArrayList<>();
 
         while (attemptsLeft > 0) {
             System.out.print("\nGuess a letter: ");
             String guess = scanner.next().toUpperCase();
 
             // Check validity of input
             if (guess.length() != 1) {
                 System.out.println("Invalid! Please make sure to enter a single letter.");
                 continue;
             }
 
             char guessedChar = guess.charAt(0);
 
             if (guessedLetters.contains(guessedChar)) {
                 System.out.println("Invalid! You already guessed this letter before. Try again.");
                 continue;
             }
             guessedLetters.add(guessedChar);
 
             // Check if there is a matching letter
             if (word.indexOf(guessedChar) >= 0) {
                 System.out.println(drawHangMan(wrongGuesses));
                 System.out.println("Correct Guess!");
                 for (int i = 0; i < word.length(); i++) {
                     if (word.charAt(i) == guessedChar) {
                         underscores.set(i, guessedChar);
                     }
                 }
             } else {
                 attemptsLeft--;
                 wrongGuesses++;
                 System.out.println(drawHangMan(wrongGuesses));
                 System.out.println("Wrong guess!");
             }
 
             // Check if the word is fully guessed
             if (!underscores.contains('_')) {
                 System.out.println("\nCongratulations! YOU WIN!! \nThe word was: " + word);
                 break;
             }
 
             // Display current progress
             System.out.print("Word: ");
             for (char c : underscores) {
                 System.out.print(c + " ");
             }
             System.out.println();
 
             System.out.print("Guessed Letters: ");
             for (Character c : guessedLetters) {
                 System.out.print(c + " ");
             }
             System.out.println();
 
             System.out.println("Attempts Left: " + attemptsLeft);
         }
 
         // Game over message
         if (attemptsLeft == 0) {
             System.out.println("\nGame Over!! The word was: " + word);
         }
     }
 
 }