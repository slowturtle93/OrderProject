package dev.parctice.order.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSqsConfig {

    @Value("${cloud.aws.access-key}")
    private String awsAccessKey; // aws SQS 일반 엑세스 키

    @Value("${cloud.aws.secret-key}")
    private String awsSecretKey; // aws SQS 비밀 키

    /**
     * 메시지 전송 클라이언트와 메세지 리스너를 생성하기 위해서는
     * SQS 서비스에 접근할 수 있는 AmazonSQSAsync 빈 생성
     *
     * @return
     */
    @Bean
    public AmazonSQSAsync amazonSQSAsync(){
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
             new BasicAWSCredentials(awsAccessKey, awsSecretKey)
        );

        return AmazonSQSAsyncClientBuilder
                .standard()
                .withRegion(Regions.AP_NORTHEAST_2)
                .withCredentials(awsCredentialsProvider)
                .build();
    }
}
