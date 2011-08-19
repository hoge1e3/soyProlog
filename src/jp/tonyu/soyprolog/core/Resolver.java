package jp.tonyu.soyprolog.core;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import jp.tonyu.util.Ref;

public class Resolver {
	public static boolean unify(Object x, Env x_env, Object y, Env y_env, List<Pair> trail, Env tmp_env) {
		debug("unify - ");
		debug("x="+x+"  env="+x_env);
		debug("y="+y+"  env="+y_env);
		debug("trail="+trail+" tmp_env="+tmp_env);
		while(true) {
			if (x instanceof Symbol) {
				Pair xp = x_env.get(x);
				if (xp==null) {
					//y, y_env = y_env.dereference(y)
					Pair yp = y_env.dereference(y);
					y=yp.value;
					y_env=yp.env;
					if (!( x.equals(y) && x_env.equals(y_env))) {
						x_env.put(x, yp);
						if (!(x_env == tmp_env)) {
							trail.add( new Pair(x, x_env));
						}
					}
					debug(true);
					return true;
				} else {
					x=xp.value;
					x_env = xp.env;
					Pair xp2 = x_env.dereference(x);
					x=xp2.value;
					x_env = xp2.env;
				}
			} else if (y instanceof Symbol) {
				//x, x_env, y, y_env = y, y_env, x, x_env
				Object tmp=x;
				x=y;
				y=tmp;
				Env tmpe=x_env;
				x_env=y_env;
				y_env=tmpe;
			} else {
				break;
			}
		}
		if  (x instanceof Goal && y instanceof Goal) {
			Goal xg=(Goal)x;
			Goal yg=(Goal)y;
			if (xg.pred!= yg.pred ) {
				debug(false);
				return false;
			}
			x = xg.args;
			y = yg.args;
		}
		if (x instanceof List && y instanceof List) {
			List xl=(List)x;
			List yl=(List)y;
			if (xl.size()!= yl.size()) {
				debug(false);
				return false;
			}
			for (int i=0 ; i<xl.size(); i++) {
               if (!unify(xl.get(i), x_env, yl.get(i), y_env, trail, tmp_env) ) {
					debug(false);
					return false;
               }
			}
			debug(true);
			return true;
		} else if (x instanceof Cons && y instanceof Cons) {
			Cons xc=(Cons)x;
			Cons yc=(Cons)y;
			if (!unify(xc.car, x_env, yc.car, y_env, trail, tmp_env)) {
				debug(false);
				return false;
			}
			if (!unify(xc.cdr, x_env, yc.cdr, y_env, trail, tmp_env)) {
				debug(false);
				return false;
			}
			debug(true);
			return true;
		} else {
			if (x==null) return y==null;
			debug(x.equals(y));
			return x.equals(y);
		}
	}
	public static void resolve(List goals, final EnvIter it) {
		final Env env = new Env();
		_resolve_body(Cons.create(goals), env, Ref.create(false), new Runnable() {
			public void run() {
				it.yield(env);
			}
		});
	}
	static void _resolve_body(Cons body, final Env env, final Ref<Boolean> cut , final Runnable it) {
		if (body==null) {
			it.run();
		} else {
			Goal goal=(Goal)body.car;
			final Cons rest=(Cons)body.cdr;
			debug("resolve: "+goal + " - "+rest);

			if (goal.equals(Goal.CUT)) {
				_resolve_body(rest, env, cut, it);
				cut.set(true);
			} else {
				Env d_env = new Env();
				final Ref<Boolean> d_cut = Ref.create(false);
				for (Def d :goal.pred.defs) {
					Goal d_head=d.head ;  Object d_body=d.body;
					//break if d_cut[0] or cut[0]
					if (d_cut.get() || cut.get()) break;
					List<Pair> trail = new Vector<Pair>();
					if (unify(goal, env, d_head, d_env, trail, d_env)) {
						if (d_body instanceof CallbackEnvIter) {
							CallbackEnvIter d_b=(CallbackEnvIter) d_body;
							d_b.run(new CallbackEnv(d_env, trail, new Runnable(){
								public void run() {
									_resolve_body(rest, env, cut, it);
								}
							}));
						} else {
							Cons cb=(Cons)d_body;
							_resolve_body(cb, d_env, d_cut ,new Runnable() {
								@Override
								public void run() {
									_resolve_body(rest, env, cut, it);
									d_cut.set(d_cut.get() || cut.get());
								}
							});
						}
					}
					for (Pair x_x_env : trail) {
						Object x=x_x_env.value;
						Env x_env=x_x_env.env;
						x_env.delete(x);
					}
					d_env.clear();
				}
			}
		}
	}
	private static void debug(Object string) {
		//System.out.println(string);
	}
}
/*
def resolve(*goals)
    env = Env.new
    _resolve_body(list(*goals), env, [false]) {
        yield env
    }
end

def _resolve_body(body, env, cut)
    if body.nil?
        yield
    else
        goal, rest = body
        if goal == :CUT
            _resolve_body(rest, env, cut) {
                yield
            }
            cut[0] = true
        else
            d_env = Env.new
            d_cut = [false]
            for d_head, d_body in goal.pred.defs
                break if d_cut[0] or cut[0]
                trail = []
                if _unify(goal, env, d_head, d_env, trail, d_env)
                    if Proc === d_body
                        if d_body[CallbackEnv.new(d_env, trail)]
                            _resolve_body(rest, env, cut) {
                                yield
                            }
                        end
                    else
                        _resolve_body(d_body, d_env, d_cut) {
                            _resolve_body(rest, env, cut) {
                                yield
                            }
                            d_cut[0] ||= cut[0]
                        }
                    end
                end
                for x, x_env in trail
                    x_env.delete(x)
                end
                d_env.clear
            end
        end
    end
end
*/

