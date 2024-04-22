import java.util.ArrayList;

public class OAS {


    public static void main(String[] args){

        // Use Case 1: Live Bidding
        Item vase = new Item("vase", 300);
        ArrayList<Bidder> bidders = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();


        AuctionHouse auctionHouse = new AuctionHouse();

        // Define bidders and their security labels
        Bidder bidderA = new Bidder("BidderA");
        Bidder bidderB = new Bidder("BidderB");
        auctionHouse.addBidder(bidderA);
        auctionHouse.addBidder(bidderB);

        // Create an auction
        Item item = new Item("Artwork", 500);
        auctionHouse.createAuction(item, SecurityLabel.CONFIDENTIAL);



        // Use Case 2: Live Bidding with Commission Bids


        // Use Case 3: Reputation System
    }
}

