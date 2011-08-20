package jp.tonyu.soyprolog.core;

public interface NativeSubgoal extends Subgoal {

	public void run(NativeSubgoalContext callBackEnv);

}
