import java.util.*;
import java.util.ArrayList;

class AuctionHouse {
    private ArrayList<Auction> auctions;
    private Map<Bidder, Reputation> bidderReputations;

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

    public void placeCommissionBid(Bidder bidder, double amount) {
        // Implementation of commission bids
    }

    public void simulateLiveBidding() {
        // Implementation of live bidding simulation
    }

    public void registerWithReputation(Bidder bidder, AuctionHouse otherAuctionHouse, Bidder referenceBidder) {
        Reputation reputation = otherAuctionHouse.getReputation(referenceBidder);
        bidderReputations.put(bidder, reputation);
    }

    public double getBiddingLimit(Bidder bidder) {
        Reputation reputation = bidderReputations.getOrDefault(bidder, Reputation.NEW);
        return reputation.getBiddingLimit();
    }

    public Reputation getReputation(Bidder bidder) {
        return bidderReputations.getOrDefault(bidder, Reputation.NEW);
    }