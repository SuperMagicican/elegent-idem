package cn.elegent.idempotence.core.config;

import cn.elegent.idempotence.core.ExceptionManager;
import cn.elegent.idempotence.exception.DefaultExceptionManager;
import cn.elegent.idempotence.core.UniqueID;
import cn.elegent.idempotence.unique.DefaultUniqueID;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdempotenceConfg {

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

}
