package dev.parctice.order.domain.item;

import dev.parctice.order.domain.item.option.ItemOption;
import dev.parctice.order.domain.item.optionGroup.ItemOptionGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
/**
 * Item의 Create, Update, Delete 처리를 위한 파라미터를 관리하는 클래스 파일
 */
public class ItemCommand {

    // Item 등록 Request
    @Getter
    @Builder
    @ToString
    public static class RegisterItemRequest{
        private final String itemName;
        private final Long   itemPrice;
        private final List<RegisterItemOptionGroupRequest> itemOptionGroupRequestList;

        public Item toEntity(Long partnerId){
            return Item.builder()
                    .partnerId(partnerId)
                    .itemName(itemName)
                    .itemPrice(itemPrice)
                    .build();
        }
    }

    // ItemOptionGroup 저장 Request
    @Getter
    @Builder
    @ToString
    public static class RegisterItemOptionGroupRequest{
        private final Integer ordering;
        private final String  itemOptionGroupName;
        private List<RegisterItemOptionRequest> itemOptionRequestList;

        public ItemOptionGroup toEntity(Item item){
            return ItemOptionGroup.builder()
                    .item(item)
                    .ordering(ordering)
                    .itemOptionGroupName(itemOptionGroupName)
                    .build();
        }
    }

    // ItemOption 저장 Request
    @Getter
    @Builder
    @ToString
    public static class RegisterItemOptionRequest{
        private final Integer ordering;
        private final String  itemOptionName;
        private final Long    itemOptionPrice;

        public ItemOption toEntity(ItemOptionGroup itemOptionGroup){
            return ItemOption.builder()
                    .itemOptionGroup(itemOptionGroup)
                    .ordering(ordering)
                    .itemOptionName(itemOptionName)
                    .itemOptionPrice(itemOptionPrice)
                    .build();
        }
    }
}
