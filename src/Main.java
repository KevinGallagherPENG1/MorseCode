import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;



public class Main {

    public static final String FILE_NAME = "MorseCode.txt";

    public static void main(String[] args) {
        BinaryTree<String> morseCode = new BinaryTree<>();


        try {
            Scanner file = new Scanner(new File(FILE_NAME));
            morseCode = BinaryTree.readBinaryTree(file);
        } catch (FileNotFoundException e) {
            System.out.println("Your file didnt work :(");
        }

        //System.out.println(morseCode);

        String sos = "... --- ...";
        System.out.println("Original morse code: " + sos);
        String result = BinaryTree.decode(sos, morseCode);
        System.out.println("What does it say? " + getString(result));


        String theQuickBrownFoxJumpedOverTheLazyDog = "the quick brown fox jumped over the lazy dog";
        String[] parts = theQuickBrownFoxJumpedOverTheLazyDog.split(" ");
        StringBuilder morseResult = new StringBuilder();

        for (String string: parts) {
            System.out.print(string + " Morse code: ");
            morseResult.append(BinaryTree.encode(string, morseCode));
            System.out.println(morseResult);
        }

        System.out.println(getString(BinaryTree.decode(morseResult.toString(), morseCode)));





    }

    /**
     * This replaces all non alphabetical characters with "", shearing the numbers off of the string input
     * @param string The input to remove any non alphabetical characters
     * @return The string with only alphabetical characters
     */
    public static String getString(String string){
        if(string == null)
            return "";
        return string.replaceAll("[^a-zA-Z]", "");
    }
}