package dev.parctice.order.infrastructure.order;

import dev.parctice.order.domain.item.ItemReader;
import dev.parctice.order.domain.order.Order;
import dev.parctice.order.domain.order.OrderCommand;
import dev.parctice.order.domain.order.OrderItemSeriesFactory;
import dev.parctice.order.domain.order.OrderStore;
import dev.parctice.order.domain.order.item.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderItemSeriesFactoryImpl implements OrderItemSeriesFactory {

    private final ItemReader itemReader;
    private final OrderStore orderStore;

    @Override
    public List<OrderItem> store(Order order, OrderCommand.RegisterOrder requestOrder) {
        return requestOrder.getOrderItemList().stream()
                .map(orderItemRequest ->{
                    var item = itemReader.getItemBy(orderItemRequest.getItemToken()); // 주문 상품 정보 조회
                    var initOrderItem = orderItemRequest.toEntity(order,item);    // 주문 상품 정보 객체화
                    var orderItem = orderStore.store(initOrderItem);              // 주문 상품 정보 저장

                    orderItemRequest.getOrderItemOptionGroupList().forEach(orderItemOptionGroupRequest ->{
                        // 주문 상품의 OptionGroup 정보 객체화
                        var initOrderItemOptionGroup = orderItemOptionGroupRequest.toEntity(orderItem);
                        // 주문 상품의 OptionGroup 정보 저장
                        var orderItemOptionGroup = orderStore.store(initOrderItemOptionGroup);

                        orderItemOptionGroupRequest.getOrderItemOptionList().forEach(orderItemOptionRequest ->{
                            // 주문 상품의 Option 정보 객체화
                            var initOrderItemOption = orderItemOptionRequest.toEntity(orderItemOptionGroup);
                            // 주문 상품의 Option 정보 저장
                            orderStore.store(initOrderItemOption);
                        });
                    });
                    return orderItem;
                }).collect(Collectors.toList());
    }
}
