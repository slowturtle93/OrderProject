package dev.parctice.order.infrastructure.order.payment.pgcard;

import dev.parctice.order.domain.order.OrderCommand;
import dev.parctice.order.domain.order.payment.PayMethod;
import dev.parctice.order.infrastructure.order.payment.PaymentApiCaller;

public class PgCardApiCaller implements PaymentApiCaller {
    @Override
    public boolean support(PayMethod payMethod) {
        return PayMethod.CARD == payMethod;
    }

    @Override
    public void pay(OrderCommand.PaymentRequest request) {
        // TODO - 구현
    }
}
