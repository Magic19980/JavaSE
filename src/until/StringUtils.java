package until;



/**
 *字符串工具类
 * 
 * @author Evil
 * @creatTime：2021年1月5日  下午5:08:46
 *
 */
public class StringUtils {

	private StringUtils() {
		
	}
	
	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str==null ||str.trim().length()==0;
	}
}
