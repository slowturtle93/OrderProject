package dev.parctice.order.domain.order;

import dev.parctice.order.domain.order.payment.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderStore orderStore;
    private final OrderReader orderReader;
    private final OrderItemSeriesFactory orderItemSeriesFactory;
    private final PaymentProcessor paymentProcessor;
    private final OrderInfoMapper orderInfoMapper;

    /**
     * 주문 정보 저장
     *
     * @param requestOrder
     * @return
     */
    @Override
    @Transactional
    public String registerOrder(OrderCommand.RegisterOrder requestOrder) {
        Order order = orderStore.store(requestOrder.toEntity()); // 주문 정보 저장
        orderItemSeriesFactory.store(order, requestOrder);       // 주문 정보 하위 객체 저장
        return order.getOrderToken();
    }

    /**
     * 주문 결제 요청
     *
     * @param paymentRequest
     */
    @Override
    @Transactional
    public void paymentOrder(OrderCommand.PaymentRequest paymentRequest) {
        var orderToken = paymentRequest.getOrderToken(); // 주문 Token 정보 get
        var order = orderReader.getOrder(orderToken);    // 주문 정보 조회
        paymentProcessor.pay(order, paymentRequest);            // 주문 결제 요청
        order.orderComplete();                                  // 주문 완료
    }

    /**
     * 주문 정보 조회
     *
     * @param orderToken
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public OrderInfo.Main retrieveOrder(String orderToken) {
        var order = orderReader.getOrder(orderToken);         // 주문 정보 조회
        var orderItemList = order.getOrderItemList(); //  주문 상품 리스트 조회
        return orderInfoMapper.of(order, orderItemList);
    }
}
