package jp.tonyu.soyprolog;

import java.util.List;

import android.accounts.OnAccountsUpdateListener;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ClausePanel extends Panel {
	Clause clause;
	boolean subgoal;

	public ClausePanel(Clause clause2) {
		clause=clause2;
		subgoal=false;
	}
	public ClausePanel(Clause clause2, boolean sb) {
		clause=clause2;
		subgoal=sb;
	}

	@Override
	public View getView(ArrayAdapter<Panel> ary, final Context ctx) {
		TextView view = new TextView(ctx);//(TextView)super.getView(position, convertView,parent);
		view.setTypeface(Typeface.MONOSPACE);
        view.setTextSize(12);
        view.setHeight(30);
        view.setMinimumHeight(30);
        LinearLayout l=new LinearLayout(ctx);
        l.addView(view);
        view.setText(clause.toString());
        Button b = new Button(ctx);
		b.setText("?");
        b.setHeight(20);
        b.setMinimumHeight(20);
        b.setMaxHeight(20);
        b.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View view) {
        		btn(ctx);
        	}
        });
		l.addView(b);
        return l;
	}
	public void btn(Context ctx) {
		final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);

        // 表示項目の配列
        final CharSequence[] colors = { Main.EDIT, Main.U_INSERT, Main.V_INSERT,Main.HYPERLINK,Main.DELETE };
        // タイトルを設定
        alertDialogBuilder.setTitle(clause.toString());
        // 表示項目とリスナの設定
        alertDialogBuilder.setItems(colors,
                new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					    // 項目が選択されたときの処理
		               // adapter.add("new: "+msg);

					}
                });

        // back keyを使用不可に設定
        //alertDialogBuilder.setCancelable(false);

        // ダイアログを表示
        alertDialogBuilder.create().show();
	}


}
