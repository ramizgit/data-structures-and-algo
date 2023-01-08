package strings;

public class IntegerAddition {
    public static void main(String[] args) //todo:practice
    {
        System.out.println(addPositiveIntegers("99345", "999")); //100344
    }

    public static String addPositiveIntegers(String num1, String num2)
    {
        String str1 = num1.length() > num2.length() ? num1 : num2;
        String str2 = num1.length() > num2.length() ? num2 : num1;
        int diff = Math.abs(num1.length() - num2.length());

        StringBuilder sum = new StringBuilder();
        int carry = 0;

        for(int i=str2.length()-1; i>=0; i--)
        {
            int tmpsum = (str2.charAt(i)-'0') + (str1.charAt(i+diff)-'0') + carry;
            sum.append((char)(tmpsum%10 + '0'));
            carry = tmpsum/10;
        }

        for(int i=diff-1; i>=0;i--)
        {
            int tmpsum = (str1.charAt(i)-'0') + carry;
            sum.append((char)(tmpsum%10 + '0'));
            carry = tmpsum/10;
        }
        if(carry != 0)
        {
            sum.append((char)(carry + '0'));
        }

        return sum.reverse().toString();
    }
}
