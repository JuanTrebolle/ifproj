package src;

import java.util.ArrayList;

public class OAS {


    public static void main(String[] args){

        // Use Case 1: Live Bidding
        Item vase = new Item("vase", 300);
        ArrayList<Bidder> bidders = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();

        AuctionHouse auctionHouse = new AuctionHouse("Cool-House");

        // Define bidders and their security labels
        Bidder bidderA = new Bidder("BidderA");
        Bidder bidderB = new Bidder("BidderB");
        Bidder bidderC = new Bidder("BidderC");
        Reputation reputationA = new Reputation();
        Reputation reputationB = new Reputation();
        Reputation reputationC = new Reputation();


        auctionHouse.addBidder(bidderA, reputationA);
        auctionHouse.addBidder(bidderB, reputationB);
        auctionHouse.addBidder(bidderC, reputationC);

        // Create an auction
        Item item = new Item("Artwork", 500);
        // ArrayList<Bidder> participatingBidders = new ArrayList<>();
        bidders.add(bidderA);
        bidders.add(bidderB);
        bidders.add(bidderC);
        auctionHouse.createAuction(item, bidders);

        if (!bidders.isEmpty()){
            //auctionHouse.simulateLiveBidding(bidders, 500, item);
            auctionHouse.liveBiddingSimulation(bidders);
        } else {
            System.out.println("No bidders available for live bidding.");
        }

        // Use Case 2: Live Bidding with Commission Bids


        // Use Case 3: Reputation System
    }
}

