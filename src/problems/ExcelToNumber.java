package problems;

public class ExcelToNumber {
    /**
     * Given a string columnTitle that represents the column title as appear in an Excel sheet,
     * return its corresponding column number.
     * @param columnTitle
     * @return
     */
    public int titleToNumber(String columnTitle) {
        int result = 0;
        for( char c : columnTitle.toCharArray()) {
            result*=26;
            result+=c-'A'+1;
        }

        return result;
    }


    /**
     * Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
     * @param no
     * @return
     */
    public String convertToTitle(int no) {
        StringBuilder output = new StringBuilder();

        while(no>0) {
            no--;
            output.insert(0,(char)(no%26+'A'));
            no = no/26;
        }

        return output.toString();
    }
}
