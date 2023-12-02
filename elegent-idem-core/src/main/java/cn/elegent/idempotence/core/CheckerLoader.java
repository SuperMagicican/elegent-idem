package cn.elegent.idempotence.core;

import cn.elegent.idempotence.annotation.CheckerName;
import cn.elegent.idempotence.core.Checker;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 检查器加载器
 */
@Component
public class CheckerLoader implements ApplicationContextAware {

    private static Map<String, Checker> elegentCheckerMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        //加载所有检查器
        Collection<Checker> checkers = applicationContext.getBeansOfType(Checker.class).values();
        checkers.stream().forEach(checker->{
            //通过反射拿到类上的平台注解
            CheckerName annotation = checker.getClass().getAnnotation(CheckerName.class);
            if (annotation != null) {
                elegentCheckerMap.put(annotation.value(), checker);
            }
        });

    }


    /**
     * 查询检查器
     * @param name 名称
     * @return
     */
    public static Checker getChecker(String name){
        Checker checker = elegentCheckerMap.get(name);
        if(checker!=null){
            return checker;
        }else{
            throw new RuntimeException("未找到"+ name+"检查器");
        }
    }


}
