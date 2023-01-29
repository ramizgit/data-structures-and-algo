package dp;

public class Fibonacci {
    public static void main(String[] args)
    {
        System.out.println(getFibonacci(0));
        System.out.println(getFibonacci(1));
        System.out.println(getFibonacci(2));
        System.out.println(getFibonacci(3));
        System.out.println(getFibonacci(4));
        System.out.println(getFibonacci(5));
        System.out.println(getFibonacci(8));
        System.out.println(getFibonacci(10));

    }

    //O(n) solution
    private static long getFibonacci(int n)
    {
        if(n < 2){
            return n;
        }

        long minusOne = 1;
        long minusTwo = 0;
        long answer = 0;

        for(int i=2; i<=n; i++){
            answer = minusOne + minusTwo;

            minusTwo = minusOne;
            minusOne = answer;
        }

        return answer;
    }
}
