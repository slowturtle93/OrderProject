package dev.parctice.order.domain.order.item;

import com.google.common.collect.Lists;
import dev.parctice.order.common.exception.IllegalStatusException;
import dev.parctice.order.common.exception.InvalidParamException;
import dev.parctice.order.domain.AbstractEntity;
import dev.parctice.order.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;

/**
 * 주문 상품 Entity
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // 주문 고유번호

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;        // 주문 정보

    private Integer orderCount; // 주문 수량
    private Long    partnerId;  // 파트너 고유번호
    private Long    itemId;     // 상품 고유번호
    private String  itemName;   // 상품명
    private String  itemToken;  // 상품Token
    private Long    itemPrice;  // 상품 가격

    // 주문상품의 옵션그룹 리스트
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderItem", cascade = CascadeType.PERSIST)
    private List<OrderItemOptionGroup> orderItemOptionGroupList = Lists.newArrayList();

    // 주문배송상태
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Getter
    @AllArgsConstructor
    public enum DeliveryStatus{
        BEFORE_DELIVERY("배송전"),
        DELIVERY_PREPARE("배송준비중"),
        DELIVERING("배송중"),
        COMPLETE_DELIVERY("배송완료");

        private final String description;
    }

    @Builder
    public OrderItem(
            Order   order,
            Integer orderCount,
            Long    partnerId,
            Long    itemId,
            String  itemName,
            String  itemToken,
            Long    itemPrice
    ){
        if (order      == null)             throw new InvalidParamException("OrderItemLine.order");
        if (orderCount == null)             throw new InvalidParamException("OrderItemLine.orderCount");
        if (partnerId  == null)             throw new InvalidParamException("OrderItemLine.partnerId");
        if (itemPrice  == null)             throw new InvalidParamException("OrderItemLine.itemPrice");
        if (StringUtils.isEmpty(itemToken)) throw new InvalidParamException("OrderItemLine.itemToken");
        if (itemId == null && StringUtils.isEmpty(itemName))
            throw new InvalidParamException("OrderItemLine.itemNo and itemName");


        this.order      = order;
        this.orderCount = orderCount;
        this.partnerId  = partnerId;
        this.itemId     = itemId;
        this.itemName   = itemName;
        this.itemToken  = itemToken;
        this.itemPrice  = itemPrice;
        this.deliveryStatus = DeliveryStatus.BEFORE_DELIVERY;
    }

    // 주문 상품 가격 계산(상품의 옵션 가격 포함)
    // (상품 가격 + 상품 옵션 가격) * 주문수량
    public Long calculateTotalAmount(){
        var itemOptionTotalAmount = orderItemOptionGroupList.stream()
                .mapToLong(OrderItemOptionGroup::calculateTotalAmount)
                .sum();
        return (itemPrice + itemOptionTotalAmount) * orderCount;
    }

    public void deliveryPrepare() {
        if (this.deliveryStatus != DeliveryStatus.BEFORE_DELIVERY) throw new IllegalStatusException();
        this.deliveryStatus = DeliveryStatus.DELIVERY_PREPARE;
    }

    public void inDelivery() {
        if (this.deliveryStatus != DeliveryStatus.DELIVERY_PREPARE) throw new IllegalStatusException();
        this.deliveryStatus = DeliveryStatus.DELIVERING;
    }

    public void deliveryComplete() {
        if (this.deliveryStatus != DeliveryStatus.DELIVERING) throw new IllegalStatusException();
        this.deliveryStatus = DeliveryStatus.COMPLETE_DELIVERY;
    }
}
