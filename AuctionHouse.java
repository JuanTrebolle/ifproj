import java.util.ArrayList;

class AuctionHouse {
    private String houseName;
    private ArrayList<Bidder> customers; // Not sure if the type is "Bidder" or if there should be a "Customer" class
    private ArrayList<Item> items;

    AuctionHouse(String houseName, ArrayList<Bidder> customers, ArrayList<Item> items){
        this.houseName = houseName;
        this.customers = customers;
        this.items = items;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public ArrayList<Bidder> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Bidder> customers) {
        this.customers = customers;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
