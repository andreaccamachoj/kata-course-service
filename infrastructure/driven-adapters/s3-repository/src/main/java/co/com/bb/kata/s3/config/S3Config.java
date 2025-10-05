package co.com.bb.kata.s3.config;

import co.com.bb.kata.s3.config.model.S3ConnectionProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;
import software.amazon.awssdk.metrics.MetricPublisher;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class S3Config {

    @Bean
    public S3Client s3Client(S3ConnectionProperties s3Properties, MetricPublisher publisher) {
        return S3Client.builder()
                .overrideConfiguration(o -> o.addMetricPublisher(publisher))
                .region(Region.of(s3Properties.region()))
                .build();
    }

}
