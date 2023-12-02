package cn.elegent.idempotence.core.aspect;

import cn.elegent.idempotence.annotation.ElegentIdem;
import cn.elegent.idempotence.core.Checker;
import cn.elegent.idempotence.core.CheckerLoader;
import cn.elegent.idempotence.core.ExceptionManager;
import cn.elegent.idempotence.core.UniqueID;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * InterfaceIdempotenceTableAspect
 * @description 接口幂等表的方式实现接口幂等性
 * @author WGL
 * @date 2022/11/11 20:33
 */
@Aspect
@Component
@Slf4j
public class ElegentIdemAspect {

    @Autowired
    private ExceptionManager exceptionManager;



    @Autowired
    private UniqueID uniqueID;

    /**
     * 织入点
     */
    @Pointcut("@annotation(cn.elegent.idempotence.annotation.ElegentIdem)")
    public void elegentIdem() {
    }

    /**
     * 接口幂等性操作流程
     * 1）首先判断每个请求id是否存在
     * 2）如果不存在直接存入redis -> key：requestid value:PROC
     * 3) 如果存在则判断状态是否存在如果存在 则拒绝
     * 4)业务逻辑执行完 删除对应的requestId
     */
    @Around("elegentIdem() && @annotation(elegentIdem)")
    public Object methodBefore(ProceedingJoinPoint point , ElegentIdem elegentIdem) {
        //请求id由接口获取，目的是提高拓展性
        String uniqueID = this.uniqueID.getUniqueID(elegentIdem.name());
        log.info("elegent-iu 框架 uniqueID：{}",uniqueID);
        Object proceed = null;

        Checker checker = CheckerLoader.getChecker(elegentIdem.type());  //根据类型匹配检查器

        if(checker.check(uniqueID)){
            try {
                proceed = point.proceed(point.getArgs());//执行处理
                checker.afterSuccess(uniqueID);//成功后续处理
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }else{
            exceptionManager.interfaceDempotenceHandler();//重复请求的处理
        }
        return proceed;
    }
}