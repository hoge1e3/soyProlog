package jp.tonyu.debug;

public interface LogCat {
	public void d(Object tag, Object content);
	public void e(Object tag, Object content);
	public void v(Object tag, Object content);
	public void w(Object tag, Object content);
}
