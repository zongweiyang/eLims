package cn.labsoft.labos.framework.common.mutidatasource;

import java.io.IOException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 
 * <strong>Title : DynamicLoadBean </strong>. <br>
 * <strong>Description : Spring动态加载的Bean</strong> <br>
 * <strong>Create on : Nov 13, 2009 11:41:35 AM </strong>. <br>
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
public class DynamicLoadBean implements ApplicationContextAware {

	private ConfigurableApplicationContext applicationContext = null;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = (ConfigurableApplicationContext) applicationContext;
	}

	public ConfigurableApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void loadBean(String configLocationString) throws GlobalException {
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(
				(BeanDefinitionRegistry) getApplicationContext()
						.getBeanFactory());
		beanDefinitionReader.setResourceLoader(getApplicationContext());
		beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(
				getApplicationContext()));
		try {
			String[] configLocations = new String[] { configLocationString };
			for (int i = 0; i < configLocations.length; i++)
				beanDefinitionReader
						.loadBeanDefinitions(getApplicationContext()
								.getResources(configLocations[i]));
		} catch (BeansException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}

}
