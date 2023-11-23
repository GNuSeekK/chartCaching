package stock.caching;

import io.lettuce.core.ReadFrom;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import stock.caching.global.entity.CacheComplete;
import stock.caching.global.entity.CacheLock;
import stock.caching.global.entity.CachePriority;
import stock.caching.price.entity.CacheStockPrice;

@Slf4j
@Configuration
@EnableRedisRepositories
@RequiredArgsConstructor
public class RedisConfig {

    @Value("${spring.redis.cluster.nodes}")
    private List<String> clusterNodes;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
            .readFrom(ReadFrom.MASTER) // 쓰기 로직만 수행하므로, master에서 읽는다
            .build();
        RedisStaticMasterReplicaConfiguration slaveConfig = new RedisStaticMasterReplicaConfiguration(
            clusterNodes.get(0).split(":")[0], Integer.parseInt(clusterNodes.get(0).split(":")[1]));
        clusterNodes.subList(1, clusterNodes.size()).forEach(node -> slaveConfig.addNode(node.split(":")[0], Integer.parseInt(node.split(":")[1])));
        return new LettuceConnectionFactory(slaveConfig, clientConfig);
    }

    @Bean
    public RedisTemplate<String, CachePriority> cachePriorityTemplate() {
        RedisTemplate<String, CachePriority> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(CachePriority.class));


        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(CachePriority.class));
        return template;
    }
    @Bean
    public RedisTemplate<String, CacheLock> cacheLockTemplate() {
        RedisTemplate<String, CacheLock> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(CacheLock.class));


        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(CacheLock.class));
        return template;
    }
    @Bean
    public RedisTemplate<String, CacheComplete> cacheCompleteTemplate() {
        RedisTemplate<String, CacheComplete> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(CacheComplete.class));


        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(CacheComplete.class));
        return template;
    }
    @Bean
    public RedisTemplate<String, CacheStockPrice> cacheStockPriceTemplate() {
        RedisTemplate<String, CacheStockPrice> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(CacheStockPrice.class));

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(CacheStockPrice.class));
        return template;
    }
}