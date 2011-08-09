package jp.tonyu.debug;



public class TLog {
	static LogCat logc=new LogCat() {
		
		@Override
		public void w(Object tag, Object content) {
			System.out.println("["+tag+"]"+content);
		}
		
		@Override
		public void v(Object tag, Object content) {
			System.out.println("["+tag+"]"+content);
		}
		
		@Override
		public void e(Object tag, Object content) {
			System.out.println("["+tag+"]"+content);
		}
		
		@Override
		public void d(Object tag, Object content) {
			System.out.println("["+tag+"]"+content);
		}
	};
	public static void setLogCat(LogCat lc) {
		logc=notNull(lc,"setLogCat lc==null");
	}
	public static void d(Object tag,Object content) {
		logc.d(tag, content);
	}

	public static Object die(String string) {
		throw new RuntimeException(string);
		
	}

	public static void w(Object tag, Object content) {
		logc.w(tag, content);
	}

	public static <T> T notNull(T value,String msg) {
		if (value==null) die(msg);
		return value;
	}
}
