import java.util.ArrayList;

class Auction {
    private Item item; // {AuctionHouse:AuctionHouse, LiveBidder, CommisionBidder}
    private ArrayList<Bid> commissionBids; // {AuctionHouse:AuctionHouse}
    private ArrayList<Bid> bids; // {AuctionHouse:LiveBidder}
    private int soldPrice; // {AuctionHouse:AuctionHouse, LiveBidder, CommisionBidder}

    public Auction(Item item) {
        this.item = item;
        bids = new ArrayList<>();
    }

    // Method to receive a bid
    public void receiveBid(Bidder bidder, int amount) {
        Bid bid = new Bid(bidder, amount);
        bids.add(bid);
    }


}
