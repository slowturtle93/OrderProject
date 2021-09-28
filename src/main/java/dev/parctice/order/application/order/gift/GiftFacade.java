package dev.parctice.order.application.order.gift;

import dev.parctice.order.domain.order.OrderCommand;
import dev.parctice.order.domain.order.gift.GiftOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiftFacade {
    private final GiftOrderService giftOrderService;

    public void paymentOrder(OrderCommand.PaymentRequest request){
        giftOrderService.paymentOrder(request);
    }
}
