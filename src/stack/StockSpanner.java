package stack;

import java.util.*;

public class StockSpanner {

    //https://leetcode.com/problems/online-stock-span/description/

    Stack<Stock> stack;

    public StockSpanner() {
        this.stack = new Stack<>();
    }

    public int next(int price) {

        int span = 1;

        while(!this.stack.isEmpty() && price >= this.stack.peek().price){
            span += this.stack.peek().span;
            this.stack.pop();
        }

        this.stack.push(new Stock(price, span));

        return span;
    }

    class Stock{
        int price;
        int span;

        public Stock(int price, int span) {
            this.price = price;
            this.span = span;
        }
    }
}
