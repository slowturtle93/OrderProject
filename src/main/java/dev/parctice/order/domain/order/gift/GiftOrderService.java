package dev.parctice.order.domain.order.gift;

import dev.parctice.order.domain.order.OrderCommand;

public interface GiftOrderService {
    void paymentOrder(OrderCommand.PaymentRequest paymentRequest);
}
