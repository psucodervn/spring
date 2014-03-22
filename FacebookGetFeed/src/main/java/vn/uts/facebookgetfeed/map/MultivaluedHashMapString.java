package vn.uts.facebookgetfeed.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.MultiValueMap;

public class MultivaluedHashMapString implements MultiValueMap<String, String> {

	Map<String, List<String>> map;

	public MultivaluedHashMapString() {
		map = new HashMap<String, List<String>>();
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		if (key instanceof String)
			return map.containsKey((String) key);
		else
			return false;
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public List<String> get(Object key) {
		return map.get(key);
	}

	@Override
	public List<String> put(String key, List<String> value) {
		return map.put(key, value);
	}

	@Override
	public List<String> remove(Object key) {
		return map.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends List<String>> m) {
		map.putAll(m);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Set<String> keySet() {
		return map.keySet();
	}

	@Override
	public Collection<List<String>> values() {
		return map.values();
	}

	@Override
	public Set<java.util.Map.Entry<String, List<String>>> entrySet() {
		return map.entrySet();
	}

	@Override
	public void add(String key, String value) {
		if (map.containsKey(key)) {
			map.get(key).add(value);
		} else {
			List<String> vs = new ArrayList<String>();
			vs.add(value);
			map.put(key, vs);
		}
	}

	@Override
	public String getFirst(String key) {
		if (map.containsKey(key)) {
			List<String> vs = (map.get(key));
			if (vs.size() > 0) {
				return vs.get(0);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public void set(String key, String value) {
		if (!map.containsKey(key))
			map.put(key, new ArrayList<String>());
		List<String> vs = map.get(key);
		vs.add(value);
	}

	@Override
	public void setAll(Map<String, String> m) {
		for (String key : m.keySet()) {
			set(key, m.get(key));
		}
	}

	@Override
	public Map<String, String> toSingleValueMap() {
		Map<String, String> m = new HashMap<String, String>();
		for (String key : map.keySet()) {
			for (String value : map.get(key)) {
				m.put(key, value);
			}
		}
		return m;
	}

}
