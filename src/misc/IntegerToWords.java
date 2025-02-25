package meta;

public class IntegerToWords {

    /*
    Break number into parts (Billion, Million, Thousand, and below 1000).
    Recursive processing: Convert each chunk of 3 digits into words.
    Handle edge cases: Zero, negative numbers, large values.
     */
    private static final String[] BELOW_TWENTY = {
            "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
            "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] TENS = {
            "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private static final String[] THOUSANDS = { "", "Thousand", "Million", "Billion" };

    public static void main(String[] args) {
        System.out.println(numberToWords(12345)); // Output: "Twelve Thousand Three Hundred Forty Five"
        System.out.println(numberToWords(1000000)); // Output: "One Million"
        System.out.println(numberToWords(0)); // Output: "Zero"
    }

    public static String numberToWords(int num) {
        if (num == 0) return "Zero";

        String result = "";
        int i = 0;

        while (num > 0) {
            if (num % 1000 != 0) { // Process only non-zero groups
                result = helper(num % 1000) + THOUSANDS[i] + " " + result;
            }
            num /= 1000;
            i++;
        }

        return result.trim();
    }

    private static String helper(int num) {
        if (num == 0) return "";
        else if (num < 20) return BELOW_TWENTY[num] + " ";
        else if (num < 100) return TENS[num / 10] + " " + helper(num % 10);
        else return BELOW_TWENTY[num / 100] + " Hundred " + helper(num % 100);
    }
}
