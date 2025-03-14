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

    public void addBidder(Bidder bidder, Reputation reputation) {
        bidderReputations.put(bidder, reputation);
    }

    public void createAuction(Item item, ArrayList<Bidder> bidders) {
        Auction auction = new Auction(item, bidders);
        auctions.add(auction);
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

    public void placeCommissionBid(Bidder bidder, double amount, int auctionID) {
        Reputation bidderReputation = bidderReputations.get(bidder);
        if (bidderReputation.getBiddingLimit() < amount) {
            throw new IllegalArgumentException("Bidder not registered with auction house");
        }

        // Add commission bid to specific auction
        this.auctions.get(auctionID).placeCommissionBid(amount, bidder);

        for (Auction auction : auctions) {
            if (auction.getCurrentHighestBid() < amount) {
                auction.placeCommissionBid(amount, bidder);
            }
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
    public void liveBiddingSimulation(ArrayList<Bidder> bidders) {
        for (Auction auction : this.auctions) {
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
    }

    /*
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
    */

    // Method to declassify and share reputation information with another auction house
    // Method to declassify and share reputation information with another auction house
    public Reputation declassifyReputation(Bidder bidder) {
        Reputation reputation = bidderReputations.get(bidder);
        // Simulate declassification process
        System.out.println("Reputation declassified for bidder: " + bidder.getName());
        return reputation;
    }

    // Method to securely share declassified reputation information with another auction house
    public void shareReputation(Reputation reputation, AuctionHouse otherAuctionHouse) {
        // Simulate secure sharing process
        System.out.println("Reputation shared securely with " + otherAuctionHouse.auctionHouseName);
        otherAuctionHouse.receiveReputation(reputation);
    }

    // Method to receive reputation information from another auction house
    public void receiveReputation(Reputation reputation) {
        // Process received reputation information securely
        System.out.println("Reputation received securely");
        // Update bidder's reputation (not implemented in this example)
    }


    // Method to set bidding limit for a bidder based on their reputation status
    public void setBiddingLimit(Bidder bidder, double limit) {
        Reputation reputation = bidderReputations.get(bidder);
        reputation.setBiddingLimit(limit);
    }



}