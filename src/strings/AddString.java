package meta;

public class AddString {

    public static void main(String[] args)
    {
        System.out.println(add("11", "123")); //134
        System.out.println(add("456", "77")); //533
        System.out.println(add("189", "89")); //278
        System.out.println(add("989", "89")); //1078
    }

    private static String add(String num1, String num2)
    {
        StringBuilder sb = new StringBuilder();
        int i = num1.length()-1;
        int j = num2.length()-1;
        int carry = 0;

        while (i>=0 || j>=0){
            int n1 = ( i >= 0 ? Character.getNumericValue(num1.charAt(i)) : 0 );
            int n2 = ( j >= 0 ? Character.getNumericValue(num2.charAt(j)) : 0 );

            int sum = n1 + n2 + carry;

            sb.append(sum % 10);
            carry = sum / 10;

            i--;
            j--;
        }

        if(carry > 0){
            sb.append(carry);
        }

        return sb.reverse().toString();
    }
}
