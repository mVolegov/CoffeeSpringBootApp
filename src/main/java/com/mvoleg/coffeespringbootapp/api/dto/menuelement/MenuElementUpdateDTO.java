package com.mvoleg.coffeespringbootapp.api.dto.menuelement;

import java.math.BigDecimal;

public class MenuElementUpdateDTO {

    private String name;
    private String description;
    private Double kcal;
    private Double proteins;
    private Double fats;
    private Double carbohydrates;
    private Double size;
    private BigDecimal price;

    public MenuElementUpdateDTO() {
    }

    public MenuElementUpdateDTO(String name,
                                String description,
                                Double kcal,
                                Double proteins,
                                Double fats,
                                Double carbohydrates,
                                Double size,
                                BigDecimal price) {
        this.name = name;
        this.description = description;
        this.kcal = kcal;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.size = size;
        this.price = price;
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
}
