package binary;

public class ReverseBits {
    //https://leetcode.com/problems/reverse-bits/description/

    //todo : Follow up: If this function is called many times, how would you optimize it?

    public static void main(String[] args)
    {
        System.out.println(reverseBits(43261596));
    }

    private static int reverseBits(int n)
    {
        int result = 0;
        int idx=31;
        while (n != 0){
            int bit = n & 1;
            result |= (bit << idx);
            idx--;
            n = n >>> 1;
        }

        return result;
    }
}

//todo : Follow up: If this function is called many times, how would you optimize it?
/*
public class Solution {
    private static final int[] lookup = new int[256];

    // Static block to initialize lookup table once
    static {
        for (int i = 0; i < 256; i++) {
            lookup[i] = reverseByte(i);
        }
    }

    private static int reverseByte(int b) {
        int result = 0;
        for (int i = 0; i < 8; i++) {
            result <<= 1;
            result |= (b & 1);
            b >>= 1;
        }
        return result;
    }

    public int reverseBits(int n) {
        return  (lookup[n & 0xff] << 24) |
                (lookup[(n >>> 8) & 0xff] << 16) |
                (lookup[(n >>> 16) & 0xff] << 8) |
                (lookup[(n >>> 24) & 0xff]);
    }
}

 */
