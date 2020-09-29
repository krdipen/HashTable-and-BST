import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Assignment3 {
	public static void main(String[] args) throws IOException {
		MyHashTableDH<Pair<String,String>,Student> mapDH=new MyHashTableDH<Pair<String,String>,Student>(Integer.parseInt(args[0]));
		MyHashTableSCBST<Pair<String,String>,Student> mapSCBST=new MyHashTableSCBST<Pair<String,String>,Student>(Integer.parseInt(args[0]));
		FileReader file=new FileReader(args[2]);
		BufferedReader infile=new BufferedReader(file);
		String line;
		while((line=infile.readLine())!=null) {
			String[] word=line.split("\\ ");
			if(word[0].equals("insert")) {
				String first=word[1];
				String second=word[2];
				Pair<String,String> key=new Pair<String,String>(first,second);
				String fname=word[1];
				String lname=word[2];
				String hostel=word[3];
				String department=word[4];
				String cgpa=word[5];
				Student obj=new Student(fname,lname,hostel,department,cgpa);
				int i;
				if(args[1].equals("DH")) {
					i=mapDH.insert(key,obj);
				}
				else {
					i=mapSCBST.insert(key,obj);
				}
				if(i==-1) {
					System.out.println("E");
				}
				else {
					System.out.println(i);
				}
			}
			else if(word[0].equals("update")) {
				String first=word[1];
				String second=word[2];
				Pair<String,String> key=new Pair<String,String>(first,second);
				String fname=word[1];
				String lname=word[2];
				String hostel=word[3];
				String department=word[4];
				String cgpa=word[5];
				Student obj=new Student(fname,lname,hostel,department,cgpa);
				int i;
				if(args[1].equals("DH")) {
					i=mapDH.update(key,obj);
				}
				else {
					i=mapSCBST.update(key,obj);
				}
				if(i==-1) {
					System.out.println("E");
				}
				else {
					System.out.println(i);
				}
			}
			else if(word[0].equals("delete")) {
				String first=word[1];
				String second=word[2];
				Pair<String,String> key=new Pair<String,String>(first,second);
				int i;
				if(args[1].equals("DH")) {
					i=mapDH.delete(key);
				}
				else {
					i=mapSCBST.delete(key);
				}
				if(i==-1) {
					System.out.println("E");
				}
				else {
					System.out.println(i);
				}
			}
			else if(word[0].equals("contains")) {
				String first=word[1];
				String second=word[2];
				Pair<String,String> key=new Pair<String,String>(first,second);
				boolean b;
				if(args[1].equals("DH")) {
					b=mapDH.contains(key);
				}
				else {
					b=mapSCBST.contains(key);
				}
				if(b) {
					System.out.println("T");
				}
				else {
					System.out.println("F");
				}
			}
			else if(word[0].equals("get")) {
				String first=word[1];
				String second=word[2];
				Pair<String,String> key=new Pair<String,String>(first,second);
				Student obj;
				if(args[1].equals("DH")) {
					try {
						obj=mapDH.get(key);
						System.out.println(obj.toString());
					}
					catch(NotFoundException e) {
						System.out.println("E");
					}
				}
				else {
					try {
						obj=mapSCBST.get(key);
						System.out.println(obj.toString());
					}
					catch(NotFoundException e) {
						System.out.println("E");
					}
				}
			}
			else if(word[0].equals("address")) {
				String first=word[1];
				String second=word[2];
				Pair<String,String> key=new Pair<String,String>(first,second);
				String str;
				if(args[1].equals("DH")) {
					try {
						str=mapDH.address(key);
						System.out.println(str);
					}
					catch(NotFoundException e) {
						System.out.println("E");
					}
				}
				else {
					try {
						str=mapSCBST.address(key);
						System.out.println(str);
					}
					catch(NotFoundException e) {
						System.out.println("E");
					}
				}
			}
			else {
				System.out.println("E");
			}
		}
		infile.close();
	}
}