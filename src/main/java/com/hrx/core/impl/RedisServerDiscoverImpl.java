package com.hrx.core.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.hrx.core.ServerDiscover;
import com.hrx.core.util.RedisUtil;

import redis.clients.jedis.Jedis;

public class RedisServerDiscoverImpl implements ServerDiscover{

	@Override
	public List<String> serverdiscover(String serverName) {
		Jedis jedis = RedisUtil.getJedis();
		Set<String> zrange = jedis.zrange(serverName, 0, 99);
		List<String> uris = new ArrayList<>();
		for (String uriSet : zrange) {
			System.out.println(uriSet);
			uris.add(uriSet);
		}
		return uris;
	}
	

}
