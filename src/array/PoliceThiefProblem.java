package array;

public class PoliceThiefProblem {

    public static void main(String[] args)
    {
        System.out.println(countOfThiefCaught(new char[]{'P','T', 'T', 'P', 'T'}, 1));

    }

    public static int countOfThiefCaught(char[] arr, int k)
    {
        int pi = 0;
        int ti = 0;
        int count = 0;

        while(pi < arr.length && ti < arr.length){

            //move pi till police
            while(pi < arr.length && arr[pi] != 'P'){
                pi++;
            }

            //move ti till thief
            while(ti < arr.length && arr[ti] != 'T'){
                ti++;
            }

            while(Math.abs(pi-ti) > k){
                if(pi < ti){
                    pi++;
                }else if(ti < pi){
                    ti++;
                }
            }

            if(arr[pi] == 'P' && arr[ti] == 'T' && Math.abs(pi-ti) <= k){
                count++;
            }

            //move both pointers
            pi++;
            ti++;
        }

        return count;
    }
}
