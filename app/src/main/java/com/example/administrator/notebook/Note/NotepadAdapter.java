package com.example.administrator.notebook.Note;

import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.notebook.R;

public class NotepadAdapter extends BaseAdapter {

	public Context context;
	public Context activity;
	public LayoutInflater inflater;
	public ArrayList<Map<String, Object>> list;

	public NotepadAdapter(Activity activity, ArrayList<Map<String, Object>> list) {

		this.context = activity;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		// �˴���ȥ��if���֮��ˢ�����������Ч
		SetShow setShow = new SetShow();
		// ȡ��map�е�չ�����
		Map<String, Object> map = list.get(arg0);
		boolean boo = (Boolean) map.get("EXPANDED");
		if (!boo) {
			arg1 = inflater.inflate(R.layout.showtypes, arg2, false);
			setShow.contentView = (TextView) arg1
					.findViewById(R.id.contentTextView);
			setShow.dateView = (TextView) arg1.findViewById(R.id.dateTextView);
			String str = (String) list.get(arg0).get("titleItem");
			String dateStr = (String) list.get(arg0).get("dateItem");
			setShow.contentView.setText("   " + str);
			setShow.dateView.setText(dateStr);
			setShow.showButtonWrite = (Button) arg1
					.findViewById(R.id.smallbutton1);
			setShow.showButtonDelete = (Button) arg1
					.findViewById(R.id.smallbutton2);
			setShow.showButtonWrite.setOnClickListener(new WriteButtonListener(
					arg0));
			setShow.showButtonDelete
					.setOnClickListener(new DeleteButtonListener(arg0));
		} else {
			arg1 = inflater.inflate(R.layout.style, arg2, false);
			setShow.cContentView = (TextViewLine) arg1
					.findViewById(R.id.changecontentview);
			setShow.cDateView = (TextView) arg1
					.findViewById(R.id.changedateview);
			String str = (String) list.get(arg0).get("contentItem");
			String dateStr = (String) list.get(arg0).get("dateItem");
			setShow.cContentView.setText("" + str);
			setShow.cDateView.setText(dateStr);
			setShow.styleButtonWrite = (Button) arg1
					.findViewById(R.id.stylebutton1);
			setShow.styleButtonWrite
					.setOnClickListener(new WriteButtonListener(arg0));
			setShow.styleButtonDelete = (Button) arg1
					.findViewById(R.id.stylebutton2);
			setShow.styleButtonDelete
					.setOnClickListener(new DeleteButtonListener(arg0));
		}
		return arg1;
	}

	class WriteButtonListener implements OnClickListener {
		private int position;

		public WriteButtonListener(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			Bundle b = new Bundle();
			b.putString("contentItem",
					(String) list.get(position).get("contentItem"));
			b.putString("dateItem", (String) list.get(position).get("dateItem"));
			b.putString("idItem", (String) list.get(position).get("idItem"));
			Intent intent = new Intent((MainActivity) context,
					EditActivity.class);
			intent.putExtras(b);
			((MainActivity) context).startActivity(intent);
		}

	}

	class DeleteButtonListener implements OnClickListener {
		private int position;

		public DeleteButtonListener(int position) {
			this.position = position;

		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			// TODO Auto-generated method stub

			Builder builder = new Builder(context);
			builder.setTitle("ȷ��ɾ����");
			builder.setPositiveButton("ɾ��",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int i) {
							// TODO Auto-generated method stub
							SqliteHelper sql = new SqliteHelper(context, null,
									null, 0);
							SQLiteDatabase dataBase = sql.getWritableDatabase();
							ChangeSqlite change = new ChangeSqlite();
							Notepad notepad = new Notepad();
							notepad.setid((String) list.get(position).get(
									"idItem"));
							change.delete(dataBase, notepad);
							// �˴�����activity��ķ�����������ת��
							((MainActivity) context).showUpdate();
							// a.showUpdate();

						}
					});
			builder.setNegativeButton("ȡ��",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
			builder.create();
			builder.show();
		}

	}

	class SetShow {
		public TextView contentView;
		public TextView dateView;
		public TextViewLine cContentView;
		public TextView cDateView;
		public Button styleButtonWrite;
		public Button styleButtonDelete;
		public Button showButtonWrite;
		public Button showButtonDelete;
	}

}
