package dev.parctice.order.infrastructure.order.payment.kakao;


import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

import java.util.Map;

public interface RetrofitKakaoApi {

    @FormUrlEncoded
    @POST("v1/payment/ready")
    Call<KakaoApiResponse.response> kakaoPayRequest(
            @Header("Authorization") String Authorization,
            @Header("Content-Type") String ContentType,
            @FieldMap Map<String, Object> params
            );

}
