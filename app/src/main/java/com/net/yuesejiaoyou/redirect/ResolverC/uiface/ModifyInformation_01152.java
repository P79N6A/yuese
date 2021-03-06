package com.net.yuesejiaoyou.redirect.ResolverC.uiface;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/*import com.net.yuesejiaoyou.R;
import com.example.vliao.getset.Member_01152;
import com.example.vliao.interface3.UploadFileTask;
import com.example.vliao.interface3.UsersThread_01152;
import com.example.vliao.interface4.LogDetect;
import com.example.vliao.interface4.LogDetect.DataType;
import com.example.vliao.interface4.RoundImageView;
import com.example.vliao.util.Util;*/
import com.net.yuesejiaoyou.R;
import com.net.yuesejiaoyou.classroot.interface4.LogDetect;
import com.net.yuesejiaoyou.classroot.interface4.util.Util;
import com.net.yuesejiaoyou.redirect.ResolverC.getset.Member_01152;
import com.net.yuesejiaoyou.redirect.ResolverC.interface3.UploadFileTask;
import com.net.yuesejiaoyou.redirect.ResolverC.interface3.UsersThread_01152;
import com.net.yuesejiaoyou.redirect.ResolverC.interface4.RoundImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class ModifyInformation_01152 extends Activity implements OnClickListener {
			//返回按钮，照片， 昵称        ，性别		//头像
	ImageView back;
				//性别	//从相册中选择  //拍照
	TextView quxiao1,nicheng,quxiao;
	RoundImageView touxiang;

	private static final int TAKE_PICTURE = 0x000001;
	             //修改头像，修改昵称
	RelativeLayout RL,rl,t2;
	LinearLayout xiangji,xiangce;
	EditText newname;
	TextView queding;
	Button queren;
	String path= Util.url+"/img/imgheadpic/";
	//初始化相册
	private Uri photoUri;
	private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
	};
	//初始化异步加载图片
	private DisplayImageOptions options=null;
	
	//弹出框
	private PopupWindow popupWindow;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogDetect.send(LogDetect.DataType.specialType, "ModifyInformation_01152:", "布局开始");
		setContentView(R.layout.xiugaiziliao_1152);
		//初始化头像	
		touxiang = (RoundImageView)findViewById(R.id.touxiang);
		//初始化昵称
		nicheng = (TextView)findViewById(R.id.nicheng);
		
		back = (ImageView)findViewById(R.id.back);
		back.setOnClickListener(this);
		
		queding = (TextView) findViewById(R.id.queding);
		queding.setOnClickListener(this);
		
		RL = (RelativeLayout)findViewById(R.id.RL);
		RL.setOnClickListener(this);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			for(String permission: permissions) {
				// 检查该权限是否已经获取
				int i = ContextCompat.checkSelfPermission(ModifyInformation_01152.this, permission);
				// 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
				if (i != PackageManager.PERMISSION_GRANTED) {
					// 如果没有授予该权限，就去提示用户请求
					ActivityCompat.requestPermissions(ModifyInformation_01152.this, permissions, 321);
					return;
				}
			}
		}
		rl = (RelativeLayout)findViewById(R.id.rl);
		rl.setOnClickListener(this);
		LogDetect.send(LogDetect.DataType.specialType, "ModifyInformation_01152:", "布局结束");

		//向服务端请求个人信息
		String mode = "personal_information";
		String[] paramsMap = {Util.userid};
		UsersThread_01152 b = new UsersThread_01152(mode,paramsMap,requestHandler);
		Thread t = new Thread(b.runnable);
		t.start();
	}			
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch(id){
			
		case R.id.back:
			//点击左尖号，返回到上一个页面
			finish();
			break;		
		case R.id.queding:
			//点击保存，保存整个页面,在服务端将整个修改的地方重新初始化，先修改再查询
			String mode = "save_personal_information";//输入的昵称                                                    //头像图片
			String[] paramsMap =  {Util.userid,nicheng.getText().toString(),path};
			UsersThread_01152 b = new UsersThread_01152(mode,paramsMap,requestHandler);
			Thread t = new Thread(b.runnable);
			t.start();
			break;
		case R.id.RL:
			//点击图片右尖号，弹出更改图片页面，更改图片。
			int i = 1;
			showPopupspWindow(v,i);
			break;		
		case R.id.rl:
			//点击昵称右尖号，更改昵称
			int i1 = 2;
			//showPopupspWindow2(v,i1);
			Intent intent=new Intent();
			intent.setClass(ModifyInformation_01152.this,Vliao_setnickname_01162.class);
			intent.putExtra("old_name",nicheng.getText());
			startActivityForResult(intent,162);
			break;			
//		case R.id.xingbie1:
//			//点击性别右尖号，更改性别
//			int i2 = 3;
//			showPopupspWindow1(v,i2);
//			break;
		}
	}
	private Handler requestHandler = new Handler() {
		  @Override
		  public void handleMessage(Message msg) {
		    switch (msg.what) {
			//上传头像
		      case 101:
		    	  @SuppressWarnings("unchecked")
                  String path = (String) msg.obj;
		    	  SharedPreferences share1 = getSharedPreferences("Acitivity", Activity.MODE_PRIVATE);
		    	  share1.edit().putString("photo",path).commit();

		    	  touxiang.setTag(Util.url + path);
		    		/*ImgLoad.imgDL(Utils.url + path,
							iv_photoimage);*/
		    	String mode2 = "personal_information";
		  		String[] paramsMap2 = {Util.userid};
		  		UsersThread_01152 b2 = new UsersThread_01152(mode2,paramsMap2,requestHandler);
		  		Thread t2 = new Thread(b2.runnable);
		  		t2.start();
		     break; 
			//将用户的信息返回显示
		    case 210:
		    	  @SuppressWarnings("unchecked")
                  ArrayList<Member_01152> list1 = (ArrayList<Member_01152>)msg.obj;

		    	  if(list1 == null || list1.size() <= 0) {
		    	  	break;
				  }
				LogDetect.send(LogDetect.DataType.specialType, "ModifyInformation_01152昵称:", list1.get(0).getNickname());
		    	  nicheng.setText(list1.get(0).getNickname());
		    	 
		    	  if(list1.get(0).getPhoto().contains("http")){
		    		  ImageLoader.getInstance().displayImage(list1.get(0).getPhoto(),touxiang,options);
		    	  }
		    	  break;
		    //点击保存后先修改后展示
		    case 202:
		    	  String json = (String)msg.obj;
		    	  if(json.equals("")){
						Toast.makeText(ModifyInformation_01152.this, "返回的json为空", Toast.LENGTH_SHORT).show();
					break;
					}
		    	  
		    	try { //如果服务端返回1，说明个人信息修改成功了
		    		JSONObject jsonObject = new JSONObject(json);
					if(jsonObject.getString("success").equals("1")){
					Toast.makeText(ModifyInformation_01152.this, "修改成功", Toast.LENGTH_SHORT).show();//Modify the success
					finish();
				}else{
					Toast.makeText(ModifyInformation_01152.this, "修改失败", Toast.LENGTH_SHORT).show();//Modify the failure
				}
			   } catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	//向服务端请求个人信息
				String mode = "personal_information";
				String[] paramsMap = {Util.userid};
				UsersThread_01152 b = new UsersThread_01152(mode,paramsMap,requestHandler);
				Thread t = new Thread();
				t.start();
				
				SharedPreferences share=getSharedPreferences("Acitivity", Activity.MODE_PRIVATE);
				share.edit().putString("nickname",nicheng.getText().toString()).commit();
				
		    }
		  }
	};

