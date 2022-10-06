package sort;

import java.util.Arrays;

/**
 * You are playing a game that contains multiple characters, and each of the characters has two main properties: attack and defense. You are given a 2D integer array properties where properties[i] = [attacki, defensei] represents the properties of the ith character in the game.
 * A character is said to be weak if any other character has both attack and defense levels strictly greater than this character's attack and defense levels. More formally, a character i is said to be weak if there exists another character j where attackj > attacki and defensej > defensei.
 * Return the number of weak characters.*
 *
 * Example 1:
 * Input: properties = [[5,5],[6,3],[3,6]]
 * Output: 0
 * Explanation: No character has strictly greater attack and defense than the other.
 * Example 2:
 * Input: properties = [[2,2],[3,3]]
 * Output: 1
 * Explanation: The first character is weak because the second character has a strictly greater attack and defense.
 * Example 3:
 * Input: properties = [[1,5],[10,4],[4,3]]
 * Output: 1
 * Explanation: The third character is weak because the second character has a strictly greater attack and defense.
 */
public class WeakCharactersInGame {
    public int numberOfWeakCharacters(int[][] properties) {

        /*
        Sort using attack in ascending order and defense in descending order.
         */
        Arrays.sort(properties,(a, b) -> {
            if(a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int max = properties[properties.length - 1][1];
        int count = 0;
        // If we find there exists a character whose defense value is lower on the left
        // we update count.
        for(int i = properties.length - 2; i >= 0; i--){
            // since for the same value of attack we sort such that defense is in descending this will trigger
            // only when we have smaller value of attack.
            if(properties[i][1] < max) {
                count++;
            }
            max = Math.max(properties[i][1], max);
        }

        return count;
    }

    public static void main(String[] args) {
        WeakCharactersInGame weakCharactersInGame = new WeakCharactersInGame();
        int[][] arr = {{1,1},{2,1},{2,2},{1,2}};
        weakCharactersInGame.numberOfWeakCharacters(arr);
    }
}
