package jp.tonyu.soyprolog.core;

import java.util.Vector;

public class Pred {
	public static final Pred CUT = new Pred("!");
	Vector<Rule> rules;
	String name;
    public Pred(String name) {
        this.name = name;
        rules = new Vector<Rule>();
    }
    public String toString() {
    	return name;
    }
    /*public Goal a(List args) {
        return new Goal(this, args);
    }*/
    public Goal goal(Object... args) {
    	Vector<Object> alist=new Vector<Object>();
    	for (Object ae:args) {
    		alist.add(ae);
    	}
    	return new Goal(this, alist);
    }
}
