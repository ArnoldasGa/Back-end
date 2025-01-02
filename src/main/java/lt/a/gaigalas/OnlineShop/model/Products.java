package lt.a.gaigalas.OnlineShop.model;


import jakarta.persistence.*;
import lt.a.gaigalas.OnlineShop.repository.CategoryRepository;

@Table(name = "product")
@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Categorys category;

    public Products(String name, String description, Categorys category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Products() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categorys getCategory() {
        return category;
    }

    public void setCategory(Categorys category) {
        this.category = category;
    }
}
