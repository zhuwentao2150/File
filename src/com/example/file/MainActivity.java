package com.example.file;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText edt_01;
	private EditText edt_02;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new ButtonClickListener());
		Button button2 = (Button) findViewById(R.id.button02);
		button2.setOnClickListener(new ButtonClickListener02());
		Button button3 = (Button) findViewById(R.id.button03);
		button3.setOnClickListener(new ButtonClickListener03());
	}
	// ���ļ������ڴ洢����
	private class ButtonClickListener implements View.OnClickListener{
		public void onClick(View v){
			edt_01 = (EditText)findViewById(R.id.filename);
			edt_02 = (EditText)findViewById(R.id.filecontent);
			String filename = edt_01.getText().toString();
			String content = edt_02.getText().toString();
			FileService service = new FileService(getApplicationContext());
			try {
				service.save(filename, content);
				Toast.makeText(getApplicationContext(), "�ɹ�", 1).show();
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), "ʧ��", 1).show();
				e.printStackTrace();
			}
			
		}
	}
	// ��ȡ�ֻ��Դ��洢���������
	private class ButtonClickListener02 implements View.OnClickListener{
		public void onClick(View v){
			FileService service = new FileService(getApplicationContext());
			try {
				String result = service.read("zhuwentao.txt");
				TextView textview = (TextView)findViewById(R.id.tv_read);
				textview.setText(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// �������ݵ�SD��
	private class ButtonClickListener03 implements View.OnClickListener{
		public void onClick(View v){
			edt_01 = (EditText)findViewById(R.id.filename);
			edt_02 = (EditText)findViewById(R.id.filecontent);
			String filename = edt_01.getText().toString();
			String content = edt_02.getText().toString();
			FileService service = new FileService(getApplicationContext());
			try{
				// �ж�SDCard�Ƿ���ڣ��Ƿ���Խ��ж�д
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
					service.saveToSDCard(filename, content);
					Toast.makeText(getApplicationContext(), "�ɹ�", 1).show();
				}else{
					Toast.makeText(getApplicationContext(), "������SDCard���ж�д����", 1).show();
				}
			}catch(Exception e){
				Toast.makeText(getApplicationContext(), "ʧ��", 1).show();
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
