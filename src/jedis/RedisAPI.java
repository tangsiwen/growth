package jedis;

import java.util.Map;

import redis.clients.jedis.Jedis;

/**
 * Redis操作接口
 * 
 */
public class RedisAPI implements RedisInterface {
	/**
	 * 从默认表中获取数据
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = MyJedisPool.getinstance().getResource();
			jedis.select(index);
			value = jedis.get(key);
		} catch (Exception e) {
			// 释放redis对象
			e.printStackTrace();
		} finally {
			// 返还到连接池
			if(jedis!=null)
				jedis.close();
		}
		return value;
	}

	/**
	 * 从指定的表中获取数据
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public String get(String key, int index) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(index);
			value = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis!=null)
				jedis.close();
		}

		return value;
	}

	@Override
	public String set(String key, String value) {
		String ret = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(index);
			ret = jedis.set(key,value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis!=null)
				jedis.close();
		}

		return ret;
	}

	@Override
	public String set(String key, String value, int index) {
		String ret = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(index);
			ret = jedis.set(key,value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis!=null)
				jedis.close();
		}
		return ret;
	
	}
	@Override
	public String set(String key, int expire, String value) {
		String ret = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(index);
			ret = jedis.set(key,value);
			jedis.expire(key, expire);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis!=null)
				jedis.close();
		}
		return ret;
	}

	@Override
	public String set(String key, int expire, String value, int index) {
		String ret = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(index);
			ret = jedis.set(key,value);
			jedis.expire(key, expire);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis!=null)
				jedis.close();
		}
		return ret;
	}
	@Override
	public Long delete(String key) {
		Long ret = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(index);
			ret = jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis!=null)
				jedis.close();
		}
		return ret;
	}

	@Override
	public Long delete(String key, int index) {
		Long ret = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(index);
			ret = jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis!=null)
				jedis.close();
		}
		return ret;
	}

	@Override
	public Long append(String key, String value) {
		Long ret = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(index);
			ret = jedis.append(key,value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis!=null)
				jedis.close();
		}
		return ret;
	}

	@Override
	public Long append(byte[] key, byte[] value) {
		Long ret = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(index);
			ret = jedis.append(key,value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis!=null)
				jedis.close();
		}
		return ret;
	}

	@Override
	public String HGET(String key, String field) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(index);
			value = jedis.hget(key, field);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis!=null)
				jedis.close();
		}

		return value;

	}

	@Override
	public Long HSET(String key, String field, String value) {
		Long ret = 0L;
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(index);
			ret = jedis.hset(key, field, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis!=null)
				jedis.close();
		}
		return ret;
	}

	@Override
	public Long HDEL(String key, String... fields) {
		Long ret = 0L;
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(index);
			ret = jedis.hdel(key, fields);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis!=null)
				jedis.close();
		}
		return ret;

	}

	@Override
	public Map<String, String> HGETALL(String key) {
		Map<String, String> ret = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.select(index);
			ret = jedis.hgetAll(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis!=null)
				jedis.close();
		}
		return ret;
	}

	@Override
	public void select(int index) {
		this.index = index;
	}

//	private void returnBrokenResource(Jedis jedis) {
//		MyJedisPool.getinstance().returnBrokenResource(jedis);
//	}
//	private void returnResource(Jedis jedis) {
//		MyJedisPool.getinstance().returnResource(jedis);
//	}
	private Jedis getResource() {
		return MyJedisPool.getinstance().getResource();
	}
	public RedisAPI(int index){
		this.index = index;
	}
	private int index = RedisAPI.TABLE_DEFAULT;

}
