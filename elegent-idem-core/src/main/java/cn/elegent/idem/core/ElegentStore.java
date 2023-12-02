package cn.elegent.idem.core;

/**
 * 数据处理通用接口
 */
public interface ElegentStore {

    /**
     * 获取值
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 存值
     * @param key
     * @param value
     */
    void set(String key,String value);

    /**
     * 删除值
     * @param key
     */
    void del(String key);

    /**
     * HashGet
     * @param key  键 不能为null
     * @param item 项 不能为null
     */
    String hget(String key, String item);

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    void hset(String key, String item, String value);

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    void hdel(String key, String... item);

    /**
     * 加锁
     *
     */
    boolean setIfAbsent(String key, String value, int seconds);

}