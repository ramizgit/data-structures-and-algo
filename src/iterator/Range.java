package iterator;

import java.util.Iterator;

public class Range implements Iterable<Integer> {

    private int start;
    private int end;
    private int increment;

    public Range(int start, int end, int increment) {
        this.start = start;
        this.end = end;
        this.increment = increment;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new RangeIterator(start);
    }

    private class RangeIterator implements Iterator<Integer>{

        private int current;

        public RangeIterator(int current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return this.current <= end;
        }

        @Override
        public Integer next() {
            if(this.hasNext()){
                int val = current;
                current += increment;
                return val;
            }
            return -1;
        }
    }

    public static void main(String[] args)
    {
        Range range = new Range(0, 10, 2);
        Iterator<Integer> it = range.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

}
