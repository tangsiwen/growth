package jedis;

import bo.BlogMsg;
import growth.Global;
import net.sf.json.JSONObject;


public class Test {

	public static void main(String[] args) {
		//JSONObject json = new JSONObject();
		//json.element("id",Global.getInstance().UUID_16());
		BlogMsg bm = new BlogMsg();
		bm.setId(Global.getInstance().UUID_16());
		bm.setTitle("啊实打实大师的");
		JSONObject json = JSONObject.fromObject(bm);
		System.out.println(json.toString());
		
//		RedisAPI redis = new RedisAPI(RedisAPI.TABLE_SIX);
//		
//		System.out.println(redis.HGET("blog", "title"));
		//redis.HSET("blog", "title", "啊实打实大撒实打实大师大师的");
	}
}
