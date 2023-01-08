package iterator;

import java.util.*;

public class ListFlattenedIterator<T> implements Iterator<T> {

    Queue<T> queue;

    public ListFlattenedIterator(List<Iterator<T>> iterators) {
        if(iterators == null){
            throw new RuntimeException();
        }

        queue = new LinkedList<>();
        for(Iterator<T> it : iterators){
           while (it.hasNext()){
               queue.add(it.next());
           }
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public T next() {
        if(!queue.isEmpty()){
            return queue.poll();
        }
        return null;
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

        ListFlattenedIterator<Integer> listFlattenedIterator = new ListFlattenedIterator(iterators);

        //output : 1,4,6,7,2,5,8,3,9
        while(listFlattenedIterator.hasNext()){
            System.out.print(listFlattenedIterator.next()+",");
        }
    }
}