//弹出的更改图片界面
public void showPopupspWindow(View parent, final int i) {
	// 加载布局
	LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	View layout = inflater.inflate(R.layout.modify_photo_01152, null);
	xiangji=(LinearLayout) layout.findViewById(R.id.xiangji);
	xiangji.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			takePhoto();
			popupWindow.dismiss();
			
		}
	});
	
	
	
	
	xiangce = (LinearLayout)layout.findViewById(R.id.xiangce);
	xiangce.setOnClickListener(new OnClickListener() {
		//点击相册，从相册中选择
		@Override
		public void onClick(View arg0) {
			xiangce();
			popupWindow.dismiss();
		}
		//打开系统相册
	private void xiangce() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent, 3);
		
	}
	});
	t2= (RelativeLayout) layout.findViewById(R.id.t2);
	t2.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View view) {
			popupWindow.dismiss();
		}
	});



	popupWindow = new PopupWindow(layout, LayoutParams.MATCH_PARENT,
			LayoutParams.WRAP_CONTENT, true);
	// 控制键盘是否可以获得焦点
	popupWindow.setFocusable(true);
	// 设置popupWindow弹出窗体的背景
	WindowManager.LayoutParams lp = getWindow().getAttributes();
	lp.alpha = 0.4f;
	getWindow().setAttributes(lp);
	popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
	WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
	@SuppressWarnings("deprecation")
	// 获取xoff
	int xpos = manager.getDefaultDisplay().getWidth() / 2
			- popupWindow.getWidth() / 2;
	// xoff,yoff基于anchor的左下角进行偏移。
	// popupWindow.showAsDropDown(parent, 0, 0);
	popupWindow.showAtLocation(parent, Gravity.CENTER | Gravity.CENTER,252, 0);
	// 监听

	popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
		// 在dismiss中恢复透明度
		public void onDismiss() {
			WindowManager.LayoutParams lp = getWindow().getAttributes();
			lp.alpha = 1f;
			getWindow()
					.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
			getWindow().setAttributes(lp);
		}
	});
	
}
	

