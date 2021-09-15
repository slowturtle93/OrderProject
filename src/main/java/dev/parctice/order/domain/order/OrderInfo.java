package dev.parctice.order.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * 주문 정보를 return 을 담당하는 클래스 파일
 * DB 에서 조회하여 가져온 Entity 를 그대로 리턴하지 않기 위한 객체
 */
public class OrderInfo {

    @Getter
    @Builder
    @ToString
    public static class Main{
        private final Long            orderId;           // 주문번호
        private final String          orderToken;        // 주문토큰
        private final Long            userId;            // 사용자 아이디
        private final String          payMethod;         // 결제수단
        private final Long            totalAmount;       // 총 결제금액
        private final DeliveryInfo    deliveryInfo;      // 배송지 정보
        private final ZonedDateTime   orderedAt;         // 주문일시
        private final String          status;            // 주문상태
        private final String          statusDescription; // 주문상태설명
        private final List<OrderItem> orderItemList;     // 주문 상품 리스트 정보
    }

    @Getter
    @Builder
    @ToString
    public static class DeliveryInfo{
        private final String receiverName;     // 수령인 이름
        private final String receiverPhone;    // 수령인 전화번호
        private final String receiverZipcode;  // 수령인 우편번호
        private final String receiverAddress1; // 수령인 주소1
        private final String receiverAddress2; // 수령인 주소2
        private final String etcMessage;       // 메시지
    }

    @Getter
    @Builder
    @ToString
    public static class OrderItem{
        private final Integer orderCount;                // 주문수량
        private final Long    partnerId;                 // 파트너 번호
        private final Long    itemId;                    // 상품 번호
        private final String  itemName;                  // 상품명
        private final Long    totalAmount;               // 총 결제금액(상품금액 + 옵션금액)
        private final Long    itemPrice;                 // 상품 금액
        private final String  deliveryStatus;            // 배송상태
        private final String  deliveryStatusDescription; // 배송상태설명
        private final List<OrderItemOptionGroup> orderItemOptionGroupList; // 주문 상품 옵션그룹 리스트 정보
    }

    @Getter
    @Builder
    @ToString
    public static class OrderItemOptionGroup{
        private final Integer ordering;            // 주문 상품 옵션그룹 순서
        private final String  itemOptionGroupName; // 주문 상품 옵션그룹명
        private final List<OrderItemOption> orderItemOptionList; // 주문 상품 옵션 리스트 정보
    }

    @Getter
    @Builder
    @ToString
    public static class OrderItemOption{
        private final Integer ordering;        // 옵션 순서
        private final String  itemOptionName;  // 옵션명
        private final Long    itemOptionPrice; // 옵션금액
    }
}
