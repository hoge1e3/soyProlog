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
    public List<Rule> getRules() {
    	return pred.getRules(this);
    }
    public String index() {
    	if (args.size()==0) return null;
    	Object a=args.get(0);
    	StringBuilder b=new StringBuilder();
    	if (a instanceof Symbol) {
    		return null;
    	} else if (a instanceof String) {
    		String s = (String) a;
    		b.append(s+"\t");
    	} else if (a instanceof Number) {
    		Number n = (Number) a;
    		b.append(n+"\t");
    	} else {
    		b.append(System.identityHashCode(a)+"\t");
    	}
    	return b+"";
    }
    /*public Goal(Pred pred, Object[] args) {
    	this.pred=pred;
    	this.args=args;
    }*/

    /*public void trueIf(List rhs) {
        pred.rules.add(  new Rule( this, GoalList.create(rhs)) );
    }*/
    public void trueIf(Goal... rhs) {
        pred.addRule(  new Rule( this, GoalList.create(rhs)) );
    }
    public void calls(NativeSubgoal callback) {
        pred.addRule( new Rule(this, callback) );
    }
    @Override
    public String toString() {
    	return pred+"("+args+")";
    }
	public void isTrue() {
        pred.addRule(  new Rule( this, GoalList.create()) );
	}

}
