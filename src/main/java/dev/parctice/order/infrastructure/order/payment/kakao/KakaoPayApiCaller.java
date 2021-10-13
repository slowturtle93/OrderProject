package dev.parctice.order.infrastructure.order.payment.kakao;

import dev.parctice.order.domain.order.Order;
import dev.parctice.order.domain.order.OrderCommand;
import dev.parctice.order.domain.order.OrderReader;
import dev.parctice.order.domain.order.item.OrderItem;
import dev.parctice.order.domain.order.payment.PayMethod;
import dev.parctice.order.infrastructure.order.payment.PaymentApiCaller;
import dev.parctice.order.infrastructure.retrofit.RetrofitUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoPayApiCaller implements PaymentApiCaller {

    private final RetrofitKakaoApi retrofitKakaoApi;
    private final RetrofitUtils retrofitUtils;
    private final KakaoApiRequest kakaoApiRequest;
    private final OrderReader orderReader;

    @Override
    public boolean support(PayMethod payMethod) {
        return PayMethod.KAKAO_PAY == payMethod;
    }

    @Override
    public void pay(OrderCommand.PaymentRequest request) {
        // TODO - 구현
        Map<String, Object> params = new HashMap<String, Object>(); // request 정보
        long totalAmount = 0;                         // 주문 총 수량
        StringBuilder itemName = new StringBuilder(); // 주문 상품명

        Order order = orderReader.getOrder(request.getOrderToken());
        // 주문 수량 및 상품명
        for(OrderItem orderItem : order.getOrderItemList()){
            totalAmount += orderItem.getOrderCount();
            itemName.append(orderItem.getItemName() + ",");
        }

        // request 정보 put
        params.put("cid"             , kakaoApiRequest.getApiKey());
        params.put("partner_order_id", request.getOrderToken());
        params.put("partner_user_id" , kakaoApiRequest.getPartnerUserId());
        params.put("item_name"       , itemName.toString().replaceAll("[,]$", ""));
        params.put("quantity"        , totalAmount);
        params.put("total_amount"    , request.getAmount());
        params.put("tax_free_amount" , 0);
        params.put("approval_url"    , kakaoApiRequest.getApprovalUrl());
        params.put("cancel_url"      , kakaoApiRequest.getCancelUrl());
        params.put("fail_url"        , kakaoApiRequest.getFailUrl());

        // 결제 준비 요청
        var call = retrofitKakaoApi.kakaoPayRequest(
                kakaoApiRequest.getAuthorization(),
                kakaoApiRequest.getContentType(),
                params);

        // API response
        KakaoApiResponse.response response =  retrofitUtils.responseSync2(call)
                .orElseThrow(RuntimeException::new);
    }
}
