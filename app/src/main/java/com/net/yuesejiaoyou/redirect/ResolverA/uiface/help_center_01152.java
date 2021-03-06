package com.net.yuesejiaoyou.redirect.ResolverA.uiface;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.net.yuesejiaoyou.R;

import com.net.yuesejiaoyou.classroot.interface4.LogDetect;
import com.net.yuesejiaoyou.redirect.ResolverA.getset.Member_01152;
import com.nostra13.universalimageloader.core.DisplayImageOptions;


public class help_center_01152 extends Activity implements OnClickListener {
	//自定义ID
	
	private ImageView back;
	private RelativeLayout tixing,xinxi,qita;
	private Intent intent;
	
	private ArrayList<Member_01152> list;
	DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
			.cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).build();
	@Override
	/**
	 * 对页面进行初始化
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogDetect.send(LogDetect.DataType.specialType, "help_center_01152:", "布局开始");
		setContentView(R.layout.zp_help_01152);
		LogDetect.send(LogDetect.DataType.specialType, "help_center_01152:", "开始=====");
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(this);
		
		tixing = (RelativeLayout) findViewById(R.id.tixing);
		tixing.setOnClickListener(this);
		
		xinxi = (RelativeLayout) findViewById(R.id.xinxi);
		xinxi.setOnClickListener(this);
		
		qita = (RelativeLayout) findViewById(R.id.qita);
		qita.setOnClickListener(this);
		
		
		LogDetect.send(LogDetect.DataType.specialType, "help_center_01152:", "结束=====");		
	
	}
	

	//获得onclick点击事件
	@Override
	public void onClick(View v) {
		
		int id = v.getId();
		switch (id) {		
		case R.id.back: //返回页面
			this.finish();
			break;
			
		case R.id.tixing: //消息来电提醒
			intent = new Intent();
			intent.setClass(help_center_01152.this, bengkui_01066.class);//编辑资料
			startActivity(intent);
			break;
			
		case R.id.xinxi: //崩溃
			intent = new Intent();
			intent.setClass(help_center_01152.this, bengkui_01152.class);
			startActivity(intent);
			break;
			
		case R.id.qita: //意见反馈
			intent = new Intent();
			intent.setClass(help_center_01152.this, Vliao_yijianfankui_01168.class);
			startActivity(intent);
			break;
			
		}
	}
	
	


}
