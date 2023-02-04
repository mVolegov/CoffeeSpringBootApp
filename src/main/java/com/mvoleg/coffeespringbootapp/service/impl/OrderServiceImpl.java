package com.mvoleg.coffeespringbootapp.service.impl;

import com.mvoleg.coffeespringbootapp.api.dto.order.MenuElementInOrderDTO;
import com.mvoleg.coffeespringbootapp.api.dto.order.UserOrderDTO;
import com.mvoleg.coffeespringbootapp.persistence.model.MenuElementEntity;
import com.mvoleg.coffeespringbootapp.persistence.model.OrderCompositionEntity;
import com.mvoleg.coffeespringbootapp.persistence.model.OrderEntity;
import com.mvoleg.coffeespringbootapp.persistence.model.UserEntity;
import com.mvoleg.coffeespringbootapp.exception.MenuElementNotFoundException;
import com.mvoleg.coffeespringbootapp.exception.TotalPriceOfCartDoesNotMatchException;
import com.mvoleg.coffeespringbootapp.exception.UserNotFoundException;
import com.mvoleg.coffeespringbootapp.persistence.dao.MenuElementRepository;
import com.mvoleg.coffeespringbootapp.persistence.dao.OrderCompositionRepository;
import com.mvoleg.coffeespringbootapp.persistence.dao.OrderRepository;
import com.mvoleg.coffeespringbootapp.persistence.dao.UserRepository;
import com.mvoleg.coffeespringbootapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public List<UserOrderDTO> findAllOrders() {
        List<UserOrderDTO> userOrderDTOs = new ArrayList<>();

        List<OrderEntity> allOrderEntities = orderRepository.findAll();
        for (OrderEntity orderEntity : allOrderEntities) {
            UserEntity user = orderEntity.getUser();
            List<OrderCompositionEntity> menuElementsInOrder = orderEntity.getMenuElementsInOrder();

            List<MenuElementInOrderDTO> menuElementInOrderDTOs = new ArrayList<>();

            for (OrderCompositionEntity menuElementInOrder : menuElementsInOrder) {
                Long menuElementId = menuElementInOrder.getMenuElement().getId();
                Integer menuElementAmount = menuElementInOrder.getMenuElementAmount();
                Integer sugarAmount = menuElementInOrder.getSugarAmount();
                Integer milkAmount = menuElementInOrder.getMilkAmount();

                menuElementInOrderDTOs.add(new MenuElementInOrderDTO(
                        menuElementId,
                        menuElementAmount,
                        sugarAmount,
                        milkAmount
                    )
                );
            }

            userOrderDTOs.add(new UserOrderDTO(
                    user.getUsername(),
                    menuElementInOrderDTOs,
                    orderEntity.getTotalOrderPrice()
                )
            );
        }

        return userOrderDTOs;
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

        List<MenuElementInOrderDTO> menuElementsInOrderDTOs = userOrderDTO.getMenuElementsInOrder();

        List<OrderCompositionEntity> menuElementsInOrderEntities = new ArrayList<>();

        for (MenuElementInOrderDTO menuElementInOrderDTO : menuElementsInOrderDTOs) {
            Long id = menuElementInOrderDTO.getId();
            Integer amount = menuElementInOrderDTO.getAmount();
            Integer sugarAmount = menuElementInOrderDTO.getSugarAmount();
            Integer milkAmount = menuElementInOrderDTO.getMilkAmount();

            MenuElementEntity menuElementEntity = menuElementRepository
                    .findById(id)
                    .orElseThrow(() -> new MenuElementNotFoundException(id));

            menuElementsInOrderEntities.add(new OrderCompositionEntity(
                    menuElementEntity,
                    amount,
                    orderToSave,
                    sugarAmount,
                    milkAmount
                )
            );
        }

        BigDecimal receivedTotalOrderPrice = userOrderDTO.getTotalOrderPrice();
        BigDecimal expectedTotalOrderPrice = BigDecimal.ZERO;
        for (OrderCompositionEntity orderCompositionEntity : menuElementsInOrderEntities) {
            BigDecimal menuElementPrice = orderCompositionEntity.getMenuElement().getPrice();
            BigDecimal menuElementAmount = BigDecimal.valueOf(orderCompositionEntity.getMenuElementAmount());

            expectedTotalOrderPrice = expectedTotalOrderPrice.add(menuElementPrice.multiply(menuElementAmount));
        }

        if (receivedTotalOrderPrice.compareTo(expectedTotalOrderPrice) != 0) {
            throw new TotalPriceOfCartDoesNotMatchException(
                    expectedTotalOrderPrice.doubleValue(),
                    receivedTotalOrderPrice.doubleValue()
            );
        }

        orderToSave.setTotalOrderPrice(receivedTotalOrderPrice);
        orderToSave.setMenuElementsInOrder(menuElementsInOrderEntities);

        return orderRepository.save(orderToSave);
    }
}
