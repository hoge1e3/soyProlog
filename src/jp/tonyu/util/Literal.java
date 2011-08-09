package jp.tonyu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Literal {
	public static final Pattern DQ = Pattern.compile("\"(.*)\"");
	public static final Pattern SQ = Pattern.compile("'(.*)'");

	public static String toLiteralPreserveCR(String value) {
		return "\""+value.toString().replaceAll("\\\\","\\\\\\\\")
        .replaceAll("\r", "\\\\r")
        .replaceAll("\n", "\\\\n")
        .replaceAll("\"", "\\\\\"")+"\"";
	}
	public static String toLiteral(String value) {
		return "\""+value.toString().replaceAll("\\\\","\\\\\\\\")
        .replaceAll("\r", "")
        .replaceAll("\n", "\\\\n")
        .replaceAll("\"", "\\\\\"")+"\"";
	}
	public static String fromLiteral(String literal) {
		Matcher m = DQ.matcher(literal);
		if (m.matches()) {
			return fromQuoteStrippedLiteral(m.group(1));
		}
		m = SQ.matcher(literal);
		if (m.matches()) {
			return fromQuoteStrippedLiteral(m.group(1));
		}
		return null;
	}
	public static String fromQuoteStrippedLiteral(String strippedLiteral) {
		StringBuilder b=new StringBuilder();
		for (int i=0 ; i<strippedLiteral.length(); i++) {
			char c=strippedLiteral.charAt(i);
			if (c=='\\' && i+1<strippedLiteral.length()) {
				char d=strippedLiteral.charAt(i+1);
				if (d=='n') b.append("\n");
				else if (d=='t') b.append("\t");
				else if (d=='r') b.append("\r");
				else b.append(d);
				i++;
			} else b.append(c);
		}
		return b.toString();
	}
}
