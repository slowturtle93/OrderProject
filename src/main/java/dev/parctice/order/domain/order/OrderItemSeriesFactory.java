package dev.parctice.order.domain.order;

import dev.parctice.order.domain.order.item.OrderItem;

import java.util.List;

/**
 *
 */
public interface OrderItemSeriesFactory {
    List<OrderItem> store(Order order, OrderCommand.RegisterOrder requestOrder);
}
