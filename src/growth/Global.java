package growth;
public class Global {
  /**
   * 禁止外部新建Global对象
   */
   private Global(){};
   private Global global = null;
   public Global getInstance(){
	   if(global==null)
		   global = new Global();
	   return global;
   }
}
