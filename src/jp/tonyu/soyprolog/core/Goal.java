package jp.tonyu.soyprolog.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Goal {
    public static final Object CUT = new Goal(Pred.CUT,new Vector());
	Pred pred;
    List args;
    public Goal(Pred pred, List args) {
    	this.pred=pred;
    	this.args=args;
    }
    public void si(List rhs) {
        pred.defs.add(  new Def( this, GoalList.create(rhs)) );
    }
    public void calls(CallbackEnvIter callback) {
        pred.defs.add( new Def(this, callback) );
    }
    @Override
    public String toString() {
    	return pred+"("+args+")";
    }
	public void si() {
        pred.defs.add(  new Def( this, GoalList.create()) );
	}

}
