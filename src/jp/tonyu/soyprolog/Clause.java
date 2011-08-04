package jp.tonyu.soyprolog;

import java.util.List;

public class Clause {
	public Clause(String head, String[] args) {
		this.head=head;
		this.args=args;
	}
	String head;
	String[] args;
	@Override
	public String toString() {
		StringBuffer b=new StringBuffer();
		b.append(head);
		String com="(";
		for (String a :args) {
			b.append(com);
			b.append(a);
			com=",";
		}
		b.append(")");
		return b+"";
	}
}
