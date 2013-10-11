import java.util.ArrayList;
import java.util.HashMap;


public class Data<E> {
	private HashMap<Integer,E> map;
	private HashMap<Integer,ArrayList<Integer>> link;
	
	public Data() {
		map = new HashMap<>();
		link = new HashMap<>();
		hashCode();
	}

	public boolean hasContent(E obj) {
		return map.containsValue(obj);
	}
	
	public Integer insert(E obj) {
		if (!map.containsValue(obj)){
			map.put(obj.hashCode(), obj);
			link.put(obj.hashCode(),new ArrayList<Integer>());
			return obj.hashCode();
		}
		return null;
	}

	public E remove(Integer key) {
		if (map.containsKey(key)){
			if (link.containsKey(key)) link.remove(key);
			return map.remove(key);
		}
		return null;
	}
	
	public E getContent(Integer key) {
		if (map.containsKey(key)){
			return map.get(key);
		}
		return null;
	}

	public Integer getKey(E obj) {
		if (map.containsValue(obj)) {
			return obj.hashCode();
		}
		return null;
	}
	
	public boolean insertLinkReference(Integer key,Integer reference) {
		boolean contains = map.containsKey(key);
		if (contains) {
			link.get(key).add(reference);
		}
		return contains;
	}
	
	public boolean removeLinkReference(Integer key,Integer reference) {
		boolean contains = map.containsKey(key) && link.get(key).contains(reference);
		if (contains) {
			contains = link.get(key).remove(reference);
		}
		return contains;
	}
	
	public ArrayList<Integer> getLinkList(Integer key) {
		if (map.containsKey(key)) {
			return link.get(key);
		}
		return null;
	}
}
