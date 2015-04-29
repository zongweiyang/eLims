package cn.labsoft.labos.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import cn.labsoft.labos.framework.common.exception.GlobalException;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemakerUtils {
	/**
	 * 
	 * 生成静态页面主方法
	 * 
	 * @param context
	 *            ServletContext
	 * @param data
	 *            一个Map的数据结果集
	 * @param templatePath
	 *            ftl模版路径
	 * @param targetHtmlPath
	 *            生成静态页面的路径
	 * @throws GlobalException 
	 */
	public static void crateHTML(ServletContext context,
			Map<String, Object> data, String templatePath, String targetHtmlPath) throws GlobalException {
		Configuration freemarkerCfg = new Configuration();
		// 加载模版文件的位置
		freemarkerCfg.setServletContextForTemplateLoading(context,
				"WEB-INF/templates");
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
		try {
			// 指定模版路径
			Template template = freemarkerCfg
					.getTemplate(templatePath, "UTF-8");
			template.setEncoding("UTF-8");
			// 静态页面路径
			String htmlPath = context.getRealPath("/html") + "/"
					+ targetHtmlPath;
			File htmlFile = new File(htmlPath);
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(htmlFile), "UTF-8"));
			// 处理模版
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

	/**
	 * 
	 * 生成友情链接的静态页index_link.html
	 * 
	 * @param context
	 *            ServletContext
	 * @param data
	 *            数据集
	 * @throws GlobalException 
	 */
	public static void createIndexFriendLink(ServletContext context,
			Map<String, Object> data) throws GlobalException {
		crateHTML(context, data, "center.ftl", "center.html");
	}
}
