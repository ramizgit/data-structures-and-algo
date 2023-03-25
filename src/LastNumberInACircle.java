public class LastNumberInACircle {

    public static void main(String[] args)
    {
        lastNumberInCircle(5, 3);
    }

    public static void lastNumberInCircle(int n, int m)
    {
        int last = 0;

        for(int i=2; i <= n; i++)
        {
            last = (last + m) % i;
        }

        System.out.println(last);
    }
}
