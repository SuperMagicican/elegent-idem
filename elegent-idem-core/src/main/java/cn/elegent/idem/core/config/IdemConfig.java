package cn.elegent.idem.core.config;

import cn.elegent.idem.core.ElegentStore;
import cn.elegent.idem.core.ExceptionManager;
import cn.elegent.idem.exception.DefaultExceptionManager;
import cn.elegent.idem.core.UniqueID;
import cn.elegent.idem.store.ElegentStoreRedis;
import cn.elegent.idem.unique.DefaultUniqueID;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdemConfig {

    /**
     * 标识器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public UniqueID uniqueID(){
        return new DefaultUniqueID();
    }

    /**
     * 异常处理器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public ExceptionManager exceptionManager(){
        return new DefaultExceptionManager();
    }

    /**
     * 存储器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public ElegentStore elegentStore(){
        return new ElegentStoreRedis();
    }

}
