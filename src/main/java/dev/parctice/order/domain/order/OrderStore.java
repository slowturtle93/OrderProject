package dev.parctice.order.domain.order;

import dev.parctice.order.domain.order.item.OrderItem;
import dev.parctice.order.domain.order.item.OrderItemOption;
import dev.parctice.order.domain.order.item.OrderItemOptionGroup;

/**
 * 주문 정보 저장을 담당하는 interface
 */
public interface OrderStore {
    Order store(Order order);
    OrderItem store(OrderItem orderItem);
    OrderItemOptionGroup store(OrderItemOptionGroup orderItemOptionGroup);
    OrderItemOption store(OrderItemOption orderItemOption);
}
