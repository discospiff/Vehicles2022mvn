import java.util.*;
import java.util.stream.Collectors;

public class WordValidation {

    private static WordValidation wordValidation = null;

    private Set<String> badWords = new HashSet<>();

    private WordValidation() {
        badWords.add("Fudge");
        badWords.add("Fiddlesticks");
        badWords.add("Gosh");
        badWords.add("Darnit");
    }

    public static WordValidation getInstance() {
        if (wordValidation == null) {
            wordValidation = new WordValidation();
        }
        return wordValidation;
    }

    public String filterString (String input) {
        String[] wordsArray = input.split(" ");
        List<String> wordsList = Arrays.asList(wordsArray);
        LinkedList<String> wordsLinkedList = new LinkedList<>(wordsList);
        wordsLinkedList.removeAll(badWords);
        StringBuilder wordsBuilder = new StringBuilder();
        for(String word: wordsLinkedList) {
            wordsBuilder.append(word).append(" ");
        }
        String streamWords = wordsLinkedList.stream().collect(Collectors.joining(" "));
        return wordsBuilder.toString();
    }
}
