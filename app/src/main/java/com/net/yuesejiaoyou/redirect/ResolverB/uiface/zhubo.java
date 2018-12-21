package com.net.yuesejiaoyou.redirect.ResolverB.uiface;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.vliao.R;
//import com.example.vliao.interface3.UsersThread_01158;
//import com.example.vliao.interface4.LogDetect;
//import com.example.vliao.util.AgoraVideoManager;
//import com.example.vliao.util.Util;
//import com.lazysellers.sellers.core.Utils;
//import com.lazysellers.sellers.infocenter.db.Const;
//import com.lazysellers.sellers.infocenter.hengexa1.smack.XMPPException;
//import com.lazysellers.sellers.infocenter.hengexa2.smack.SmackException;
import com.net.yuesejiaoyou.R;
import com.net.yuesejiaoyou.classroot.interface4.LogDetect;
import com.net.yuesejiaoyou.classroot.interface4.openfire.core.Utils;
import com.net.yuesejiaoyou.classroot.interface4.openfire.infocenter.db.Const;
import com.net.yuesejiaoyou.classroot.interface4.openfire.infocenter.hengexa1.smack.XMPPException;
import com.net.yuesejiaoyou.classroot.interface4.openfire.infocenter.hengexa2.smack.SmackException;
import com.net.yuesejiaoyou.classroot.interface4.util.Util;
import com.net.yuesejiaoyou.redirect.ResolverB.interface3.UsersThread_01158B;
import com.net.yuesejiaoyou.redirect.ResolverB.interface4.util.AgoraVideoManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.Date;


public class zhubo extends Activity {
	private RelativeLayout layout;
	private LinearLayout exit_del,exit_queding;
	private SimpleDateFormat sd;
	private TextView user_exit;
	private String msgBody;
	public String yid,msgbody;
	private ImageView photo;
	private TextView nickname;
	private DisplayImageOptions options=null;
	private String headpic;

