package jp.tonyu.util;

public interface FormatStringHandler {
	public int proc(CharSequence format, Object value, StringBuffer buf);
}
