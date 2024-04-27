import java.util.ArrayList;
import java.util.UUID;

class Auction {
    private String auctionID; // {AuctionHouse:AuctionHouse}
    private Item item; // {AuctionHouse:AuctionHouse, LiveBidder, CommisionBidder}
    private ArrayList<Bid> commissionBids; // {AuctionHouse:AuctionHouse}
    private ArrayList<Bid> bids; // {AuctionHouse:LiveBidder}
    private int soldPrice; // {AuctionHouse:AuctionHouse, LiveBidder, CommisionBidder}
    private int currentHighestBid; // TODO: complete lattice

    public Auction(Item item) {
        this.auctionID = UUID.randomUUID().toString();
        this.item = item;
        bids = new ArrayList<>();
        currentHighestBid = item.getStartingPrice();
    }

    public String getAuctionID() {
        return this.auctionID;
    }

    // Method to receive a bid
    public void receiveBid(Bidder bidder, int amount) {
        Bid bid = new Bid(Bid.BidType.LIVE,bidder, amount);
        bids.add(bid);
    }

    public void placeCommissionBid(double amount, Bidder bidder) {
        Bid newBid = new Bid(Bid.BidType.COMMISION, bidder, amount);
        bids.add(newBid);
        if (amount > currentHighestBid) {
            currentHighestBid = (int) amount;
        }
    }

    public void placeLiveBid(double amount, Bidder bidder) {
        Bid newBid = new Bid(Bid.BidType.LIVE, bidder, amount);
        bids.add(newBid);
        if (amount > currentHighestBid) {
            currentHighestBid = (int) amount;
        }
    }

    public int getCurrentHighestBid() {
        return currentHighestBid;
    }

}
