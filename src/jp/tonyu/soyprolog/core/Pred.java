package jp.tonyu.soyprolog.core;

import java.util.Vector;

public class Pred {
	Vector<Def> defs;
	String name;
    public Pred(String name) {
        this.name = name;
        defs = new Vector<Def>();
    }
    public String toString() {
    	return name;
    }
    public Object a(Object args) {
        return new Goal(this, args);
    }
}
