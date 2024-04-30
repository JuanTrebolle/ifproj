import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

class Auction {
    private String auctionID; // {AuctionHouse:AuctionHouse}
    private Item item; // {AuctionHouse:AuctionHouse, LiveBidder, CommisionBidder}
    private ArrayList<Bid> commissionBids; // {AuctionHouse:AuctionHouse}
    private Map<String, Bid> map_commisionBids; // {AuctionHouse: AuctionHouse}
    private ArrayList<Bid> bids; // {AuctionHouse:LiveBidder}
    private Map<String, Bid> map_bids; // {AuctionHouse: LiveBidder}
    private int soldPrice; // {AuctionHouse:AuctionHouse, LiveBidder, CommisionBidder}
    private Map<String, Integer> map_soldPrice; // {AuctionHouse: AuctionHouse, LiveBidder, CommissionBidder}
    private int currentHighestBid; // TODO: complete lattice {Auction: Auction, AuctionHouse, LiveBidder, CommisionBidder}
    private ArrayList<Bidder> bidders; // TODO: complete lattice {Auction: Auction, AuctionHouse}
    // private Map<Bidder, Bid> historicalBids; // TODO: lattice {Auction: Auction, AuctionHouse}

    public Auction(Item item, ArrayList<Bidder> bidders) {
        this.auctionID = UUID.randomUUID().toString();
        this.item = item;
        bids = new ArrayList<>();
        currentHighestBid = item.getStartingPrice();
        this.bidders = bidders;
    }

    public ArrayList<Bidder> getBidders() {
        return this.bidders;
    }

    public String getAuctionID() {
        return this.auctionID;
    }

    // Method to receive a bid
    public void receiveBid(Bidder bidder, int amount) {
        Bid bid = new Bid(Bid.BidType.LIVE,bidder.getBidderID(), amount);
        bids.add(bid);
    }

    public void placeCommissionBid(double amount, Bidder bidder) {
        Bid newBid = new Bid(Bid.BidType.COMMISION, bidder.getBidderID(), amount);
        commissionBids.add(newBid); // we have a separated variable to keep track of the commission bids.
        bids.add(newBid);
        map_bids.put(bidder.getBidderID(), newBid); // Maybe we should use only the map
        map_commisionBids.put(bidder.getBidderID(), newBid); // Maybe we should use only the map
        if (amount > currentHighestBid) {
            currentHighestBid = (int) amount; // TODO: map
        }
    }

    public void placeLiveBid(double amount, Bidder bidder) {
        Bid newBid = new Bid(Bid.BidType.LIVE, bidder.getBidderID(), amount);
        bids.add(newBid);
        if (amount > currentHighestBid) {
            currentHighestBid = (int) amount;
        }
    }

    public int getCurrentHighestBid() {
        return currentHighestBid;
    }

    public double getBidAmountByBidderId(String bidderId) {
        for(Bid bid : bids) {
            if (bid.getBidderID().equals(bidderId)) {
                return bid.getBidAmount();

            }
        }
        return 0;
    }


    public void auctionWinner() {
        // TODO: order map and get highest
    }
}
