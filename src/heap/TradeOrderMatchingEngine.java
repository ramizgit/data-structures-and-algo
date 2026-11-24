package heap;

import java.util.*;

/*
Design a Simplified Stock Exchange Order Matching Engine
Difficulty: Hard

Topics: Priority Queue, HashSet, Heap, Custom Comparator, Simulation, Design
Problem Statement
You are asked to implement a simplified stock exchange order matching engine.

The exchange receives BUY and SELL orders. Each order contains:
- orderId: Unique identifier for the order.
- side: Either BUY or SELL.
- price: The price at which the trader is willing to buy or sell.
- quantity: Number of shares.
- timestamp: Time at which the order was submitted.
Your task is to implement an order book that supports adding orders, cancelling orders, and retrieving the best available bid and ask.
Order Matching Rules
An incoming order must be matched against the opposite side of the order book before being added to its own book.

BUY order matching:
- Match against the lowest-priced SELL order.
- A match is possible when buyPrice >= sellPrice.
SELL order matching:
- Match against the highest-priced BUY order.
- A match is possible when sellPrice <= buyPrice.
Price-time priority:

Orders must be processed according to the following rules:
Order side	Priority
BUY	Higher price first
SELL	Lower price first
Both	Earlier timestamp first

If two orders have the same price and timestamp, use the lower orderId first.

Partial fills:

If the incoming order cannot be completely matched, its remaining quantity must be added to its own order book.

For example:

Existing SELL orders:
Order S1: SELL 100 @ 105
Order S2: SELL  50 @ 103


Incoming order:
Order B1: BUY 120 @ 105


Execution:
B1 matches S2: 50 shares @ 103
B1 matches S1: 70 shares @ 105


Remaining order book:
SELL 30 @ 105


Implement the following methods
class OrderMatchingEngine {

    public void addOrder(Order order) {
        // Add an order and execute possible matches
    }

    public void cancelOrder(String orderId) {
        // Cancel an active order
    }

    public Order getBestBid() {
        // Return the highest-priority BUY order
    }

    public Order getBestAsk() {
        // Return the highest-priority SELL order
    }
}


You may define the Order class yourself.
Constraints
- 1 <= orderId.length() <= 50
- Order IDs are unique.
- 1 <= price <= 1,000,000,000
- 1 <= quantity <= 1,000,000
- 1 <= timestamp <= 1,000,000,000
- Up to \(10^6\) orders may be submitted.
- Orders may be cancelled before they are completely filled.
- An order that is fully filled must no longer appear in the order book.
- A partially filled order retains its original timestamp and price.
- Cancelled orders must never participate in matching.
Expected behavior
- getBestBid() returns the best active BUY order, or null if no BUY orders exist.
- getBestAsk() returns the best active SELL order, or null if no SELL orders exist.
- Cancellation of an unknown, already cancelled, or fully filled order should have no effect.
- Matching should continue until no compatible opposite-side order remains or the incoming order is completely filled.
 */

public class TradeOrderMatchingEngine {

    private PriorityQueue<Order> maxHeapForBuySide; //always put highest buy bid at the top
    private PriorityQueue<Order> minHeapForSellSide; //always put lowest sell bid at the top

    private Set<String> cancelledOrders; //for lazy deletion

    public TradeOrderMatchingEngine(){

        this.maxHeapForBuySide = new PriorityQueue<>( (a, b) -> {

            if(a.price != b.price){
                return Double.compare(b.price, a.price); //highest bid first
            }else if(a.timestamp != b.timestamp){
                return Integer.compare(a.timestamp, b.timestamp); //if same bid, put earlier bid first
            }

            //tie breaker to put lower orderId comes first.
            return a.orderId.compareTo(b.orderId);
        });

        this.minHeapForSellSide = new PriorityQueue<>( (a, b) -> {

            if(a.price != b.price){
                return Double.compare(a.price, b.price); //highest bid first
            }else if(a.timestamp != b.timestamp){
                return Integer.compare(a.timestamp, b.timestamp); //if same bid, put earlier bid first
            }

            //tie breaker to put lower orderId comes first.
            return a.orderId.compareTo(b.orderId);
        });

        this.cancelledOrders = new HashSet<>();
    }


