import java.util.*;

public class Tools {
     /*
     * Name: fancyText
     * Description: Prints a message with a delay between each character and a delay
     * after the message so it looks nice
     * Parameters: String message, int textSpeed, int ms - milliseconds to delay
     * after message
     * Returns: n/a
     */
    public static void fancyText(String message, int textSpeed, int ms) throws InterruptedException {
        for (int j = 0; j < message.length(); j++) {
            System.out.print(message.charAt(j));
            Thread.sleep(textSpeed);
        }
        Thread.sleep(ms);
    } // ends fancyText

     /*
     * Name: validInt
     * Description: Checks if a number is an int or not and returns it
     * Parameters: Scanner scanner, String message
     * Returns: a valid int number that the user enters
     */
    public static int validInt(Scanner scanner, String instruction) {
        int validNumber = 0;
  
        while (true) {
            try {
                validNumber = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException mismatch) {
                System.out.println(instruction);
                scanner.nextLine();
            }
        }
        return validNumber;
    } // ends validInt

    /*
     * Name: validDouble
     * Description: Checks if a number is a double or not and returns it
     * Parameters: Scanner scanner, String message
     * Returns: a valid double number that the user enters
     */
    public static double validDouble(Scanner scanner, String message) {
        double validNumber = 0;
        
        while (true) {
            try {
                validNumber = scanner.nextDouble();
                scanner.nextLine();
                break;
            } catch (InputMismatchException mismatch) {
                System.out.println("Please input " + message + ": ");
                scanner.nextLine();
            }
        }
        return validNumber;
    } // ends validDouble

    /*
     * Name: ranNumBetween
     * Description: Generates a random number between two numbers
     * Parameters: int min, int max
     * Returns: a random number between min and max
     */
    public static int ranNumBetween(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    } // ends ranNumBetween

} // ends class
