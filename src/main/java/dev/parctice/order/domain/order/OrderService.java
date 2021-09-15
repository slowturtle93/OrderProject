package dev.parctice.order.domain.order;

/**
 * 주문 도메인의 요구사항을 관리하는 interface
 */
public interface OrderService {

    String registerOrder(OrderCommand.RegisterOrder registerOrder);

    void paymentOrder(OrderCommand.PaymentRequest paymentRequest);

    OrderInfo.Main retrieveOrder(String orderToken);
}
