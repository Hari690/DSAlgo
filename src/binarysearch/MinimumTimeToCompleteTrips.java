package binarysearch;

public class MinimumTimeToCompleteTrips {
    public long minimumTime(int[] time, int totalTrips) {
        long min=Long.MAX_VALUE;
        for(int t:time){
            min=Math.min(min,t);
        }
        long low=min;
        long high=totalTrips*min;
        while(low<=high){
            long mid=low+(high-low)/2;
            if(canComplete(mid,totalTrips,time)){
                high=mid-1;
            } else {
                low=mid+1;
            }
        }
        return low;
    }
    public boolean canComplete(long isvalidtime, int totalTrips, int[] time){
        long trips=0;
        for(int t:time){
            trips+=isvalidtime/t;
        }
        if(trips>=totalTrips)
            return true;
        return false;
    }
}
