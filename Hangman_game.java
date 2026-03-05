import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman_game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("HANGMAN GAME");

        String word = randomWord();
        int guessCount = 0;

        ArrayList<Character> wordChar = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            wordChar.add('_');
        }

        while (guessCount<=5) {
            System.out.print("word: ");

            for (char c : wordChar) {
                System.out.print(c + " ");
            }

            checker(wordChar);

            System.out.print("\n Enter your guess: ");
            char guess = sc.next().toLowerCase().charAt(0);

            if (word.indexOf(guess) >= 0) {
                System.out.println("CORRECT GUESS!!!");

                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess) {
                        wordChar.set(i, guess);
                    }
                }

            } else {
                System.out.println("WRONG GUESS");
                System.out.println(getHangman(guessCount));
                guessCount++;
            }
        }

        System.out.println("YOU LOSE!!!");
        sc.close();
    }

    static String getHangman(int wrongGuess){
        return switch (wrongGuess){
            case 0 -> """
                    o
                    
                    
                    """;
            case 1 -> """
                    o
                    |
                    
                    """;
            case 2 -> """
                    o
                   /|
                  
                   """;
            case 3 -> """
                    o
                   /|\\
                   
                   """;
            case 4 -> """
                    o
                   /|\\
                   /
                   """;
            case 5 -> """
                    o
                   /|\\
                   / \\
                   """;
            default -> "Invalid";
        };
    }

    static String randomWord(){
        Random rand= new Random();
        String filePath = "Words.txt";
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(filePath))){
            String line;

            while ((line = read.readLine()) != null){
                words.add(line.toLowerCase());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return String.valueOf(words.get(rand.nextInt(words.size())));
    }

    static void checker(ArrayList<Character> wordContain){
        if (!wordContain.contains('_')){
            System.out.println("\nYOU WIN!!!");
            System.exit(0);
        }
    }
}




