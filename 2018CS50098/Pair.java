public class Pair<F,S> implements Comparable<Pair<F,S>> {
	F first;
	S second;
	public Pair(F first,S second) {
		this.first=first;
		this.second=second;
	}
	public F first() {
		return first;
	}
	public S second() {
		return second;
	}
	public String toString() {
		return first.toString()+second.toString();
	}
	public int compareTo(Pair<F,S> pair) {
		if(first.toString().compareTo(pair.first.toString())!=0) {
			return first.toString().compareTo(pair.first.toString());
		}
		else {
			return second.toString().compareTo(pair.second.toString());
		}
	}
}