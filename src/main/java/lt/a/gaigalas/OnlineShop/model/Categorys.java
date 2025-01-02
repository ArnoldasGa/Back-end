package lt.a.gaigalas.OnlineShop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categoty")
public class Categorys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String category;

    public Categorys(String category) {
        this.category = category;
    }

    public Categorys() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
