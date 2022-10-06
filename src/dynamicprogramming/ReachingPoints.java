package dynamicprogramming;

/**
 * Given four integers sx, sy, tx, and ty, return true if it is possible to convert the point (sx, sy) to the point (tx, ty) through some operations, or false otherwise.
 * The allowed operation on some point (x, y) is to convert it to either (x, x + y) or (x + y, y).
 * Example 1:
 * Input: sx = 1, sy = 1, tx = 3, ty = 5
 * Output: true
 * Explanation:
 * One series of moves that transforms the starting point to the target is:
 * (1, 1) -> (1, 2)
 * (1, 2) -> (3, 2)
 * (3, 2) -> (3, 5)
 *
 * Example 2:
 * Input: sx = 1, sy = 1, tx = 2, ty = 2
 * Output: false
 *
 * Example 3:
 * Input: sx = 1, sy = 1, tx = 1, ty = 1
 * Output: true
 */
public class ReachingPoints {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
//        if (sx == tx && sy == ty)
//            return true;
//
//        if (sx > tx || sy > ty)
//            return false;
//
//        return (reachingPoints(sx + sy, sy, tx, ty) || reachingPoints(sx, sx + sy, tx, ty));
        // move target point as close as possible to start
        while (tx > sx && ty > sy) {
            if (tx > ty) {
                tx %= ty;
            } else {
                ty %= tx;
            }
        }
        // after previous simplification, target point could fit one or both start point coordinates
        // or it could overlap start point, so become not reachable
        if (sx > tx || sy > ty) return false;
        // if one coordinate is equal (x or y) -
        // check if we could reach target by incrementing other coordinate
        // in this case - doing modulus by first coordinate
        if (sx == tx) return (ty - sy) % sx == 0;
        if (sy == ty) return (tx - sx) % sy == 0;
        // this is not reachable at all. but still need to be returned
        return sx == tx && sy == ty;
    }
}
