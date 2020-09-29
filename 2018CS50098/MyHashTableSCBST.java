import java.lang.Math;

public class MyHashTableSCBST<K extends Comparable<K>, T> implements MyHashTable_<K, T> {
	int hashtableSize;
	NodeSCBST<K,T>[] hashtable;
	public MyHashTableSCBST(int hashtableSize) {
		this.hashtableSize=hashtableSize;
		hashtable=new NodeSCBST[this.hashtableSize];
	}
	public static long djb2(String str, int hashtableSize) { 
	    long hash = 5381; 
	    for (int i = 0; i < str.length(); i++) { 
	        hash = ((hash << 5) + hash) + str.charAt(i); 
	    } 
	    return Math.abs(hash) % hashtableSize; 
	}
	public int insert(K key, T obj) {
		NodeSCBST<K,T> node=new NodeSCBST<K,T>(key,obj);
        int h1=(int)djb2(key.toString(),hashtableSize);
		NodeSCBST<K,T> iterator=hashtable[h1%hashtableSize];
		int i=1;
		if(iterator==null) {
			hashtable[h1%hashtableSize]=node;
			return i;
		}
		else {
			while(true) {
				i++;
				if((iterator.key()).compareTo(key)>0) {
					if(iterator.lnode()==null) {
						iterator.loadl(node);
						return i;
					}
					else {
						iterator=iterator.lnode();
					}
				}
				else if((iterator.key()).compareTo(key)<0) {
					if(iterator.rnode()==null)
					{
						iterator.loadr(node);
						return i;
					}
					else
					{
						iterator=iterator.rnode();
					}
				}
				else {
						return -1;
				}
			}
		}
	}
	public int update(K key, T obj) {
		int h1=(int)djb2(key.toString(),hashtableSize);
		NodeSCBST<K,T> iterator=hashtable[h1%hashtableSize];
		int i=0;
		if(iterator==null) {
			return -1;
		}
		else {
			while(true) {
				i++;
				if((iterator.key()).compareTo(key)>0) {
					if(iterator.lnode()==null) {
						return -1;
					}
					else {
						iterator=iterator.lnode();
					}
				}
				else if((iterator.key()).compareTo(key)<0) {
					if(iterator.rnode()==null)
					{
						return -1;
					}
					else
					{
						iterator=iterator.rnode();
					}
				}
				else {
						iterator.loado(obj);
						return i;
				}
			}
		}
	}
	public int delete(K key) {
		int h1=(int)djb2(key.toString(),hashtableSize);
		String previous_side=null;
		NodeSCBST<K,T> previous=hashtable[h1%hashtableSize];
		NodeSCBST<K,T> iterator=hashtable[h1%hashtableSize];
		int i=0;
		if(iterator==null) {
			return -1;
		}
		else {
			while(true) {
				i++;
				if((iterator.key()).compareTo(key)>0) {
					if(iterator.lnode()==null) {
						return -1;
					}
					else {
						previous_side="left";
						previous=iterator;
						iterator=previous.lnode();
					}
				}
				else if((iterator.key()).compareTo(key)<0) {
					if(iterator.rnode()==null)
					{
						return -1;
					}
					else
					{
						previous_side="right";
						previous=iterator;
						iterator=previous.rnode();
					}
				}
				else {
					if((iterator.lnode()==null)&&(iterator.rnode()==null)) {
						if(i==1) {
							hashtable[h1%hashtableSize]=null;
						}
						else if(previous_side.equals("left")) {
							previous.loadl(null);
						}
						else {
							previous.loadr(null);
						}
					}
					else if((iterator.lnode()!=null)&&(iterator.rnode()==null)) {
						if(i==1) {
							hashtable[h1%hashtableSize]=iterator.lnode();
						}
						else if(previous_side.equals("left")) {
							previous.loadl(iterator.lnode());
						}
						else {
							previous.loadr(iterator.lnode());
						}
						i++;
					}
					else if((iterator.lnode()==null)&&(iterator.rnode()!=null)) {
						if(i==1) {
							hashtable[h1%hashtableSize]=iterator.rnode();
						}
						else if(previous_side.equals("left")) {
							previous.loadl(iterator.rnode());
						}
						else {
							previous.loadr(iterator.rnode());
						}
						i++;
					}
					else {
						previous=iterator;
						NodeSCBST<K,T> previous2=iterator;
						iterator=iterator.rnode();
						i++;
						boolean b=false;
						while(iterator.lnode()!=null) {
							b=true;
							previous2=iterator;
							iterator=iterator.lnode();
							i++;
						}
						previous.loadk(iterator.key());
						previous.loado(iterator.obj());
						if(iterator.rnode()==null) {
							if(b) {
								previous2.loadl(null);
							}
							else {
								previous2.loadr(null);
							}
						}
						else {
							if(b) {
								previous2.loadl(iterator.rnode());
							}
							else {
								previous2.loadr(iterator.rnode());
							}
							i++;
						}
					}
					return i;
				}
			}
		}
	}
	public boolean contains(K key) {
		int h1=(int)djb2(key.toString(),hashtableSize);
		NodeSCBST<K,T> iterator=hashtable[h1%hashtableSize];
		if(iterator==null) {
			return false;
		}
		else {
			while(true) {
				if((iterator.key()).compareTo(key)>0) {
					if(iterator.lnode()==null) {
						return false;
					}
					else {
						iterator=iterator.lnode();
					}
				}
				else if((iterator.key()).compareTo(key)<0) {
					if(iterator.rnode()==null)
					{
						return false;
					}
					else
					{
						iterator=iterator.rnode();
					}
				}
				else {
					return true;
				}
			}
		}
	}
	public T get(K key) throws NotFoundException {
		int h1=(int)djb2(key.toString(),hashtableSize);
		NodeSCBST<K,T> iterator=hashtable[h1%hashtableSize];
		if(iterator==null) {
			throw new NotFoundException();
		}
		else {
			while(true) {
				if((iterator.key()).compareTo(key)>0) {
					if(iterator.lnode()==null) {
						throw new NotFoundException();
					}
					else {
						iterator=iterator.lnode();
					}
				}
				else if((iterator.key()).compareTo(key)<0) {
					if(iterator.rnode()==null)
					{
						throw new NotFoundException();
					}
					else
					{
						iterator=iterator.rnode();
					}
				}
				else {
						return iterator.obj();
				}
			}
		}
	}
	public String address(K key) throws NotFoundException {
		int h1=(int)djb2(key.toString(),hashtableSize);
		NodeSCBST<K,T> iterator=hashtable[h1%hashtableSize];
		String sofar="";
		if(iterator==null) {
			throw new NotFoundException();
		}
		else {
			while(true) {
				if((iterator.key()).compareTo(key)>0) {
					if(iterator.lnode()==null) {
						throw new NotFoundException();
					}
					else {
						sofar=sofar+"L";
						iterator=iterator.lnode();
					}
				}
				else if((iterator.key()).compareTo(key)<0) {
					if(iterator.rnode()==null)
					{
						throw new NotFoundException();
					}
					else
					{
						sofar=sofar+"R";
						iterator=iterator.rnode();
					}
				}
				else {
					return (h1%hashtableSize)+"-"+sofar;
				}
			}
		}
	}
}