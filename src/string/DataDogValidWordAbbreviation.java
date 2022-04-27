package string;

/**
 * Given anon-emptystringsand an abbreviationabbr, return whether the string matches with the given abbreviation.
 * A string such as"word"contains only the following valid abbreviations:
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * Notice that only the above abbreviations are valid abbreviations of the string"word". Any other string is not a valid abbreviation of"word".
 * Note:
 * Assumescontains only lowercase letters andabbrcontains only lowercase letters and digits.
 */
public class DataDogValidWordAbbreviation {
    public boolean validWordAbbreviation(String abbr, String word) {
        if (word == null || abbr == null) {
            return false;
        }
        int i = 0, j = 0;
        int wordLength = word.length();
        int abbrLength = abbr.length();
        char[] s = word.toCharArray();
        char[] t = abbr.toCharArray();

        while (i < wordLength && j < abbrLength) {
            if (Character.isDigit(t[j])) {
                if (t[j] == '0') {
                    return false;
                }
                int val = 0;
                while (j < abbrLength && Character.isDigit(t[j])) {
                    val = val * 10 + (t[j] - '0');
                    j++;
                }
                i += val;
            } else {
                if (s[i] != t[j]) {
                    return false;
                }
                i++;
                j++;
            }
        }
        return i == wordLength && j == abbrLength;
    }

    public static void main(String[] args) {
        DataDogValidWordAbbreviation solution = new DataDogValidWordAbbreviation();
        System.out.println(solution.validWordAbbreviation("d3dog","datadog"));
        System.out.println(solution.validWordAbbreviation("d3dog","datadogs"));
        System.out.println(solution.validWordAbbreviation("4","aaaa"));
        System.out.println(solution.validWordAbbreviation("a3","abc"));
        System.out.println(solution.validWordAbbreviation("a3","abcde"));
        System.out.println(solution.validWordAbbreviation("4dog","datadog"));
        System.out.println(solution.validWordAbbreviation("data3","datadog"));
        System.out.println(solution.validWordAbbreviation("4d2","datadog"));
        System.out.println(solution.validWordAbbreviation("a11y","accessibility"));
        System.out.println(solution.validWordAbbreviation("13","accessibility"));
        System.out.println(solution.validWordAbbreviation("11ty","accessibility"));
        System.out.println(solution.validWordAbbreviation("ac11","accessibility"));
    }
}
