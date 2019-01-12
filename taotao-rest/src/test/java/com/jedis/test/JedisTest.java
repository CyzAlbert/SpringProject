package com.jedis.test;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {
	
	private ApplicationContext applicationContext;
	
	@Test
	public void testJedisSingle() {
		Jedis jedis = new Jedis("192.168.0.41", 6379);
		jedis.set("name", "bar");
		String name = jedis.get("name");
		System.out.println(name);
		jedis.close();

	}
	
	@Test
	public void pool() {
		JedisPoolConfig config = new JedisPoolConfig();
		//最大连接数
		config.setMaxTotal(30);
		//最大连接空闲数
		config.setMaxIdle(2);
		
		JedisPool pool = new JedisPool(config, "192.168.0.41", 6379);
		Jedis jedis = null;

		try  {
			jedis = pool.getResource();
			
			jedis.set("name", "lisi");
			String name = jedis.get("name");
			System.out.println(name);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(jedis != null){
				//关闭连接
				jedis.close();
			}
		}
		
	}
	

	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext-*.xml");
	}

	@Test
	public void testJedisPool() {
		JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
			Jedis jedis = null;
			try  {
			jedis = pool.getResource();
			
			jedis.set("name", "chen");
			String name = jedis.get("name");
			System.out.println(name);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(jedis != null){
				//关闭连接
				jedis.close();
			}
		}
	}
}
