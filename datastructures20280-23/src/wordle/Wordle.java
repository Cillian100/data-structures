package wordle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import project20280.hashtable.ChainHashMap;


public class Wordle {
    //String fileName = "wordle/resources/dictionary.txt";
    String fileName = "wordle/resources/extended-dictionary.txt";
    List<String> dictionary = null;
    final int num_guesses = 50;
    final long seed = 42;
    //Random rand = new Random(seed);
    Random rand = new Random();

    static final String winMessage = "CONGRATULATIONS! YOU WON! :)";
    static final String lostMessage = "YOU LOST :( THE WORD CHOSEN BY THE GAME IS: ";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_GREY_BACKGROUND = "\u001B[100m";

    Wordle() {
        this.dictionary = readDictionary(fileName);
        System.out.println("dict length: " + this.dictionary.size());
        System.out.println("dict: " + dictionary);
    }

    public static void main(String[] args) {
        Wordle game = new Wordle();
        String target = game.getRandomTargetWord();
        game.play(target);
    }

    public void play(String target) {
        target="staff";
        Collections.sort(dictionary);
        int counter=0;
        for(int i = 0; i < num_guesses; ++i){
            counter++;
            solver(dictionary);
            //System.out.println("dictionary size: " + dictionary.size());
            String guess = getGuess();

            if(target.equals(guess)) { // you won!
                System.out.println("and it only took you guesses- " + counter);
                win(target);
                return;
            }

            char[] hint = {'_', '_', '_', '_', '_'};
            HashMap<Character, Integer> targetMap = new HashMap<Character, Integer>(); 
            HashMap<Character, Integer> guessMap = new HashMap<Character, Integer>(); 
            HashMap<Character, Integer> overLap = new HashMap<Character, Integer>();

            for(int a=0;a<5;a++){
                if(!targetMap.containsKey(target.charAt(a))){
                    targetMap.put(target.charAt(a), 1);
                }else{
                    targetMap.put(target.charAt(a), targetMap.get(target.charAt(a))+1);
                }
            }
            System.out.println(targetMap);
            for(int a=0;a<5;a++){
                if(!guessMap.containsKey(guess.charAt(a))){
                    guessMap.put(guess.charAt(a), 1);
                }else{
                    guessMap.put(guess.charAt(a), guessMap.get(guess.charAt(a))+1);
                }
            }
            System.out.println(guessMap);
            
            for(int a=0;a<5;a++){
                boolean badCode=true;
                 if(guess.charAt(a)==target.charAt(a)){
                    if(!overLap.containsKey(guess.charAt(a))){
                        overLap.put(guess.charAt(a), 1);
                     }else{
                        if(overLap.get(guess.charAt(a)) + 1 > targetMap.get(guess.charAt(a))){
                            for(int b=a-1;b>=0;b--){
                                if(guess.charAt(a) == guess.charAt(b)){
                                    hint[b]='_';
                                }
                            }
                        }
                        overLap.put(guess.charAt(a), overLap.get(guess.charAt(a)) + 1);
                    }
                     hint[a]='+';
                 }else if(target.indexOf(guess.charAt(a)) > - 1){
                     if(!overLap.containsKey(guess.charAt(a))){
                        overLap.put(guess.charAt(a), 1);
                     }else{
                        if(overLap.get(guess.charAt(a)) + 1 > targetMap.get(guess.charAt(a))){
                            badCode=false;
                        }
                        overLap.put(guess.charAt(a), overLap.get(guess.charAt(a)) + 1);
                     }
                     if(badCode==true){
                        hint[a]='o';
                     }else{
                        hint[a]='_';
                     }
                 }else{
                     hint[a]='_';
                 }
             }

            System.out.println("hint: " + Arrays.toString(hint));

            dictionary = remover(guess, hint, dictionary);
        }

        lost(target);
    }

    public void lost(String target) {
        System.out.println();
        System.out.println(lostMessage + target.toUpperCase() + ".");
        System.out.println();

    }
    public void win(String target) {
        System.out.println(ANSI_GREEN_BACKGROUND + target.toUpperCase() + ANSI_RESET);
        System.out.println();
        System.out.println(winMessage);
        System.out.println();
    }

    public boolean containsValue(String string, char character){
        for(int a=0;a<string.length();a++){
            if(string.charAt(a)==character){
                return true;
            }
        }
        return false;
    }

    public List<String> remover(String guess, char[] result, List<String> dic){
        List<String> returnString = new ArrayList<String>();
        boolean setorNot=false;
        boolean containsPlus=false;
        boolean containsO=false;
        for(int a=0;a<5;a++){
            if(result[a]=='+'){
                containsPlus=true;
            }
            if(result[a]=='o'){
                containsO=true;
            }
        }

        for(String i : dic){
            setorNot=true;
                for(int a=0;a<5;a++){
                    if(result[a]=='_' && guess.charAt(a)==i.charAt(a)){
                        setorNot=false;
                    }
                    if(containsPlus==true && result[a]=='+' && guess.charAt(a)!=i.charAt(a)){
                        setorNot=false;
                    }
                    if(containsO==true && result[a]=='o' && !containsValue(i, guess.charAt(a))){
                         setorNot=false; 
                    }
                }
                if(setorNot){
                    returnString.add(i);
                }
        }
        return returnString;
    }

    public void solver(List<String> dic){
        ChainHashMap<Character, Integer> frequency = new ChainHashMap<Character, Integer>();
        ChainHashMap<Integer, String> mostCommon = new ChainHashMap<Integer, String>();

        for(String i : dic){
            for(int a=0;a<i.length();a++){
                if(!frequency.containsKey(i.charAt(a))){
                    frequency.put(i.charAt(a), 1);
                }else{
                    frequency.put(i.charAt(a), frequency.get(i.charAt(a))+1);
                }
            }
        }
        int counter;

        for(String i : dic){
            counter=0;
            for(int a=0;a<i.length();a++){
                counter=counter+frequency.get(i.charAt(a));
            }
            mostCommon.put(counter, i);
        }

        System.out.println("solver: " + mostCommon.last());
    }

    public String getGuess() {
        Scanner myScanner = new Scanner(System.in, StandardCharsets.UTF_8.displayName());  // Create a Scanner object
        System.out.println("Guess:");

        String userWord = myScanner.nextLine();  // Read user input
        userWord = userWord.toLowerCase(); // covert to lowercase

        // check the length of the word and if it exists
        while ((userWord.length() != 5) || !(dictionary.contains(userWord))) {
            if ((userWord.length() != 5)) {
                System.out.println("The word " + userWord + " does not have 5 letters.");
            } else {
                System.out.println("The word " + userWord + " is not in the word list.");
            }
            // Ask for a new word
            System.out.println("Please enter a new 5-letter word.");
            userWord = myScanner.nextLine();
        }
        return userWord;
    }

    public String getRandomTargetWord() {
        // generate random values from 0 to dictionary size
        return dictionary.get(rand.nextInt(dictionary.size()));
    }
    public List<String> readDictionary(String fileName) {
        List<String> wordList = new ArrayList<>();

        try {
            // Open and read the dictionary file
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
            assert in != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String strLine;

            //Read file line By line
            while ((strLine = reader.readLine()) != null) {
                wordList.add(strLine.toLowerCase());
            }
            //Close the input stream
            in.close();

        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return wordList;
    }
}