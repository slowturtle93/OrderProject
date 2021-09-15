package dev.parctice.order.interfaces.order;

import dev.parctice.order.application.order.OrderFacade;
import dev.parctice.order.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderApiController {

    private final OrderFacade orderFacade;
    private final OrderDtoMapper orderDtoMapper;

    /**
     * 주문 정보 저장
     *
     * @param request
     * @return
     */
    @PostMapping("/init")
    public CommonResponse registerOrder(@RequestBody @Valid OrderDto.RegisterOrderRequest request){
        var orderCommand = orderDtoMapper.of(request); // Command 로 객체 변환
        var orderToken = orderFacade.registerOrder(orderCommand);      // 주문 정보 저장
        var response = orderDtoMapper.of(orderToken);    // 저장 된 주문 정보 객체 변환
        return CommonResponse.success(response);
    }

    /**
     * 주문 정보 조회
     *
     * @param orderToken
     * @return
     */
    @GetMapping("/{orderToken}")
    public CommonResponse retrieveOrder(@PathVariable String orderToken){
        var orderResult = orderFacade.retrieveOrder(orderToken); // 주문 정보 조회
        var response = orderDtoMapper.of(orderResult);           // 주문 정보 객체 변환
        return CommonResponse.success(response);
    }

    /**
     * 주문 결제 요청
     *
     * @param paymentRequest
     * @return
     */
    @PostMapping("/payment-order")
    public CommonResponse paymentOrder(@RequestBody @Valid OrderDto.PaymentRequest paymentRequest){
        var paymentCommand = orderDtoMapper.of(paymentRequest); // Command 로 객체 변환
        orderFacade.paymentOrder(paymentCommand);                                        // 주문 결제 요청
        return CommonResponse.success("OK");
    }
}
