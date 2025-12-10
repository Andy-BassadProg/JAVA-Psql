import java.time.LocalDate;
import java.time.LocalDateTime;

public class Product {
    private int id;
    private String name;
    private LocalDateTime creationDateTime;
    private double price;
    Category category;

    public Product(int id, String name, LocalDateTime date, double price, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.creationDateTime =  creationDateTime;
        this.price = price;
    }
}
