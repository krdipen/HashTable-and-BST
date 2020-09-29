public class NodeSCBST<K,T> {
	K key;
	T obj;
	NodeSCBST<K,T> lnode;
	NodeSCBST<K,T> rnode;
	public NodeSCBST(K key,T obj) {
		this.key=key;
		this.obj=obj;
		this.lnode=null;
		this.rnode=null;
	}
	public K key() {
		return key;
	}
	public T obj() {
		return obj;
	}
	public NodeSCBST<K,T> lnode() {
		return lnode;
	}
	public NodeSCBST<K,T> rnode() {
		return rnode;
	}
	public void loadk(K key) {
		this.key=key;
	}
	public void loado(T obj) {
		this.obj=obj;
	}
	public void loadl(NodeSCBST<K,T> lnode) {
		this.lnode=lnode;
	}
	public void loadr(NodeSCBST<K,T> rnode) {
		this.rnode=rnode;
	}
}