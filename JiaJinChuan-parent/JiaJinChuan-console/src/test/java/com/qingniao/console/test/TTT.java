package com.qingniao.console.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml"})
public class TTT {
	@Test
	public void te6(){
		Jedis jedis = new Jedis("192.168.0.1",6379);
		jedis.set("mingzi", "123张");
		jedis.close();
	}
	
	/**
	 * 
	 */
	@Test
	public void tes7(){
		Jedis jedis = new Jedis("192.168.0.1",6379);
		String name = jedis.get("mingzi");
		System.out.println(name);
		jedis.close(); 
	}
	
	@Autowired
	JedisPool jedisPool;
	
	@Test
	public void tes88(){
		Jedis jedis = jedisPool.getResource();
		jedis.del("zset1");
		jedis.close();
	}
}
