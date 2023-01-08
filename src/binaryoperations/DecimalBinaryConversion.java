package binaryoperations;

public class DecimalBinaryConversion {
    public static void main(String[] args)
    {
        binaryToDecimal(111);
        binaryToDecimal(1001);

        decimalToBinary(9);
        System.out.println();
        decimalToBinary(7);
        System.out.println();
        decimalToBinary(10);
    }

    public static void binaryToDecimal(int input)
    {
        int decimal = 0;
        int base=1;

        while(input > 0){
            int lastDigit = input % 10;
            decimal += (base * lastDigit);
            base *= 2;
            input /= 10;
        }

        System.out.println("Decimal is : " + decimal);
    }

    public static void decimalToBinary(int input)
    {
        StringBuilder sb = new StringBuilder();

        while(input != 0){
            sb.append(input % 2);
            input /= 2;
        }

        int binary = Integer.parseInt(sb.reverse().toString());

        System.out.println("Binary is : "+binary);



        /*
        Decimal is : 7
Decimal is : 9
1001
111
1010

        int[] bin = new int[32];
        int i=0;

        while(input > 0){
            bin[i] = input % 2;
            input = input / 2;
            i++;
        }

        for(int j=i-1; j>=0; j--){
            System.out.print(bin[j]);
        }*/
    }
}

