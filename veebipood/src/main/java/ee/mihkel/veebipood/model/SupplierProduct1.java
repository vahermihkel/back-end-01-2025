package ee.mihkel.veebipood.model;

import lombok.Data;

@Data
public class SupplierProduct1 {
    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private SupplierProduct1Rating rating;
    private double retailPrice;
}
