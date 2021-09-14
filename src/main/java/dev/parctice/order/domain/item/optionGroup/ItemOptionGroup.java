package dev.parctice.order.domain.item.optionGroup;

import com.google.common.collect.Lists;
import dev.parctice.order.common.exception.InvalidParamException;
import dev.parctice.order.domain.item.Item;
import dev.parctice.order.domain.item.option.ItemOption;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;

/**
 * 아이템 옵션그룹 Entity를 선언하여 관리하는 클래스 파일
 */
@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "item_option_groups")
public class ItemOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ItemOptionGroup : Item = N : 1
     *
     * @JoinColumn
     * 조인컬럼은 외래키를 매핑할 때 사용한다. name 속성에는 매핑할 외래키 이름을 지정한다.
     */
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item    item;
    private Integer ordering;
    private String  itemOptionGroupName;

    /**
     *
     * fetch = FetchType.LAZY
     * 지연로딩, 참조 객체들의 데이터들은 무시하고 해당 엔티티의 데이터만 조회
     *
     * mappedBy = "item"
     * 어떤 객체가 연관관계의 주인인지 지칭
     *
     * cascade = CascadeType.PERSIST
     * 하위 엔티티까지 영속성 전달
     *
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemOptionGroup", cascade = CascadeType.PERSIST)
    private List<ItemOption> itemOptionList = Lists.newArrayList();

    @Builder
    public ItemOptionGroup(Item item, Integer ordering, String itemOptionGroupName) {
        if (item == null)     throw new InvalidParamException("ItemOptionGroup.item");
        if (ordering == null) throw new InvalidParamException("ItemOptionGroup.ordering");
        if (StringUtils.isBlank(itemOptionGroupName))
            throw new InvalidParamException("ItemOptionGroup.itemOptionGroupName");

        this.item     = item;
        this.ordering = ordering;
        this.itemOptionGroupName = itemOptionGroupName;
    }



}
