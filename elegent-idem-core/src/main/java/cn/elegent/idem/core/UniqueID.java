package cn.elegent.idem.core;

/**
 * UniqueID
 * @description 提供一个获取唯一ID的接口，用于拓展其他组件
 * @author WGL
 * @date 2022/11/11 9:40
*/
public interface UniqueID {

    /**
     * 获取唯一id的方法
     * @return
     */
    String getUniqueID(String name);
}
