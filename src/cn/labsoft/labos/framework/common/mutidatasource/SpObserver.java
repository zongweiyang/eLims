/**
 * 
 */
package cn.labsoft.labos.framework.common.mutidatasource;

/**
 * 
 * <strong>Title : SpObserver </strong>. <br>
 * <strong>Description : 数据源变更观察者</strong> <br>
 * <strong>Create on : Nov 13, 2009 11:41:35 AM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 *
 * @author TonyLee<br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@SuppressWarnings("unchecked")
public class SpObserver {

	private static ThreadLocal local = new ThreadLocal();

	public static void putSp(String sp) {
		local.set(sp);
	}

	public static String getSp() {
		return (String) local.get();
	}
}