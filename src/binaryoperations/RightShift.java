package binaryoperations;

public class RightShift {
    public static void main(String[] args)
    {
        int l = 1;
        int r = Integer.MAX_VALUE;

        //Wrong way, will overflow
        int mid = (l + r ) / 2;
        System.out.println(mid);

        //Right way, will not overflow
        int mid2 = (r-l)/2 + l;
        System.out.println(mid2);

        //Wrong way, will overflow
        int mid3 = (l+r) >> 1;
        System.out.println(mid3);

        //Right way, will not overflow
        int mid4 = (l+r) >>> 1;
        System.out.println(mid4);
    }
}
