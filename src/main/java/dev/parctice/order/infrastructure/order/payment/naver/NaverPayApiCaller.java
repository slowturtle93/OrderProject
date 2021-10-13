package dev.parctice.order.infrastructure.order.payment.naver;

import dev.parctice.order.domain.order.OrderCommand;
import dev.parctice.order.domain.order.payment.PayMethod;
import dev.parctice.order.infrastructure.order.payment.PaymentApiCaller;

public class NaverPayApiCaller implements PaymentApiCaller {

    @Override
    public boolean support(PayMethod payMethod) {
        return PayMethod.NAVER_PAY == payMethod;
    }

    @Override
    public void pay(OrderCommand.PaymentRequest request) {
        // TODO - 구현
    }
}
