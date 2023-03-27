public class LastNumInACircle {

    public static void main(String[] args)
    {
        //Josephus problem
        System.out.println(lastnum(4, 3)); //1
        System.out.println(lastnum(5, 3)); //4
        System.out.println(lastnum(10, 3)); //4
    }

    private static int lastnum(int n, int m)
    {
        int last = 0;

        for(int i=2; i<=n; i++){
            last = (last + m) % i;
        }
        return last+1;
    }
}
