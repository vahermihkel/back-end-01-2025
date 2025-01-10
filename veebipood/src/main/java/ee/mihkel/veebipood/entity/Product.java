package ee.mihkel.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Entity // see tekitab Hibernate abil andmebaasi automaatselt tabeli
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1,2,4,5,6,7....
    private Long id;
    private String name;
    private double price;
    private boolean active;
    private String image;
    // parempoolne tähendab kas mul on üks või List
//siin:    @OneToOne   parempoolne:Üks       Isik + KontaktAndmed       Toode + Koostisosad
//         @OneToMany    parempoolne:List
//siin:    @ManyToOne     parempoolne:Üks     Toode + Kategooria
//         @ManyToMany    parempoolne:List
    // vasakpoolne tähendab, kas ma saan seda mida ma siia panen kasutada ka kellelgi teisel
    // Isik + KontaktAndmed
    // Isik tabelis on sama palju kirjeid kui KontaktAndmed tabelis
    // Isik tabelisse KontaktAndmed "null" --> siis sellevõrra väheneb
    // Kui kustutan Isiku ja tal on KontaktAndmed, siis kustub ka KontaktAndmed
    // Siin EI KUSTU Kategooria kui kustutan Toote.
    @ManyToOne
    private Category category;
    // Kümnevõistluses: Tulemuse külge Sportlane @ManyToOne

    public Product() {
    }

    public Product(Long id, String name, double price, boolean active, String image, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.active = active;
        this.image = image;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
