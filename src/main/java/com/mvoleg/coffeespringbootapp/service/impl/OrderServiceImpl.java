package com.mvoleg.coffeespringbootapp.service.impl;

import com.mvoleg.coffeespringbootapp.dto.UserOrderDTO;
import com.mvoleg.coffeespringbootapp.entity.MenuElementEntity;
import com.mvoleg.coffeespringbootapp.entity.OrderCompositionEntity;
import com.mvoleg.coffeespringbootapp.entity.OrderEntity;
import com.mvoleg.coffeespringbootapp.entity.UserEntity;
import com.mvoleg.coffeespringbootapp.exception.MenuElementNotFoundException;
import com.mvoleg.coffeespringbootapp.exception.UserNotFoundException;
import com.mvoleg.coffeespringbootapp.repository.MenuElementRepository;
import com.mvoleg.coffeespringbootapp.repository.OrderCompositionRepository;
import com.mvoleg.coffeespringbootapp.repository.OrderRepository;
import com.mvoleg.coffeespringbootapp.repository.UserRepository;
import com.mvoleg.coffeespringbootapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MenuElementRepository menuElementRepository;
    private final OrderCompositionRepository orderCompositionRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            UserRepository userRepository,
                            MenuElementRepository menuElementRepository,
                            OrderCompositionRepository orderCompositionRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.menuElementRepository = menuElementRepository;
        this.orderCompositionRepository = orderCompositionRepository;
    }

    @Override
    public List<OrderEntity> findAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    @Override
    public OrderEntity saveNewOrder(UserOrderDTO userOrderDTO) {
        String username = userOrderDTO.getUsername();

        UserEntity userEntity = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        OrderEntity orderToSave = new OrderEntity();
        orderToSave.setUser(userEntity);
        orderToSave.setCreatedDate(LocalDateTime.now());

        Map<Long, Integer> cart = userOrderDTO.getCart();

        List<OrderCompositionEntity> menuElementsInOrder = new ArrayList<>();

        cart
                .forEach((menuElementId, amount) -> {
                    MenuElementEntity menuElement = menuElementRepository
                            .findById(menuElementId)
                            .orElseThrow(() -> new MenuElementNotFoundException(menuElementId));

                    menuElementsInOrder.add(new OrderCompositionEntity(menuElement, amount, orderToSave));
                }
        );

        orderCompositionRepository.saveAll(menuElementsInOrder);

        orderToSave.setMenuElementsInOrder(menuElementsInOrder);

        return orderRepository.save(orderToSave);
    }
}
