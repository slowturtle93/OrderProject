package dev.parctice.order.infrastructure.order.payment;

import dev.parctice.order.domain.order.OrderCommand;
import dev.parctice.order.domain.order.payment.PayMethod;

/**
 * 결제 수단 Caller
 */
public interface PaymentApiCaller {
    boolean support(PayMethod payMethod);
    void pay(OrderCommand.PaymentRequest request);
}
