import java.util.ArrayList;
import java.util.UUID;

class Bidder {
    private String id;
    private ArrayList<ActionHouse> houses;

    public Bidder(int id, ArrayList<AuctionHouse> houses) {
        this.id = UUID.randomUUID().toString();;
        this.houses = houses;
    }

    public String getId() {
        return id;
    }

    public ArrayList<ActionHouse> getHouses() {
        return houses;
    }

    public void setHouses(ArrayList<ActionHouse> houses) {
        this.houses = houses;
    }
}
