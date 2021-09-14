package dev.parctice.order.application.item;

import dev.parctice.order.domain.item.ItemCommand;
import dev.parctice.order.domain.item.ItemInfo;
import dev.parctice.order.domain.item.ItemService;
import dev.parctice.order.domain.notification.NotificationService;
import dev.parctice.order.domain.parthner.PartnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 비즈니스 결정을 내리진 않지만 수행할 작업을 정의
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ItemFacade {
    private final ItemService itemService;
    private final PartnerService partnerService;
    private final NotificationService notificationService;

    // Item 등록
    public String registerItem(ItemCommand.RegisterItemRequest request, String partnerToken){
        var itemToken = itemService.registerItem(request, partnerToken);   // Item 등록
        var partnerInto = partnerService.getPartnerInfo(partnerToken); // 동록한 파트너 정보
        notificationService.sendEmail(partnerInto.getEmail(), "title", "description"); // 이메일 고지
        return itemToken;
    }

    // 해당 Item 상태변경(판매중)
    public void changeOnSaleItem(String itemToken){
        itemService.changeOnSale(itemToken);
    }

    // 해당 Item 상태변경(판매종료)
    public void changeEndOfSaleItem(String itemToken){
        itemService.changeEndOfSale(itemToken);
    }

    // Item 정보 조회
    public ItemInfo.Main retrieveItemInfo(String itemToken){
        return itemService.retrieveItemInfo(itemToken);
    }
}
