package strings;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class FirstNonRepeatingInStreamOfChars {

    public static Set<Character> set = new HashSet<>();
    public static Deque<Character> deque = new LinkedList<>();

    public static void main(String[] args)
    {
        System.out.println(getFirstNonRepeatingChar('a'));
        System.out.println(getFirstNonRepeatingChar('b'));
        System.out.println(getFirstNonRepeatingChar('c'));
        System.out.println(getFirstNonRepeatingChar('a'));
        System.out.println(getFirstNonRepeatingChar('b'));
        System.out.println(getFirstNonRepeatingChar('c'));
        System.out.println(getFirstNonRepeatingChar('d'));
    }

    public static char getFirstNonRepeatingChar(char c)
    {
        if(!set.contains(c)){
            //add to set and dequeue
            set.add(c);
            deque.addLast(c);
        }else {
            //remove from deque
            deque.remove(c);
        }

        return deque.isEmpty() ? '1' : deque.peekFirst();
    }
}
