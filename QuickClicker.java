import java.text.DecimalFormat;
import java.util.Scanner;

public class QuickClicker {
    public static void main(String[] args) throws InterruptedException {
        // initializes scanner and variables
        Scanner userInput = new Scanner(System.in);
        int time = 5000; // how long the game is played, in milliseconds
        int highestScore = 0; // initializing here to get around out of bounds error
        int playerWithHighestScore = -1;

        // variables for printing messages
        int textSpeed = 18;
        int messageLongDelay = 1100;
        int messageShortDelay = 225;

        // welcomes the user and explains the game
        instructions(textSpeed, messageLongDelay, time);

        boolean playAgain = false; // for if they want to play again at the end

        // main game
        do {

            // asks how many rounds they would like to play and stores it
            int numberRounds = numberRounds(textSpeed, messageShortDelay, userInput);

            // asks how many players are playing and stores it
            int numberPlayers = numberPlayers(textSpeed, messageShortDelay, userInput);

            // creates the count variable before for loop & after getting number of players
            // to get around weird out of scope issues
            int[] count = new int[numberPlayers];
            Scanner[] counterInput = new Scanner[numberPlayers];

            // for each round, it will loop through the game
            for (int c = 0; c < numberRounds; c++) {
                Tools.fancyText("\n------------------------Round #" + (c + 1) + "-----------------------", textSpeed,
                        messageLongDelay);

                // for each player, it will loop through the game
                for (int i = 0; i < numberPlayers; i++) {
                    // gets the player ready and starts a countdown from 3
                    countdown(i, textSpeed);

                    counterInput[i] = new Scanner(System.in);

                    count[i] = counter(counterInput[i], time);

                    // gives a delay after they're done, so it doesn't mess up messages
                    System.out.println("Finished!!");
                    Thread.sleep(2750);

                    Tools.fancyText("Your score was " + count[(i)] + ". Great job!", textSpeed, messageLongDelay);

                    // gives a delay in between so it's not so fast in between players (only if it's
                    // not the last player)
                    if (!((i + 1) == numberPlayers)) {
                        Tools.fancyText("\n------------------------Loading-----------------------", textSpeed, 2500);
                    }

                    // checks if the new score is the highest score or not
                    highestScore = (highestScore(count));

                    // check if the current player's score is the highest so far
                    if (count[i] == highestScore) {
                        playerWithHighestScore = i + 1; // store the player number
                    }
                } // ends for (each player's turn)

                // tells the user who had the highest score
                Tools.fancyText("\nThe player with the highest score is player #" + playerWithHighestScore + " with " + highestScore + "!", textSpeed, messageLongDelay);

            } // ends for (each round)

            // checks if the user wants to play again
            playAgain = playAgain(userInput);

            // resets everything
            highestScore = 0;
            playerWithHighestScore = -1;

        } while (playAgain); // ends the game

        userInput.close();
    } // ends main

    /**
     * Prompts the user to input the number of rounds they want to play and returns
     * the chosen number.
     *
     * @param textSpeed    The speed of text display.
     * @param messageDelay The delay between messages.
     * @param userInput    Scanner object to read user input.
     * @return The number of rounds chosen by the user.
     * @throws InterruptedException If interrupted while waiting for user input.
     */
    public static int numberRounds(int textSpeed, int messageDelay, Scanner userInput) throws InterruptedException {
        // initializes variables
        String instruction = "\nInvalid answer, how many rounds would you like to play: ";
        int numberRounds = -1;

        // asks how many rounds they would like to play and stores it
        Tools.fancyText("\nHow many round would you like to play?: ", textSpeed, messageDelay);

        // will loop until it gets a valid answer (greater than 0 and an integer)
        while (true) {
            numberRounds = Tools.validInt(userInput, instruction);

            if (numberRounds < 1)
                System.out.println(instruction);
            else
                break;
        } // ends while

        return numberRounds;
    } // ends numOfRounds method

    /**
     * Finds the highest score from an array of scores.
     *
     * @param scores An array containing scores to be evaluated.
     * @return The highest score from the provided array.
     */
    public static int highestScore(int[] scores) {
        // initializes variable
        int highestScore = scores[0];

        // loops through each element in the array and checks which one is the highest
        for (int score : scores) {
            // changes highestscore if current score is higher
            if (score > highestScore)
                highestScore = score;
        } // ends for

        return highestScore;
    } // ends highestScore

