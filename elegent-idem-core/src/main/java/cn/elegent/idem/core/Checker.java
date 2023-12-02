package cn.elegent.idem.core;

/**
 * 检查器
 */
public interface Checker {


    /**
     * 检查方法
     * @param uniqueID
     * @return
     */
    boolean check(String uniqueID);

    /**
     * 执行成功后要做的事情
     * @param uniqueID
     * @return
     */
    void afterSuccess(String uniqueID);

}
