import java.util.ArrayList;

class Bidder {
    private String name; // {Bidder:AuctionHouse}
    private String bidderID; // {AuctionHouse:AuctionHouse, Bidder)
    private int bidLimit;
    private String reputation; // {AuctionHouse:AuctionHouse}
                               // Declassification: share with other auction houses
    public Bidder(String name) {
        this.name = name;
    }
}

