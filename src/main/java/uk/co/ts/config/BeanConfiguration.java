package uk.co.ts.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Configuration
@EnableCaching
public class BeanConfiguration {

    @Value("${cache.accessToken.timeToLiveSeconds}")
    private int accessTokenCacheTimeToLiveSeconds;

    @Value("${cache.riskMeasuresCache.timeToLiveSeconds}")
    private int riskMeasuresCacheCacheTimeToLiveSeconds;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new ObjectMapper().setSerializationInclusion(NON_NULL);
    }

    @Bean(name = "accessTokenCache")
    public Cache<Object, Object> accessTokenCache() {
        return CacheBuilder
                .newBuilder()
                .expireAfterWrite(accessTokenCacheTimeToLiveSeconds, TimeUnit.SECONDS)
                .maximumSize(1)
                .recordStats()
                .build();
    }

    @Bean(name = "riskMeasuresCache")
    public Cache<Object, Object> riskMeasuresCache() {
        return CacheBuilder
                .newBuilder()
                .expireAfterWrite(riskMeasuresCacheCacheTimeToLiveSeconds, TimeUnit.SECONDS)
                .maximumSize(1000)
                .recordStats()
                .build();
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        org.springframework.cache.Cache driverGuidCache = new GuavaCache("accessTokenCache", accessTokenCache());
        org.springframework.cache.Cache riskMeasuresCache = new GuavaCache("riskMeasuresCache", riskMeasuresCache());
        cacheManager.setCaches(Arrays.asList(driverGuidCache, riskMeasuresCache));
        return cacheManager;
    }
}
