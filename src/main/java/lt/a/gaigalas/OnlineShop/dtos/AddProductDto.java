package lt.a.gaigalas.OnlineShop.dtos;

public class AddProductDto {
    public String name;
    public String description;
    public int category_id;

    public AddProductDto(String name, String description, int category_id) {
        this.name = name;
        this.description = description;
        this.category_id = category_id;
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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
