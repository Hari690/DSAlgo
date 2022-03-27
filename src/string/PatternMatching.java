package string;

/**
You're tasked with writing a function which determines if a word matches a pattern. Ex. given the pattern "d3dog" and the word "datadog" your function should return True. However, given the same pattern and the word "datadogs", your function should return False.
 */

public class PatternMatching {
    public static void main(String[] args) {
        PatternMatching solution = new PatternMatching();
        System.out.println(solution.compare("d3dog","datadog"));
        System.out.println(solution.compare("d3dog","datadogs"));
        System.out.println(solution.compare("4","aaaa"));
        System.out.println(solution.compare("a3","abc"));
        System.out.println(solution.compare("a3","abcde"));
        System.out.println(solution.compare("4dog","datadog"));
        System.out.println(solution.compare("data3","datadog"));
        System.out.println(solution.compare("4d2","datadog"));
        System.out.println(solution.compare("a11y","accessibility"));
        System.out.println(solution.compare("13","accessibility"));
        System.out.println(solution.compare("11ty","accessibility"));
        System.out.println(solution.compare("ac11","accessibility"));
    }

    public boolean compare(String pattern,String s) {
        int j=0;
        StringBuilder nos = new StringBuilder();
        for(int i=0;i<pattern.length();i++) {
            char patternChar = pattern.charAt(i);
            char inputChar = s.charAt(j);
            if(Character.isDigit(patternChar)) {
                nos.append(patternChar);
            } else {
                if(nos.length()>0) {
                    int no = Integer.parseInt(nos.toString());
                    if(j+no<=s.length()) {
                        j+=no;
                    } else {
                        return false;
                    }
                    if(j==s.length())
                        return true;

                    inputChar = s.charAt(j);
                    nos = new StringBuilder();
                }
                if(patternChar!=inputChar) {
                    return false;
                }
                j++;
            }
        }
        if(nos.length()>0) {
            int no = Integer.parseInt(nos.toString());
            if(j+no<=s.length()) {
                j+=no;
            } else {
                return false;
            }
        }
        return (j==s.length());
    }
}
