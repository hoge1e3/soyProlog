package jp.tonyu.soyprolog.core;

public interface EnvIter {

	EnvIter PRINT = new EnvIter() {
		@Override
		public void yield(Env arg0) {
			System.out.println(arg0);
		}
	};
	void yield(Env env);

}
