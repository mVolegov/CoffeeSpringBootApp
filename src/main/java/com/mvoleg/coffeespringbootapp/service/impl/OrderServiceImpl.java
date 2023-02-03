package com.mvoleg.coffeespringbootapp.service.impl;

import com.mvoleg.coffeespringbootapp.dto.order.MenuElementInOrderDTO;
import com.mvoleg.coffeespringbootapp.dto.order.UserOrderDTO;
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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // TODO Пересчитывать цену корзины (если не совпадает - кидать исключение)
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

        orderToSave.setTotalOrderPrice(receivedTotalOrderPrice);
        orderToSave.setMenuElementsInOrder(menuElementsInOrderEntities);

        return orderRepository.save(orderToSave);
    }
}
