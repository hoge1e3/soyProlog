package jp.tonyu.util;

public class Conv {
	public static <T> T convert(Object src, Class<T> type) {
		if (type.isAssignableFrom(Integer.class)) {
			return (T)(Object)Integer.parseInt(src+"");
		}
		return (T)src;
	}
	public static void main(String[] args) {
		int x=convert("3",Integer.class);
		System.out.println(x+5);
		System.out.println((String.class).isAssignableFrom(Object.class));
		System.out.println((Object.class).isAssignableFrom(String.class));
	}
}
