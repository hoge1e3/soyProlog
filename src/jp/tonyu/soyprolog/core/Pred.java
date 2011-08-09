package jp.tonyu.soyprolog.core;

import java.util.List;
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
    public Goal a(List args) {
        return new Goal(this, args);
    }
}
