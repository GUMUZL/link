package com.hrx.core.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	
	private static JedisPool pool =null;
	static {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(30);
		poolConfig.setMaxIdle(25);
		poolConfig.setMinIdle(20);
		String host="192.168.230.134";
		pool = new JedisPool(poolConfig, host);
	}
	
	public static JedisPool getPool() {
		return pool;
	}
	
	public static Jedis getJedis() {
		return getPool().getResource();
	}
	
}
