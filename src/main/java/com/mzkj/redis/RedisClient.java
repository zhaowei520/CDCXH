package com.mzkj.redis;

import org.crazycake.shiro.RedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @Description:  jedis客户端操作
 * @Author: zw
 * @Date: 2019/3/18 13:08
 * @Version: 1.0
 */
public class RedisClient extends RedisManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisClient.class);

    private static JedisPool jedisPool = null;

    public RedisClient(JedisPool jedisPool) {
    	
//    	JedisPoolConfig config = new JedisPoolConfig();
//    	config.setMaxTotal(200);
//    	config.setMaxIdle(50);
//    	config.setMinIdle(8);//设置最小空闲数
//    	config.setMaxWaitMillis(10000);
//    	config.setTestOnBorrow(true);
//    	config.setTestOnReturn(true);
//    	//Idle时进行连接扫描
//    	config.setTestWhileIdle(true);
//    	//表示idle object evitor两次扫描之间要sleep的毫秒数
//    	config.setTimeBetweenEvictionRunsMillis(30000);
//    	//表示idle object evitor每次扫描的最多的对象数
//    	config.setNumTestsPerEvictionRun(10);
//    	//表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
//    	config.setMinEvictableIdleTimeMillis(60000);
//    	
//    	this.jedisPool = new JedisPool(config, "172.18.155.85", 6379, 10000, "CXH_Passw0rd", 0);
//    	this.jedisPool = new JedisPool(config, "47.107.44.137", 6379, 10000, "CXH_Passw0rd", 0);
//    	this.jedisPool = new JedisPool(config, "192.168.50.133", 6379, 10000, "12345", 0);
        this.jedisPool = jedisPool;
    }

//    public Jedis getJedis(){
//		Properties pros = getPprVue();
//		String isopen = pros.getProperty("redis.isopen");	//地址
//		String host = pros.getProperty("redis.host");		//地址
//		String port = pros.getProperty("redis.port");		//端口
//		String pass = pros.getProperty("redis.pass");		//密码
//		if("yes".equals(isopen)){
//			Jedis jedis = new Jedis(host,Integer.parseInt(port));
//			jedis.auth(pass);
//			return jedis;
//		}else{
//			return null;
//		}
//	}
//    public Properties getPprVue(){
//		InputStream inputStream = DbFH.class.getClassLoader().getResourceAsStream("redis.properties");
//		Properties p = new Properties();
//		try {
//			p.load(inputStream);
//			inputStream.close();
//		} catch (IOException e) {
//			//读取配置文件出错
//			e.printStackTrace();
//		}
//		return p;
//	}
    
   
    public void init() {
        super.init();
    }

    @Override
    public byte[] get(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        byte[] value;

        try {
            value = jedis.get(key);
        } catch (Exception e) {
            LOGGER.error("redis key:{} get value occur exception", new String(key));
            throw new RuntimeException("redis operation error:", e);
        } finally {
            jedis.close();
        }

        return value;
    }

    @Override
    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();

        try {
            jedis.set(key, value);
            Integer expire = getExpire();
            if(expire != 0) {
                jedis.expire(key, expire);
            }
        } catch (Exception e) {
            LOGGER.error("redis key:{} set value:{} occur exception", new String(key), new String(value));
            throw new RuntimeException("redis operation error:", e);
        } finally {
            jedis.close();
        }

        return value;
    }

    @Override
    public byte[] set(byte[] key, byte[] value, int expire) {
        Jedis jedis = jedisPool.getResource();

        try {
            jedis.set(key, value);
            if(expire != 0) {
                jedis.expire(key, expire);
            }
        } catch (Exception e) {
            LOGGER.error("redis key:{} set value:{} in expire:{} occur exception", new String(key), new String(value), expire);
            throw new RuntimeException("redis operation error:", e);
        } finally {
            jedis.close();
        }

        return value;
    }

    @Override
    public void del(byte[] key) {
        Jedis jedis = jedisPool.getResource();

        try {
            jedis.del(key);
        } catch (Exception e) {
            LOGGER.error("redis key:{} del value occur exception", new String(key));
            throw new RuntimeException("redis operation error:", e);
        } finally {
            jedis.close();
        }
    }

    public void flushDB() {
        Jedis jedis = jedisPool.getResource();

        try {
            jedis.flushDB();
        } catch (Exception e) {
            LOGGER.error("redis flushDB occur exception");
            throw new RuntimeException("redis operation error:", e);
        } finally {
            jedis.close();
        }

    }

    @Override
    public Long dbSize() {
        Long dbSize = Long.valueOf(0L);
        Jedis jedis = jedisPool.getResource();

        try {
            dbSize = jedis.dbSize();
        } catch (Exception e) {
            LOGGER.error("redis get dbSize occur exception");
            throw new RuntimeException("redis operation error:", e);
        } finally {
            jedis.close();
        }

        return dbSize;
    }

    public Set<byte[]> keys(String pattern) {
        Set keys = null;
        Jedis jedis = jedisPool.getResource();

        try {
            keys = jedis.keys(pattern.getBytes());
        } catch (Exception e) {
            LOGGER.error("redis get keys in pattern:{} occur exception", pattern);
            throw new RuntimeException("redis operation error:", e);
        } finally {
            jedis.close();
        }

        return keys;
    }
}
