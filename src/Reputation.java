public class Reputation {

    private double biddingLimit;

    public Reputation() {
        this.biddingLimit = 100.0; // only steps of 50kr are allowed
    }

    public double getBiddingLimit() {
        return this.biddingLimit;
    }

    public double increaseBiddingLimit(double amount) {
        this.biddingLimit += amount;
        return this.biddingLimit;
    }

    public void setBiddingLimit(double newBiddingLimit) {
        this.biddingLimit = newBiddingLimit;
    }
}
