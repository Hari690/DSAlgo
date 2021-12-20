package array;

/**
 * On an infinite plane, a robot initially stands at (0, 0) and faces north. The robot can receive one of three instructions:
 *
 * "G": go straight 1 unit;
 * "L": turn 90 degrees to the left;
 * "R": turn 90 degrees to the right.
 * The robot performs the instructions given in order, and repeats them forever.
 *
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 */
public class RobotBoundedInCircle {
    public boolean isRobotBounded(String instructions) {
        int x=0,y=0,dX=0,dY=1;
        for(char c: instructions.toCharArray()) {
            if(c=='G') {
                y+=dY;
                x+=dX;
            }
            if(c=='L') {
                int temp = dX;
                dX=-1*(dY);
                dY=temp;
            }
            if(c=='R') {
                int temp = dY;
                dY=-1*(dX);
                dX=temp;
            }
            System.out.println(x+" "+y);
        }
        //System.out.println(dX+" "+dY);
        return (x==0 && y==0) || dX!=0 || dY!=1;
    }

    public static void main(String[] args) {
        RobotBoundedInCircle solution = new RobotBoundedInCircle();
        solution.isRobotBounded("GLGLGGLGL");
    }
}
