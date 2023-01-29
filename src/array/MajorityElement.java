package array;

public class MajorityElement {

    public static void main(String[] args)
    {
        System.out.println(findMajority(new int[]{1,2,3,2,2})); //2
        System.out.println(findMajority(new int[]{1,2,3,2,2,2,5,4,2})); //2
    }

    private static int findMajority(int[]arr)
    {
        int majority = arr[0];
        int count = 1;

        for(int i=1; i<arr.length; i++){
            if(arr[i] == majority){
                count++;
            }else if(count == 0){
                majority = arr[i];
                count++;
            }else {
                count--;
            }
        }

        return majority;
    }
}
