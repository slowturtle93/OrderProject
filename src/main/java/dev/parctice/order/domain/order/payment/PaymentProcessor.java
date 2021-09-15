package dev.parctice.order.domain.order.payment;

import dev.parctice.order.domain.order.Order;
import dev.parctice.order.domain.order.OrderCommand;

/**
 * 결제 프로세스 interface
 */
public interface PaymentProcessor {
    void pay(Order order, OrderCommand.PaymentRequest paymentRequest);
}
