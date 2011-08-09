package jp.tonyu.util;

public class AttributedStringBuffer {
	public String toString() {return buf.toString();}
	StringBuffer buf=new StringBuffer();
	StringBuffer attribute=new StringBuffer(); 
	public AttributedStringBuffer append(CharSequence s,char attr) {
		buf.append(s);
		for (int i=0; i<s.length();i++) {
			attribute.append(attr);
		}
		return this;
	}
	public CharSequence extract(String filter) {
		StringBuilder res=new StringBuilder();
		for (int i=0; i<buf.length() ; i++) {
			if (filterMatch( getAttr(i), filter) ) res.append(buf.charAt(i));
		}
		return res;
	}
	public boolean filterMatch(char attr , String filter) {
		return filter.indexOf(attr)>=0;
	}
	public char getAttr(int i) {
		return attribute.charAt(i);
	}
	public CharSequence plainText() {
		return buf;
	}
	public int globalToLocal(int gPos,String filter) {
		//gPos 0123456789
		// buf=ABCDEFGHIJ
		//attr=aaabbbbbaa         gPos=4,filter="b"  -> return 1      
		//lPos 0120123434         gPos=4,filter="a"  -> return 2
		int res=0;
		if (gPos>buf.length()) gPos=buf.length();
		for (int i=0 ; i<gPos ;i++) {
			if (filterMatch(getAttr(i),filter)) res++;
		}
		return res;
	}
	public int localToGlobal(int lPos,String filter) {
		int lPosCnt=0;
		for (int i=0 ; i<buf.length() ;i++) {
			if (filterMatch(getAttr(i),filter)) lPosCnt++;
			if (lPosCnt>=lPos) return i;
		}
		return buf.length();		
	}
	public static void main(String[] args) {
		AttributedStringBuffer b=new AttributedStringBuffer();
		b.append("print ", 'g');
		b.append("こんにちは\n",'S').append("お元気ですか", 's');
		b.append("; ", 'g');
		int errPos=8;
		System.out.println("Error at "+b.globalToLocal(errPos, "sS")+" in "+b.extract("sS"));
	}
}
