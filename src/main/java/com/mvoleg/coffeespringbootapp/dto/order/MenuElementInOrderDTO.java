package com.mvoleg.coffeespringbootapp.dto.order;

public class MenuElementInOrderDTO {

    private Long id;
    private Integer amount;
    private Integer sugarAmount;
    private Integer milkAmount;

    public MenuElementInOrderDTO() {
    }

    public MenuElementInOrderDTO(Long id, Integer amount, Integer sugarAmount, Integer milkAmount) {
        this.id = id;
        this.amount = amount;
        this.sugarAmount = sugarAmount;
        this.milkAmount = milkAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getSugarAmount() {
        return sugarAmount;
    }

    public void setSugarAmount(Integer sugarAmount) {
        this.sugarAmount = sugarAmount;
    }

    public Integer getMilkAmount() {
        return milkAmount;
    }

    public void setMilkAmount(Integer milkAmount) {
        this.milkAmount = milkAmount;
    }
}
