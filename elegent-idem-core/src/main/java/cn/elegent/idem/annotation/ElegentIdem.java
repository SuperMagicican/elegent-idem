package cn.elegent.idem.annotation;


import java.lang.annotation.*;

/**
 * ElegentIdem
 * @description 自定义接口幂等性注解
 * @author 黑马刘皇叔
 * @date 2022/11/11 9:08
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ElegentIdem {

    String type() default "sn"; //类型

    String name() default "requestId";// 获取的头信息的名字

}
