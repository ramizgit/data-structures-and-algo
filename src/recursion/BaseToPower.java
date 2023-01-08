package recursion;

public class BaseToPower {
    public static void main(String[] args)
    {
        System.out.println("result:");
        System.out.println(powerViaRecursionWay2(2,5));
        System.out.println(powerViaRecursionWay2(2,6));
        System.out.println(powerViaRecursionWay2(2,7));
        System.out.println(powerViaRecursionWay2(2,8));
    }

    public static double powerViaRecursion(int base, int exponent)
    {
        if(exponent == 0){
            return 1;
        }
        if(exponent == 1){
            return base;
        }
        double result = powerViaRecursion(base, exponent/2);
        result *= result;
        if(exponent %2 != 0){ 
            result *= base;
        }
        return result;
    }

    public static double powerViaRecursionWay2(int base, int exponent)
    {
        if(exponent == 0){
            return 1;
        }
        if(exponent == 1){
            return base;
        }
        if(exponent %2 == 0) {
            return powerViaRecursionWay2(base, exponent/2) * powerViaRecursionWay2(base, exponent/2);
        }
        else{
            return base * powerViaRecursionWay2(base, exponent/2) * powerViaRecursionWay2(base, exponent/2);
        }
    }

    public static double power(int base, int exponent)
    {
        //5^31 = 5^15 * 5 ^15 * 5 = 5^7 * 5 ^7 * 5 * 5^7 * 5 ^7 * 5 * 5
        double result = 1;
       while(exponent > 0)
       {
           if(exponent % 2 == 0)
           {
               base = base * base;
               exponent = exponent / 2;

           }
           else{
               result = result * base;
               exponent = exponent - 1;
           }
       }
       return result;
    }
}
