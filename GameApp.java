/* Assignment_1_Part1_Task1
 * Somaya Tamer Magdy Shoaib
 * ID: 20231225
 * email:somayatamer2015@gmail.com
 * Project Discreption : A game app that includes the games Hangman and Mastermind. Hangman is implemented in both terminal and GUI.
 * Project github : https://github.com/SomayaTamer/GameApps
 */

 import java.util.*;
 import javax.swing.*;
 
 public class GameApp{
    public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("\t\t\t\t\t\t***** Welcome! Lets get Started *****\"");
        System.out.print("1) Play Hangman\n2) Play Mastermind\n3) Exit\nEnter your choice (1-3): ");
        String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("\n1) Play Hangman Terminal Version \n2) Play Hangman GUI Version \nEnter your choice(1-2): ");
                    String option = scanner.nextLine();
                    switch(option){
                        case"1":
                          Hangman.play(scanner); 
                          break;
                        case "2":
                          System.out.println("Launching Hangman GUI...");
                          JDialog dialog = new JDialog();
                          dialog.setAlwaysOnTop(true);
                          JOptionPane.showMessageDialog(dialog, "Welcome to Hangman Game");
                          SwingUtilities.invokeLater(HangmanGUI::new); // Launch GUI 
                          break;
                      
                        default:
                          System.out.println("Invalid choice! Please enter a valid choice between (1-2)");
                    }
                    break;
                case "2":
                    Mastermind.play(scanner); 
                    break;
                case "3":
                System.out.println("\t\t\t\t\t\t***** Game App Program Exited *****");
                    return; 
                default:
                    System.out.println("Invalid choice! Please enter a valid choice between (1-3)");
            }
        }
    }
 }
