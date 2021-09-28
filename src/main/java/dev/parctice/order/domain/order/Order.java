package dev.parctice.order.domain.order;

import com.google.common.collect.Lists;
import dev.parctice.order.common.exception.IllegalStatusException;
import dev.parctice.order.common.exception.InvalidParamException;
import dev.parctice.order.common.util.TokenGenerator;
import dev.parctice.order.domain.AbstractEntity;
import dev.parctice.order.domain.order.fragment.DeliveryFragment;
import dev.parctice.order.domain.order.item.OrderItem;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * 주문 정보 Entity
 */
@Slf4j
@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "orders")
public class Order extends AbstractEntity {

    private static final String ORDER_PREFIX = "ord_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;         // 주문 고유번호
    private String orderToken; // 주문 토큰
    private Long   userId;     // 사용자 아이디
    private String payMethod;  // 결제방법

    // 주문상품 리스트
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItemList = Lists.newArrayList();

    // 주문 시 배송정보
    @Embedded
    private DeliveryFragment deliveryFragment;

    // 주문일시
    private ZonedDateTime orderedAt;

    // 주문.배송 상태
    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        INIT("주문시작"),
        ORDER_COMPLETE("주문완료"),
        DELIVERY_PREPARE("배송준비"),
        IN_DELIVERY("배송중"),
        DELIVERY_COMPLETE("배송완료");

        private final String description;
    }

    @Builder
    public Order(
            Long userId,
            String payMethod,
            DeliveryFragment deliveryFragment
    ) {
        if (userId           == null) throw new InvalidParamException("Order.userId");
        if (deliveryFragment == null) throw new InvalidParamException("Order.deliveryFragment");
        if (StringUtils.isEmpty(payMethod)) throw new InvalidParamException("Order.payMethod");

        this.orderToken       = TokenGenerator.randomCharacterWithPrefix(ORDER_PREFIX);
        this.deliveryFragment = deliveryFragment;
        this.userId           = userId;
        this.payMethod        = payMethod;
        this.orderedAt        = ZonedDateTime.now();
        this.status           = Status.INIT;
    }

    /**
     * 주문 가격 = 주문 상품의 총 가격
     * 주문 하나의 상품의 가격 = (상품 가격 + 상품 옵션 가격) * 주문 갯수
     */
    public Long calculateTotalAmount() {
        return orderItemList.stream()
                .mapToLong(OrderItem::calculateTotalAmount)
                .sum();
    }

    /**
     *  주문 완료 상태변경 (INIT 인 경우 예외)
     */
    public void orderComplete() {
        if (this.status != Status.INIT) throw new IllegalStatusException();
        this.status = Status.ORDER_COMPLETE;
    }

    /**
     * 주문 상태 [배송준비] 변경
     */
    public void deliveryPrepare() {
        if (this.status != Status.ORDER_COMPLETE) throw new IllegalStatusException();
        this.status = Status.DELIVERY_PREPARE;
        this.getOrderItemList().forEach(OrderItem::deliveryPrepare);
    }

    /**
     * 주문 상태 [배송중] 변경
     */
    public void inDelivery() {
        if (this.status != Status.DELIVERY_PREPARE) throw new IllegalStatusException();
        this.status = Status.IN_DELIVERY;
        this.getOrderItemList().forEach(OrderItem::inDelivery);
    }

    /**
     * 주문 상태 [배송완료] 변경
     */
    public void deliveryComplete() {
        if (this.status != Status.IN_DELIVERY) throw new IllegalStatusException();
        this.status = Status.DELIVERY_COMPLETE;
        this.getOrderItemList().forEach(OrderItem::deliveryComplete);
    }

    /**
     * 주문 상태 변경 준비 확인
     * @return
     */
    public boolean isAlreadyPaymentComplete() {
        return this.status != Status.INIT;
    }

    public void updateDeliveryFragment(
            String receiverName,
            String receiverPhone,
            String receiverZipcode,
            String receiverAddress1,
            String receiverAddress2,
            String etcMessage
    ) {
        this.deliveryFragment = DeliveryFragment.builder()
                .receiverName(receiverName)
                .receiverPhone(receiverPhone)
                .receiverZipcode(receiverZipcode)
                .receiverAddress1(receiverAddress1)
                .receiverAddress2(receiverAddress2)
                .etcMessage(etcMessage)
                .build();
    }

}
