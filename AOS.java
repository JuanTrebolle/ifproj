import java.util.ArrayList;

public class AOS {
    Item vase = new Item("vase", 300);
    ArrayList<Bidder> bidders = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();
    AuctionHouse houseA = new AuctionHouse("A", bidders, items);




    public static void main(String[] args){

        System.out.println("Hello, World!");

    }
}

