package dev.parctice.order.infrastructure.order.payment.toss;

import dev.parctice.order.domain.order.OrderCommand;
import dev.parctice.order.domain.order.payment.PayMethod;
import dev.parctice.order.infrastructure.order.payment.PaymentApiCaller;
import dev.parctice.order.infrastructure.retrofit.RetrofitUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TossPayApiCaller implements PaymentApiCaller {

    private final RetrofitTossApi retrofitTossApi;
    private final RetrofitUtils retrofitUtils;
    private final TossApiRequest tossApiRequest;

    @Override
    public boolean support(PayMethod payMethod) {
        return PayMethod.TOSS_PAY == payMethod;
    }

    @Override
    public void pay(OrderCommand.PaymentRequest request) {
        // TODO - 구현
        JSONObject params = new JSONObject();
        params.put("orderNo", request.getOrderToken());
        params.put("amount", request.getAmount());
        params.put("amountTaxFree", 0);
        params.put("productDesc", "상품 결제");
        params.put("apiKey", tossApiRequest.getApiKey());
        params.put("autoExecute", true);
        params.put("resultCallback", tossApiRequest.getResultCallback());
        params.put("callbackVersion", tossApiRequest.getCallbackVersion());
        params.put("retUrl", tossApiRequest.getRetUrl());
        params.put("retCancelUrl", tossApiRequest.getRetCancelUrl());

        var call = retrofitTossApi.tossPayRequest(params);

        // API response
        TossApiResponse.response response =  retrofitUtils.responseSync2(call)
                .orElseThrow(RuntimeException::new);
    }
}