//弹出的更改昵称页面
public void showPopupspWindow2(View parent, final int i) {
	// 加载布局
	LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	View layout = inflater.inflate(R.layout.modify_nickname_01152, null);
	
	newname = (EditText)layout.findViewById(R.id.newname);
	
	queren = (Button)layout.findViewById(R.id.queren);
	queren.setOnClickListener(new OnClickListener() {
		
		//如果点击确认，就把输入的值放入昵称控件
		@Override
		public void onClick(View arg0) {
			
			nicheng.setText(newname.getText().toString());
			popupWindow.dismiss();
		}
	});
	
	popupWindow = new PopupWindow(layout, LayoutParams.MATCH_PARENT,
			LayoutParams.WRAP_CONTENT, true);
	// 控制键盘是否可以获得焦点
	popupWindow.setFocusable(true);
	// 设置popupWindow弹出窗体的背景
	WindowManager.LayoutParams lp = getWindow().getAttributes();
	lp.alpha = 0.4f;
	getWindow().setAttributes(lp);
	popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
	WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
	@SuppressWarnings("deprecation")
	// 获取xoff
	int xpos = manager.getDefaultDisplay().getWidth() / 2
			- popupWindow.getWidth() / 2;
	// xoff,yoff基于anchor的左下角进行偏移。
	// popupWindow.showAsDropDown(parent, 0, 0);
	popupWindow.showAtLocation(parent, Gravity.CENTER | Gravity.CENTER,252, 0);
	// 监听

	popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
		// 在dismiss中恢复透明度
		public void onDismiss() {
			WindowManager.LayoutParams lp = getWindow().getAttributes();
			lp.alpha = 1f;
			getWindow()
					.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
			getWindow().setAttributes(lp);
		}
	});
}


