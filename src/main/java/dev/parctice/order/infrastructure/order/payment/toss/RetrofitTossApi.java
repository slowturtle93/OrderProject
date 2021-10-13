package dev.parctice.order.infrastructure.order.payment.toss;

import org.json.simple.JSONObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitTossApi {

    @Headers("Content-Type: application/json")
    @POST("api/v2/payments")
    Call<TossApiResponse.response> tossPayRequest(@Body JSONObject jsonBoby);
}
