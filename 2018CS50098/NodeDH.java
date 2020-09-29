public class NodeDH<K, T> {
	K key;
	T obj;
	public NodeDH(K key, T obj) {
		this.key=key;
		this.obj=obj;
	}
	public K key() {
		return key;
	}
	public T obj() {
		return obj;
	}
}