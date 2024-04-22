import java.util.ArrayList;
import java.util.HashMap;

class AuctionHouse {
    private ArrayList<Auction> auctions;
    private HashMap<Bidder, SecurityLabel> bidderLabels;
    private HashMap<Bidder, Double> commissionBids; // Stores commission bids for each bidder

    public AuctionHouse() {
        auctions = new ArrayList<>();
        bidderLabels = new HashMap<>();
        commissionBids = new HashMap<>();
    }

    // Method to add a bidder with a security label
    public void addBidder(Bidder bidder, SecurityLabel label) {
        bidderLabels.put(bidder, label);
    }

    // Method to create a new auction
    public void createAuction(Item item, SecurityLabel label) {
        Auction auction = new Auction(item, label);
        auctions.add(auction);
    }

    // Method to place a commission bid
    public void placeCommissionBid(Bidder bidder, double amount) {
        commissionBids.put(bidder, amount);
    }


    // Information flow analysis: Check if the bid amount leaks information
    public boolean isInformationFlowSecure(Bidder bidder, double amount, SecurityLabel label) {
        SecurityLabel bidderLabel = bidderLabels.get(bidder);
        return label.compareTo(bidderLabel) <= 0; // Check if label is less sensitive or equal to bidder's label
    }
}
