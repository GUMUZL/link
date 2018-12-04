package com.hrx.core.impl;

import java.util.HashMap;
import java.util.Map;

import com.hrx.core.ServerRegistry;
import com.hrx.core.util.RedisUtil;

import redis.clients.jedis.Jedis;

public class RedisServerRegistryImpl implements ServerRegistry{
	private static Integer i = 0;

	@Override
	public void serverRegistry(String serverName, String uri) {
			Jedis jedis = RedisUtil.getJedis();
			//该服务极限100
			Map<String, Double> scoreMembers = new HashMap<String, Double>();
			i++;
			scoreMembers.put(uri,i.doubleValue());
			jedis.zadd(serverName, scoreMembers);
			jedis.close();
	}

}
