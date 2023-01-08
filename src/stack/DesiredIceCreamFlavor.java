package stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DesiredIceCreamFlavor {
    public static void main(String[] args)
    {
        System.out.println(numOfCustomers(new int[]{1, 1, 0, 0}, new int[]{0, 1, 0, 1})); //4
        System.out.println(numOfCustomers(new int[]{1, 1, 1, 0, 0, 1}, new int[]{1, 0, 0, 0, 1, 1})); //3
    }

    private static int numOfCustomers(int[] customer, int[] icecream)
    {
        Queue<Integer> queue = new LinkedList<>();
        Stack<Integer> flavors = new Stack<>();
        int answer = 0;

        for(int i=0; i<customer.length; i++){
            queue.add(customer[i]);
        }

        for(int i=icecream.length-1; i>=0; i--){
            flavors.push(icecream[i]);
        }

        while (!queue.isEmpty() && !flavors.empty()){

            if(!queue.contains(flavors.peek())){
                break;
            }

            int frontCustomer = queue.remove();
            if(frontCustomer == flavors.peek()){
                flavors.pop();
                answer++;
            }else {
                queue.add(frontCustomer);
            }
        }

        return answer;
    }
}
