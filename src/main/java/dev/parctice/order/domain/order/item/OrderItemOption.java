package dev.parctice.order.domain.order.item;

import dev.parctice.order.common.exception.InvalidParamException;
import dev.parctice.order.domain.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

/**
 * 주문 상품의 Option Entity
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "order_item_options")
public class OrderItemOption extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 주문 고유번호

    @ManyToOne
    @JoinColumn(name = "order_item_option_group_id")
    private OrderItemOptionGroup orderItemOptionGroup; // 주문상품의 OptionGroup 정보
    private Integer ordering;        // 옵션 순서
    private String  itemOptionName;  // 상품 옵션명
    private Long    itemOptionPrice; // 상품 옵션 가격

    @Builder
    public OrderItemOption(
            OrderItemOptionGroup orderItemOptionGroup,
            Integer ordering,
            String itemOptionName,
            Long itemOptionPrice) {
        if (orderItemOptionGroup == null) throw new InvalidParamException();
        if (ordering             == null) throw new InvalidParamException();
        if (itemOptionPrice      == null) throw new InvalidParamException();
        if (StringUtils.isEmpty(itemOptionName)) throw new InvalidParamException();

        this.orderItemOptionGroup = orderItemOptionGroup;
        this.ordering        = ordering;
        this.itemOptionName  = itemOptionName;
        this.itemOptionPrice = itemOptionPrice;
    }
}
