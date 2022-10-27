import javax.swing.*;

public class Palendrome {

    public static void main(String[] args) {
        promptUser();
    }

    private static void promptUser() {
          String phrase = JOptionPane.showInputDialog("Enter a Phrase to see if it's a Palendrome");
          phrase.replaceAll(" ", "");
          boolean palendrome = isPalendrome(phrase.toLowerCase());


    }

    private static boolean isPalendrome(String phrase) {
        // handle the base case.
        if (phrase.length() <= 1) {
            // we have a palendrome!
            return true;
        }

        // handle recursive case.
        String firstCharacter = phrase.substring(0, 1);
        String lastCharacter = phrase.substring(phrase.length() - 1);
        if (firstCharacter.equals(lastCharacter)) {
            // recurse
            String shortenedPhrase = phrase.substring(1, phrase.length()-1);
            return isPalendrome(shortenedPhrase);
        } else {
            // we're finished
            return false;
        }
    }
}
