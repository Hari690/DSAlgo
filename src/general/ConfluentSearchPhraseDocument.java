package general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class Position<T,S> {
    T documentNbr;
    S position;
    Position(T val1, S val2) {
        documentNbr = val1;
        position = val2;
    }
    public T getDocumentNbr() {
        return documentNbr;
    }
    public S getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position<?, ?> position1 = (Position<?, ?>) o;
        return Objects.equals(documentNbr, position1.documentNbr) && Objects.equals(position, position1.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNbr, position);
    }
}

/**
 * You are given a list of documents with id and text.
 * Eg :-
 * DocId, Text
 * 1, "Cloud computing is the on-demand availability of computer system resources."
 * 2, "One integrated service for metrics uptime cloud monitoring dashboards and alerts reduces time spent navigating between systems."
 * 3, "Monitor entire cloud infrastructure, whether in the cloud computing is or in virtualized data centers."
 *
 * Search a given phrase in all the documents in a efficient manner. Assume that you have more than 1 million docs.
 * Eg :-
 * search("cloud") >> This should output [1,2,3]
 * search("cloud monitoring") >> This should output [2]
 * search("Cloud computing is") >> This should output [1,3]
 */
public class ConfluentSearchPhraseDocument {

    /*
        We can use inverted index with document position to search for.
        Worst case runtime O(n * m) lookup where n = number of words in phrase, m = number of occurrences of first phrase word in all documents

        Proposed a Trie with Hashmap approach.
     */
    public List<Integer> search(List<Position<Integer, String>> documents, String phrase) {
        if (phrase == null || phrase.length() == 0)
            return new ArrayList<>();

        // Build inverted index with position
        Map<String, Set<Position<Integer, Integer>>> wordPositionMap = new HashMap<>();
        for (Position<Integer, String> document : documents) {
            Integer docId = document.getDocumentNbr();
            String text = document.getPosition().replaceAll("[.,!]", "").toLowerCase();
            String[] words = text.split(" ");
            for (int j = 0; j < words.length; j++)
                wordPositionMap.computeIfAbsent(words[j], k -> new HashSet<>()).add(new Position<>(docId, j));
        }

        // Populate candidates from first word index lookup
        String[] phraseWords = phrase.toLowerCase().split(" ");
        Set<Position<Integer, Integer>> candidates = wordPositionMap.getOrDefault(phraseWords[0], new HashSet<>());

        for (int i = 1; i < phraseWords.length; i++) {
            Set<Position<Integer, Integer>> phraseWordIdx = wordPositionMap.getOrDefault(phraseWords[i], new HashSet<>());
            Set<Position<Integer, Integer>> newCandidates = new HashSet<>();
            for (Position<Integer, Integer> pair : candidates) {
                Position<Integer, Integer> nextWordIdx = new Position<>((pair.getDocumentNbr()), pair.getPosition() + 1);
                if (phraseWordIdx.contains(nextWordIdx)) {
                    newCandidates.add(nextWordIdx);
                }
            }
            candidates = newCandidates;
        }

        Set<Integer> matchingDocuments = new HashSet<>();
        for (Position<Integer, Integer> wordIdx: candidates) {
            matchingDocuments.add(wordIdx.getDocumentNbr());
        }

        return new ArrayList<>(matchingDocuments);
    }

    public static void main(String[] args) {
        ConfluentSearchPhraseDocument searchPhraseDocument = new ConfluentSearchPhraseDocument();
        Position<Integer,String> pair1 = new Position<>(1, "Cloud computing is the on-demand availability of computer system resources.");
        Position<Integer,String> pair2 = new Position<>(2, "One integrated service for metrics uptime cloud monitoring dashboards and alerts reduces time spent navigating between systems.");
        Position<Integer,String> pair3 = new Position<>(3, "Monitor entire cloud infrastructure, whether in the cloud computing is or in virtualized data centers.");
        List<Position<Integer,String>> list = List.of(pair1, pair2, pair3);
        System.out.println(searchPhraseDocument.search(list, "cloud"));
    }
}
