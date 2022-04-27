package string;

import java.util.PriorityQueue;
/**
 * You are given an array of strings nums and an integer k. Each string in nums represents an integer without leading zeros.
 *
 * Return the string that represents the kth largest integer in nums.
 *
 * Note: Duplicate numbers should be counted distinctly. For example, if nums is ["1","2","2"], "2" is the first largest integer, "2" is the second-largest integer, and "1" is the third-largest integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = ["3","6","7","10"], k = 4
 * Output: "3"
 * Explanation:
 * The numbers in nums sorted in non-decreasing order are ["3","6","7","10"].
 * The 4th largest integer in nums is "3".
 * Example 2:
 *
 * Input: nums = ["2","21","12","1"], k = 3
 * Output: "2"
 * Explanation:
 * The numbers in nums sorted in non-decreasing order are ["1","2","12","21"].
 * The 3rd largest integer in nums is "2".
 * Example 3:
 *
 * Input: nums = ["0","0"], k = 2
 * Output: "0"
 * Explanation:
 * The numbers in nums sorted in non-decreasing order are ["0","0"].
 * The 2nd largest integer in nums is "0".
 */
public class KthLargestNumber {
    public String kthLargestNumber(String[] nums, int k) {
        PriorityQueue<String> minHeap = new PriorityQueue<>((o1, o2) -> {
            if (o1.length() == o2.length()) { // If the same length then compare by their string
                return o1.compareTo(o2);
            }
            return Integer.compare(o1.length(), o2.length()); // Compare by their length
        });
        for (String num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // pop the minimum value in the heap
            }
        }
        return minHeap.poll();
    }

    public static void main(String[] args) {
        KthLargestNumber kthLargestNumber = new KthLargestNumber();
        String[] nums = {"683339452288515879","7846081062003424420","4805719838","4840666580043","83598933472122816064","522940572025909479","615832818268861533","65439878015","499305616484085","97704358112880133","23861207501102","919346676","60618091901581","5914766072","426842450882100996","914353682223943129","97","241413975523149135","8594929955620533","55257775478129","528","5110809","7930848872563942788","758","4","38272299275037314530","9567700","28449892665","2846386557790827231","53222591365177739","703029","3280920242869904137","87236929298425799136","3103886291279"};
        kthLargestNumber.kthLargestNumber(nums, 3);
    }
}
