package heaps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You operate a market place for buying & selling used textbooks For a giventext book eg“TheoryofCryptography”
 * there are people who want to buy this textbook and people who want to sell
 *
 * OfferstoBUY:$100$100$99$99$97$90
 * OfferstoSELL:$109$110$110$114$115$119
 *
 * A match occurs when two people agree on a price Some newoffers donotmatchTheseoffersshouldbeaddedtotheactivesetofoffers
 *
 * Forexample:TimofferstoSELLat$150ThiswillnotmatchNooneiswillingtobuyatthatpricesowesavetheoffer
 *
 * OfferstoSELL::$109$110$110$114$115$119$150
 *
 * Whenmatchingwewanttogivethecustomerthe“bestprice”
 *
 * Examplematches:IfJaneofferstoBUYat$120
 *
 * shewillmatchandbuyabookfor$109(thelowestoffer
 */

/*
Offers to BUY: $100$100$99$99$97$90
Offers to SELL: $109$110$110$114$115$119

Use two heaps. Put all the buy orders in a max heap and put all the sell orders in a min heap.

Let's say we get a new BUY order for $113. We want to find the best selling price ($109), which is right at the top of the seller min heap. Take that value out and match it with the user. We are done for this case.

Now let's say we got a BUY order for $90, the value at the top of the seller min heap is $110 (we already polled the $109 from the seller heap), so no one will sell at this price. So we cannot match it at this point.
IMPORTANT: We are asked to save this value if it was unmatched. So put this $90 BUY order in the buy orders max heap.

Do the same logic for SELL orders (poll max buying price from buyers max heap, save the value in the seller min heap if there was no match).

Time complexity: O(n logn) --> because we put everything in the heaps which are O(log n) per offer/poll operations.
Space complexity: O(n) --> we're putting the entire input lists in the heaps
 */
public class BuyAndSellStock {
    public static void main(String[] args) {
        BuyAndSellStock m = new BuyAndSellStock();
        int[] buy = new int[]{100,100,90,96,98};
        int[] sell = new int[]{109,70,160,120,90};
        List<Integer> p = m.bestMatch(buy,sell,200, 90);

        System.out.println("OptimumBuy:"+p.get(0).toString());
        System.out.println("OptimumSell:"+p.get(1).toString());
    }


    public List<Integer> bestMatch(int[] buyOffers, int[] sellOffers, int buyPrice, int sellPrice) {
        PriorityQueue<Integer> buy = new PriorityQueue<>((a, b) -> b-a);
        PriorityQueue<Integer> sell = new PriorityQueue<>((a,b) -> a-b);
        int optimumSell = 0;
        int optimumBuy = 0;

        for(int b: buyOffers) {
            buy.add(b);
        }
        for(int s: sellOffers) {
            sell.add(s);
        }
        if(buy.peek() < sellPrice) {
            sell.add(sellPrice);
        } else {
            optimumBuy = buy.poll();
        }

        if(buyPrice < sell.peek()) {
            buy.add(buyPrice);
        } else {
            optimumSell = sell.poll();
        }
        List<Integer> res = new ArrayList<>();
        res.add(optimumBuy);
        res.add(optimumSell);
        return res;
    }
}
