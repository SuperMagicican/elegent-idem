package cn.elegent.data.redis;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis配置类
 */
@Configuration
@Log
@ConditionalOnProperty(prefix = "elegent.data",name = "type",havingValue = "redis",matchIfMissing = true)
public class JedisConfig {

    @Value("${elegent.data.redis.host:127.0.0.1}")
    private String host;

    @Value("${elegent.data.redis.port:6379}")
    private int port;

    @Value("${elegent.data.redis.password:}")
    private String password;
    @Value("${elegent.data.redis.timeout:30}")
    private int timeout;

    @Value("${elegent.data.redis.pool.max-active:500}")
    private int maxActive;//// 最大连接数：可同时建立的最大链接数

    @Value("${elegent.data.redis.pool.max-idle:200}")
    private int maxIdle;// // 最大空闲数：空闲链接数大于maxIdle时，将进行回收

    @Value("${elegent.data.redis.pool.min-idle:100}")
    private int minIdle;//最小空闲数：低于minIdle时，将创建新的链接

    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxTotal(maxActive);

        JedisPool jedisPool;
        if("".equals(password)){
            jedisPool=new JedisPool(jedisPoolConfig,host,port,timeout);
        }else{
            jedisPool=new JedisPool(jedisPoolConfig,host,port,timeout,password);
        }
        log.info("ElegentData 数据源连接成功:"+host+"\t"+port);

        return jedisPool;
    }
}
