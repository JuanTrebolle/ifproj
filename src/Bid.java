package src;

class Bid {

    enum BidType {
        COMMISSION, // 0 = Commission bid
        LIVE // 1 = Live bid
    }

    private BidType bidType; // {Bidder:AuctionHouse}
    private String bidderID; // {Bidder:AuctionHouse}
    private double amount; // {Bidder:AuctionHouse}

    public Bid(BidType bidType, String bidderID, double amount) {
        this.bidType = bidType;
        this.bidderID = bidderID;
        this.amount = amount;
    }

    public BidType getBidType() {
        return this.bidType;
    }

    public double getBidAmount() {
        return this.amount;
    }

    public String getBidderID() {
        return this.bidderID;
    }


}