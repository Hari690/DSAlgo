package general;

// 102 -> "One hundred and two"
// 13008 -> "Thirteen thousand and eight"
// 123123123 -> "One hundred and twenty three million, one hundred and twenty three thousand, one hundred and twenty three"





/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Noon {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());

        for (String string : strings) {
            System.out.println(string);
        }
    }

    public String numToString(int num) {
        StringBuilder number = new StringBuilder();
        Map<Integer,String> map = createMapping();
        int place = 1;

        while(num!=0) {
            int result = num % 1000;
            String output = getRepresentation(result, place, map);
            num = num/1000;
            number.append(output);
            place++;
        }

        return number.toString();
    }

    public String getRepresentation(int number, int place, Map<Integer,String> map) {
        StringBuilder num = new StringBuilder();

        if( number<= 20) {
            return map.get(number);
        }

        int ones = number%10;
        int tens = (number%100)/10;
        int hundreds = number/100;

        if( hundreds > 0) {
            num.append(map.get(hundreds)).append(map.get(100)).append(map.get(tens)).append(map.get(ones));
        } else if ( tens>0 ) {
            num.append(map.get(tens)).append(map.get(ones));
        } else {
            num.append(map.get(ones));
        }

        return num.toString();
    }

    public Map<Integer,String> createMapping() {
        Map<Integer,String> map = new HashMap<>();

        map.put(0,"zero");
        map.put(1,"one");
        map.put(2,"two");
        map.put(3,"three");
        map.put(4,"four");
        map.put(5,"five");
        map.put(6,"six");
        map.put(7,"seven");
        map.put(8,"eight");
        map.put(9,"nine");
        map.put(10,"ten");
        map.put(11,"eleven");
        map.put(12,"twelve");
        map.put(13,"thirteen");
        map.put(14,"fourteen");
        map.put(15,"fifteen");
        map.put(16,"sixteen");
        map.put(17,"seventeen");
        map.put(18,"eighteen");
        map.put(19,"nineteen");
        map.put(20,"twenty");
        map.put(30,"thirty");
        map.put(40,"fourty");
        map.put(50,"fifty");
        map.put(60,"sixty");
        map.put(70,"seventy");
        map.put(80,"eighty");
        map.put(90,"ninety");
        map.put(100,"hundred");
        map.put(1000,"thousand");
        map.put(1000000, "million");

        return map;
    }

}
