package jp.tonyu.soyprolog.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Cons  {
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
		if (ary==null || ary.size()<=i) return null;
		return new Cons(ary.get(i),create(ary,i+1));
	}
	@Override
	public String toString() {
		return "("+car+"."+cdr+")";
	}
	/*public Object get(int i) {
		if (i==0) return car;
		if (cdr==null) return null;
		if (cdr instanceof Cons) {
			Cons c = (Cons) cdr;
			return c.get(i-1);
		}
		return cdr;
	}
	public int size() {
		if (cdr==null) return 1;
		if (cdr instanceof Cons) {
			Cons c = (Cons) cdr;
			return c.size()+1;
		}
		return 2;
	}*/
}
