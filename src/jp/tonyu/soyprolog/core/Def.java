package jp.tonyu.soyprolog.core;

public class Def implements IDef {
	Goal head;
	@Override
	public Goal getHead() {
		return head;
	}
	SubGoal body;
	public Def(Goal head, SubGoal body) {
		super();
		this.head = head;
		this.body = body;
	}

}
