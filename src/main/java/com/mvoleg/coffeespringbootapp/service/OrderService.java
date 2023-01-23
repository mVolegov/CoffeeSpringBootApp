package com.mvoleg.coffeespringbootapp.service;

import com.mvoleg.coffeespringbootapp.dto.OrderDTO;
import com.mvoleg.coffeespringbootapp.dto.UserOrderDTO;
import com.mvoleg.coffeespringbootapp.entity.OrderEntity;

import java.util.List;

public interface OrderService {

    List<OrderEntity> findAllOrders();

    OrderEntity saveNewOrder(UserOrderDTO userOrderDTO);
}
