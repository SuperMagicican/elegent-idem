package cn.elegent.idem.store;
import cn.elegent.idem.core.ElegentStore;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ElegentStoreRedis implements ElegentStore {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    @Override
    public void set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key,value);
        jedis.close();
    }

    @Override
    public void del(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
        jedis.close();
    }

    /**
     * HashGet
     * @param key  键 不能为null
     * @param item 项 不能为null
     */
    @Override
    public String hget(String key, String item) {
        Jedis jedis = jedisPool.getResource();
        String json = jedis.hget(key, item);
        jedis.close();
        return json;
    }


    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    @Override
    public void hset(String key, String item, String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.hset(key,item,value);
        jedis.close();
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    @Override
    public void hdel(String key, String... item) {
        Jedis jedis = jedisPool.getResource();
        jedis.hdel(key,item);
        jedis.close();
    }

    @Override
    public boolean setIfAbsent(String key, String value, int seconds) {
        Jedis jedis = jedisPool.getResource();
        long res = jedis.setnx(key, value);
        if (new Long(1L).equals(res)) {
            // 设定过期时间，最多30秒自动过期，防止长期死锁发生
            jedis.expire(key,seconds);
            jedis.close();
            return true;
        }else{
            jedis.close();
            return false;
        }
    }

}