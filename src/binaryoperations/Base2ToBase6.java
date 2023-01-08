package binaryoperations;

public class Base2ToBase6 {
    public static void main(String[] args)
    {
        System.out.println(base2Tobase6(100111)); //103
    }

    public static int base2Tobase6(int base2)
    {
        //get decimal
        int decimal = getDecimal(base2);

        //convert to base 6
        int base6 = getBase6(decimal);

        return base6;
    }

    public static int getBase6(int n)
    {
        StringBuilder sb = new StringBuilder();

        while (n != 0){
            sb.append(n%6);
            n /= 6;
        }

        return Integer.parseInt(sb.reverse().toString());
    }

    public static int getDecimal(int n)
    {
        int decimal = 0;
        int base = 1;

        while(n != 0){
            int last = n % 10;
            decimal += last * base;
            base *= 2;
            n /= 10;
        }

        return decimal;
    }



}
