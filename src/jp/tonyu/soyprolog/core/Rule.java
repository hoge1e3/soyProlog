package jp.tonyu.soyprolog.core;

/**
 * goal :- subgoal.
 * @author shinya
 *
 */
public class Rule {
	Goal goal;
	Subgoal subgoal;
	public Rule(Goal goal, Subgoal subgoal) {
		super();
		this.goal = goal;
		this.subgoal = subgoal;
	}

}
