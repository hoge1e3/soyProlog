package jp.tonyu.soyprolog.core;

import java.util.List;

public class Goal {
    Pred pred;
    Object args;
    public Goal(Pred pred, Object args) {
    	this.pred=pred;
    	this.args=args;
    }
    public void si(List rhs) {
        pred.defs.add(  new Def( this, Cons.create(rhs)) );
    }
    public void calls(Object callback) {
        pred.defs.add( new Def(this, callback) );
    }
    @Override
    public String toString() {
    	return pred+" "+args;
    }

}
