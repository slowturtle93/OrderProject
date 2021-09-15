package dev.parctice.order.domain.order.payment.validator;

import dev.parctice.order.common.exception.InvalidParamException;
import dev.parctice.order.domain.order.Order;
import dev.parctice.order.domain.order.OrderCommand;
import org.springframework.stereotype.Component;

/**
 * 주문 상품의 가격 validation
 *
 * @Order
 * List 형태로 DI 받을때 주입받는 Bean 의 우선순위가 필요할때 사용하는 어노테이션
 * value 가 작을수록 우선순위가 높아 앞쪽으로 정렬되고, value 가 클수록 우선순위가 낮아 뒷쪽으로 정렬된다.
 */
@org.springframework.core.annotation.Order(value = 1)
@Component
public class PayAmountValidator implements PaymentValidator {

    @Override
    public void validate(Order order, OrderCommand.PaymentRequest paymentRequest) {
        if (!order.calculateTotalAmount().equals(paymentRequest.getAmount()))
            throw new InvalidParamException("주문가격이 불일치합니다");
    }
}
