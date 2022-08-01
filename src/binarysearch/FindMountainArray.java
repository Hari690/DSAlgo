package binarysearch;

/**
 * (This problem is an interactive problem.)
 * You may recall that an array arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such an index does not exist, return -1.
 *
 * You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
 *
 * MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * MountainArray.length() returns the length of the array.
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.
 * Also, any solutions that attempt to circumvent the judge will result in disqualification.
 * Example 1:
 *
 * Input: array = [1,2,3,4,5,3,1], target = 3
 * Output: 2
 * Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
 * Example 2:
 *
 * Input: array = [0,1,2,4,2,1], target = 3
 * Output: -1
 * Explanation: 3 does not exist in the array, so we return -1.
 */
public class FindMountainArray {

    public int findInMountainArray(int target, MountainArray mountainArr) {
        // find the summit of mountain array.
        int left = 0;
        int right = mountainArr.length()-1;

        while(left<right) {
            int mid = left + (right-left)/2;
            if(mountainArr.get(mid)>mountainArr.get(mid-1)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // check left side or right side.

        //left
        int temp = left;
        left = 0;
        right = temp;
        while(left<=right) {
            int mid = left + (right-left)/2;
            if (mountainArr.get(mid)==target) {
                return mid;
            } else if (mountainArr.get(mid)<target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        //right
        left = temp;
        right = mountainArr.length()-1;
        while(left<=right) {
            int mid = left + (right-left)/2;
            if (mountainArr.get(mid)==target) {
                return mid;
            } else if (mountainArr.get(mid)>target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        FindMountainArray findMountainArray = new FindMountainArray();
        int arr[] = {3,5,3,2,0};
        findMountainArray.findInMountainArray(3, new MountainArray(arr));
    }
}

class MountainArray {
    int[] arr;
    MountainArray(int[] arr) {
        this.arr = arr;
    }

    int get(int index) {
        return arr[index];
    }

    int length(){
        return arr.length;
    }
}
