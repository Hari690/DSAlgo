package array;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

interface Master {
    int guess(String word);
}

/**
 * You are given an array of unique strings words where words[i] is six letters long. One word of words was chosen as a secret word.
 * You are also given the helper object Master. You may call Master.guess(word) where word is a six-letter-long string, and it must be from words. Master.guess(word) returns:
 * -1 if word is not from words, or
 * an integer representing the number of exact matches (value and position) of your guess to the secret word.
 * There is a parameter allowedGuesses for each test case where allowedGuesses is the maximum number of times you can call Master.guess(word).
 * For each test case, you should call Master.guess with the secret word without exceeding the maximum number of allowed guesses. You will get:
 * "Either you took too many guesses, or you did not find the secret word." if you called Master.guess more than allowedGuesses times or if you did not call Master.guess with the secret word, or
 * "You guessed the secret word correctly." if you called Master.guess with the secret word with the number of calls to Master.guess less than or equal to allowedGuesses.
 * The test cases are generated such that you can guess the secret word with a reasonable strategy (other than using the bruteforce method).
 */
public class FindSecretWord {
        /*
         * I will try to explain this question in a better way than the author did. Here We are given with a wordlist, which contains a secret word. Ex:
         * wordlist = <abcmno, demfab, gmhipq, jkldef, limnpq, opqabc> and secret word is limnpq
         * Now there is a function guess(String guessWord) supplied by Leetcode backend which returns the number of matches with the secret word. And remember,
         * guessWord is always a word from the supplied wordList
         * Eg: guess(demfab) means demfab will be compared with secret word (limnpq). so compare(d,l)=0, compare(e,i)=0, compare(m,m)=1, compare(f,n)=0,
         * compare(a,p)=0, compare(b,q)=0. So comparing demfab(it could be any random string from the wordlist) with the secret word returns total match = 1
         * position that matched. Now if guess() is supplied with secret word itself, all characters will match(here 6).
         * So the idea is to eliminate some number of strings from word list after each loop until we find 100% match where we can guess the word. If we run the
         * loop less that 10 times and find the answer, we are good. If not the test fails. So we have to reduce the search space by eliminating some of the
         * words from wordlist.
         */
        public void findSecretWord(String[] wordlist, Master master) {
            Random random = new Random();
            //First we will have a list constructed from wordlist to keep the original list of words/ original search space given to us
            List<String> currentSearchSpace = new ArrayList<>();
            for(String s: wordlist)
                currentSearchSpace.add(s);
            //Now we have to try to guess the word with in 10 attempts. If not we quit
            int maxTrials = 10;
            int trial = 1;
            while(trial<=maxTrials){
                //First we try to randomly pick an index in the wordlist and check if that string is the correct word
                //This random number could be anything from 0 till size-1
                int currentPickIndex = random.nextInt(currentSearchSpace.size());
                String currentPickedWord = currentSearchSpace.get(currentPickIndex);
                //Check if the guessed word is the secret one by getting number of matches returned from guess()
                int numMatches = master.guess(currentPickedWord);
                //If we get 6 matches it is all done, as this is the secret word which we guessed
                if(numMatches==6) return;
                //If not, we create a new search space with all the words with which the currentPickedWord has same
                //number of matches numMatches. That means the new search space will include atleast the secret word
                //along with (may be) some extra words from the list. But definitely the search space would be smaller
                //than the original list provided.
                List<String> newSearchSpace = new ArrayList<>();
                for(String s: currentSearchSpace){
                    if(countMatches(currentPickedWord, s)==numMatches)
                        newSearchSpace.add(s);
                }
                //Now assign the newSearchSpace to the currentSearchSpace
                currentSearchSpace = new ArrayList(newSearchSpace);
                trial++;
            }
            //If we cannot guess the word in 10 attempts, this test fails.

        }

        public int countMatches(String word1, String word2){
            int count = 0;
            for(int i=0;i<6;i++){
                if(word1.charAt(i)==word2.charAt(i)) count++;
            }
            return count;
        }
}
