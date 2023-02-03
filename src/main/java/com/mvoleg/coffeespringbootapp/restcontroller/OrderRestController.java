package com.mvoleg.coffeespringbootapp.restcontroller;

import com.mvoleg.coffeespringbootapp.dto.order.UserOrderDTO;
import com.mvoleg.coffeespringbootapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderRestController {

    private final OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<UserOrderDTO>> getAllOrders() {
        List<UserOrderDTO> allOrders = orderService.findAllOrders();
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody UserOrderDTO userOrderDTO) {
        orderService.saveNewOrder(userOrderDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
