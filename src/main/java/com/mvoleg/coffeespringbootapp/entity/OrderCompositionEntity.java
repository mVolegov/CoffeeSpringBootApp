package com.mvoleg.coffeespringbootapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_has_menuelement")
public class OrderCompositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menuelement_id", referencedColumnName = "id")
    private MenuElementEntity menuElement;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity order;

    @Column(name = "menuelement_amount")
    private Integer menuElementAmount;

    public OrderCompositionEntity() {
    }

    public OrderCompositionEntity(MenuElementEntity menuElement,
                                  Integer menuElementAmount,
                                  OrderEntity order) {
        this.id = id;
        this.menuElement = menuElement;
        this.order = order;
        this.menuElementAmount = menuElementAmount;
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

    public Integer getMenuElementAmount() {
        return menuElementAmount;
    }

    public void setMenuElementAmount(Integer menuElementAmount) {
        this.menuElementAmount = menuElementAmount;
    }
}
