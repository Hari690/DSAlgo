package array;

/**
 * You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
 *
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return if n new
 * flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.
 */
public class FlowerPlacement {

    /*
        When 3 consecutive 0s come mark the 0 in between to 1 and decrement n, consider first and last as 0s also.
     */
    public boolean canPlaceFlowersExtraSpace(int[] flowerbed, int n) {
        int[] newFlowerbed = new int[flowerbed.length+2];

        for(int i=1;i<newFlowerbed.length-1;i++)
            newFlowerbed[i]=flowerbed[i-1];

        for(int i=1;i<newFlowerbed.length-1;i++) {
            if(newFlowerbed[i-1]==0 && newFlowerbed[i]==0 && newFlowerbed[i+1]==0) {
                n--;
                newFlowerbed[i]=1;
                if(n==0)
                    return true;
            }
        }

        return (n<=0)?true:false;
    }
    /*
        base condition for leftmost and rightmost.
     */
    public boolean canPlaceFlowers(int[] f, int n) {
        for(int i=0;i<f.length && n>0;i++) {
            int left = (i==0)?0:f[i-1];
            int right = (i==f.length-1)?0:f[i+1];

            if(f[i]==0 && left==0 && right==0) {
                i+=1;
                n--;
            }
        }
        if(n==0)
            return true;

        return false;
    }

    public static void main(String[] args) {
        FlowerPlacement flowerPlacement = new FlowerPlacement();

        flowerPlacement.canPlaceFlowersExtraSpace(new int[]{1,0,0,0,1},2);
    }
}
