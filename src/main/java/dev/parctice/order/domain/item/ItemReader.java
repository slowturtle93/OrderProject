package dev.parctice.order.domain.item;

import java.util.List;

/**
 * Item 도메인 조회를 담당하는 클래스 파일
 */
public interface ItemReader {
    Item getItemBy(String itemToken);
    List<ItemInfo.ItemOptionGroupInfo> getItemOptionSeries(Item item);
}
