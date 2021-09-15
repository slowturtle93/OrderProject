package dev.parctice.order.infrastructure.order;

import dev.parctice.order.common.exception.EntityNotFoundException;
import dev.parctice.order.domain.order.Order;
import dev.parctice.order.domain.order.OrderReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderReaderImpl implements OrderReader {

    private final OrderRepository orderRepository;

    /**
     * 주문 상품 조회
     *
     * @param orderToken
     * @return
     */
    @Override
    public Order getOrder(String orderToken) {
        return orderRepository.findByOrderToken(orderToken)
                .orElseThrow(EntityNotFoundException::new);
    }
}
