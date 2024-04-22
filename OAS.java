import java.util.ArrayList;

public class OAS {
    Item vase = new Item("vase", 300);
    ArrayList<Bidder> bidders = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();


    public static void main(String[] args){

        System.out.println("Hello, World!");
        AuctionHouse auctionHouse = new AuctionHouse();

        // Define bidders and their security labels
        Bidder bidderA = new Bidder("BidderA");
        Bidder bidderB = new Bidder("BidderB");
        auctionHouse.addBidder(bidderA, SecurityLabel.CONFIDENTIAL);
        auctionHouse.addBidder(bidderB, SecurityLabel.PUBLIC);

        // Create an auction
        Item item = new Item("Artwork", 500.0);
        auctionHouse.createAuction(item, SecurityLabel.CONFIDENTIAL);
    }
}

