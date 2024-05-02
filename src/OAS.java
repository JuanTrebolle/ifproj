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
        // Create AuctionHouse instances
        AuctionHouse auctionHouse1 = new AuctionHouse("Auction House 1");
        AuctionHouse auctionHouse2 = new AuctionHouse("Auction House 2");

        // Define Bidder A with a known reputation status
        Bidder bidderA = new Bidder("Bidder A");
        Reputation reputationA = new Reputation();
        // Set Bidder A's reputation status to "GOOD" with a bidding limit of 700 kr
        reputationA.setBiddingLimit(700);
        auctionHouse1.addBidder(bidderA, reputationA);
        // Bidder A wants to participate in an auction in Auction House 2
        // Auction House 2 checks Bidder A's reputation status in Auction House 1
        Reputation sharedReputation = auctionHouse1.declassifyReputation(bidderA);
        // Share Bidder A's reputation status with Auction House 2
        auctionHouse2.receiveReputation(sharedReputation);

        // Set bidding limit for Bidder A in Auction House 2 based on their reputation status
        auctionHouse2.setBiddingLimit(bidderA, sharedReputation.getBiddingLimit());

        // Now, Bidder A can participate in auctions in Auction House 2 with a bidding limit of 700 kr
    }
}

