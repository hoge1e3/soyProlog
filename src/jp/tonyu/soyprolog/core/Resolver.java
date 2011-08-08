package jp.tonyu.soyprolog.core;

import java.util.Collections;
import java.util.List;

public class Resolver {
	public static boolean unify(Object x, Env x_env, Object y, Env y_env, List trail, Env tmp_env) {
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
			if (xg.pred!= yg.pred ) return false;
			x = xg.args;
			y = yg.args;
		}
		if (x instanceof List && y instanceof List) {
			List xl=(List)x;
			List yl=(List)y;
			if (xl.size()!= yl.size()) return false;
			for (int i=0 ; i<xl.size(); i++) {
               if (!unify(xl.get(i), x_env, yl.get(i), y_env, trail, tmp_env) ) {
				return false;
               }
			}
			return true;
		} else {
			return x.equals(y);
		}
	}
	void resolve(List goals, final EnvIter it) {
		final Env env = new Env();
		resolve_body(Cons.create(goals), env, /*(List)Collections.singletonList(false),*/ new Runnable() {
			public void run() {
				it.yield(env);
			}
		});
	}
void _resolve_body(Cons body, Env env, /*List cut ,*/ Runnable it) {
    if (body==null) {
        it.run();
    } else {
        Goal goal=(Goal)body.car;
        Cons rest=(Cons)body.cdr;

        /*if goal == :CUT
            _resolve_body(rest, env, cut) {
                yield
            }
            cut[0] = true*/
        //else
            d_env = new Env();
            //d_cut = [false]
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
}
