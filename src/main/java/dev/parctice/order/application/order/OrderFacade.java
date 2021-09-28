package dev.parctice.order.application.order;

import dev.parctice.order.domain.notification.NotificationService;
import dev.parctice.order.domain.order.OrderCommand;
import dev.parctice.order.domain.order.OrderInfo;
import dev.parctice.order.domain.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 비즈니스 결정을 내리진 않지만 수행할 작업을 정의
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final NotificationService notificationService;

    /**
     * 주문 등록
     *
     * @param registerOrder
     * @return
     */
    public String registerOrder(OrderCommand.RegisterOrder registerOrder){
        var orderToken = orderService.registerOrder(registerOrder);            // 주문 정보 등록
        notificationService.sendKakao("ORDER_COMPLETE", "content"); // 주문 완료 고지
        return orderToken;
    }

    /**
     * 주문 조회
     *
     * @param orderToken
     * @return
     */
    public OrderInfo.Main retrieveOrder(String orderToken){
        return orderService.retrieveOrder(orderToken);
    }

    /**
     * 주문 결제 요청
     *
     * @param paymentRequest
     */
    public void paymentOrder(OrderCommand.PaymentRequest paymentRequest){
        orderService.paymentOrder(paymentRequest);                             // 주문 결제 요청
        notificationService.sendKakao("PAYMENT", "content"); // 주문 결제 고지
    }

    /**
     * 선물하기 수락 시 수령인 배송지 정보 업데이트
     *
     * @param orderToken
     * @param orderCommand
     */
    public void updateReceiverInfo(String orderToken, OrderCommand.UpdateReceiverInfoRequest orderCommand) {
        orderService.updateReceiverInfo(orderToken, orderCommand);
        notificationService.sendKakao(null, null);
    }

}