	private boolean isAccept = false;	// 是否已经接通
	private boolean isThreadStop = false;	// 是否停止计时线程
	private Thread timerThread;
    private boolean isdianji=false;
	// 延时函数
	static public void Sleep(int millisecond) {
		try {
			Thread.sleep(millisecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//设置cpu永不休眠并写入cpu运行日志
	private void cpuWakeLockThread()
	{
		Thread mThread=new Thread(new Runnable() {

			@Override
			public void run() {
				//acquireWakeLock(MainActivity.this);
				while (true) {
					LogDetect.send(LogDetect.DataType.basicType,"不休眠测试不休眠测试","-------------测试主播页面保活");
					Sleep(3000);
				}

			}
		});
		mThread.start();
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startCallingTimer();
		setContentView(R.layout.tonghuabohao_01146);
		//--------------10000-------------------

		getWindow().addFlags(
				//WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|               //这个在锁屏状态下
						WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
						| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
						| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//		//--------------158-------------------
//		//LogDetect.send(LogDetect.DataType.basicType,"主播1111",msgbody);
//		//保活测试
//		cpuWakeLockThread();
//
//		//创建Intent  		//发送播放音乐广播，含有保活
//		KeyguardManager mKeyguardManager = (KeyguardManager)this.getSystemService(this.KEYGUARD_SERVICE);
//
////		if (mKeyguardManager.inKeyguardRestrictedInputMode()) {
////			this.sendBroadcast(new Intent("playmusic"));
////			LogDetect.send(LogDetect.DataType.basicType,"不休眠测试不休眠测试","-------------playmusic12");
////		}else {
////			//正常非屏保下开始播放音乐
////			MusicUtil.playSound(1,100);
////			LogDetect.send(LogDetect.DataType.basicType,"不休眠测试不休眠测试","-------------非屏保下开始播放音乐");
////		}
//
//		MusicUtil.playSound(1,100);
//
//		//Intent intent = new Intent(zhubo_bk.this, PlayerMusicService1.class);
//		//startService(intent);
//
//		//--------------10000-------------------
//		yid= getIntent().getStringExtra("yid_zhubo");
//		msgbody= getIntent().getStringExtra("msgbody");
//
//		Log.e("jj",yid+"++++++++++"+ msgbody);
//		LogDetect.send(LogDetect.DataType.basicType,"查看一下主播接受页面的信息",msgbody);
//
//		options=new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).build();
//		String[] z = msgbody.split("卍");
//		int sum = z.length;
//
//
//		photo = (ImageView) findViewById(R.id.photo);
//		nickname = (TextView) findViewById(R.id.nickname);
//
//		String strphoto = z[4];
//		headpic = strphoto;
//		String str = strphoto.substring(0,1);
//		if(str.equals("h")){
//			ImageLoader.getInstance().displayImage(
//					strphoto, photo,
//					options);
//		}else{
//			ImageLoader.getInstance().displayImage(
//					strphoto, photo,
//					options);
//		}
//		if(z[3] != null){
//			nickname = (TextView) findViewById(R.id.nickname);
//			nickname.setText(z[3]+"");
//		}
//
//
//		user_exit = (TextView) findViewById(R.id.exit_jieshao);
//
//		user_exit.setText("");
//		user_exit.setOnClickListener(new OnClickListener() {
//			public void onClick(View view) {
//
//
//		    }
//	    });
//
//		exit_queding = (LinearLayout) findViewById(R.id.exit_login);
//		exit_queding.setOnClickListener(new OnClickListener() {
//			public void onClick(View view) {
//				if(isdianji){
//					return;
//				}
//				isdianji=true;
//				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				final String message1 = "接0通1视2频"+ Util.zhuboRoomId + Const.SPLIT + Const.ACTION_MSG_ONEINVITE
//						+ Const.SPLIT + sd.format(new Date()) + Const.SPLIT + Util.nickname + Const.SPLIT + Util.headpic;
//				new Thread(new Runnable() {
//					@Override
//					public void run() {
//						try {
//							Utils.sendmessage(Utils.xmppConnection, message1, yid);
//						} catch (XMPPException | SmackException.NotConnectedException e) {
//							e.printStackTrace();
//							Looper.prepare();
//							Looper.loop();
//						}
//					}
//				}).start();
//				//--------------------------------------------
////				Intent intent = new Intent(zhubo_bk.this, LiveRoomActivity_zhubo.class);
////				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
////				intent.putExtra("roomid","");	// 主播的roomid可以不写
////				intent.putExtra("yid_zhubo",yid);
////				intent.putExtra("guke_name",nickname.getText().toString());
////				intent.putExtra("headpic",headpic);
////				intent.putExtra("type","zhubo_bk");
////				startActivity(intent);
//				//-----------------------------------------------------------------------------------------------
////				finish();
//				//--------------------158---------------------
//				//关闭音乐
//				MusicUtil.stopPlay();
//				//--------------------158---------------------
//		       }
//	        });
//
//		exit_del = (LinearLayout) findViewById(R.id.exit_tuichu);
//		exit_del.setOnClickListener(new OnClickListener() {
//			public void onClick(View view) {
//
//				if(isdianji){
//					return;
//				}
//				isdianji=true;
//
//				stopCallingTimer();
//				MusicUtil.stopPlay();
//
//				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				final String message1 = "拒0绝1邀2请" + Const.SPLIT + Const.ACTION_MSG_ONEINVITE
//						+ Const.SPLIT + sd.format(new Date()) + Const.SPLIT + Util.nickname + Const.SPLIT + Util.headpic;
//				//LogDetect.send(DataType.noType,Utils.seller_id+"=phone="+Utils.android,"message: "+message);
//				new Thread(new Runnable() {
//					@Override
//					public void run() {
//						try {
//							//LogDetect.send(DataType.noType,Utils.seller_id+"=phone="+Utils.android,"before sendMessage()");
//							Utils.sendmessage(Utils.xmppConnection, message1, yid);
//						} catch (XMPPException | SmackException.NotConnectedException e) {
//							e.printStackTrace();
//							Looper.prepare();
//							Looper.loop();
//						}
//					}
//				}).start();
//
//				//-----------------------------------
//				String mode1 = "modezhubostate";
//				String[] paramsMap1 = {Util.userid,yid};
//				LogDetect.send(LogDetect.DataType.specialType,"01160 主播改变在线状态 video:",Util.userid);
//				UsersThread_01158B a = new UsersThread_01158B(mode1,paramsMap1,handler);
//				Thread c = new Thread(a.runnable);
//				c.start();
//				//-----------------------------------
//
//				String mode2 = "removep2pvideo";
//				String[] paramsMap2 = {"",AgoraVideoManager.getCurRoomid()};
//				UsersThread_01158B a2 = new UsersThread_01158B(mode2,paramsMap2,handler);
//				Thread c2 = new Thread(a2.runnable);
//				c2.start();
//				AgoraVideoManager.close();
//                finish();
//
//		       }
//	        });
//
//		// 判断此时声网是否已经关闭了
//		// 从AgoraVideoManager跳转到此页面到注册接收"挂0断1邀2请"消息的时候有一个空档期
//		// 在这个空档期如果收到了对方的 "挂0断1邀2请" 的消息则被忽略掉了，判断一下声网是否关闭来判断要不要打开 zhubo_bk.java页面
//		// Application 里面对 getCurrentActivity()的判断有些延迟
//		if(AgoraVideoManager.isAgoraStart() == false) {
//			finish();
//		}
//
//		String mode1 = "mod_mang";
//		String[] paramsMap1 = {Util.userid};
//		UsersThread_01158B a = new UsersThread_01158B(mode1,paramsMap1,handler);
//		Thread c = new Thread(a.runnable);
//		c.start();
//		//1有空  2再聊  3勿扰 0离线
//
//
//		MsgOperReciver msgOperReciver = new MsgOperReciver();
//		IntentFilter intentFilter = new IntentFilter(Const.ACTION_MSG_ONEINVITE);
//		registerReceiver(msgOperReciver, intentFilter);

	}
	private class MsgOperReciver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {

			msgBody=intent.getStringExtra("oneuponeinvite");
			Log.e("jj","---------"+ msgBody);
			if (msgBody.contains("挂0断1邀2请")) {
				stopCallingTimer();
				AgoraVideoManager.close();
				LogDetect.send(LogDetect.DataType.specialType,"01160 主播挂断 invite:",msgBody);
				handler.sendMessage(handler.obtainMessage(200, (Object)msgBody));
			} else if (msgBody.contains("拒0绝1邀2请")) {

			} else if (msgBody.contains("接0通1视2频")) {

			} else if (msgBody.contains("开0始1视2频")) {
				handler.sendMessage(handler.obtainMessage(201, (Object)msgBody));
			} else if (msgBody.contains("结0束1视2频")){

			}
			//handler.sendMessage(handler.obtainMessage(200, (Object) msgBody));
		}
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case 260:
					Toast.makeText(zhubo.this, "对方已挂断", Toast.LENGTH_SHORT).show();
					//--------------------158---------------------
					//关闭音乐
					MusicUtil.stopPlay();
					//--------------------158---------------------
					finish();
					break;
				case 200:

					LogDetect.send(LogDetect.DataType.specialType,"01160 改为在线:","");
					String mode1 = "mod_online1";
					String[] paramsMap1 = {Util.userid, yid,"0","1"};
					UsersThread_01158B a = new UsersThread_01158B(mode1,paramsMap1,handler);
					Thread c = new Thread(a.runnable);
					c.start();
					break;
				case 201:
					msgBody=(String) msg.obj;
					if(!msgBody.equals("")){
						isAccept = true;
						String[] roid=msgBody.split(Const.SPLIT);
						//Toast.makeText(zhubo_bk.this, "对方已挂断", Toast.LENGTH_SHORT).show();
						//-----------------------------------------------------------------------------------------------
						//Intent intent = new Intent(zhubo_bk.this, VideoChatViewActivity_zhubo.class);
						//Intent intent = new Intent(zhubo_bk.this, AgoraChatActivity_zhubo.class);
						Intent intent = new Intent(zhubo.this, AgoraRtcActivity_zhubo.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
						intent.putExtra("roomid",roid[2]);
						intent.putExtra("yid_zhubo",yid);
						intent.putExtra("guke_name",nickname.getText().toString());
						intent.putExtra("guke_pic",headpic);
						startActivity(intent);
						//-----------------------------------------------------------------------------------------------
//						Intent intent = new Intent(zhubo_bk.this, LiveRoomActivity_zhubo.class);
//						intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//						intent.putExtra("roomid",roid[2]);
//						intent.putExtra("yid_zhubo",yid);
//						intent.putExtra("guke_name",nickname.getText().toString());
//						intent.putExtra("headpic",headpic);
//						intent.putExtra("type","zhubo_bk");
//						startActivity(intent);
						//-----------------------------------------------------------------------------------------------
						finish();
						//--------------------158---------------------
						//关闭音乐
						MusicUtil.stopPlay();
						//--------------------158---------------------
						finish();
					}else{
//						Toast.makeText(zhubo_bk.this,"网络异常,请稍后重试",Toast.LENGTH_SHORT).show();
					}
					break;
			}
		}
	};

	//连点两次退出
	private long firstTime = 0;
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
			case KeyEvent.KEYCODE_BACK:
				long secondTime = System.currentTimeMillis();
				if (secondTime - firstTime > 2000) {
					Toast.makeText(this, "再按一次可以挂断", Toast.LENGTH_SHORT).show();
					firstTime = secondTime;// 更新firstTime
				} else {// 两次按键小于2秒时，退出应用
					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					final String message1 = "拒0绝1邀2请" + Const.SPLIT + Const.ACTION_MSG_ONEINVITE
							+ Const.SPLIT + sd.format(new Date()) + Const.SPLIT + Util.nickname + Const.SPLIT + Util.headpic;
					//LogDetect.send(DataType.noType,Utils.seller_id+"=phone="+Utils.android,"message: "+message);
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								//LogDetect.send(DataType.noType,Utils.seller_id+"=phone="+Utils.android,"before sendMessage()");
								Utils.sendmessage(Utils.xmppConnection, message1, yid);
							} catch (XMPPException | SmackException.NotConnectedException e) {
								e.printStackTrace();
								Looper.prepare();
								Looper.loop();
							}
						}
					}).start();
					stopCallingTimer();
					String mode1 = "modezhubostate";
					String[] paramsMap1 = {Util.userid,yid};
					LogDetect.send(LogDetect.DataType.specialType,"01160 主播改变在线状态 video:",Util.userid);
					UsersThread_01158B a = new UsersThread_01158B(mode1,paramsMap1,handler);
					Thread c = new Thread(a.runnable);
					c.start();
					String mode2 = "removep2pvideo";
					String[] paramsMap2 = {"",AgoraVideoManager.getCurRoomid()};
					UsersThread_01158B a2 = new UsersThread_01158B(mode2,paramsMap2,handler);
					Thread c2 = new Thread(a2.runnable);
					c2.start();

					AgoraVideoManager.close();
					handler.sendMessage(handler.obtainMessage(200, (Object)msgBody));
				}
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	@Override
	protected void onStop() {
		super.onStop();

		//MusicUtil.stopPlay();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//stopCallingTimer();
	}

	/**
	 * 待接通倒计时，如果超时则退出呼叫
	 * 关闭声网预接通，关闭页面，向对方发送挂断消息，提示对方也做挂断处理
	 *
	 * 为了防止顾客端超时的时候碰巧主播端做了接听操作，所以把顾客端超时时间设置为25秒，主播端超时时间设置为20秒
	 */
	private void startCallingTimer() {

		isThreadStop = false;
		timerThread = new Thread(new Runnable() {

			private int maxTime = 60;
			private int curTime = 0;
			@Override
			public void run() {

				while(isThreadStop == false && curTime < maxTime) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					curTime++;
					if(isAccept) {
						return;
					}
				}
				if(isThreadStop == false && isAccept == false) {
					MusicUtil.stopPlay();

					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					final String message1 = "拒0绝1邀2请" + Const.SPLIT + Const.ACTION_MSG_ONEINVITE
							+ Const.SPLIT + sd.format(new Date()) + Const.SPLIT + Util.nickname + Const.SPLIT + Util.headpic;
					//LogDetect.send(DataType.noType,Utils.seller_id+"=phone="+Utils.android,"message: "+message);
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								//LogDetect.send(DataType.noType,Utils.seller_id+"=phone="+Utils.android,"before sendMessage()");
								Utils.sendmessage(Utils.xmppConnection, message1, yid);
							} catch (XMPPException | SmackException.NotConnectedException e) {
								e.printStackTrace();
								Looper.prepare();
								Looper.loop();
							}
						}
					}).start();

					//-----------------------------------
					String mode1 = "modezhubostate";
					String[] paramsMap1 = {Util.userid,yid};
					LogDetect.send(LogDetect.DataType.specialType,"01160 主播改变在线状态 video:",Util.userid);
					UsersThread_01158B a = new UsersThread_01158B(mode1,paramsMap1,handler);
					Thread c = new Thread(a.runnable);
					c.start();
					//-----------------------------------

					AgoraVideoManager.close();
					finish();
				}
			}
		});
		timerThread.start();
	}

	private void stopCallingTimer() {
		isThreadStop = true;
	}
}
