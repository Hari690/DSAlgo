package stack;

import java.util.Stack;

/**
 * Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the
 * current day.
 *
 * The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards) for
 * which the price of the stock was less than or equal to today's price.
 *
 * For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock spans would be
 * [1, 1, 1, 2, 1, 4, 6].
 *
 * Keep storing the span in a stack(value and span) and use the span of previous lower value to calculate current span value of stock.
 *
 */
// since we don't care about elements older than smaller element on left we maintain stack in decreasing order of value with
// index.
public class SpanStock {

    Stack<int[]> stack = new Stack<>();

    public int next(int price) {
        int res = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price)
            res += stack.pop()[1];
        stack.push(new int[]{price, res});
        return res;
    }
}