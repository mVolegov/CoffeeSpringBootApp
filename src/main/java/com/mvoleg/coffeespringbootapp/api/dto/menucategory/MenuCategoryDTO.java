package com.mvoleg.coffeespringbootapp.api.dto.menucategory;

public class MenuCategoryDTO {

    private Long id;
    private String name;

    public MenuCategoryDTO() {}

    public MenuCategoryDTO(String name) {
        this.name = name;
    }

    public MenuCategoryDTO(Long id, String name) {
        this(name);
        this.id = id;
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
}
