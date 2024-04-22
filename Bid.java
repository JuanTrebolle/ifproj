class Bid {
    private String bidType; // {Bidder:AuctionHouse}
    private Bidder bidderID; // {Bidder:AuctionHouse}
    private double amount; // {Bidder:AuctionHouse}

    public Bid(String bidType, Bidder bidderID, double amount) {
        this.bidType = bidType;
        this.bidderID = bidderID;
        this.amount = amount;

    }
}