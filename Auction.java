import java.util.ArrayList;

class Auction {
    private Item item;
    private ArrayList<Bid> bids;
    private SecurityLabel label;

    public Auction(Item item, SecurityLabel label) {
        this.item = item;
        this.label = label;
        bids = new ArrayList<>();
    }

    // Method to receive a bid
    public void receiveBid(Bidder bidder, double amount, SecurityLabel label) {
        Bid bid = new Bid(bidder, amount, label);
        bids.add(bid);
    }


}
