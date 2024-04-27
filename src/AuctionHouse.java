import java.util.*;
import java.util.ArrayList;

class AuctionHouse {
    private ArrayList<Auction> auctions; // TODO: lattice?
    private Map<Bidder, Reputation> bidderReputations; // TODO: lattice?

    public AuctionHouse() {
        auctions = new ArrayList<>();
        bidderReputations = new HashMap<>();
    }

    public void addBidder(Bidder bidder, Reputation reputation) {
        bidderReputations.put(bidder, reputation);
    }

    public void createAuction(Item item) {
        Auction auction = new Auction(item);
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
    public void simulateLiveBidding(ArrayList<Bidder> bidders, double amount) {
        // Implementation of live bidding simulation
        Random random = new Random();
        for (Auction auction : this.auctions) {
            for (int i = 0; i < 10; i++) {
                // Generate a random bid amount
                double bidAmount = auction.getCurrentHighestBid() + 50 * (random.nextDouble(10) + 1);
                
                // Find a random bidder
                Bidder bidder = bidders.get(random.nextInt(bidders.size()));

                // Place the bid
                if(auction.getCurrentHighestBid() < amount) {
                    auction.placeCommissionBid(bidAmount, bidder);
                }
            }
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

}