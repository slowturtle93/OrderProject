package dev.parctice.order.domain.item.option;


import dev.parctice.order.common.exception.InvalidParamException;
import dev.parctice.order.domain.AbstractEntity;
import dev.parctice.order.domain.item.optionGroup.ItemOptionGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

/**
 * 아이템 옵션 Entity를 선언하여 관리하는 클래스 파일
 */
@Slf4j
@Getter
@NoArgsConstructor
@Table(name = "item_options")
public class ItemOption extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ItemOption : ItemOptionGroup : N : 1
     *
     * @JoinColumn
     * 조인컬럼은 외래키를 매핑할 때 사용한다. name 속성에는 매핑할 외래키 이름을 지정한다.
     */
    @ManyToOne
    @JoinColumn(name = "item_option_group_id")
    private ItemOptionGroup itemOptionGroup;
    private Integer ordering;
    private String  itemOptionName;
    private Long    itemOptionPrice;

    @Builder
    public ItemOption(
            ItemOptionGroup itemOptionGroup,
            Integer ordering,
            String itemOptionName,
            Long itemOptionPrice
    ){
        if (itemOptionGroup == null)             throw new InvalidParamException("ItemOption.itemOptionGroup");
        if (ordering == null)                    throw new InvalidParamException("ItemOption.ordering");
        if (StringUtils.isBlank(itemOptionName)) throw new InvalidParamException("ItemOption.itemOptionName");
        if (itemOptionPrice == null)             throw new InvalidParamException("ItemOption.itemOptionPrice");

        this.itemOptionGroup = itemOptionGroup;
        this.ordering        = ordering;
        this.itemOptionName  = itemOptionName;
        this.itemOptionPrice = itemOptionPrice;
    }
}
