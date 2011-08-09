package jp.tonyu.soyprolog.core;

import java.util.List;

public class CallbackEnv {
	Env env;
	List trail;

	public CallbackEnv(Env env,List trail) {
		this.env=env;
		this.trail=trail;
	}
	public Object a(Object t) {
		return env.a(t);
	}
	public boolean unify(Object t,Object u) {
		return Resolver.unify(t, env, u, env, trail, env);
	}
}

/*
class CallbackEnv
def initialize(env, trail)
    @env, @trail = env, trail
end
def [](t)
    return @env[t]
end
def unify(t, u)
    return _unify(t, @env, u, @env, @trail, @env)
end
end
*/