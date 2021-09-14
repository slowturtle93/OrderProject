package dev.parctice.order.domain.item;

import antlr.Token;
import com.google.common.collect.Lists;
import dev.parctice.order.common.exception.InvalidParamException;
import dev.parctice.order.common.util.TokenGenerator;
import dev.parctice.order.domain.AbstractEntity;
import dev.parctice.order.domain.item.optionGroup.ItemOptionGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;

/**
 * Item Entity를 선언하여 관리하는 클래스 파일
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "items")
public class Item extends AbstractEntity {
    private static final String PREFIX_ITEM = "itm_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String itemToken;
    private Long   partnerId;
    private String itemName;
    private Long   itemPrice;

    /**
     * Item : ItemOptionGroup = 1:N
     *
     * fetch = FetchType.LAZY
     * 지연로딩, 참조 객체들의 데이터들은 무시하고 해당 엔티티의 데이터만 조회
     * fetch = FetchType.EAGER
     * 하나의 객체를 DB로부터 읽어올 때 참조 객체들의 데이터까지 조회
     *
     * mappedBy = "item"
     * 어떤 객체가 연관관계의 주인인지 지칭
     *
     * cascade = CascadeType.PERSIST
     * 하위 엔티티까지 영속성 전달
     *
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.PERSIST)
    private List<ItemOptionGroup> itemOptionGroupList = Lists.newArrayList();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        PREPARE("판매준비중"),
        ON_SALES("판매중"),
        END_OF_SALES("판매종료");

        private final  String description;
    }

    @Builder
    public Item(Long partnerId, String itemName, Long itemPrice){
        if(partnerId == null) throw new InvalidParamException("item.partnerId");
        if(StringUtils.isEmpty(itemName)) throw new InvalidParamException("item.itemName");
        if(itemPrice == null) throw new InvalidParamException("item.itemPrice");

        this.itemToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_ITEM);
        this.partnerId = partnerId;
        this.itemName  = itemName;
        this.itemPrice = itemPrice;
        this.status    = Status.PREPARE;
    }

    // Item 상태변경(판매준비중)
    public void changePrepare(){
        this.status = Status.PREPARE;
    }

    // Item 상태변경(판매중)
    public void changeOnSales(){
        this.status = Status.ON_SALES;
    }

    // Item 상태병견(판매종료)
    public void changeEndOfSales(){
        this.status = Status.END_OF_SALES;
    }
}

