package com.mvoleg.coffeespringbootapp.service;

import com.mvoleg.coffeespringbootapp.api.dto.order.UserOrderDTO;
import com.mvoleg.coffeespringbootapp.model.entity.OrderEntity;

import java.util.List;

public interface OrderService {

    List<UserOrderDTO> findAllOrders();

    OrderEntity saveNewOrder(UserOrderDTO userOrderDTO);
}
