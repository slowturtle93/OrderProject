package dev.parctice.order.infrastructure.order;

import dev.parctice.order.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderToken(String orderToken); // 주문 상품 find
}
