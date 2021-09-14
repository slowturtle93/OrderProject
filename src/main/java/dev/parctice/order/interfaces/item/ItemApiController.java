package dev.parctice.order.interfaces.item;

import dev.parctice.order.application.item.ItemFacade;
import dev.parctice.order.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/items")
public class ItemApiController {
    private final ItemFacade itemFacade;
    private final ItemDtoMapper itemDtoMapper;

    /**
     * Item 정보 저장
     * @param request
     * @return
     */
    @PostMapping
    public CommonResponse registerItem(@RequestBody @Valid ItemDto.RegisterItemRequest request){
        var partnerToken = request.getPartnerToken();
        var itemCommand = itemDtoMapper.of(request);
        var itemToken = itemFacade.registerItem(itemCommand, partnerToken);
        var response = itemDtoMapper.of(itemToken);
        return CommonResponse.success(response);
    }

    /**
     * Item 상태 변경(판매중)
     * @param request
     * @return
     */
    @PostMapping("/change-on-sales")
    public CommonResponse changeOnSaleItem(@RequestBody @Valid ItemDto.ChangeStatusItemRequest request){
        var itemToken = request.getItemToken();
        itemFacade.changeOnSaleItem(itemToken);
        return CommonResponse.success("OK");
    }

    /**
     * Item 상태 변경(판매종료)
     * @param request
     * @return
     */
    @PostMapping("/change-end-of-sales")
    public CommonResponse changeEndOfSalesItem(@RequestBody @Valid ItemDto.ChangeStatusItemRequest request){
        var itemToken = request.getItemToken();
        itemFacade.changeEndOfSaleItem(itemToken);
        return CommonResponse.success("OK");
    }

    /**
     * Item 조회
     * @param itemToken
     * @return
     */
    @GetMapping("/{itemToken}")
    public CommonResponse retrieve(@PathVariable("itemToken") String itemToken){
        var itemInfo = itemFacade.retrieveItemInfo(itemToken);
        var response = itemDtoMapper.of(itemInfo);
        return CommonResponse.success(response);
    }
}
