package dev.parctice.order.domain.item;

import dev.parctice.order.domain.parthner.PartnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final PartnerReader partnerReader;
    private final ItemStore itemStore;
    private final ItemReader itemReader;

    /**
     * Item 등록
     *
     * @param command
     * @param partnerToken
     * @return
     */
    @Override
    @Transactional
    public String registerItem(ItemCommand.RegisterItemRequest command, String partnerToken) {
        var partner = partnerReader.getPartner(partnerToken); // partner 정보 조회
        var initItem = command.toEntity(partner.getId());       // 저장 할 Item 도메인 convert
        var item = itemStore.store(initItem);                   // Item 정보 저장
        return item.getItemToken();                                   // ItemToken 리턴
    }

    /**
     * Item 상태 변경 메서드(판매중)
     * @param itemToken
     */
    @Override
    @Transactional
    public void changeOnSale(String itemToken) {
        var item = itemReader.getItemBy(itemToken);
        item.changeOnSales();
    }

    /**
     * Item 상태 변경 메서드(판매종료)
     * @param itemToken
     */
    @Override
    @Transactional
    public void changeEndOfSale(String itemToken) {
        var item = itemReader.getItemBy(itemToken);
        item.changeEndOfSales();
    }

    /**
     * Item 조회
     * @param itemToken
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public ItemInfo.Main retrieveItemInfo(String itemToken) {
        var item = itemReader.getItemBy(itemToken);
        var itemOptionGroupInfoList = itemReader.getItemOptionSeries(item);
        return new ItemInfo.Main(item, itemOptionGroupInfoList);
    }
}
