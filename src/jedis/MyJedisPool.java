package jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class MyJedisPool extends JedisPool {
	/**
	 * 构建redis连接池
	 * 
	 * @param ip
	 * @param port
	 * @return JedisPool
	 */
	private static MyJedisPool pool = null;

	private MyJedisPool() {
	}

	private MyJedisPool(JedisPoolConfig config, String ip, int port) {
		super(config, ip, port);
	}
	private MyJedisPool(final JedisPoolConfig config, final String host, int port,
		      int timeout, final String password, final int database) {
		super(config, host, port,timeout,password,database);
	}
	public static MyJedisPool getinstance() {
		if (pool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
			// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
			config.setMaxTotal(100);
			// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
			config.setMaxIdle(5);
			// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
			config.setMaxWaitMillis(1000 * 60);
			// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
			config.setTestOnBorrow(true);
			pool = new MyJedisPool(config, "192.168.7.212", 6479,Protocol.DEFAULT_TIMEOUT,"longrise",Protocol.DEFAULT_DATABASE);
		}
		return pool;
	}
	// public MyJedisPool getPool() {
	//
	// }

	/**
	 * 回收到连接池
	 * 
	 * @param redis
	 */
	public void returnResource(Jedis redis) {
		if (redis != null) {
			super.returnResource(redis);
		}
	}

	/**
	 * 关闭连接
	 * 
	 * @param redis
	 */
	public void returnBrokenResource(Jedis redis) {
		if (redis != null) {
			super.returnBrokenResource(redis);
		}
	}
}
