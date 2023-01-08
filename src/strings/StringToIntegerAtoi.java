package strings;

public class StringToIntegerAtoi {
    public static void main(String[] args)
    {

        System.out.println(getIntegerValue("123a4")); //123
        System.out.println(getIntegerValue("-12356a4")); //-12356
        System.out.println(getIntegerValue("b12356a4")); //0
        System.out.println(getIntegerValue("111111111111111111111111")); //Integer.MAX_VALUE
        System.out.println(getIntegerValue("-111111111111111111111111")); //Integer.MIN_VALUE

        System.out.println("int max = "+ Integer.MAX_VALUE);
        System.out.println("int min = "+ Integer.MIN_VALUE);
    }

    private static int getIntegerValue(String string)
    {
        int result = 0;
        int sign = 1;
        int i=0;

        String str = string.trim();

        if(str.charAt(i) == '+'){
            i++;
        }else if (str.charAt(i) == '-'){
            sign = -1;
            i++;
        }

        while(i<str.length()){
            int num = str.charAt(i) - '0';

            //handle boundary condition
            if( (result > Integer.MAX_VALUE / 10) || (result == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10 ) ){
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            if(num >= 0 && num < 9){
                result = result * 10 + num;
            }else {
                //handle chars other than 0 to 9, like a,b,c etc.
                break;
            }
            i++;
        }

        return result * sign;
    }
}
