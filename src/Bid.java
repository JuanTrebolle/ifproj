class Bid {

    enum BidType {
        COMMISION, // 0 = Commission bid
        LIVE // 1 = Live bid
    }

    private BidType bidType; // {Bidder:AuctionHouse}
    private Bidder bidderID; // {Bidder:AuctionHouse}
    private double amount; // {Bidder:AuctionHouse}

    public Bid(BidType bidType, Bidder bidderID, double amount) {
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
}