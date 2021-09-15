package dev.parctice.order.domain.order;

/**
 * 주문 조회를 담당하는 interface
 */
public interface OrderReader {
    Order getOrder(String orderToken);
}
