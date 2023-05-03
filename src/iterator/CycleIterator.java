package iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CycleIterator<T> extends ListFlattenedIterator<T>{

    public CycleIterator(List list) {
        super(list);
    }

    @Override
    public T next() {
        T poll = queue.poll();
        queue.add(poll);
        return poll;
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

        ListFlattenedIterator<Integer> cycleIterator = new CycleIterator(iterators);

        int i=0;
        while(cycleIterator.hasNext()){
            System.out.print(cycleIterator.next()+",");
            i++;
            if(i == 50){
                break;
            }
        }
    }
}
