package dev.parctice.order.domain.order.item;

import com.google.common.collect.Lists;
import dev.parctice.order.common.exception.InvalidParamException;
import dev.parctice.order.domain.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;

/**
 * 주문 상품의 OptionGroup Entity
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "order_item_option_groups")
public class OrderItemOptionGroup extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 주문 고유번호

    @ManyToOne
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem; // 주문 상품
    private Integer   ordering;  // 옵션그룹 순서
    private String    itemOptionGroupName; // OptionGroup 명

    // 주문 상품의 옵션 리스트
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderItemOptionGroup", cascade = CascadeType.PERSIST)
    private List<OrderItemOption> orderItemOptionList = Lists.newArrayList();

    @Builder
    public OrderItemOptionGroup(
            OrderItem orderItem,
            Integer ordering,
            String itemOptionGroupName
    ) {
        if (orderItem == null) throw new InvalidParamException();
        if (ordering  == null) throw new InvalidParamException();
        if (StringUtils.isEmpty(itemOptionGroupName)) throw new InvalidParamException();

        this.orderItem = orderItem;
        this.ordering  = ordering;
        this.itemOptionGroupName = itemOptionGroupName;
    }

    // 주문 상품 옵션의 총 가격 계산
    public Long calculateTotalAmount() {
        return orderItemOptionList.stream()
                .mapToLong(OrderItemOption::getItemOptionPrice)
                .sum();
    }
}
