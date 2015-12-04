package com.example.slt_andorid_qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.MipcaActivityCapture;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class MainActivity extends Activity {
	
	@ViewInject(R.id.textView1)
	TextView tv1;
	static final private int GET_CODE = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		
	}
	
	@OnClick(R.id.button1)
	private void Button1(View v){
		startActivity(new Intent(MainActivity.this,MakeQRCodeActivity.class));
	}
	
	@OnClick(R.id.button2)
	private void Button2(View v){
		//先点击扫一扫按钮，跳转到另一个页面，打开摄像头扫描，回传扫描的结果
		Intent intent = new Intent();//打开MipcaActivityCapture这个画面
		intent.setClass(MainActivity.this, MipcaActivityCapture.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//将需要启动的Activity设置在当前的任务中
		startActivityForResult(intent, GET_CODE);
//		startActivityForResult(intent, 0);
	}
	/*
	 * 处理回传的结果
	 * (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 当前只有一个返回值
		if (resultCode == RESULT_OK && requestCode == GET_CODE) {//判断是否得到回传结果
			String result = data.getExtras().getString("result");//得到回传的结果
			tv1.setText(result);//得到回传结果传到主界面MAIN里去
			if (TextUtils.isEmpty(result)) {//如果没有扫描到结果
				Toast.makeText(MainActivity.this, R.string.scan_retry,
						Toast.LENGTH_SHORT).show();
				return;
			} else {//如果扫描到回传结果
				Toast.makeText(MainActivity.this, result,
						Toast.LENGTH_SHORT).show();
				return;
			}
		}
		
	}
}
