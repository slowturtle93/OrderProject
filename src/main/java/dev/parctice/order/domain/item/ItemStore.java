package dev.parctice.order.domain.item;

/**
 * Item 도메인 저장을 담당하는 클래스 파일
 */
public interface ItemStore {
    Item store(Item initItem);
}
