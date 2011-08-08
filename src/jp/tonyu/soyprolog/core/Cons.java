package jp.tonyu.soyprolog.core;

import java.util.List;

public class Cons {
	Object car;
	Object cdr;
	public static Cons create(List ary) {
		return create(ary,0);
	}
	public Cons(Object car, Object cdr) {
		super();
		this.car = car;
		this.cdr = cdr;
	}
	public static Cons create(List ary, int i) {
		if (ary.size()<=i) return null;
		return new Cons(ary.get(i),create(ary,i+1));
	}
	@Override
	public String toString() {
		return "("+car+" "+cdr+")";
	}
}
