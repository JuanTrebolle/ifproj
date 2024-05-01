package src;

import java.util.*;

class AuctionHouse {
    private String auctionHouseName; // TODO: lattice {AuctionHouse: AuctionHouse, LiveBidder, CommisionBidder}
    private String auctionHouseId; // TODO: lattice {AuctionHouse: AuctionHouse}
    private ArrayList<Auction> auctions; // TODO: lattice? {AuctionHouse: AuctionHouse}
    private Map<Bidder, Reputation> bidderReputations; // TODO: lattice?

    public AuctionHouse(String name) {
        this.auctionHouseName = name;
        this.auctionHouseId = UUID.randomUUID().toString();
        auctions = new ArrayList<>();
        bidderReputations = new HashMap<>();
    }

    public void addBidder(Bidder bidder, Reputation reputation, Auction auction) {
        bidderReputations.put(bidder, reputation);
        auction.addBidder(bidder);
    }

    public void createAuction(Item item, ArrayList<Bidder> bidders) {
        Auction auction = new Auction(item, bidders);
        auctions.add(auction);
    }

    public List<Auction> getAuctions() {
        return this.auctions;
    }

   /* public void placeCommissionBid(Bidder bidder, double amount) {
        // Store commission bid amount securely (separate from bidder object)
        double commissionBidAmount = amount;

        // Store bidder reference securely for later comparison (not bidder name)
        Bidder bidderRef = bidder;

        // Loop through auctions and process bids securely
        for (Auction auction : auctions) {
            // Compare commission bid amount with current bids
            if (processBid(auction, commissionBidAmount)) {
                // Bid accepted, no need to check further auctions
                return;
            }
        }
    }*/

    public void placeCommissionBid(Bidder bidder, double amount, Auction auction) {
        double currentHighestBid = auction.getCurrentHighestBid();

        if (amount > currentHighestBid) {
            auction.placeCommissionBid(amount, bidder);
        }
    }

    public boolean processBid(Auction auction, double commissionBidAmount) {
        // Access current highest bid amount
        int currentHighestBid = auction.getCurrentHighestBid();

        if (commissionBidAmount > currentHighestBid) {
            auction.placeCommissionBid(commissionBidAmount, null);
            return true;
        }
        return false;
    }


    /**
     *  Iterates over all auctions and for each auction, it simulates a series of bids, using random values
     */
//    public void simulateLiveBidding(ArrayList<Bidder> bidders, double amount, Item item) {
//        System.out.println("start: auction house starts the bid for item: " + item.getName() + ". The bid starts at " + amount + " Kr");
//        // Implementation of live bidding simulation
//        Random random = new Random();
//        for (Auction auction : this.auctions) {
//            for (int i = 0; i < 10; i++) {
//                // Generate a random bid amount
//                double bidAmount = auction.getCurrentHighestBid() + 50 * (random.nextDouble() + 1);
//
//                // Find a random bidder
//                Bidder bidder = bidders.get(random.nextInt(bidders.size()));
//                auction.placeLiveBid(550, bidders.get(0));
//                auction.placeLiveBid(600, bidders.get(1));
//                auction.placeLiveBid(650, bidders.get(0));
//                auction.placeLiveBid(700, bidders.get(2));
//                auction.placeLiveBid(1000, bidders.get(0));
//                auction.placeLiveBid(1250, bidders.get(2));
//
//                // Place the bid
//                if(auction.getCurrentHighestBid() < amount) {
//                    auction.placeLiveBid(bidAmount, bidder);
//                    System.out.println(bidder.getName() + " bids " + bidAmount + " Kr");
//                }
//            }
//        }
//    }

    /**
     * This is the method I am using in the Use Case 1 simulation
     */
    public void liveBiddingSimulation(ArrayList<Bidder> bidders, Auction auction) {
        // Auction starts - B bids
        auction.placeLiveBid(550, bidders.get(1));
        System.out.println("B bids: " + auction.getBidAmountByBidderId(bidders.get(1).getBidderID()) + " Kr.");
        // C bids
        auction.placeLiveBid(600, bidders.get(2));
        System.out.println("C bids " + auction.getBidAmountByBidderId(bidders.get(2).getBidderID()) + " Kr.");

        // B bids
        auction.placeLiveBid(650, bidders.get(1));
        System.out.println("B bids: " + auction.getBidAmountByBidderId(bidders.get(1).getBidderID()) +  " Kr.");

        // A bids
        auction.placeLiveBid(700, bidders.get(0));
        System.out.println("A bids " + auction.getBidAmountByBidderId(bidders.get(0).getBidderID()) + " Kr.");

        // B bids
        auction.placeLiveBid(700, bidders.get(1));
        System.out.println("B bids: " + auction.getBidAmountByBidderId(bidders.get(1).getBidderID()) + " Kr.");

        // C bids
        auction.placeLiveBid(750, bidders.get(2));
        System.out.println("C bids " + auction.getBidAmountByBidderId(bidders.get(2).getBidderID()) + " Kr.");

        // Auction ends
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("going once ... going twice ... sold"); // TODO: method that resumes everything.
    }

    public void commissionBiddingSimulation(ArrayList<Bidder> bidders, Auction auction) {
        Map<String, Bid> commissionBids = auction.getCommissionBids();

        // While there are commission bids
        while (!commissionBids.isEmpty()) {
            // Get the highest commission bid
            double highestCommissionBid = 0;
            Bid highestCommissionBidder = null;
            for (Bid bid : commissionBids.values()) {
                if (bid.getBidAmount() > highestCommissionBid) {
                    highestCommissionBid = bid.getBidAmount();
                    highestCommissionBidder = bid;
                }
            }

            // Place the highest commission bid
            auction.placeCommissionBid(highestCommissionBid, bidders.get(2));
            System.out.println("Commission bid: " + highestCommissionBid + " Kr.");
            commissionBids.remove(highestCommissionBidder.getBidderID());
        }
    }

    public void registerWithReputation(Bidder bidder, AuctionHouse otherAuctionHouse, Bidder referenceBidder) {
        Reputation reputation = otherAuctionHouse.getReputation(referenceBidder);
        bidderReputations.put(bidder, reputation);
    }

    public double getBiddingLimit(Bidder bidder) {
        Reputation reputation = bidderReputations.getOrDefault(bidder, bidder.getReputation());
        return reputation.getBiddingLimit();
    }

    public Reputation getReputation(Bidder bidder) {
        return bidderReputations.getOrDefault(bidder, bidder.getReputation());
    }

    public void addAuction(Auction auction) {

        auctions.add(auction);
    }

}