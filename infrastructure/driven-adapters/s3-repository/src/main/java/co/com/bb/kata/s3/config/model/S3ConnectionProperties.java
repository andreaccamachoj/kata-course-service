package co.com.bb.kata.s3.config.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "adapter.aws.s3")
public record S3ConnectionProperties(
        String region,
        String bucketName,
        String accessKey,
        String secretKey
) {}