package own.zkj.tcp;

import java.util.HashMap;
import java.util.Map;

public enum NameInfoMap {
	inst;
	Map<String, NameInfo> namemaps = new HashMap<String, NameInfo>();

	private NameInfoMap() {
	}

	public void addNameInfo(String code, String cnname, String enname) {
		NameInfo info = new NameInfo();
		info.cnname = cnname;
		info.enname = enname;
		info.code = code;
		namemaps.put(code, info);
	}

	public NameInfo getByCode(String code) {
		return namemaps.get(code);
	}

	public final class NameInfo {
		String cnname;
		String enname;
		String code;
		public String getCnname() {
			return cnname;
		}
		public void setCnname(String cnname) {
			this.cnname = cnname;
		}
		public String getEnname() {
			return enname;
		}
		public void setEnname(String enname) {
			this.enname = enname;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		
	}
}
