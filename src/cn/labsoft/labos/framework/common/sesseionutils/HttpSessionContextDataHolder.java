package cn.labsoft.labos.framework.common.sesseionutils;
import javax.servlet.http.HttpSession;



public class HttpSessionContextDataHolder {
	  //  �����߳�    
    private static ThreadLocal contextHolder = new ThreadLocal();

    /**
     * ���� session
     * @param session
     */
    public static void setContext(HttpSession session) 
    {
        contextHolder.set(session); 
    }
	/**
	 * �õ� session
	 * @return
	 */
    public static HttpSession getContext() 
    {
        return (HttpSession) contextHolder.get();
    }
    /**
     * ���session
     *
     */
    public static void clearContext() 
    {
        contextHolder.set(null);
    }
}
