package array;

/**
 * Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.
 *
 * Answers within 10-5 of the actual value will be accepted as correct.
 *
 * Input: hour = 12, minutes = 30
 * Output: 165
 *
 * Input: hour = 3, minutes = 30
 * Output: 75
 *
 * Input: hour = 3, minutes = 15
 * Output: 7.5
 *
 */
public class AngleBetweenHourAndMinuteHandClock {

    /*
        contribution in degree by hour hand is 30 and min hand is 6.
        check contribution by (hour and min converted to hour hand) and min hand
        find difference between them. If greater than 180 find diff from 360.
     */
    public double angleClock(int hour, int minutes) {
        double hourAngle = (hour+((double)minutes/60))%12*30;
        double minAngle = minutes*6;
        double answer = Math.abs(hourAngle-minAngle);
        if(answer>180)
            answer = 360-answer;
        return answer;
    }
}
