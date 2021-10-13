package dev.parctice.order.infrastructure.retrofit;

import dev.parctice.order.infrastructure.order.payment.kakao.RetrofitKakaoApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RetrofitServiceRegistry {

    @Value("${payment.method.kakao.url}")
    private String kakaoUrl;

    @Bean
    public RetrofitKakaoApi retrofitKakaoApi() {
        var retrofit = RetrofitUtils.initRetrofit(kakaoUrl);
        return retrofit.create(RetrofitKakaoApi.class);
    }

}
