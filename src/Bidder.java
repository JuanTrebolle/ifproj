import java.util.ArrayList;
import java.util.UUID;

class Bidder {
    private String name; // {Bidder:AuctionHouse}
    private String bidderID; // {AuctionHouse:AuctionHouse, Bidder)
    private double bidLimit;
    private Reputation reputation; // {AuctionHouse:AuctionHouse}
    
    // Declassification: share with other auction houses
    public Bidder(String name) {
        this.name = name;
        this.bidderID = UUID.randomUUID().toString();
        this.bidLimit = 100.0; // ??
        this.reputation = new Reputation();
    }

    public String getName() {
        return this.name;
    }

    public String getBidderID() {
        return this.bidderID;
    }

    public double getBidLimit() {
        return this.bidLimit;
    }

    public Reputation getReputation() {
        return this.reputation;
    }
}

