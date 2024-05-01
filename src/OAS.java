package src;

import java.util.ArrayList;

public class OAS {


    public static void main(String[] args){

        // Use Case 1: Live Bidding
        Item vase = new Item("vase", 300);
        ArrayList<Bidder> bidders1 = new ArrayList<>(); // bidders for Use Case 1 - Live Bidding
        ArrayList<Item> items = new ArrayList<>();

        AuctionHouse auctionHouse = new AuctionHouse("Cool-House");

        // Define bidders and their security labels
        Bidder bidderA = new Bidder("BidderA");
        Bidder bidderB = new Bidder("BidderB");
        Bidder bidderC = new Bidder("BidderC");
        Reputation reputationA = new Reputation();
        Reputation reputationB = new Reputation();
        Reputation reputationC = new Reputation();


        auctionHouse.addBidder(bidderA, reputationA, auctionHouse.getAuctions().get(0));
        auctionHouse.addBidder(bidderB, reputationB, auctionHouse.getAuctions().get(0));
        auctionHouse.addBidder(bidderC, reputationC, auctionHouse.getAuctions().get(0));

        // Create an auction
        Item item = new Item("Artwork", 500);
        // ArrayList<Bidder> participatingBidders = new ArrayList<>();
        bidders1.add(bidderA);
        bidders1.add(bidderB);
        bidders1.add(bidderC);
        //auctionHouse.createAuction(item, bidders1); // auction[0] - item: vase
        Auction auctionArtwork = new Auction(item, bidders1);
        auctionHouse.addAuction(auctionArtwork);

        if (!bidders1.isEmpty()){
            //auctionHouse.simulateLiveBidding(bidders, 500, item);
            // auctionHouse.liveBiddingSimulation(bidders1, auctionHouse.getAuctions().get(0));
            auctionHouse.liveBiddingSimulation(bidders1, auctionArtwork);
        } else {
            System.out.println("No bidders available for live bidding.");
        }

        // Use Case 2: Live Bidding with Commission Bids
        Item painting = new Item("watch-rolex", 25000);
        ArrayList<Bidder> bidders2 = new ArrayList<>(); // bidders for Use Case 2 - Live Bidding with Commission Bids

        // Define bidders and their security labels
        Bidder bidderA_2 = new Bidder("BidderA");
        Bidder bidderB_2 = new Bidder("BidderB");
        Bidder bidderC_2 = new Bidder("BidderC");
        Reputation reputationA_2 = new Reputation();
        Reputation reputationB_2 = new Reputation();
        Reputation reputationC_2 = new Reputation();


        auctionHouse.addBidder(bidderA_2, reputationA_2, auctionHouse.getAuctions().get(1));
        auctionHouse.addBidder(bidderB_2, reputationB_2, auctionHouse.getAuctions().get(1));
        auctionHouse.addBidder(bidderC_2, reputationC_2, auctionHouse.getAuctions().get(1));

        Auction auctionForWatch = new Auction(painting, bidders2);
        auctionHouse.addAuction(auctionForWatch); // auction[1] - item: watch-rolex
        double maxBidAmount = 1000;
        auctionForWatch.placeCommissionBid(maxBidAmount, bidderC);

        if (!bidders1.isEmpty()){
            //auctionHouse.simulateLiveBidding(bidders, 500, item);
            auctionHouse.commissionBiddingSimulation(bidders2, auctionForWatch);
        } else {
            System.out.println("No bidders available for live bidding.");
        }


        // Use Case 3: Reputation System
    }
}

