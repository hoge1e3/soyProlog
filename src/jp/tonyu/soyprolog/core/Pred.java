package jp.tonyu.soyprolog.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import jp.tonyu.debug.TLog;
import jp.tonyu.util.Maps;


public class Pred {
	public static final Pred CUT = new Pred("!");
	HashMap<String, List<Rule>> ruleMap=new HashMap<String, List<Rule>>();
	List<Rule> unindexedRules=new Vector<Rule>();
	List<Rule> allRules=new Vector<Rule>();
	public void addRule(Rule r) {
		String idx=r.goal.index();
		//TLog.d("addRule","goal="+r.goal+" idx="+idx);
		if (idx==null) {
			for (List<Rule> rl: ruleMap.values()) {
				rl.add(r);
			}
			unindexedRules.add(r);
		} else {
			List<Rule> rules=ruleMap.get(idx);
			if (rules==null) {
				rules=new Vector<Rule>();
				rules.addAll(unindexedRules);
				ruleMap.put(idx, rules);
			}
			rules.add(r);
		}
		allRules.add(r);
	}
	public List<Rule> getRules(Goal g) {
		String idx=g.index();
		//TLog.d("getRules","goal="+g+" idx="+idx);
		List<Rule> rules;
		if (idx==null) {
			rules=allRules;
		} else rules=ruleMap.get(idx);
		if (rules!=null) return rules;
		return unindexedRules;
	}
	//Vector<Rule> rules;
	String name;
    public Pred(String name) {
        this.name = name;
        //rules = new Vector<Rule>();
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
