public class LikeDislike {
    public static void main(String[] args)
    {
        getcount(1001, 1000);
    }

    public static void getcount(int num1, int num2)
    {
        int xor = num1 ^ num2;
        int countofzero = 0;
        int totalcount = 0;

        while(num1 != 0){
            num1 /= 10;
            totalcount++;
        }

        while(xor !=0){
            if( (xor & 1) ==0){
                countofzero++;
            }
            xor = xor >> 1;
        }
        System.out.println(totalcount - countofzero);
    }
}
