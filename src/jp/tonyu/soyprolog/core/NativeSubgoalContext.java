package jp.tonyu.soyprolog.core;

import java.util.List;

public class NativeSubgoalContext {
	Env env;
	List<Pair> trail;
	Runnable yield;

	public NativeSubgoalContext(Env env,List<Pair> trail,Runnable yield) {
		this.env=env;
		this.trail=trail;
		this.yield=yield;
	}
	public Object get(Object t) {
		return env.get(t);
	}
	public boolean unify(Object t,Object u) {
		return Resolver.unify(t, env, u, env, trail, env);
	}
	public void yield() {
		yield.run();
	}
	public void put(Object key, Object value) {
		env.put(key, value);
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