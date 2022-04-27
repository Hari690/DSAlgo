package array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class Posistion {
    int start;
    int end;
    Posistion(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

/**
 * Given an array that contains contiguous segments that are sorted, return a sorted array.
 * Example: [1,3,5,2,4,20,12]
 * has the following segements: [1,3,5],[2,4,20],[12] which are all sorted
 */
class MergeSegmentsOfSortedList {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,2,4,20,12,15,16};
        ArrayList<Integer> answer = new ArrayList<Integer>();
        ArrayList<Posistion> arraylist = new ArrayList<>();
        int start = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[i - 1]){
                continue;
            }else{
                Posistion pos = new Posistion(start,i - 1);
                arraylist.add(pos);
                start = i;
            }
        }
        arraylist.add(new Posistion(start,nums.length - 1));
        PriorityQueue<Posistion> apq = new PriorityQueue<>(Comparator.comparingInt(s -> nums[s.start]));
        for(Posistion posistion : arraylist){
            apq.add(posistion);
        }

        while(!apq.isEmpty()){
            Posistion posistion = apq.poll();
            answer.add(nums[posistion.start]);
            if(posistion.start < posistion.end){
                posistion.start += 1;
                apq.add(posistion);
            }
        }
        System.out.println(answer);
    }
}
