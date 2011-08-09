package jp.tonyu.soyprolog.core;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Env {
	Map<Object,Pair> table=new Hashtable<Object,Pair>();
	public Env() {
		//@table = {}
	}
	public void put(Object x, Pair pair) {
        table.put(x,pair);
	}
    public Pair get(Object x) {
        return table.get(x);
    }
    public void delete(Object x) {
    	table.remove(x);
    }
    public void clear() {
    	table.clear();
    }
    public Pair dereference(Object t) {
        Env env = this;
        while (t instanceof Symbol) {
            Pair p = env.get(t);
            if (p==null) break;
            t=p.value;
            env=p.env;
        }
        return new Pair(t, env);
    }
    public Object a(Object t) { //[]
        Pair p=dereference(t);
        t=p.value;
        Env env=p.env;
        if (t instanceof Goal) {
//          when Goal then Goal.new(t.pred, env[t.args])
			Goal g = (Goal) t;
			return new Goal(g.pred, (List)env.a(g.args));
		}
        if (t instanceof Cons) {
//          when Cons then cons(env[t[0]], env[t[1]])
			Cons c = (Cons) t;
			return new Cons(env.a(c.car), env.a(c.cdr));
		}
        if (t instanceof List) {
//            when Array then t.collect {|e| env[e]}
			List l = (List) t;
			List res=new Vector();
			for(Object e:l) {
				res.add(env.a(e));
			}
			return res;
		}
        return t;
    }
    @Override
    public String toString() {
    	StringBuilder b = new StringBuilder();
    	for (Object k:table.keySet()) {
    		b.append(k+"="+table.get(k).value);
    	}
    	return b+"";
    }
}
