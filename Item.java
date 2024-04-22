class Item {
    private String name; // {⊥}
    private int startingPrice; // {⊥}

    Item(String name, int startingPrice){
        this.name = name;
        this.startingPrice = startingPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(int startingPrice) {
        this.startingPrice = startingPrice;
    }
}
