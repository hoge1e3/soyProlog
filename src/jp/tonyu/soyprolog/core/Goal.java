package jp.tonyu.soyprolog.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Goal {
    public static final Goal CUT = new Goal(Pred.CUT,new Vector());
	Pred pred;
    List args;
    public Goal(Pred pred, List args) {
    	this.pred=pred;
    	this.args=args;
    }
    /*public Goal(Pred pred, Object[] args) {
    	this.pred=pred;
    	this.args=args;
    }*/

    public void trueIf(List rhs) {
        pred.rules.add(  new Rule( this, GoalList.create(rhs)) );
    }
    public void trueIf(Goal... rhs) {
        pred.rules.add(  new Rule( this, GoalList.create(rhs)) );
    }
    public void calls(NativeSubgoal callback) {
        pred.rules.add( new Rule(this, callback) );
    }
    @Override
    public String toString() {
    	return pred+"("+args+")";
    }
	public void isTrue() {
        pred.rules.add(  new Rule( this, GoalList.create()) );
	}

}
