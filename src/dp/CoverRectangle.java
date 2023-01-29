package dp;

public class CoverRectangle {

    //cover 2x8 rectangle with 2x1

    public static void main(String[] args)
    {
        System.out.println(getFibonacci(0));
        System.out.println(getFibonacci(1));
        System.out.println(getFibonacci(2));
        System.out.println(getFibonacci(3));
        System.out.println(getFibonacci(4));
        System.out.println(getFibonacci(5));
        System.out.println(getFibonacci(6));
        System.out.println(getFibonacci(7));
        System.out.println(getFibonacci(8));
        System.out.println(getFibonacci(9));
    }

    //O(n) solution
    private static long getFibonacci(int n)
    {
        if(n <= 3){
            return n;
        }

        long minusOne = 3;
        long minusTwo = 2;
        long answer = 0;

        for(int i=4; i<=n; i++){
            answer = minusOne + minusTwo;

            minusTwo = minusOne;
            minusOne = answer;
        }

        return answer;
    }
}
