package dev.parctice.order.infrastructure.order.payment;

import dev.parctice.order.domain.order.OrderCommand;
import dev.parctice.order.domain.order.payment.PayMethod;

public class TossPayApiCaller implements PaymentApiCaller{
    @Override
    public boolean support(PayMethod payMethod) {
        return PayMethod.TOSS_PAY == payMethod;
    }

    @Override
    public void pay(OrderCommand.PaymentRequest request) {
        // TODO - 구현
    }
}
