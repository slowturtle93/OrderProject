package dev.parctice.order.domain.order.gift;

import dev.parctice.order.common.exception.IllegalStatusException;
import dev.parctice.order.domain.order.Order;
import dev.parctice.order.domain.order.OrderCommand;
import dev.parctice.order.domain.order.OrderReader;
import dev.parctice.order.domain.order.payment.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiftOrderServiceImpl implements GiftOrderService{

    private final OrderReader orderReader;
    private final PaymentProcessor paymentProcessor;
    private final GiftMessageChannelSender giftMessageChannelSender;

    /**
     * 선물하기 주문
     *
     * @param paymentRequest
     */
    @Override
    @Transactional
    public void paymentOrder(OrderCommand.PaymentRequest paymentRequest) {
        log.info("GiftOrderService.paymentOrder request = {}", paymentRequest);
        var order = orderReader.getOrder(paymentRequest.getOrderToken());

        if(order.getStatus() != Order.Status.INIT) throw new IllegalStatusException();

        paymentProcessor.pay(order, paymentRequest);
        order.orderComplete();

        giftMessageChannelSender.paymentComplete(new GiftPaymentCompleteMessage(order.getOrderToken()));
    }
}
