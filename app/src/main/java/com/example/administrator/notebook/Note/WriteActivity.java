package com.example.administrator.notebook.Note;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.administrator.notebook.R;

public class WriteActivity extends BaseActivity {
	  private Button cancelButton;
	  private Context context = this;
	  private String date;
	  private EditText editText;
	  private Date getDate;
	  private Button sureButton;
	  private TextView textView;

	  protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	    setContentView(R.layout.writedown);
	    this.textView = ((TextView)findViewById(R.id.writedate));
	    this.editText = ((DrawLine)findViewById(R.id.edittext));
	    this.cancelButton = ((Button)findViewById(R.id.button));
	    this.sureButton = ((Button)findViewById(R.id.button2));
	    this.getDate = new Date();
	    this.date = this.getDate.getDate();
		  //显示日期时间
	    this.textView.setText(this.date);

		  //按钮响应事件
	    this.sureButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase localSqLiteDatabase = new SqliteHelper(WriteActivity.this.context, null, null, 0).getWritableDatabase();
				Notepad localNotepad = new Notepad();
				ChangeSqlite localChangeSqlite = new ChangeSqlite();
				String strContent = WriteActivity.this.editText.getText().toString();
				if (strContent.equals("")) {
					Toast.makeText(WriteActivity.this.context, "Data Is Empty", Toast.LENGTH_SHORT).show();
					return;
				}
				String strTitle=strContent.length()>11?" "+strContent.substring(0, 11):strContent;
				localNotepad.setContent(strContent);
				localNotepad.setTitle(strTitle);
				localNotepad.setdata(date);
				localChangeSqlite.add(localSqLiteDatabase, localNotepad);
				finish();
			}
		});
	    this.cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	  }
}


























