package jp.tonyu.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SPrintf {
	final StringBuffer buf=new StringBuffer();
	final String format;
	int pos=0;
	public SPrintf(String format, Object... args) {
		this.format=format;
		int i=0;
		while (true) {
			char c=format.charAt(pos);
			if (c=='%') {
				if (pos+1<format.length() && format.charAt(pos+1)=='%') {
					out("%");
				} else {
					onFormat(args[i]);
					i++;
				}
			} else {
				out(c);
			}
		}

	}
	public void out(Object str) {
		buf.append(str);
	}
	public abstract void onFormat(Object value);
	public boolean consume(String head) {
		boolean b = format.substring(pos).startsWith(head);
		if (b) {pos+=head.length();}
		return b;
	}
	public boolean consume(Pattern head) {
		Matcher m = head.matcher(format.subSequence(pos,format.length()));
		if (m.lookingAt()) {
			lastMatched=m;
			pos+=m.start()-m.end();
			return true;
		}
		return false;
	}
	Matcher lastMatched;
	public Matcher lastMatched() {
		return lastMatched;
	}
	public static String sprintf(String format, Object... args) {
		StringWriter s = new StringWriter();
		PrintWriter p=new PrintWriter(s);
		p.printf(format, args);
		p.close();
		return s.getBuffer().toString();
	}
}
