package dev.parctice.order.infrastructure.order.payment;

import dev.parctice.order.domain.order.OrderCommand;
import dev.parctice.order.domain.order.payment.PayMethod;

public class PgCardApiCaller implements PaymentApiCaller{
    @Override
    public boolean support(PayMethod payMethod) {
        return PayMethod.CARD == payMethod;
    }

    @Override
    public void pay(OrderCommand.PaymentRequest request) {
        // TODO - 구현
    }
}
