package lt.a.gaigalas.OnlineShop.dtos;

public class AddCategoryDto {
    private String name;

    public AddCategoryDto() {
    }

    public AddCategoryDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
