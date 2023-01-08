package array;

public class HitCounter {

    private static int[] timestamps = new int[301];
    private static int[] counter = new int[301];

    public static void main(String[] args)
    {
        //initialize arrays to zero
        for(int i=0; i<301; i++){
            timestamps[i] = 0;
            counter[i] = 0;
        }

        addHit(1);
        addHit(2);
        addHit(3);
        System.out.println(getHit(4)); //3

        addHit(300);
        System.out.println(getHit(300)); //4

        System.out.println(getHit(301)); //3

        addHit(302);
        System.out.println(getHit(303)); //4
    }

    public static void addHit(int timestamp)
    {
        int index = timestamp % 301;

        //check if need to reset
        if(timestamps[index] != timestamp){
            //reset
            timestamps[index] = timestamp;
            counter[index] = 0;
        }
        //increment counter
        counter[index]++;
    }

    public static int getHit(int timestamp)
    {
        int count=0;
        for(int i=0; i<counter.length; i++){
            if(timestamps[i] > (timestamp - 300)){
                count += counter[i];
            }
        }

        return count;
    }
}

