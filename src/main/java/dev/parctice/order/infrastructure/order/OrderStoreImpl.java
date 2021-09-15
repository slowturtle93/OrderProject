package dev.parctice.order.infrastructure.order;

import dev.parctice.order.domain.order.Order;
import dev.parctice.order.domain.order.OrderStore;
import dev.parctice.order.domain.order.item.OrderItem;
import dev.parctice.order.domain.order.item.OrderItemOption;
import dev.parctice.order.domain.order.item.OrderItemOptionGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderStoreImpl implements OrderStore {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemOptionGroupRepository orderItemOptionGroupRepository;
    private final OrderItemOptionRepository orderItemOptionRepository;

    /**
     * 주문 정보 저장
     *
     * @param order
     * @return
     */
    @Override
    public Order store(Order order) {
        return orderRepository.save(order);
    }

    /**
     * 주문 상품 정보 저장
     *
     * @param orderItem
     * @return
     */
    @Override
    public OrderItem store(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    /**
     * 주문 상품 OptionGroup 정보 저장
     *
     * @param orderItemOptionGroup
     * @return
     */
    @Override
    public OrderItemOptionGroup store(OrderItemOptionGroup orderItemOptionGroup) {
        return orderItemOptionGroupRepository.save(orderItemOptionGroup);
    }

    /**
     * 주문 상품 Option 정보 저장
     *
     * @param orderItemOption
     * @return
     */
    @Override
    public OrderItemOption store(OrderItemOption orderItemOption) {
        return orderItemOptionRepository.save(orderItemOption);
    }
}