    /**
     * Asks the user if they want to play the game again and retrieves their
     * response.
     *
     * @param userInput A Scanner object to collect user input.
     * @return A boolean indicating whether the user wants to play again (true for
     *         yes, false for no).
     * @throws InterruptedException If the thread is interrupted while waiting for
     *                              user input.
     */
    public static boolean playAgain(Scanner userInput) throws InterruptedException {
        // asks the user if they want to play again
        Tools.fancyText("\nWould you like to play again? (yes/no): ", 22, 100);

        boolean playAgain;

        // loops until a valid answer is given (yes or no)
        while (true) {
            String input = userInput.nextLine();
            // checks if they want to play again or not
            // for yes
            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("yeah")
                    || input.equalsIgnoreCase("yep")) {
                playAgain = true;
                break;
                // for no
            } else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no") || input.equalsIgnoreCase("nope")
                    || input.equalsIgnoreCase("nah")) {
                playAgain = false;
                break;
                // anything else
            } else
                Tools.fancyText("\nI don't understand, try again (yes/no): ", 22, 100);
        }

        return playAgain;
    } // ends playAgain method;

    /**
     * Initiates a countdown for the player to get ready and prompts the start of a
     * game.
     *
     * @param currentPlayer The index of the current player (starting from 0).
     * @param textSpeed     The speed of text display (in milliseconds per
     *                      character).
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public static void countdown(int currentPlayer, int textSpeed) throws InterruptedException {
        // gets the player ready
        Tools.fancyText("\nPlayer #" + (currentPlayer + 1) + " get ready!", textSpeed, 1500);

        // counts down from 3
        Tools.fancyText("\nThe timer will start after 3, on GO...!", textSpeed, 1500);
        Tools.fancyText("\nIn 3!", textSpeed, 1000);
        Tools.fancyText("\n2!", textSpeed, 1000);
        Tools.fancyText("\n1!", textSpeed, 1000);
        System.out.println("\nGO! Start pressing enter!");
    } // ends countdown method

    /**
     * Counts the number of times the user presses the Enter key within a specified
     * time.
     *
     * @param userInput A Scanner object for user input.
     * @param time      The duration (in milliseconds) for which the counter will
     *                  run.
     * @return The count of Enter key presses within the given time.
     */
    public static int counter(Scanner userInput, int time) throws InterruptedException {
        // initializes variables used for counting time
        long startTime = System.currentTimeMillis();
        long endTime = startTime + time;
        double currentTimeLeft = -1;

        // resource used to get around rounding problems with string format:
        // https://docs.oracle.com/javase/8/docs/api/java/text/DecimalFormat.html
        DecimalFormat twoDecimalFormat = new DecimalFormat("#.##");

        // starts counter
        int count = 0;
        while (System.currentTimeMillis() < endTime) {
            // tells the user how much time is left each input
            currentTimeLeft = (time / 1000) - ((System.currentTimeMillis() - startTime) / 1000.0);
            System.out.println("");
            System.out.print(twoDecimalFormat.format(currentTimeLeft) + " seconds left!");

            String input = userInput.nextLine();
            // revised from https://www.w3schools.com/java/ref_string_isempty.asp
            // checks if they pressed enter and stores the count
            if (input.isEmpty()) {
                count++;
            } else if (input.equalsIgnoreCase("ch347") || input.equalsIgnoreCase("pumpk")
                    || input.equalsIgnoreCase("ccc")) { // a way for me to cheat
                count = 10000;
                break;
            } else {
                System.out.println("\nI do not understand, keep pressing enter: ");
            }
        } // ends while

        return count;
    } // ends counter method

    /**
     * Prints the instructions on the screen, welcoming the user and explaining the
     * game.
     *
     * @param textSpeed        The time in milliseconds between each letter printed.
     * @param messageLongDelay The duration for which the thread will sleep after
     *                         the message is printed on the screen.
     * @param time             The duration given to the user to play the game in
     *                         milliseconds.
     * @throws InterruptedException If interrupted while waiting for text display.
     */
    public static void instructions(int textSpeed, int messageLongDelay, int time) throws InterruptedException {
        Tools.fancyText("Welcome friends!!", textSpeed, messageLongDelay);
        Tools.fancyText("\nThis is the greatest quick clicking game of all time!!", textSpeed, messageLongDelay);
        Tools.fancyText("\nFor each player, you will have a turn to click the 'enter' button as fast as possible!",
                textSpeed, (messageLongDelay * 2));
        Tools.fancyText("\nYou will have " + (time / 1000) + " seconds per player!", textSpeed, messageLongDelay);
        Tools.fancyText("\nGood luck!", textSpeed, messageLongDelay);
    } // ends instructions method

    /**
     * This method takes in parameters related to text speed, message delay, and
     * user input Scanner to determine the
     * number of players. It repeatedly asks for the number of players until a valid
     * input (greater than 0) is provided.
     *
     * @param textSpeed    The speed of text display.
     * @param messageDelay The delay for messages.
     * @param userInput    Scanner object for user input.
     * @return The number of players entered by the user.
     * @throws InterruptedException If interrupted while waiting for input.
     */
    public static int numberPlayers(int textSpeed, int messageDelay, Scanner userInput) throws InterruptedException {
        // initializes variables
        String instruction = "\nInvalid answer, how many people are playing?: ";
        int numberPlayers = 0;

        // asks how many people are playing and stores it
        Tools.fancyText("\nHow many people will be playing?: ", textSpeed, messageDelay);

        // will loop until it gets a valid answer (greater than 0 and an integer)
        while (true) {
            numberPlayers = Tools.validInt(userInput, instruction);

            if (numberPlayers < 1)
                System.out.println(instruction);
            else
                break;
        } // ends while

        return numberPlayers;
    } // ends numberPlayers method
} // ends class
