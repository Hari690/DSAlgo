package string;

public class StringToInteger {
    public static void convert(String s)
    {

        // Initialize a variable
        int num = 0;
        int n = s.length();

        // Iterate till length of the string
        for(int i = 0; i < n; i++)

            // Subtract 48 from the current digit
            num = num * 10 + ((int)s.charAt(i) - 48);

        // Print the answer
        System.out.print(num);
    }
}
