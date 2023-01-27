package com.mvoleg.coffeespringbootapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_has_menuelement")
public class OrderCompositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "menuelement_id", referencedColumnName = "id", nullable = false)
    private MenuElementEntity menuElement;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private OrderEntity order;

    @Column(name = "menuelement_amount", nullable = false)
    private Integer menuElementAmount;

    @Column(name = "sugar_amount")
    private Integer sugarAmount;

    @Column(name = "milk_amount")
    private Integer milkAmount;

    public OrderCompositionEntity() {
    }

    public OrderCompositionEntity(MenuElementEntity menuElement,
                                  Integer menuElementAmount,
                                  OrderEntity order,
                                  Integer sugarAmount,
                                  Integer milkAmount) {
        this.id = id;
        this.menuElement = menuElement;
        this.order = order;
        this.menuElementAmount = menuElementAmount;
        this.sugarAmount = sugarAmount;
        this.milkAmount = milkAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MenuElementEntity getMenuElement() {
        return menuElement;
    }

    public void setMenuElement(MenuElementEntity menuElement) {
        this.menuElement = menuElement;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
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

    public Integer getMenuElementAmount() {
        return menuElementAmount;
    }

    public void setMenuElementAmount(Integer menuElementAmount) {
        this.menuElementAmount = menuElementAmount;
    }
}
