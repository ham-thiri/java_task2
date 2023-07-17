import java.util.Scanner;

public class Hangman {
    private static final String[] WORDS = { "apple", "banana", "orange", "mango", "grape" };
    private static final int MAX_ATTEMPTS = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = getRandomWord();
        char[] guessedLetters = new char[word.length()];
        int attempts = 0;
        boolean gameOver = false;

        System.out.println("Welcome to Hangman!");

        while (!gameOver) {
            System.out.println();
            displayHangman(attempts);
            displayGuessedWord(guessedLetters);

            System.out.print("Enter a letter: ");
            char letter = scanner.next().toLowerCase().charAt(0);

            if (isLetterAlreadyGuessed(letter, guessedLetters)) {
                System.out.println("You already guessed that letter. Try again!");
                continue;
            }

            boolean foundLetter = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter) {
                    guessedLetters[i] = letter;
                    foundLetter = true;
                }
            }

            if (foundLetter) {
                System.out.println("Good guess!");
            } else {
                System.out.println("Oops! Wrong guess.");
                attempts++;
            }

            if (attempts >= MAX_ATTEMPTS || isWordGuessed(guessedLetters)) {
                gameOver = true;
            }
        }

        System.out.println();
        displayHangman(attempts);
        displayGuessedWord(guessedLetters);

        if (attempts >= MAX_ATTEMPTS) {
            System.out.println("Game over! You lost. The word was: " + word);
        } else {
            System.out.println("Congratulations! You won!");
        }

        scanner.close();
    }

    private static String getRandomWord() {
        int index = (int) (Math.random() * WORDS.length);
        return WORDS[index];
    }

    private static void displayHangman(int attempts) {
        String[] hangman = {
                " ________",
                "|      |",
                "|      " + (attempts >= 1 ? "O" : ""),
                "|     " + (attempts >= 3 ? "/" : "") + (attempts >= 2 ? "|" : "") + (attempts >= 4 ? "\\" : ""),
                "|      " + (attempts >= 5 ? "|" : ""),
                "|     " + (attempts >= 6 ? "/" : "") + " " + (attempts >= 6 ? "\\" : ""),
                "|",
                "|___"
        };

        for (String line : hangman) {
            System.out.println(line);
        }
    }

    private static void displayGuessedWord(char[] guessedLetters) {
        System.out.print("Word: ");
        for (char letter : guessedLetters) {
            System.out.print(letter != '\0' ? letter : "_");
            System.out.print(" ");
        }
        System.out.println();
    }

    private static boolean isLetterAlreadyGuessed(char letter, char[] guessedLetters) {
        for (char guessedLetter : guessedLetters) {
            if (guessedLetter == letter) {
                return true;
            }
        }
        return false;
    }

    private static boolean isWordGuessed(char[] guessedLetters) {
        for (char letter : guessedLetters) {
            if (letter == '\0') {
                return false;
            }
        }
        return true;
    }
}
