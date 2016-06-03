package growth;

import org.apache.commons.lang3.RandomStringUtils;

public class Global {
  /**
   * 禁止外部新建Global对象
   */
   private Global(){};
   private static Global global = null;
   public static Global  getInstance(){
	   if(global==null)
		   global = new Global();
	   return global;
   }
   public  String UUID_32(){
   	return RandomStringUtils.randomAlphanumeric(32);
   }
   public  String UUID_4(){
   	return RandomStringUtils.randomAlphanumeric(4);
   }
   public  String UUID_5(){
   	return RandomStringUtils.randomAlphanumeric(5);
   }
   public  String UUID_16(){
   	return RandomStringUtils.randomAlphanumeric(16);
   }
   public  String UUID_64(){
   	return RandomStringUtils.randomAlphanumeric(64);
   }
}
