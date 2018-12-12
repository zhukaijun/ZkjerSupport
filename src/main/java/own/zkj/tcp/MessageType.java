package own.zkj.tcp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhukaijun
 */
public class MessageType {
		private static ObjectMapper om=new ObjectMapper();
		private static Map<String,Object> mapper=new HashMap<String,Object>();
		@SuppressWarnings("unchecked")
		@Async()
		public static void init() {
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			Resource re = resolver.getResource("/static/data/messageCodes.json");
			try {
				mapper.putAll(om.readValue(re.getInputStream(), Map.class));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public static <T> T get(T t,String ...key){
			int start=0;
			if(StringUtils.isEmpty(key[start])) {
				Iterator<String> it = mapper.keySet().iterator();
				while(it.hasNext()) {
					key[start]=it.next();
					T data = get(t,key);
					if(data!=null) {
						return data;
					}
				}
			}
			Map obj=(Map) mapper.get(key[start++]);
			for(;start<key.length&&obj!=null;start++) {
				try {
					obj = (Map)obj.get(key[start]);
				}catch (ClassCastException e) {
					return (T)obj.get(key[start]);
				}catch (Exception e) {
					return null;
				}
			}
			return (T)obj;
		}
}