//判断图片的状态
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		LogDetect.send(LogDetect.DataType.basicType, "01152-成功回调", requestCode+"????"+resultCode);
		if (requestCode == 3) {
			upimg(data);
		
		}
		switch(requestCode){
		
	        case TAKE_PICTURE:
		

		String imagePath = "";
		if (data != null && data.getData() != null) {// 有数据返回直接使用返回的图片地址

			LogDetect.send(LogDetect.DataType.specialType, "mImagePath: ", mImagePath);
			imagePath = data.getData().getEncodedPath();
			
		} else {// 无数据使用指定的图片路径
			imagePath = mImagePath;
			LogDetect.send(LogDetect.DataType.specialType, "mImagePath: ", mImagePath);
		}
		String a=compressPic(imagePath);
		UploadFileTask uploadFileTask = new UploadFileTask(ModifyInformation_01152.this, requestHandler);
		uploadFileTask.execute(a, Util.userid);
		break;


			case 162:
                  String t1=data.getStringExtra("new_name");
                  if(t1.equals("")){

				  }else{
					  nicheng.setText(t1);
				  }
				break;



		}
		
		}

		

	
	//不同手机的处理情况
	@SuppressLint("NewApi")
	protected void upimg(Intent data) {
		int colunm_index = 0;
		String path;
		if (data == null) {
			Toast.makeText(ModifyInformation_01152.this, "未选择图片", Toast.LENGTH_LONG).show();
			return;
		}
		
		photoUri = data.getData();
		if (photoUri == null) {
			Toast.makeText(ModifyInformation_01152.this, "选择文件格式错误", Toast.LENGTH_LONG).show();
			return;
		}
		String[] pojo = { MediaStore.Images.Media.DATA };
		@SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(photoUri, pojo, null, null, null);
		if(cursor != null) {
			colunm_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			path = cursor.getString(colunm_index);
			
			// 华为手机方案
			if(path == null) {
				final String docId = DocumentsContract.getDocumentId(photoUri);
	            final String[] split = docId.split(":");
	            final String[] selectionArgs = new String[] { split[1] };
				cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, pojo, "_id=?", selectionArgs, null);
				cursor.moveToFirst();
				final int index = cursor.getColumnIndexOrThrow("_data");  
	            path = cursor.getString(index); 
			}
			
			
			path = compressPic(path);	//压缩图片并保存，返回小图片path


		
			//ContentResolver cr = getContentResolver();
		} else {
			// 小米手机方案
			path = data.getData().getEncodedPath();
			path = compressPic(path);
		}
		/*try {
			bitmap = BitmapFactory.decodeStream(new FileInputStream(new File(path)));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		UploadFileTask uploadFileTask = new UploadFileTask(ModifyInformation_01152.this, requestHandler);
		uploadFileTask.execute(path,Util.userid);
	}
	
	//压缩图片的方法
	private String compressPic(String path) {
		//01107m add start
		File fileTemp = new File(path);
		long length = fileTemp.length();
		if(length > 1024*200) {	//文件大于1MB就把图片长宽各压缩1/4，图片质量降低为原来的80%
			FileOutputStream fos;
			BitmapFactory.Options newOpts = new BitmapFactory.Options();
			newOpts.inJustDecodeBounds = true;
			Bitmap bitmap = BitmapFactory.decodeFile(path,newOpts);
			newOpts.inJustDecodeBounds = false;
	        newOpts.inSampleSize = 4;
	        bitmap = BitmapFactory.decodeFile(path, newOpts);
	        try {
	        	File newFile = new File(Environment.getExternalStorageDirectory()+"/"+fileTemp.getName());
	        	if(newFile.exists()) {
	        		fos = new FileOutputStream(fileTemp);
	        	} else {	//如果是从相册发送的图片且体积过大，压缩后存放入SD根目录
	        		path = Environment.getExternalStorageDirectory()+"/"+fileTemp.getName();
	        		fos = new FileOutputStream(new File(path));
	        	}
				
				bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// 释放局部变量bitmap资源,防止OOM异常
				if(bitmap != null && !bitmap.isRecycled()) {
					bitmap.recycle();
					bitmap = null;
					System.gc();
				}
			}
		}
		return path;
	}
	String mImagePath="";
	private void takePhoto() {
		// 执行拍照前，应该先判断SD卡是否存在
		String SDState = Environment.getExternalStorageState();
		if (SDState.equals(Environment.MEDIA_MOUNTED)) {
			/**
			 * 通过指定图片存储路径，解决部分机型onActivityResult回调 data返回为null的情况
			 */
			//获取与应用相关联的路径
			String imageFilePath =getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
			//String imageFilePath =getSDPath();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
			//根据当前时间生成图片的名称
			String timestamp = "/"+formatter.format(new Date())+".jpg";
			File imageFile = new File(imageFilePath,timestamp);// 通过路径创建保存文件

			Uri imageFileUri;

			/*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果是7.0android系统
				ContentValues contentValues = new ContentValues(1);
				contentValues.put(MediaStore.Images.Media.DATA, imageFile.getAbsolutePath());
				imageFileUri= getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
			}else{
				imageFileUri = Uri.fromFile(imageFile);
			}*/
			imageFileUri = Uri.fromFile(imageFile);
			LogDetect.send(LogDetect.DataType.basicType, "01152-照片",imageFileUri);
			mImagePath = imageFile.getAbsolutePath();
			LogDetect.send(LogDetect.DataType.specialType, "mImagePath: ", mImagePath);
			//Uri imageFileUri = Uri.fromFile(imageFile);// 获取文件的Uri
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT,imageFileUri);// 告诉相机拍摄完毕输出图片到指定的Uri
			startActivityForResult(intent, TAKE_PICTURE);
		} else {
			//Toast.makeText(this, "内存卡不存在！", Toast.LENGTH_LONG).show();
		}
	}




	
}