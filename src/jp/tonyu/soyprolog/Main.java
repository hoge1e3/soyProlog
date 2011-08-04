package jp.tonyu.soyprolog;

import jp.tonyu.soyprolog.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
    public static final String DELETE = "Delete";
    public static final String HYPERLINK = "Hyperlink..";
    public static final String V_INSERT = "v Insert";
    public static final String U_INSERT = "^ Insert";
    public static final String EDIT = "Edit";
	/** Called when the activity is first created. */
	ArrayAdapter<Panel> adapter;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        adapter = new ArrayAdapter<Panel>(this, 0/*android.R.layout.simple_list_item_1*/) {
        	@Override
        	public View getView(int position, View convertView, ViewGroup parent) {
        		return adapter.getItem(position).getView(this, Main.this);
        	}
        };
        for (int i=0 ; i<10 ; i++) {
	        // アイテムを追加します
	        adapter.add(new ClausePanel(new Clause("a",new String[]{"b","c"})));
	        adapter.add(new ClausePanel(new Clause("a",new String[]{"b","d"}),true));
        }
        ListView listView = (ListView) findViewById(R.id.listview);
        // アダプターを設定します
        listView.setAdapter(adapter);
        // リストビューのアイテムがクリックされた時に呼び出されるコールバックリスナーを登録します
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                ListView listView = (ListView) parent;
                // クリックされたアイテムを取得します
                String item = (String) listView.getItemAtPosition(position);
                //Toast.makeText(Main.this, item, Toast.LENGTH_LONG).show();
                sel(item);
            }
        });
        /* リストビューのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                    int position, long id) {
                ListView listView = (ListView) parent;
                // 選択されたアイテムを取得します
                String item = (String) listView.getSelectedItem();
                //Toast.makeText(Main.this, item, Toast.LENGTH_LONG).show();
                sel(item);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/
    }
    public void sel(final String msg) {
    	final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // 表示項目の配列
        final CharSequence[] colors = { EDIT,U_INSERT, V_INSERT,HYPERLINK,DELETE };
        // タイトルを設定
        alertDialogBuilder.setTitle("タイトル");
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