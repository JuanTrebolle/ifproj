class Bid {
    private Bidder bidder;
    private double amount;
    private SecurityLabel label;

    public Bid(Bidder bidder, double amount, SecurityLabel label) {
        this.bidder = bidder;
        this.amount = amount;
        this.label = label;
    }
}