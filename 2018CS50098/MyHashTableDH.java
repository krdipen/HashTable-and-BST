import java.lang.Math;

public class MyHashTableDH<K extends Comparable<K>, T> implements MyHashTable_<K, T> {
	int hashtableSize;
	NodeDH<K,T>[] hashtable;
	public MyHashTableDH(int hashtableSize) {
		this.hashtableSize=hashtableSize;
		hashtable=new NodeDH[this.hashtableSize]; 
	}
	public static long djb2(String str, int hashtableSize) { 
	    long hash = 5381; 
	    for (int i = 0; i < str.length(); i++) { 
	        hash = ((hash << 5) + hash) + str.charAt(i); 
	    } 
	    return Math.abs(hash) % hashtableSize; 
	}
	public static long sdbm(String str, int hashtableSize) { 
	    long hash = 0; 
	    for (int i = 0; i < str.length(); i++) { 
	        hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash; 
	    } 
	    return Math.abs(hash) % (hashtableSize - 1) + 1; 
	}
	public int insert(K key, T obj) {
		if(contains(key)) {
			return -1;
		}
		else {
			int h1=(int)djb2(key.toString(),hashtableSize);
	        int h2=(int)sdbm(key.toString(),hashtableSize);
			NodeDH<K,T> stu;
			int i=0;
			while(true) {
				stu=hashtable[(h1+i*h2)%hashtableSize];
				i++;
				if(stu==null) {
					NodeDH<K,T> temp=new NodeDH<K,T>(key,obj);
					hashtable[(h1+(i-1)*h2)%hashtableSize]=temp;
					return i;
				}
				if(i==hashtableSize) {
					return -1;
				}
			}
		}
	}
	public int update(K key, T obj) {
		int h1=(int)djb2(key.toString(),hashtableSize);
        int h2=(int)sdbm(key.toString(),hashtableSize);
        NodeDH<K,T> stu;
		int i=0;
		while(true) {
			stu=hashtable[(h1+i*h2)%hashtableSize];
			i++;
			if(stu!=null) {
				if((stu.key()).compareTo(key)==0) {
					NodeDH<K,T> temp=new NodeDH<K,T>(key,obj);
					hashtable[(h1+(i-1)*h2)%hashtableSize]=temp;
					return i;
				}
			}
			if(i==hashtableSize) {
				return -1;
			}
		}
	}
	public int delete(K key) {
		int h1=(int)djb2(key.toString(),hashtableSize);
        int h2=(int)sdbm(key.toString(),hashtableSize);
        NodeDH<K,T> stu;
		int i=0;
		while(true) {
			stu=hashtable[(h1+i*h2)%hashtableSize];
			i++;
			if(stu!=null) {
				if((stu.key()).compareTo(key)==0) {
					hashtable[(h1+(i-1)*h2)%hashtableSize]=null;
					return i;
				}
			}
			if(i==hashtableSize) {
				return -1;
			}
		}
	}
	public boolean contains(K key) {
		int h1=(int)djb2(key.toString(),hashtableSize);
        int h2=(int)sdbm(key.toString(),hashtableSize);
        NodeDH<K,T> stu;
		int i=0;
		while(true) {
			stu=hashtable[(h1+i*h2)%hashtableSize];
			i++;
			if(stu!=null) {
				if((stu.key()).compareTo(key)==0) {
					return true;
				}
			}
			if(i==hashtableSize) {
				return false;
			}
		}
	}
	public T get(K key) throws NotFoundException {
		int h1=(int)djb2(key.toString(),hashtableSize);
        int h2=(int)sdbm(key.toString(),hashtableSize);
        NodeDH<K,T> stu;
		int i=0;
		while(true) {
			stu=hashtable[(h1+i*h2)%hashtableSize];
			i++;
			if(stu!=null) {
				if((stu.key()).compareTo(key)==0) {
					return stu.obj();
				}
			}
			if(i==hashtableSize) {
				throw new NotFoundException();
			}
		}
	}
	public String address(K key) throws NotFoundException {
		int h1=(int)djb2(key.toString(),hashtableSize);
        int h2=(int)sdbm(key.toString(),hashtableSize);
        NodeDH<K,T> stu;
		int i=0;
		while(true) {
			stu=hashtable[(h1+i*h2)%hashtableSize];
			if(stu!=null) {
				if((stu.key()).compareTo(key)==0) {
					return ((h1+i*h2)%hashtableSize)+"";
				}
			}
			i++;
			if(i==hashtableSize) {
				throw new NotFoundException();
			}
		}
	}
}