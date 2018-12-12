package own.zkj.tcp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import io.netty.channel.Channel;

/**
 * @author zhukaijun
 */
public class ChannelsUtil{
	private static List<Channel> list=new ArrayList<Channel>();
	private static Map<String,Channel> channelList=new HashMap<String,Channel>();

	public static void add(Channel channel) {
		list.add(channel);
	}
	public static void add(String name,Channel channel) {
		channelList.put(name, channel);
	}
	
	public static boolean has(Channel channel){
		return list.contains(channel);
	}
	public static boolean has(String name,Channel channel){
		return channelList.containsKey(name)&&channelList.containsValue(channel);
	}
	
	public static Channel get(int index) {
		try {
			Channel ch = list.get(index);
			if(ch!=null&&(ch.isActive()||ch.isOpen())) {
				return ch;
			}
			return null;
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	public static Channel get(String type) {
		try {
			Channel ch = channelList.get(type);
			if(ch!=null&&(ch.isActive()||ch.isOpen())) {
				return ch;
			}
			return null;
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static void remove(Channel o){
		list.remove(o);
	}
	
	public static void removeChannel(Channel o){
		List<String> keySet=new ArrayList<String>();
		Iterator<Entry<String, Channel>> it = channelList.entrySet().iterator();
		if(o==null||channelList.isEmpty()) {
			System.out.println(channelList);
			return;
		}
		while(it.hasNext()) {
			Entry<String, Channel> entry = it.next();
			if(entry.getValue().remoteAddress().equals(o.remoteAddress())) {
				keySet.add(entry.getKey());
			}
		}
		keySet.forEach(data->{
			channelList.remove(data);
		});
		System.out.println(channelList);
	}
	
	public static void remove(String o){
		channelList.remove(o);
	}

	public static List<Channel> getAll() {
		return list;
	}
	public static Collection<Channel> getAllChannel() {
		return channelList.values();
	}

}
