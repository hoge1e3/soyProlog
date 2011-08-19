package jp.tonyu.soyprolog.core;

import java.util.List;

public class GoalList implements SubGoal {
	Goal car;
	GoalList cdr;
	public GoalList(Goal car, GoalList cdr) {
		super();
		this.car = car;
		this.cdr = cdr;
	}
	public static GoalList create(Goal... goals) {
		GoalList res=null;
		for (int i=goals.length-1; i>=0 ; i--) {
			res=new GoalList(goals[i],res);
		}
		return res;
	}
	public static GoalList create(List<Goal> goals) {
		GoalList res=null;
		for (int i=goals.size()-1; i>=0 ; i--) {
			res=new GoalList(goals.get(i),res);
		}
		return res;
	}
}
