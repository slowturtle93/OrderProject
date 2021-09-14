package dev.parctice.order.domain.item;

import dev.parctice.order.domain.item.option.ItemOption;
import dev.parctice.order.domain.item.optionGroup.ItemOptionGroup;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
/**
 * Item 정보 return을 담당하는 클래스 파일
 * DB에서 조회하여 가져온 Entity를 그대로 리턴하지 않기 위한 객체
 */
public class ItemInfo {

    @Getter
    @ToString
    public static class Main{
        private final String itemToken;
        private final Long   partnerId;
        private final String itemName;
        private final Long   itemPrice;
        private final Item.Status status;
        private final List<ItemOptionGroupInfo> itemOptionGroupList;

        public Main(Item item, List<ItemOptionGroupInfo> itemOptionGroupIntoList){
            this.itemToken = item.getItemToken();
            this.partnerId = item.getPartnerId();
            this.itemName  = item.getItemName();
            this.itemPrice = item.getItemPrice();
            this.status    = item.getStatus();
            this.itemOptionGroupList = itemOptionGroupIntoList;
        }
    }

    @Getter
    @ToString
    public static class ItemOptionGroupInfo{
        private final Integer ordering;
        private final String  itemOptionGroupName;
        private final List<ItemOptionInfo> itemOptionList;

        public ItemOptionGroupInfo(ItemOptionGroup itemOptionGroup, List<ItemOptionInfo> itemOptionList){
            this.ordering = itemOptionGroup.getOrdering();
            this.itemOptionGroupName = itemOptionGroup.getItemOptionGroupName();
            this.itemOptionList = itemOptionList;
        }
    }

    @Getter
    @ToString
    public static class ItemOptionInfo{
        private final Integer ordering;
        private final String  itemOptionName;
        private final Long    itemOptionPrice;

        public ItemOptionInfo(ItemOption itemOption){
            this.ordering        = itemOption.getOrdering();
            this.itemOptionName  = itemOption.getItemOptionName();
            this.itemOptionPrice = itemOption.getItemOptionPrice();
        }
    }
}
