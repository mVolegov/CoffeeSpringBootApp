package com.mvoleg.coffeespringbootapp.api.dto.order;

import java.math.BigDecimal;
import java.util.List;

public class UserOrderDTO {

    private String username;
    private List<MenuElementInOrderDTO> menuElementsInOrder;
    private BigDecimal totalOrderPrice;

    public UserOrderDTO() {
    }

    public UserOrderDTO(String username,
                        List<MenuElementInOrderDTO> menuElementsInOrder,
                        BigDecimal totalOrderPrice) {
        this.username = username;
        this.menuElementsInOrder = menuElementsInOrder;
        this.totalOrderPrice = totalOrderPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<MenuElementInOrderDTO> getMenuElementsInOrder() {
        return menuElementsInOrder;
    }

    public void setMenuElementsInOrder(List<MenuElementInOrderDTO> menuElementsInOrder) {
        this.menuElementsInOrder = menuElementsInOrder;
    }

    public BigDecimal getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(BigDecimal totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }
}
