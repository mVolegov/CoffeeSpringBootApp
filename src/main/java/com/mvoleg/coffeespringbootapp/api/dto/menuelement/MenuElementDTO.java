package com.mvoleg.coffeespringbootapp.api.dto.menuelement;

import com.mvoleg.coffeespringbootapp.api.dto.menucategory.MenuCategoryDTO;

import java.math.BigDecimal;
import java.util.Set;

public class MenuElementDTO {

    private Long id;
    private String name;
    private String description;
    private Double kcal;
    private Double proteins;
    private Double fats;
    private Double carbohydrates;
    private Double size;
    private BigDecimal price;
    private Set<MenuCategoryDTO> categories;

    public MenuElementDTO() {}

    public MenuElementDTO(Long id, String name,
                          String description,
                          Double kcal,
                          Double proteins,
                          Double fats,
                          Double carbohydrates,
                          Double size,
                          BigDecimal price,
                          Set<MenuCategoryDTO> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.kcal = kcal;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.size = size;
        this.price = price;
        this.categories = categories;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getKcal() {
        return kcal;
    }

    public void setKcal(Double kcal) {
        this.kcal = kcal;
    }

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<MenuCategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<MenuCategoryDTO> categories) {
        this.categories = categories;
    }
}
