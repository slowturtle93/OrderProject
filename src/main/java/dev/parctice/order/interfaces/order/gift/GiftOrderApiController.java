package dev.parctice.order.interfaces.order.gift;

import dev.parctice.order.application.order.OrderFacade;
import dev.parctice.order.application.order.gift.GiftFacade;
import dev.parctice.order.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gift-orders")
public class GiftOrderApiController {

    private final OrderFacade orderFacade;
    private final GiftFacade giftFacade;
    private final GiftOrderDtoMapper giftOrderDtoMapper;

    /**
     * 선물하기 시 주문 정보 등록
     *
     * @param request
     * @return
     */
    @PostMapping("/init")
    public CommonResponse registerOrder(@RequestBody @Valid GiftOrderDto.RegisterOrderRequest request){
        var orderCommand = giftOrderDtoMapper.of(request); // 선물하기 주문 정보 Convert
        var result = orderFacade.registerOrder(orderCommand);              // 선물하기 주문 등록
        var response = giftOrderDtoMapper.of(result);     // 선물하기 주문 결과 Convert
        return CommonResponse.success(response);
    }

    /**
     * 선물하기 결제 진행
     *
     * @param request
     * @return
     */
    @PostMapping("/payment-order")
    public CommonResponse paymentOrder(@RequestBody @Valid GiftOrderDto.PaymentRequest request){
        var orderPaymentCommand = giftOrderDtoMapper.of(request); // 선물하기 결제 정보 Convert
        giftFacade.paymentOrder(orderPaymentCommand); // 선물하기 결제 진행
        return CommonResponse.success("OK");
    }

    /**
     * 선물하기 수락 시 수령인 배송지 정보 업데이트
     *
     * @param orderToken
     * @param request
     * @return
     */
    @PostMapping("/{orderToken}/update-receiver-info")
    public CommonResponse updateReceiverInfo(
        @PathVariable String orderToken,
        @RequestBody @Valid GiftOrderDto.UpdateReceiverInfoReq request
    ){
        var orderCommand = request.toCommand(); // 선물하기 수령인 배송지 정보 Convert
        orderFacade.updateReceiverInfo(orderToken, orderCommand);               // 선물하기 수령인 배송지 정보 업데이트
        return CommonResponse.success("OK");
    }
}
