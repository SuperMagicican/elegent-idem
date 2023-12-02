package cn.elegent.idempotence.core;

public interface ExceptionManager {


    /**
     * 当出现重复请求的时候的异常处理
     */
    void interfaceDempotenceHandler();

}