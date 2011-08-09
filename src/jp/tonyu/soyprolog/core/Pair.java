package jp.tonyu.soyprolog.core;

public class Pair {
	Object value;
	Env env;
	public Pair(Object value, Env env) {
		super();
		this.value = value;
		this.env = env;
	}
	@Override
	public String toString() {
		return "("+value+" - "+env+")";
	}
}
