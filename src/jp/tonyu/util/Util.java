package jp.tonyu.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Util {
	public static <T> T toSingleton(Collection<T> l) {
		return toSingleton(l.iterator());
	}
	public static <T> T toSingleton(Iterator<T> it) {
		if (!it.hasNext()) return null;
		return it.next();
	}
	public static <T> T toSingleton(T[] l) {
		if (l.length==0) return null;
		return l[0];
	}
	public static String encodeTabJoin(Object[] objects) {
		String sep="";
		StringBuffer res=new StringBuffer();
		for (Object e:objects) {
			res.append(sep);sep="\t";
			res.append(encodeTab(e+""));
		}
		return res.toString();
	}
	/*public static <T> T[] copyArray(T[] dst, List<T> src) {
		return dst;
	}*/
	private static String encodeTab(String t) {
		return t
		 .replaceAll("\\\\", "\\\\\\\\")
		 .replaceAll("\\r" , "\\\\r")
		 .replaceAll("\\t" , "\\\\t")
		 .replaceAll("\\n" , "\\\\n")
		;
	}
	
	public static String[] decodeTabJoin(String line) {
		String []lines=line.split("\t");
		String []res=new String[lines.length];
		for (int i=0 ; i<lines.length ; i++) {
			res[i]=decodeTab(lines[i]);
		}
		return res;
	}
	private static String decodeTab(String t) {
		return t
		 .replaceAll("\\\\r" , "\\r")
		 .replaceAll("\\\\n" , "\\n")
		 .replaceAll("\\\\t" , "\\t")
		 .replaceAll("\\\\\\\\", "\\\\")
	    ;
	}
}
