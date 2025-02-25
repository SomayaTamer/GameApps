import java.util.*;

public class Mastermind {

    public static void play(Scanner scanner) {

        while (true){
            
            System.out.println("\t\t\t\t\t\t***** Welcome to MasterMind Game *****\"");
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
                    String randomCode= randomCodeGenerator();
                    playMasterMind(scanner, randomCode);
                }
               
                else if(option.charAt(0)=='2'){
                    // The word to guess
                    String code;
                    while (true){
                        System.out.print("\nEnter Code to guess: ");
                        code = scanner.nextLine();
                        boolean validity = inputValidity(code);
                        if (!validity){
                            System.out.println("Invalid! Please enter a valid code of 4 digits between (1-6)");
                            continue;
                        }
                        else{
                            break;
                        }
                    }

                    // Clear screen by printing many lines
                    for (int i = 0; i < 50; i++) {
                        System.out.println();
                    }
                    
                    playMasterMind(scanner, code);
                }
               
                else{
                    System.out.print("Invalid! Please enter a valid choice (1-2)");
                }

            }
            else if (choice.charAt(0)=='2'){
                System.out.println("\nGame Rules:");
                System.out.println("1. A secret 4-digit code will be generated with digits between 1 and 6.\n" + 
                                    "2. Your goal is to guess the code.\n" + 
                                    "3. Feedback will be given as follows:\n" + 
                                        "   - Black Peg: Correct digit in the correct position.\n" + 
                                        "   - White Peg: Correct digit in the wrong position.\n" +
                                    "You have 10 attempts. Good luck!");
                
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


   // Method For playing MasterMind1
    public static void playMasterMind(Scanner scanner, String code ) {

        int attemptsLeft = 10;

        while (attemptsLeft > 0) {
            // User guessing
            System.out.print("\nEnter Your Guess of 4 digits between (1-6): ");
            String guess = scanner.next().toUpperCase();

            boolean validity = inputValidity(guess);
            if(!validity){
                System.out.println("Invalid! Please enter a valid code of 4 digits between (1-6)");
                continue;
            }
 
            int blackPeg=0;
            int whitePeg=0;

            boolean[] codeMatched = new boolean[4]; // Tracks matched positions in the code
            boolean[] guessMatched = new boolean[4]; // Tracks matched positions in the guess

            // Check black pegs first 
            for(int i =0 ; i<4 ; i++){
                if(code.charAt(i) == guess.charAt(i)){
                    blackPeg++;
                    codeMatched[i]=true;
                    guessMatched[i] = true;
                }
            }
            
            // Check white pegs
            for (int i = 0; i < 4; i++) {
                if (!guessMatched[i]) { // Check unmatched positions in the guess
                    for (int j = 0; j < 4; j++) {
                        if (!codeMatched[j] &&guess.charAt(i) == code.charAt(j)) {
                            whitePeg++;
                            codeMatched[j] = true; 
                            break; 
                        }
                    }
                }
            }

            // Check if the code is fully guessed
            if (blackPeg==4) {
                System.out.println("\nCongratulations! YOU WIN!! \nThe code was: " + code);
                break;
            }

            attemptsLeft--;
            // Display current progress
            System.out.println("Black Pegs (Correct Digit and Position) : " + blackPeg);
            System.out.println("White Pegs (Correct Digit, Wrong Position): " + whitePeg);
            System.out.println("Attempts Left: " + attemptsLeft);
        }

        // Game over message
        if (attemptsLeft == 0) {
            System.out.println("\nGame Over!! The code was: " + code);
        }
    }

    //Method to check validity of input 
    public static boolean inputValidity (String input){
       
        boolean numberValidity = true;
        for(char c: input.toCharArray()){
            if(c<'1' || c>'6'){
                numberValidity= false;
            }
        }
        if(!numberValidity || input.length() !=4){
            return false;
        }
        return true;
    }

    //Method for random code generator
    public static String randomCodeGenerator(){
        Random random = new Random();
        String randomCode="";
        for(int i =0; i<4 ; i++){
            randomCode += random.nextInt(6) + 1;; 
        }
        return randomCode;
    }

}
