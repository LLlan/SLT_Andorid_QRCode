package com.example.slt_andorid_qrcode;

import com.google.zxing.decoding.GenerateQRCode;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MakeQRCodeActivity extends Activity {

	@ViewInject(R.id.editText1)
	EditText contentText;
	
	@ViewInject(R.id.imageView1)
	ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_qrcode);
		ViewUtils.inject(this);
	}
	
	@OnClick(R.id.button1)
	private void MakeQR(View v){
		String content = contentText.getText().toString().trim();
		Bitmap qrcode = GenerateQRCode.generateQRCode(content);
		imageView.setImageBitmap(qrcode);
	}
}
