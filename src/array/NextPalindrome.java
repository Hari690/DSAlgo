package array;

/**
 * A palindrome is a number that reads the same backward as forward.
 * Example 1:
 * Input: num = “1221”
 * Output: “2112”
 *
 * Explanation: The next palindrome larger than “1221” is “2112”.
 *
 * Example 2:
 * Input: num = “32123”
 * Output: “”
 */
public class NextPalindrome {

    /*
    If num has an even length, then all characters occur even numbers of times. If num has an odd length,
    then only the middle character occurs an odd number of times, and the middle character
    must remain at the middle to make the string a palindrome. Therefore, only consider the first half of num.
    Get the next permutation of the first half of num, and generate the second half of the next permutation of the first half to obtain the complete string of next palindrome.
     */
    public String nextPalindrome(String num) {
        char[] arr = num.toCharArray();
        int length = arr.length;
        boolean flag = nextPermutation(arr, length / 2);
        if (!flag)
            return "";
        StringBuffer sb = new StringBuffer(new String(arr, 0, (length + 1) / 2));
        for (int i = length / 2 - 1; i >= 0; i--)
            sb.append(sb.charAt(i));
        return sb.toString();
    }

    private boolean nextPermutation(char[] arr, int endIndex) {
        int changeIndex = -1;
        for (int i = endIndex - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                changeIndex = i;
                break;
            }
        }
        if (changeIndex < 0)
            return false;
        int nextIndex = -1;
        for (int i = endIndex - 1; i > changeIndex; i--) {
            if (arr[i] > arr[changeIndex]) {
                nextIndex = i;
                break;
            }
        }
        char temp = arr[changeIndex];
        arr[changeIndex] = arr[nextIndex];
        arr[nextIndex] = temp;
        reverse(arr, changeIndex + 1, endIndex - 1);
        return true;
    }

    private void reverse(char[] arr, int start, int end) {
        int low = start, high = end;
        while (low < high) {
            char temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low++;
            high--;
        }
    }
}
