package dev.parctice.order.domain.order.payment.validator;

import dev.parctice.order.domain.order.Order;
import dev.parctice.order.domain.order.OrderCommand;

/**
 * 주문 validation interface
 */
public interface PaymentValidator {
    void validate(Order order, OrderCommand.PaymentRequest paymentRequest);
}
