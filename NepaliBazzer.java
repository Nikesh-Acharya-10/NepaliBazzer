import java.time.LocalDate;
import java.util.*;
 class NepaliProduct {

    public int productId;
    public String name;
    public double price;
    public String manufacturerLocation;

    // Constructor
    public NepaliProduct(int productId, String name, double price, String manufacturerLocation) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.manufacturerLocation = manufacturerLocation;
    }

    // Display product information
    public void displayInfo() {
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Price: Rs. " + price);
        System.out.println("Made in: " + manufacturerLocation);
    }

    // Calculate total price (default: no VAT)
    public double calculateTotalPrice() {
        return price;
    }
}
 class HandicraftProduct extends NepaliProduct {

     String artisanName;
     String craftType;
     boolean culturalSignificance;

    // Constructor
    public HandicraftProduct(int productId, String name, double price,
                             String manufacturerLocation, String artisanName,
                             String craftType, boolean culturalSignificance) {

        super(productId, name, price, manufacturerLocation);
        this.artisanName = artisanName;
        this.craftType = craftType;
        this.culturalSignificance = culturalSignificance;
    }

    @Override
    public double calculateTotalPrice() {
        return price;
    }

    public boolean isCulturallySignificant() {
        return culturalSignificance;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Artisan Name: " + artisanName);
        System.out.println("Craft Type: " + craftType);
        System.out.println("Culturally Significant: " + culturalSignificance);
    }
}

 class FoodProduct extends NepaliProduct {

    private LocalDate expiryDate;
    private String ingredients;
    private boolean isOrganic;
    private String certifiedBy;

    // Constructor
    public FoodProduct(int productId, String name, double price,
                       String manufacturerLocation, LocalDate expiryDate,
                       String ingredients, boolean isOrganic, String certifiedBy) {

        super(productId, name, price, manufacturerLocation);
        this.expiryDate = expiryDate;
        this.ingredients = ingredients;
        this.isOrganic = isOrganic;
        this.certifiedBy = certifiedBy;
    }

    @Override
    public double calculateTotalPrice() {
        return price + (price * 0.05);
    }

    public boolean isExpiringSoon() {
        return expiryDate.isBefore(LocalDate.now().plusDays(7));
    }

    public boolean isLocallySourced() {
        return manufacturerLocation.equalsIgnoreCase("Nepal");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Expiry Date: " + expiryDate);
        System.out.println("Organic: " + isOrganic);
        System.out.println("Certified By: " + certifiedBy);
    }
}


 class ShoppingCart {

     ArrayList<NepaliProduct> cart = new ArrayList<>();

    public void addProduct(NepaliProduct product) {
        cart.add(product);
        System.out.println(product.name + " added to cart.");
    }

    public void displayLocalProducts() {
        System.out.println("\nLocal Nepali Products:");
        for (NepaliProduct p : cart) {
            if (p.manufacturerLocation.equalsIgnoreCase("Nepal")) {
                p.displayInfo();
                System.out.println("Total Price: Rs. " + p.calculateTotalPrice());
                System.out.println("---------------------");
            }
        }
    }
}



public class NepaliBazzer
{
    public static void main(String[] args) {

        HandicraftProduct h1 = new HandicraftProduct(
                101, "Dhaka Topi", 1200,
                "Nepal", "Ram Bahadur",
                "Traditional Wear", true);

        FoodProduct f1 = new FoodProduct(
                201, "Gundruk", 300,
                "Nepal", LocalDate.now().plusDays(5),
                "Fermented leafy greens", true,
                "DDA Nepal");

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(h1);
        cart.addProduct(f1);

        cart.displayLocalProducts();
    }
}
