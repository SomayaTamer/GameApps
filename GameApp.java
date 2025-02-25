/* Assignment-1-Part1-Task1
 * Somaya Tamer Magdy Shoaib
 * ID: 20231225
 * email:somayatamer2015@gmail.com
 * Project Discreption : A game app that includes the games Hangman and Mastermind
 * Project github : https://github.com/SomayaTamer/GameApps
 */

 import java.util.*;

 public class GameApp{
    public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("\t\t\t\t\t\t***** Welcome! Lets get Started *****\"");
        System.out.print("1) Play Hangman\n2) Play Mastermind\n3) Exit\nEnter your choice: ");
        String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Hangman.play(scanner); 
                    break;
                case "2":
                    Mastermind.play(scanner); 
                    break;
                case "3":
                System.out.println("\t\t\t\t\t\t***** Game App Program Exited *****");
                    return; 
                default:
                    System.out.println("Invalid choice!Please enter a valid choice (1-3).");
            }
        }
    }
 }
