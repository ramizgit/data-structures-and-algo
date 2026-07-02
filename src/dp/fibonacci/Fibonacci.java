package dp.fibonacci;

public class Fibonacci {

    //O(n) solution
    private static long getFibonacci(int n)
    {
        if(n < 2){
            return n;
        }

        //base case
        long minusTwo = 0;
        long minusOne = 1;

        long answer = 0;

        for(int i=2; i<=n; i++){
            answer = minusOne + minusTwo;

            minusTwo = minusOne;
            minusOne = answer;
        }

        return answer;
    }
}
