package array;

/**
 * A web developer needs to know how to design a web page's size. So, given a specific rectangular web page’s area, your job by now is to
 * design a rectangular web page, whose length L and width W satisfy the following requirements:
 *
 * The area of the rectangular web page you designed must equal to the given target area.
 * The width W should not be larger than the length L, which means L >= W.
 * The difference between length L and width W should be as small as possible.
 * Return an array [L, W] where L and W are the length and width of the web page you designed in sequence.
 */
public class ConstructRectangle {
    public int[] constructRectangle(int area) {
        int root = (int)Math.sqrt(area);
        int[] output = new int[2];
        while(root>=1) {
            if(area%root==0) {
                output[0]=area/root;
                output[1]=root;
                return output;
            }
            root--;
        }
        return output;
    }
}
