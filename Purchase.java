package sample;

public class Purchase {
    private String name;
    private String type;
    private double price;

    public Purchase(String name, String type, double price) {
        this.name = name;
        if (type.contains("Food")) {
            this.type = "Food";
        } else if(type.contains("Clothes")) {
            this.type = "Clothes";
        } else if (type.contains("Entertainment")) {
            this.type = "Entertainment";
        } else {
            this.type = "Other";
        }
        //this.type = type;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public double getPrice() {
        return this.price;
    }

    public String print() {
        return this.getName() + ", " + this.getType() + ", " + this.getPrice();
    }





}
