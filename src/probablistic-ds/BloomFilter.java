import java.util.BitSet;

public class BloomFilter {

    BitSet bitSet;

    public BloomFilter() {
        this.bitSet = new BitSet(10);
    }

    public void seen(int num)
    {
        int hash1 = HashFunction.hash1(num);
        int hash2 = HashFunction.hash2(num);
        int hash3 = HashFunction.hash3(num);

        if(bitSet.get(hash1) && bitSet.get(hash2) && bitSet.get(hash3)){
            System.out.println("Seen before");
        }else {
            System.out.println("Not seen before");
            bitSet.set(hash1);
            bitSet.set(hash2);
            bitSet.set(hash3);
        }
    }

    public static void main(String[] args){
        BloomFilter bloomFilter = new BloomFilter();
        bloomFilter.seen(10);
        bloomFilter.seen(25);
        bloomFilter.seen(33);
        bloomFilter.seen(10);
    }
}
