package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml"})
public class asd {

	@Autowired
	JedisPool jedisPool;
	
	@Test
	public void tes88(){
		Jedis jedis = jedisPool.getResource();
		jedis.del("zset1");
		jedis.close();
	}
}