    public void addOrder(Order order) {
        // Add an order and execute possible matches
        /*
    IF incomingOrder.side == BUY:

        WHILE incomingOrder.quantity > 0:

            Remove cancelled orders from SELL heap

            IF SELL heap is empty:
                BREAK

            bestSell = SELL heap top

            IF incomingOrder.price < bestSell.price:
                BREAK

            matchedQuantity = MIN(
                incomingOrder.quantity,
                bestSell.quantity
            )

            incomingOrder.quantity -= matchedQuantity
            bestSell.quantity -= matchedQuantity

            IF bestSell.quantity == 0:
                REMOVE bestSell from SELL heap

        IF incomingOrder.quantity > 0:
            INSERT incomingOrder into BUY heap


    ELSE IF incomingOrder.side == SELL:

        WHILE incomingOrder.quantity > 0:

            Remove cancelled orders from BUY heap

            IF BUY heap is empty:
                BREAK

            bestBuy = BUY heap top

            IF incomingOrder.price > bestBuy.price:
                BREAK

            matchedQuantity = MIN(
                incomingOrder.quantity,
                bestBuy.quantity
            )

            incomingOrder.quantity -= matchedQuantity
            bestBuy.quantity -= matchedQuantity

            IF bestBuy.quantity == 0:
                REMOVE bestBuy from BUY heap

        IF incomingOrder.quantity > 0:
            INSERT incomingOrder into SELL heap
         */

        if (order.side.equals("BUY")) {

            // Match incoming BUY against best SELL orders
            while (order.quantity > 0) {

                // Remove cancelled orders from SELL heap
                removeCancelledOrders(minHeapForSellSide);

                if (minHeapForSellSide.isEmpty()) {
                    break;
                }

                Order bestSell = minHeapForSellSide.peek();

                // Cannot match: incoming BUY price is too low
                if (order.price < bestSell.price) {
                    break;
                }

                // Execute trade
                int matchedQuantity = Math.min(
                        order.quantity,
                        bestSell.quantity
                );

                order.quantity -= matchedQuantity;
                bestSell.quantity -= matchedQuantity;

                // Remove completely filled SELL order
                if (bestSell.quantity == 0) {
                    minHeapForSellSide.poll();
                }
            }

            // Add remaining BUY quantity to the BUY book
            if (order.quantity > 0) {
                maxHeapForBuySide.offer(order);
            }

        } else if (order.side.equals("SELL")) {

            // Match incoming SELL against best BUY orders
            while (order.quantity > 0) {

                // Remove cancelled orders from BUY heap
                removeCancelledOrders(maxHeapForBuySide);

                if (maxHeapForBuySide.isEmpty()) {
                    break;
                }

                Order bestBuy = maxHeapForBuySide.peek();

                // Cannot match: incoming SELL price is too high
                if (order.price > bestBuy.price) {
                    break;
                }

                // Execute trade
                int matchedQuantity = Math.min(
                        order.quantity,
                        bestBuy.quantity
                );

                order.quantity -= matchedQuantity;
                bestBuy.quantity -= matchedQuantity;

                // Remove completely filled BUY order
                if (bestBuy.quantity == 0) {
                    maxHeapForBuySide.poll();
                }
            }

            // Add remaining SELL quantity to the SELL book
            if (order.quantity > 0) {
                minHeapForSellSide.offer(order);
            }
        }
    }

    public void cancelOrder(String orderId) {
        // Cancel an active order
        this.cancelledOrders.add(orderId);
    }

    public Order getBestBid() {
        // Return the highest-priority BUY order

        //first clean up cancelled orders
        removeCancelledOrders(this.maxHeapForBuySide);

        return this.maxHeapForBuySide.isEmpty() ? null : this.maxHeapForBuySide.peek();
    }

    public Order getBestAsk() {
        // Return the highest-priority SELL order

        //first clean up cancelled orders
        removeCancelledOrders(this.minHeapForSellSide);

        return this.minHeapForSellSide.isEmpty() ? null : this.minHeapForSellSide.peek();
    }

    private void removeCancelledOrders(PriorityQueue<Order> heap) {

        while (!heap.isEmpty() && cancelledOrders.contains(heap.peek().orderId)) {
            Order removed = heap.poll();
            cancelledOrders.remove(removed.orderId);
        }
    }

    static class Order{
        private String orderId;
        private String side;
        private double price;
        private int quantity;
        private int timestamp;

        public Order(String orderId, String side, double price, int quantity, int timestamp) {
            this.orderId = orderId;
            this.side = side;
            this.price = price;
            this.quantity = quantity;
            this.timestamp = timestamp;
        }
    }
}
