package jp.tonyu.soyprolog;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

public abstract class Panel {
	//public abstract void onClick(Context c);
	//public abstract void onAdd(ArrayAdapter<Panel> ary);
	public abstract View getView(ArrayAdapter<Panel> ary, Context ctx);

	//public abstract String getLabel();
	/*public String toString() {
		return getLabel();
	}*/
}
