package own.zkj.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author zhukaijun
 * Spring容器获取工具类
 */
public class ZSpringContentHelper implements ApplicationContextAware{
	
	private static ApplicationContext applicationContenxt;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ZSpringContentHelper.setApplicationContenxt(applicationContext);
	}
	
	public static <T> T getBean(Class<T> c) {
		return getApplicationContenxt().getBean(c);
	}
	
	public static String getAttr(String k) {
		return getApplicationContenxt().getEnvironment().getProperty(k);
	}

	public static ApplicationContext getApplicationContenxt() {
		return applicationContenxt;
	}

	public static void setApplicationContenxt(ApplicationContext applicationContenxt) {
		ZSpringContentHelper.applicationContenxt = applicationContenxt;
	}

}
