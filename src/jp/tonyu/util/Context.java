package jp.tonyu.util;

public class Context<T>  {
	private ThreadLocal<T> t=new ThreadLocal<T>();
	public T get() {
		T res = t.get();
		if (res==null) throw new RuntimeException("Not entered yet.");
		return res;
	}
	public void enter(T val , Runnable action ) {
		T old=t.get();
		t.set(val);
		action.run();
		t.set(old);
	}
}
