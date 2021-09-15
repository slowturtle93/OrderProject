package dev.parctice.order.domain.order;

import dev.parctice.order.domain.item.Item;
import dev.parctice.order.domain.order.fragment.DeliveryFragment;
import dev.parctice.order.domain.order.item.OrderItem;
import dev.parctice.order.domain.order.item.OrderItemOption;
import dev.parctice.order.domain.order.item.OrderItemOptionGroup;
import dev.parctice.order.domain.order.payment.PayMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * 주문정보의 Create, Update, Delete 처리를 위한 파라미터를 관리하는 클래스 파일
 */
public class OrderCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterOrder{
        private final Long   userId;           // 사용자 아이디
        private final String payMethod;        // 결제수단
        private final String receiverName;     // 수령인 이름
        private final String receiverPhone;    // 수령인 번호
        private final String receiverZipcode;  // 수령인 우편번호
        private final String receiverAddress1; // 수령인 주소1
        private final String receiverAddress2; // 수령인 주소2
        private final String etcMessage;       // 메시지
        private final List<RegisterOrderItem> orderItemList; // 주문 상품 리스트 정보

        public Order toEntity(){
            var deliveryFragment = DeliveryFragment.builder()
                    .receiverName(receiverName)
                    .receiverPhone(receiverPhone)
                    .receiverZipcode(receiverZipcode)
                    .receiverAddress1(receiverAddress1)
                    .receiverAddress2(receiverAddress2)
                    .etcMessage(etcMessage)
                    .build();

            return Order.builder()
                    .userId(userId)
                    .payMethod(payMethod)
                    .deliveryFragment(deliveryFragment)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderItem{
        private final Integer orderCount; // 주문수량
        private final String  itemToken;  // 주문상품토큰
        private final String  itemName;   // 주문상품명
        private final Long    itemPrice;  // 주문상품가격
        private final List<RegisterOrderItemOptionGroup> orderItemOptionGroupList; // 주문상품옵션그룹 리스트 정보

        public OrderItem toEntity(Order order, Item item){
            return OrderItem.builder()
                    .order(order)
                    .orderCount(orderCount)
                    .partnerId(item.getPartnerId())
                    .itemId(item.getId())
                    .itemToken(itemToken)
                    .itemName(itemName)
                    .itemPrice(itemPrice)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderItemOptionGroup{
        private final Integer ordering;            // 옵션그룹 순서
        private final String  itemOptionGroupName; // 주문상품 옵션그룹명
        private final List<RegisterOrderItemOption> orderItemOptionList; // 주문상품 옵션 리스트 정보

        public OrderItemOptionGroup toEntity(OrderItem orderItem){
            return OrderItemOptionGroup.builder()
                    .orderItem(orderItem)
                    .ordering(ordering)
                    .itemOptionGroupName(itemOptionGroupName)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderItemOption{
        private final Integer ordering;        // 주문상품 옵션 순서
        private final String  itemOptionName;  // 주문상품 옵션명
        private final Long    itemOptionPrice; // 주뭉상품 옵션가격

        public OrderItemOption toEntity(OrderItemOptionGroup orderItemOptionGroup){
            return OrderItemOption.builder()
                    .orderItemOptionGroup(orderItemOptionGroup)
                    .ordering(ordering)
                    .itemOptionName(itemOptionName)
                    .itemOptionPrice(itemOptionPrice)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class PaymentRequest{
        private final String    orderToken; // 주문토큰
        private final Long      amount;     // 결제금액
        private final PayMethod payMethod;  // 결제수단
    }
}
