package google;

public class RLEIterator {

   //https://leetcode.com/problems/rle-iterator/description/

    private int[] encoding;
    private int index;

    public RLEIterator(int[] encoding) {
        this.encoding = encoding;
        this.index = 0;
    }

    public int next(int n) {

        while(this.index < this.encoding.length){

            if(this.encoding[index] >= n){
                this.encoding[index] -= n;
                return this.encoding[index+1];
            }

            //consume entire block and move to next
            n -= this.encoding[index];
            this.index += 2;
        }

        return -1;
    }
}
