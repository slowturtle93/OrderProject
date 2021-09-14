package dev.parctice.order.domain.item;

import dev.parctice.order.domain.item.optionGroup.ItemOptionGroup;

import java.util.List;

/**
 * 자신의 책임과 역할이 다른 객체를 생성하는 클래스 파일
 */
public interface ItemOptionSeriesFactory {
    List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest request, Item item);
}
