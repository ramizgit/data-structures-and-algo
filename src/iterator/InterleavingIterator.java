package iterator;

import java.util.*;

public class InterleavingIterator<T> implements Iterator<T> {

    private Queue<Iterator<T>> queue;

    public InterleavingIterator(List<Iterator<T>> iterators) {
        if(iterators == null){
            throw new RuntimeException();
        }

        queue = new LinkedList<>();
        for(Iterator<T> it : iterators){
            if(it.hasNext()){
                queue.add(it);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty() && queue.peek().hasNext();
    }

    @Override
    public T next() {
        T val = null;
        if(!queue.isEmpty()){
            Iterator<T> iterator = queue.poll();

            if(iterator.hasNext()){
                val = iterator.next();
            }

            //add it back to the queue if not empty
            if(iterator.hasNext()){
                queue.add(iterator);
            }
        }
        return val;
    }

    public static void main(String[] args){
        //[[1,2,3],[4,5],[6],[],[7,8,9]]
        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(4,5);
        List<Integer> list3 = Arrays.asList(6);
        List<Integer> list4 = Arrays.asList();
        List<Integer> list5 = Arrays.asList(7,8,9);

        List<Iterator<Integer>> iterators = Arrays.asList(list1.iterator(), list2.iterator(), list3.iterator(),
                list4.iterator(), list5.iterator());

        Iterator<Integer> interleavingIterator = new InterleavingIterator(iterators);

        //output : 1,4,6,7,2,5,8,3,9
        while(interleavingIterator.hasNext()){
            System.out.print(interleavingIterator.next()+",");
        }
    }
}
