package com.net.yuesejiaoyou.redirect.ResolverB.interface4.agora.zhubo;

import android.Manifest;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.net.yuesejiaoyou.R;
import com.net.yuesejiaoyou.classroot.interface4.LogDetect;
import com.net.yuesejiaoyou.classroot.interface4.openfire.core.Utils;
import com.net.yuesejiaoyou.classroot.interface4.openfire.infocenter.bean.Msg;
import com.net.yuesejiaoyou.classroot.interface4.openfire.infocenter.db.Const;
import com.net.yuesejiaoyou.classroot.interface4.openfire.infocenter.hengexa1.smack.XMPPException;
import com.net.yuesejiaoyou.classroot.interface4.openfire.infocenter.hengexa1.smack.XMPPTCPConnection;
import com.net.yuesejiaoyou.classroot.interface4.openfire.infocenter.hengexa2.smack.Chat;
import com.net.yuesejiaoyou.classroot.interface4.openfire.infocenter.hengexa2.smack.ChatManager;
import com.net.yuesejiaoyou.classroot.interface4.openfire.infocenter.hengexa2.smack.SmackException;
import com.net.yuesejiaoyou.classroot.interface4.openfire.infocenter.view.DropdownListView;
import com.net.yuesejiaoyou.classroot.interface4.util.Util;
import com.net.yuesejiaoyou.redirect.ResolverB.interface3.UsersThread_01158B;
import com.net.yuesejiaoyou.redirect.ResolverB.interface3.UsersThread_01160B;
import com.net.yuesejiaoyou.redirect.ResolverB.interface4.agora.Downloader;
import com.net.yuesejiaoyou.redirect.ResolverB.interface4.agora.IActivityListener;
import com.net.yuesejiaoyou.redirect.ResolverB.interface4.agora.IAgoraVideoEventListener;
import com.net.yuesejiaoyou.redirect.ResolverB.interface4.agora.IUserInfoHandler;
import com.net.yuesejiaoyou.redirect.ResolverB.interface4.agora.IVideoHandler;
import com.net.yuesejiaoyou.redirect.ResolverB.interface4.agora.zhubo.ZhuboActivity;
import com.net.yuesejiaoyou.redirect.ResolverB.interface4.util.AgoraVideoManager;
import com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.ClickUtils;
import com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.FilterRecyclerViewAdapter;
import com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.FilterTypeHelper;
import com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.GPUCamImgOperator;
import com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.LuoGLCameraView;
import com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.MenuAdapter;
import com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.MenuBean;
import com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.ZIP;
import com.net.yuesejiaoyou.redirect.ResolverB.uiface.AgoraRtcActivity_zhubo;
import com.net.yuesejiaoyou.redirect.ResolverB.uiface.MeiyanSet_zhubo;
import com.xiaojigou.luo.xjgarsdk.XJGArSdkApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Administrator on 2018\8\17 0017.
 */

public class ZhuboVideoFragment extends Fragment implements View.OnTouchListener,View.OnClickListener, IActivityListener, IAgoraVideoEventListener {

    private View fragmentView;
    private Activity baseActivity;
    private IUserInfoHandler userInfoHandler;
    private IVideoHandler videoHandler;
    //----------------------------------------------------------------------------------------------
    // 表情图标每页6列4行
    private int columns = 6;
    //private VideoPreProcessing mvideoPreProcessing;
    private int rows = 4;
    private EditText send_sms;
    private SimpleDateFormat sd;
    private LayoutInflater inflater;
    // 每页显示的表情view
    private List<View> views = new ArrayList<View>();
    // 表情列表
    private List<String> staticFacesList;
    private LinearLayout chat_face_container, chat_add_container,bottom;
    private ImageView image_face;// 表情图标
    private ViewPager mViewPager;
    private LinearLayout mDotsLayout;
    private EditText input;
    private TextView send;
    private DropdownListView mListView;
    private String I, YOU,name,logo,headpicture,username,duifang="";
    private ScheduledExecutorService service;
    private LinearLayout ly1;
    private TextView t2;
    // private GiftItemView giftView ;
    // 美颜
    //private VideoPreProcessing mVideoPreProcessing;
    private boolean bJHThread = true;
    private SeekBar smoothLevel;
    private SeekBar whiteLevel;
    private ImageView meiyan,meibai;
    private String guke_name;

    private int mopiLevel;
    private int meibaiLevel;
    // 群聊
    TextView grpChat,btn_chat;
    TextView sendMsg;
    EditText edtInput;
    RelativeLayout layChat;
    RelativeLayout another1;
    RelativeLayout layGrpChat;
    ArrayList<String> grpChatArray = new ArrayList<String>();

    //计时器相关
    private TextView time;
    private Timer timer;
    private String strtime;
    Reciever reciever;
    //private List<Shang_01160> shangList;

    private int num =1;
    private String roomid,yid_zhubo,yid_guke;

    private RelativeLayout relativeLayout;
    private RelativeLayout activity_video_chat_view;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;

    private int xDelta;
    private int yDelta;
    private int sWidth;

    private boolean ks =  false;
    private RelativeLayout gbsp,xz,moshubang,xx;
    private ImageView imageView7;
    private LinearLayout ly2;
    MsgOperReciver_zhubo msgOperReciver;
    private SurfaceView remoteSurface,localSurface;
    private FrameLayout remoteContainer,localContainer;

    private CheckedTextView c1,c2,c3,c4,c5,c6;

    private PopupWindow popupWindow;

    private ImageView translate;
    private LinearLayout translate_select;
    private ImageView cn,en;
    //    private SpeechTranslate spchTranslate;
    private boolean switchCameraCtrl = false;
    private int switchCameraCnt = 10;

    private CountDownTimer c;   // 一对一视频余额不足倒计时

    private RelativeLayout layMuteRemoteVideo;

    private RelativeLayout layRedpk;
    private TextView redpkUsername;
    private TextView redpkValue;
    private redpkTimer showRedpkThread;

//    private boolean isHuibo = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.e("TTT","ZhuboVideoFragment-onCreateView start");
        // 获取上层获取用户信息与操作一对一视频的接口
        baseActivity = getActivity();
        userInfoHandler = (IUserInfoHandler)baseActivity;
        videoHandler = (IVideoHandler)baseActivity;

        fragmentView = inflater.inflate(R.layout.activity_agora_rtc_zhubo, null);

//        ((ZhuboActivity)baseActivity).setKeyUpListener(this);

        // 获取远程surfaceView
        SurfaceView surfaceView = videoHandler.getRemoteSurfaceView();
        if(surfaceView != null) {
            setupRemoteVideo();
            remoteSurface = surfaceView;    //videoHandler.getRemoteSurfaceView();
            if (remoteSurface != null) {
                remoteContainer.addView(remoteSurface);
            }
        }


        layMuteRemoteVideo = (RelativeLayout)fragmentView.findViewById(R.id.lay_remote_mute);
        layRedpk = (RelativeLayout)fragmentView.findViewById(R.id.lay_redpk);
        redpkUsername = (TextView)fragmentView.findViewById(R.id.txt_username);
        redpkValue = (TextView)fragmentView.findViewById(R.id.txt_rdvalue);

        DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        sWidth = metric.widthPixels;
        time = (TextView) fragmentView.findViewById(R.id.time);
        roomid = userInfoHandler.getRoomid();   //getIntent().getStringExtra("roomid");
        yid_zhubo = userInfoHandler.getFromUserId();    //getIntent().getStringExtra("yid_zhubo");
        guke_name = userInfoHandler.getFromUserName();  //getIntent().getStringExtra("guke_name");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Acitivity", Context.MODE_PRIVATE); //私有数据
        I = sharedPreferences.getString("id","");
        sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        username = sharedPreferences.getString("name","");

        // 读取美颜设置
        mopiLevel = sharedPreferences.getInt("mopi",1);
        meibaiLevel = sharedPreferences.getInt("meibai",1);

/*      headpicture= sharedPreferences.getString("headpic","");
*/

        fragmentView.findViewById(R.id.btn_endcall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEncCallClicked(view);
            }
        });


        activity_video_chat_view = (RelativeLayout) fragmentView.findViewById(R.id.activity_video_chat_view);


        fragmentView.findViewById(R.id.cafont).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupspWindow_rp(fragmentView.findViewById(R.id.activity_video_chat_view));
            }
        });

        //获取屏幕高度
        screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight/3;




        final ImageView guanbi = (ImageView)fragmentView.findViewById(R.id.guanbi);
        guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLocalVideoMuteClicked(guanbi);
                //takePhoto();
            }
        });
//        final ImageView qiehuan = (ImageView)this.findViewById(R.id.qiehuan);
//        qiehuan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                if(switchCameraCtrl == false) {
////                    switchCameraCtrl = true;
//                onSwitchCameraClicked(qiehuan);
////                    startSwitchCameraCtrl();
////                } else {
////                    Toast.makeText(VideoChatViewActivity_zhubo.this, "请 "+switchCameraCnt+" 秒后再切换摄像头", Toast.LENGTH_SHORT).show();
////                }
//            }
//        });


        ly1 = (LinearLayout)fragmentView.findViewById(R.id.ly1);
        ly1.setVisibility(View.GONE);
        t2 = (TextView)ly1.findViewById(R.id.t2);

        LogDetect.send(LogDetect.DataType.specialType,"01160 顾客id:",yid_zhubo);
        reciever=new Reciever();
        IntentFilter intentFilter1 = new IntentFilter(Const.REWARD_ANCHOR);
        getActivity().registerReceiver(reciever, intentFilter1);


/*
        String mode1 = "kan_qian";
        String[] paramsMap1 = {Util.userid,yid_zhubo};
        UsersThread_01160 a = new UsersThread_01160(mode1,paramsMap1,handler);
        Thread t = new Thread(a.runnable);
        t.start();*/

        msgOperReciver = new MsgOperReciver_zhubo();
        IntentFilter intentFilter = new IntentFilter(Const.ACTION_MSG_ONECHAT);
        getActivity().registerReceiver(msgOperReciver, intentFilter);

        //if (checkSelfPermission(Manifest.permission.RECORD_AUDIO, PERMISSION_REQ_ID_RECORD_AUDIO) && checkSelfPermission(Manifest.permission.CAMERA, PERMISSION_REQ_ID_CAMERA)) {
            init();
            initAgoraEngineAndJoinChannel();
        //}



        timer=new Timer();
        timer.schedule(new TimerTask(){
            SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
            long t = System.currentTimeMillis();
            long tl = t / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
            @Override
            public void run() {
                Timestamp tt = new Timestamp(tl);
                String starttime = sdf.format(tt);
                handler.sendMessage(handler.obtainMessage(1,(Object)starttime));
                tl = tl+1000;
            }
        }, 0,1000);

//        if(!TextUtils.isEmpty(getIntent().getStringExtra("status"))) {
//            isHuibo = true;
//        }

//        if(isHuibo) {
            // 进入房间就删除记录
            String mode2 = "removep2pvideo";
            String[] paramsMap2 = {"", userInfoHandler.getRoomid()};
            UsersThread_01158B a2 = new UsersThread_01158B(mode2, paramsMap2, handler);
            Thread c2 = new Thread(a2.runnable);
            c2.start();
//        } else {
//            // 进入房间就删除记录
//            String mode2 = "removep2pvideo";
//            String[] paramsMap2 = {"", AgoraVideoManager.getCurRoomid()};
//            UsersThread_01158B a2 = new UsersThread_01158B(mode2, paramsMap2, handler);
//            Thread c2 = new Thread(a2.runnable);
//            c2.start();
//        }
        startAwake();   // 保持屏幕常亮

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                @SuppressLint("ResourceType") XmlPullParser parser = getResources().getXml(R.layout.lay_luoglcameraview);
//                AttributeSet attributes = Xml.asAttributeSet(parser);
//                LuoGLCameraView subView = new LuoGLCameraView(AgoraRtcActivity_zhubo.this, attributes);
//                remoteContainer.addView(subView);
//            }
//        }).start();
//        @SuppressLint("ResourceType") XmlPullParser parser = getResources().getXml(R.layout.lay_luoglcameraview);
//                AttributeSet attributes = Xml.asAttributeSet(parser);
//        cameraView = new LuoGLCameraView(getApplicationContext());
//        cameraView.setVisibility(View.VISIBLE);
//        remoteContainer.addView(cameraView);

        //=====================================================
        // 美颜初始化
        //=====================================================
        sharedPreferences = getActivity().getSharedPreferences("Acitivity", Context.MODE_PRIVATE); //私有数据
        // 获取保存在本地的美颜参数
        hongRun = sharedPreferences.getInt("hongrun",0);
        meiBai = sharedPreferences.getInt("meibai",0);
        moPi = sharedPreferences.getInt("mopi",0);
        shouLian = sharedPreferences.getInt("shoulian",0);
        daYan = sharedPreferences.getInt("dayan",0);
        vFilter = sharedPreferences.getString("filter","");
        tieZhi = sharedPreferences.getString("tiezhi","");

        // 设置美颜参数
        XJGArSdkApi.XJGARSDKSetRedFaceParam(hongRun);
        XJGArSdkApi.XJGARSDKSetWhiteSkinParam(meiBai);
        XJGArSdkApi.XJGARSDKSetSkinSmoothParam(moPi);
        XJGArSdkApi.XJGARSDKSetThinChinParam(shouLian);
        XJGArSdkApi.XJGARSDKSetBigEyeParam(daYan);
        if(vFilter.isEmpty() == false) {
            XJGArSdkApi.XJGARSDKChangeFilter(vFilter);
        }
        if(tieZhi.isEmpty() == false) {
//            String stickerPaperdir = XJGArSdkApi.getPrivateResDataDir(getActivity().getApplicationContext());
//            stickerPaperdir = stickerPaperdir +"/StickerPapers/"+ tieZhi;
            File beautyZip = new File(getStickPaperDir(), tieZhi+".zip");
            if(beautyZip.exists()) {
                ZIP.unzipAStickPaperPackages(getStickPaperDir().getAbsolutePath());
                XJGArSdkApi.XJGARSDKSetShowStickerPapers(true);
                XJGArSdkApi.XJGARSDKChangeStickpaper(tieZhi);
            }
        }

        // 设置美颜控件
        mRedFaceSeek.setProgress(hongRun);
        mSkinWihtenSeek.setProgress(meiBai);
        mSkinSmoothSeek.setProgress(moPi);
        mFaceSurgeryFaceShapeSeek.setProgress(shouLian);
        mFaceSurgeryBigEyeSeek.setProgress(daYan);

        Log.e("TTT","ZhuboVideoFragment-onCreateView end");

        return fragmentView;
    }

    //private final AgoraVideoManager.AgoraRtcListener mRtcEventHandler = new AgoraVideoManager.AgoraRtcListener() { // Tutorial Step 1
        @Override
        public void onFirstRemoteVideoDecoded() { // Tutorial Step 5

            setupRemoteVideo();
            remoteSurface = videoHandler.getRemoteSurfaceView();    //AgoraVideoManager.getRemoteSurfaceView();
            if(remoteSurface != null) {
                remoteSurface.setZOrderMediaOverlay(false);
                remoteContainer.addView(remoteSurface);
            }
            //Toast.makeText(baseActivity, "onFirstRemoteVideoDecoded", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onJoinChannelSuccess() {

        }

        @Override
        public void onUserOffline() { // Tutorial Step 7
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onRemoteUserLeft();
                }
            });
        }

        @Override
        public void onUserMuteVideo(final boolean muted) { // Tutorial Step 10
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onRemoteUserVideoMuted( muted);
                }
            });
        }
    //};

    // 美颜参数
    private int hongRun;    // 红润
    private int meiBai;     // 美白
    private int moPi;       // 磨皮
    private int shouLian;   // 瘦脸
    private int daYan;      // 大眼
    private String vFilter;    // 滤镜
    private String tieZhi;     // 贴纸


    private LuoGLCameraView cameraView;

    PowerManager.WakeLock wakeLock;
    private void startAwake() {
        PowerManager pm = (PowerManager) getActivity().getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "TAG");
        wakeLock.acquire();
    }
    private void stopAwake() {
        if(wakeLock != null) {
            wakeLock.release();
        }
    }

    /**
     * 一对一视频切换摄像头控制
     */
    private void startSwitchCameraCtrl() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                switchCameraCnt = 10;
                while(switchCameraCnt > 0) {
                    switchCameraCnt--;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                switchCameraCtrl = false;
            }
        }).start();
    }



    @Override
    public boolean onTouch(View view, MotionEvent event) {

        final int x = (int) event.getRawX();
        final int y = (int) event.getRawY();
        Log.d("TT", "onTouch: x= " + x + "y=" + y);
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                xDelta = sWidth - x - params.rightMargin;
                yDelta = y - params.topMargin;
                Log.d("TT", "ACTION_DOWN: xDelta= " + xDelta + "yDelta=" + yDelta);
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                int xDistance = sWidth - x - xDelta;
                int yDistance = y - yDelta;
                Log.d("TT", "ACTION_MOVE: xDistance= " + xDistance + "yDistance=" + yDistance);
                layoutParams.rightMargin = xDistance;
                layoutParams.topMargin = yDistance;
                view.setLayoutParams(layoutParams);
                break;
        }
        //localView.invalidate();
        return false;
    }


    public void onMeiyanClicked(View view) {
    }

    private void addGrpChat(String txt) {
        int size = grpChatArray.size();
        if(size >= 4) {
            grpChatArray.remove(0);
        }
        grpChatArray.add(txt);
    }

    private void freshGrpChat() {

        if(grpChatArray.size() <= 0) {
            layGrpChat.setVisibility(View.GONE);
            return;
        } else {
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < grpChatArray.size(); i++) {
                sb.append(grpChatArray.get(i)+"\r\n");
            }
            grpChat.setText(/*guke_name+":"+*/sb.toString().substring(0, sb.length()-2));
            /*if(duifang.equals("")){
                grpChat.setText(guke_name+":"+sb.toString().substring(0, sb.length()-2));
            }else{
                grpChat.setText(guke_name+":"+sb.toString().substring(0, sb.length()-2));
            }*/

            layGrpChat.setVisibility(View.VISIBLE);
        }


    }
    String songing="";

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.c1:
                String mode ="report";
                String[] paramsMap = {Util.userid,yid_zhubo,c1.getText().toString()};
                UsersThread_01160B az = new UsersThread_01160B(mode, paramsMap, handler);
                Thread t = new Thread(az.runnable);
                t.start();
                LogDetect.send(LogDetect.DataType.specialType,"01160 开线程举报:","3");
                break;
            case R.id.c2:
                String mode112 ="report";
                String[] paramsMap112 = {Util.userid,yid_zhubo,c2.getText().toString()};
                UsersThread_01160B a1 = new UsersThread_01160B(mode112, paramsMap112, handler);
                Thread t1 = new Thread(a1.runnable);
                t1.start();
                LogDetect.send(LogDetect.DataType.specialType,"01160 开线程举报:","3");
                break;
            case R.id.c3:
                String mode2 ="report";
                String[] paramsMap2 = {Util.userid,yid_zhubo,c3.getText().toString()};
                UsersThread_01160B a2 = new UsersThread_01160B(mode2, paramsMap2, handler);
                Thread t2 = new Thread(a2.runnable);
                t2.start();
                LogDetect.send(LogDetect.DataType.specialType,"01160 开线程举报:","3");
                break;
            case R.id.c4:
                String mode3 ="report";
                String[] paramsMap3 = {Util.userid,yid_zhubo,c4.getText().toString()};
                UsersThread_01160B a3 = new UsersThread_01160B(mode3, paramsMap3, handler);
                Thread t3 = new Thread(a3.runnable);
                t3.start();
                LogDetect.send(LogDetect.DataType.specialType,"01160 开线程举报:","3");
                break;
            case R.id.c5:
                String mode4 ="report";
                String[] paramsMap4 = {Util.userid,yid_zhubo,c5.getText().toString()};
                UsersThread_01160B a4 = new UsersThread_01160B(mode4, paramsMap4, handler);
                Thread t4 = new Thread(a4.runnable);
                t4.start();
                LogDetect.send(LogDetect.DataType.specialType,"01160 开线程举报:","3");
                break;
            case R.id.c6:
                String mode5 ="report";
                String[] paramsMap5 = {Util.userid,yid_zhubo,c6.getText().toString()};
                UsersThread_01160B a5 = new UsersThread_01160B(mode5, paramsMap5, handler);
                Thread t5 = new Thread(a5.runnable);
                t5.start();
                LogDetect.send(LogDetect.DataType.specialType,"01160 开线程举报:","3");
                break;
        }
    }

    private class MsgOperReciver_zhubo extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            String msgBody=intent.getStringExtra("oneuponechat");

            if(msgBody.contains("开0倒1计2时")) {
                LogDetect.send(LogDetect.DataType.specialType, "01160 主播开倒计时信息 :", msgBody);
                handler.sendMessage(handler.obtainMessage(330, (Object) msgBody));
            } else if(msgBody.contains("停0倒1计2时")) {
                handler.sendMessage(handler.obtainMessage(331, (Object) msgBody));
            } else if(msgBody.startsWith("[☆")){
                LogDetect.send(LogDetect.DataType.specialType, "01160 主播收信息 invite:", msgBody);
                handler.sendMessage(handler.obtainMessage(350, (Object) msgBody));
            }else{
                LogDetect.send(LogDetect.DataType.specialType, "01160 主播收信息 invite:", msgBody);
                handler.sendMessage(handler.obtainMessage(320, (Object) msgBody));
            }
        }
    }
    private class Reciever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, final Intent intent) {
            //Toast.makeText(AgoraRtcActivity_zhubo.this,"得到红包 "+intent.getStringExtra("reward_num")+" 悦币",Toast.LENGTH_LONG).show();
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    showRedpkLayout(guke_name, intent.getStringExtra("reward_num"));
                }
            });
        }
    }

    private void showSoftInputView(final View v) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((InputMethodManager) v.getContext().getSystemService(
                        Service.INPUT_METHOD_SERVICE)).toggleSoftInput(0,
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 0);
    }

    /**
     * 执行发送消息 文本类型
     * @param content
     */
    void sendMsgText(String content) {
//        edtInput.setText("");
//        addGrpChat(content);
//        handler.obtainMessage(19).sendToTarget();
        final String message = content + Const.SPLIT + Const.ACTION_MSG_ONECHAT
                + Const.SPLIT + sd.format(new Date())+ Const.SPLIT+username;
        //LogDetect.send(DataType.noType,Utils.seller_id+"=phone="+Utils.android,"message: "+message);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //LogDetect.send(DataType.noType,Utils.seller_id+"=phone="+Utils.android,"before sendMessage()");
                    sendMessage(Utils.xmppConnection, message, yid_zhubo);
                } catch (XMPPException | SmackException.NotConnectedException e) {
                    e.printStackTrace();
                    //LogDetect.send(DataType.noType,Utils.seller_id+"=phone="+Utils.android,"chatmanager: "+e.toString());
                    Looper.prepare();
                    // ToastUtil.showShortToast(ChatActivity.this, "发送失败");
                    Looper.loop();
                }
            }
        }).start();

    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) getActivity()
                .getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getActivity().getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getActivity().getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void updateRemoteSurfaceView(final SurfaceView surface) {
        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                remoteSurface = surface;
                if(remoteContainer != null) {
                    remoteContainer.removeAllViews();
                    remoteContainer.addView(remoteSurface);
                }
            }
        });

    }

    private void initAgoraEngineAndJoinChannel() {

        setupLocalVideo();           // Tutorial Step 3


        localSurface = videoHandler.getLocalSurfaceView();  //AgoraVideoManager.getLocalSurfaceView();

        if(localSurface != null) {
            // localSurface设置了1像素，再重新恢复一下
//            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)localSurface.getLayoutParams();
//            params.height = FrameLayout.LayoutParams.MATCH_PARENT;
//            params.width = FrameLayout.LayoutParams.MATCH_PARENT;
//            localSurface.setLayoutParams(params);
            localSurface.setZOrderMediaOverlay(true);
            localContainer.addView(localSurface);
        }



//        AgoraVideoManager.setAgoraRtcListener(mRtcEventHandler);
        videoHandler.enableAudio();
    }

    public boolean checkSelfPermission(String permission, int requestCode) {
        //Log.i(LOG_TAG, "checkSelfPermission " + permission + " " + requestCode);
        if (ContextCompat.checkSelfPermission(baseActivity,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(baseActivity,
                    new String[]{permission},
                    requestCode);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        //Log.i(LOG_TAG, "onRequestPermissionsResult " + grantResults[0] + " " + requestCode);

//        switch (requestCode) {
//            case PERMISSION_REQ_ID_RECORD_AUDIO: {
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    checkSelfPermission(Manifest.permission.CAMERA, PERMISSION_REQ_ID_CAMERA);
//                } else {
//                    showLongToast("没有权限 " + Manifest.permission.RECORD_AUDIO);
//                    finish();
//                }
//                break;
//            }
//            case PERMISSION_REQ_ID_CAMERA: {
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    initAgoraEngineAndJoinChannel();
//                } else {
//                    showLongToast("没有权限 " + Manifest.permission.CAMERA);
//                    finish();
//                }
//                break;
//            }
//        }
    }

    public final void showLongToast(final String msg) {
        baseActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(baseActivity.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v("TT","zhubo_bk  onDestroy()");
        stopAwake();    // 解除屏幕常亮

        Log.v("TT","before disableAudioFrame()");
        Log.v("TT","after disableAudioFrame()");
        leaveChannel();

        timer.cancel();//关闭定时器
        baseActivity.unregisterReceiver(reciever);
        baseActivity.unregisterReceiver(msgOperReciver);
    }

    // Tutorial Step 10
    public void onLocalVideoMuteClicked(View view) {
        ImageView iv = (ImageView) view;
        if (iv.isSelected()) {
            iv.setSelected(false);
            iv.clearColorFilter();
        } else {
            iv.setSelected(true);
            //iv.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        }

        videoHandler.muteLocalVideoStream(iv.isSelected());    //mRtcEngine.muteLocalVideoStream(iv.isSelected());

        FrameLayout container = (FrameLayout) fragmentView.findViewById(R.id.local_video_view_container);
        SurfaceView surfaceView = (SurfaceView) container.getChildAt(0);
        surfaceView.setZOrderMediaOverlay(!iv.isSelected());
        surfaceView.setVisibility(iv.isSelected() ? View.GONE : View.VISIBLE);
    }

    // Tutorial Step 9
    public void onLocalAudioMuteClicked(View view) {
        ImageView iv = (ImageView) view;
        if (iv.isSelected()) {
            iv.setSelected(false);
            iv.clearColorFilter();
        } else {
            iv.setSelected(true);
            //iv.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        }
        videoHandler.muteLocalAudioStream(iv.isSelected());   //mRtcEngine.muteLocalAudioStream(iv.isSelected());
    }

    // Tutorial Step 8
    public void onSwitchCameraClicked(View view) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Log.v("TT","before switchcamera");
                videoHandler.switchCamera();   //mRtcEngine.switchCamera();
                Log.v("TT","after switchcamera");
            }
        }).start();
    }

    // Tutorial Step 6
    public void onEncCallClicked(View view) {

        //修改主播 活跃状态
        String mode1 = "modezhubostate";
        String[] paramsMap1 = {Util.userid,yid_zhubo};
        LogDetect.send(LogDetect.DataType.specialType,"01160 主播挂断 video:", Util.userid);
        UsersThread_01158B a = new UsersThread_01158B(mode1,paramsMap1,handler);
        Thread c = new Thread(a.runnable);
        c.start();
        baseActivity.finish();

    }

    // Tutorial Step 3
    private void setupLocalVideo() {
        FrameLayout container = (FrameLayout) fragmentView.findViewById(R.id.local_video_view_container);
        localContainer = container;

        container.setOnTouchListener(this);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(localSurface == null || remoteSurface == null) {
                    //Toast.makeText(VideoChatViewActivity.this, "surface null", Toast.LENGTH_SHORT).show();
                } else {

                    if(remoteContainer == null || localContainer == null) {
                        //Toast.makeText(VideoChatViewActivity.this, "container null", Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(VideoChatViewActivity.this, "onClick", Toast.LENGTH_SHORT).show();
                        localContainer.removeAllViews();
                        remoteContainer.removeAllViews();
                        String tag = (String)remoteContainer.getTag();
                        if("remote".equals(tag)) {
                            remoteContainer.addView(localSurface);
                            localContainer.addView(remoteSurface);
                            localSurface.setZOrderMediaOverlay(false);
                            remoteSurface.setZOrderMediaOverlay(true);
                            remoteContainer.setTag("local");
                        } else if("local".equals(tag)) {
                            remoteContainer.addView(remoteSurface);
                            localContainer.addView(localSurface);
                            localSurface.setZOrderMediaOverlay(true);
                            remoteSurface.setZOrderMediaOverlay(false);
                            remoteContainer.setTag("remote");
                        }
                    }
                }
            }
        });
    }

    // Tutorial Step 5
    private void setupRemoteVideo() {
        FrameLayout container = (FrameLayout) fragmentView.findViewById(R.id.remote_video_view_container);
        remoteContainer = container;
        remoteContainer.setTag("remote");
    }

    // Tutorial Step 6
    private void leaveChannel() {
        videoHandler.leaveChannel();   //mRtcEngine.leaveChannel();
    }

    // Tutorial Step 7
    private void onRemoteUserLeft() {
        FrameLayout container = (FrameLayout) fragmentView.findViewById(R.id.remote_video_view_container);
        container.removeAllViews();

        String mode1 = "modezhubostate";
        String[] paramsMap1 = {Util.userid,yid_zhubo};
        LogDetect.send(LogDetect.DataType.specialType,"01160 主播改变在线状态 video:",Util.userid);
        UsersThread_01158B a = new UsersThread_01158B(mode1,paramsMap1,handler);
        Thread c = new Thread(a.runnable);
        c.start();

        //View tipMsg = findViewById(R.id.quick_tips_when_use_agora_sdk); // optional UI
        //tipMsg.setVisibility(View.VISIBLE);
        baseActivity.finish();
    }

    // Tutorial Step 10
    private void onRemoteUserVideoMuted(boolean muted) {
//        Log.v("TT","onRemoteUserVideoMuted():"+uid+","+muted);
//        FrameLayout container = (FrameLayout) findViewById(R.id.remote_video_view_container);
//
//        SurfaceView surfaceView = (SurfaceView) container.getChildAt(0);
//        LogDetect.send(LogDetect.DataType.specialType, "TyrantsFragment: ", surfaceView);
//        Object tag = surfaceView.getTag();
//        if (tag != null && (Integer) tag == uid) {
//            surfaceView.setVisibility(muted ? View.GONE : View.VISIBLE);
//        }
        if(muted) {
            layMuteRemoteVideo.setVisibility(View.VISIBLE);
        } else {
            layMuteRemoteVideo.setVisibility(View.GONE);
        }
    }


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 280:
                    String json1 = (String)msg.obj;
                    if(!json1.equals("")){
                        try {
                            JSONObject jsonObject = new JSONObject(json1);
                            LogDetect.send(LogDetect.DataType.exceptionType,"01160 给主播扣钱:",jsonObject);
                            //如果返回1，说明修改成功了
                            String success_ornot=jsonObject.getString("success");
                            if(success_ornot.equals("1")){
                                LogDetect.send(LogDetect.DataType.specialType,"扣了几次:",num);
                                String qian = num+"";
                                Toast.makeText(baseActivity, "主播挂断，扣除1分钟的钱.", Toast.LENGTH_SHORT).show();
                                baseActivity.finish();
                            }else{
                                Toast.makeText(baseActivity, "您的余额不足，无法挂断", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(baseActivity,"请检查网络连接", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 260:
           /*         service.shutdown();*/
                    Toast.makeText(baseActivity, "余额不足", Toast.LENGTH_SHORT).show();
                    baseActivity.finish();
                    break;
                case 210:
             /*     String xiaoxi = (String) msg.obj;
                    LogDetect.send(LogDetect.DataType.specialType,"01160 实时充值 :",xiaoxi);
                    Toast.makeText(VideoChatViewActivity_zhubo.this, xiaoxi, Toast.LENGTH_SHORT).show();
                    break;*/
              /*case 300:
                    String json = (String)msg.obj;
                    LogDetect.send(LogDetect.DataType.exceptionType,"01160 倒计时:",json);
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        LogDetect.send(LogDetect.DataType.exceptionType,"01160 倒计时:",jsonObject);
                        //如果返回1，说明修改成功了
                        String success_ornot=jsonObject.getString("success");
                        if(success_ornot.equals("0")){
                            ly1.setVisibility(View.GONE);
                            num ++;
                            String mode1 = "kan_qian";
                            String[] paramsMap1 = {Util.userid,yid_zhubo};
                            UsersThread_01160 a = new UsersThread_01160(mode1,paramsMap1,handler);


                            service = Executors
                                    .newSingleThreadScheduledExecutor();
                            service.scheduleAtFixedRate(a.runnable, 1, 1, TimeUnit.MINUTES);
                            LogDetect.send(LogDetect.DataType.specialType,"01160 主播执行1分钟定时看用户钱:",TimeUnit.MINUTES);

                        }//否则弹出提示
                        else if(success_ornot.equals("2")){
                            ly1.setVisibility(View.GONE);
                            String mode1 = "mod_online";
                            String[] paramsMap1 = {Util.userid,Util.userid};
                            UsersThread_01160 a = new UsersThread_01160(mode1,paramsMap1,handler);
                            Thread t = new Thread(a.runnable);
                            t.start();
                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;*/
                case 220:
                    break;
                case 320:
                    String msgBody = (String)msg.obj;
                    if(!msgBody.equals("")){
                        Msg m = new Msg();
       /*             List<Msg> listMsg = new ArrayList<Msg>();*/
                        String[] msgs = msgBody.split("卍");
                        LogDetect.send(LogDetect.DataType.specialType,"主播收到", "msgs:"+msgs);
          /*          m.setContent(msgs[0]);
                    m.setType(msgs[1]);
                    m.setDate(msgs[2]);*/
                        duifang = msgs[3];//发送人
          /*          m.setBak1(msgs[4]); //发送人头像*/

                        addGrpChat(msgs[0]);
                        LogDetect.send(LogDetect.DataType.specialType,"01160 顾客发zhubo:",guke_name+":"+msgs[0]);
                        //freshGrpChat();
                        handler.obtainMessage(19).sendToTarget();
                        LogDetect.send(LogDetect.DataType.specialType,"01160", "msgs:"+msgs.toString());
                    }else{
                        Toast.makeText(baseActivity,"网络连接异常，请稍后再试", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 350:

                    break;
                case 330:
                    //Log.v("TT","开倒计时: "+msg.obj);
                    ly1.setVisibility(View.VISIBLE);
                    Toast.makeText(baseActivity,"请提示对方，余额不足",Toast.LENGTH_LONG).show();
                    String[] msgArray = ((String)msg.obj).split("卍");
                    String countType = msgArray[0].replaceAll("开0倒1计2时","");
                    int counter;
                    Log.v("TT","开倒计时-type: "+countType);
                    switch(countType) {
                        case "2":   // 两分钟倒计时
                            counter = 60*2000;
                            break;
                        case "3":   // 一分钟倒计时
                            counter = 60*1000;
                            break;
                        default:
                            counter = 60*1000;
                            break;
                    }

                    c = new CountDownTimer(counter,1000){
                        @Override
                        public void onTick(long l) {
                            //String a = String.valueOf(millisUntilFinished);
                            t2.setText(""+ String.format("%d",l/1000)+"秒");
                        }
                        @Override
                        public void onFinish() {
//                            String mode1 = "mod_online";
//                            String[] paramsMap1 = {Util.userid,yid_guke,"1","2"};
//                            UsersThread_01158B a = new UsersThread_01158B(mode1,paramsMap1,handler);
//                            Thread t = new Thread(a.runnable);
//                            t.start();
                        }
                    }.start();

                           /* new Thread(new MyThread()).start();*/
                    break;
                case 331:
                    ly1.setVisibility(View.GONE);
                    Toast.makeText(baseActivity,"对方已充值",Toast.LENGTH_LONG).show();
                    if(c != null) {
                        c.cancel();
                    }
                    break;
                case 19:
                    freshGrpChat();
                    break;
                case 25:
                    String reportcheckjson = (String) msg.obj;
                    if(reportcheckjson.isEmpty()){
                        Toast.makeText(baseActivity, "网络连接异常。请稍后重试", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    LogDetect.send(LogDetect.DataType.specialType, "handleMessage01178--reportcheckjson-:", reportcheckjson);
                    JSONObject object3;
                    try {
                        object3 = new JSONObject(reportcheckjson);
                        reportcheckjson = object3.getString("success");
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    LogDetect.send(LogDetect.DataType.specialType, "handleMessage01178--reportcheckjson-:", reportcheckjson);
                    if("1".equals(reportcheckjson))
                    {
                        Toast.makeText(baseActivity, "成功", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(baseActivity, "失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 1:
                    strtime = (String) msg.obj;
                    if(!strtime.equals("")){
                        time.setText(strtime);
                    }else{
                        time.setText("");
                    }
                    break;
                case 203:
                    String json_report = (String) (msg).obj;
                    LogDetect.send(LogDetect.DataType.specialType,"01160:",msg);
                    if(!json_report.isEmpty()){
                        try {
                            JSONObject jsonObject1 = new JSONObject(json_report);
                            LogDetect.send(LogDetect.DataType.specialType,"01160:",jsonObject1);
                            //拉黑
                            String success_ornot=jsonObject1.getString("success");
                            LogDetect.send(LogDetect.DataType.specialType,"01160 success_ornot:",success_ornot);
                            if(success_ornot.equals("1")){
                                popupWindow.dismiss();
                                Toast.makeText(baseActivity, "举报成功", Toast.LENGTH_SHORT).show();
                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(baseActivity, "举报失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    /**
     * 表情页改变时，dots效果也要跟着改变
     * */
    class PageChange implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            for (int i = 0; i < mDotsLayout.getChildCount(); i++) {
                mDotsLayout.getChildAt(i).setSelected(false);
            }
            mDotsLayout.getChildAt(arg0).setSelected(true);
        }
    }

    /**
     * 发送消息
     *
     * @param content
     * @param touser
     * @throws XMPPException
     * @throws SmackException.NotConnectedException
     */
    public void sendMessage(XMPPTCPConnection mXMPPConnection, String content,
                            String touser) throws XMPPException, SmackException.NotConnectedException {
        if (mXMPPConnection == null || !mXMPPConnection.isConnected()) {

        }

        ChatManager chatmanager = Utils.xmppchatmanager;

        Chat chat = chatmanager.createChat(yid_zhubo + "@" + Const.XMPP_HOST, null);
        if (chat != null) {

            chat.sendMessage(content, Util.userid + "@" + Const.XMPP_HOST);
            Log.e("jj", "发送成功");
            LogDetect.send(LogDetect.DataType.noType,"01160主播发消息" ,content);
        }else{
            //LogDetect.send(DataType.noType,Utils.seller_id+"=phone="+Utils.android,"send fail:chat is null");
        }
    }

    //连点两次退出
    private long firstTime = 0;
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(baseActivity, "再次点击可以挂断电话", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;// 更新firstTime
                } else {// 两次按键小于2秒时，退出应用
                    //改变主播状态
                    //hideSoftInputView();
                    String mode1 = "modezhubostate";
                    String[] paramsMap1 = {Util.userid,yid_zhubo};
                    LogDetect.send(LogDetect.DataType.specialType,"01160 主播改变在线状态 video:",Util.userid);
                    UsersThread_01158B a = new UsersThread_01158B(mode1,paramsMap1,handler);
                    Thread c = new Thread(a.runnable);
                    c.start();
                    baseActivity.finish();
                }
                return true;
        }
        return false;
    }

//    @Override
//    public void onLayoutChange(View v, int left, int top, int right,
//                               int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//        if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > keyHeight)){
//        }else if(oldBottom != 0 && bottom != 0 &&(bottom - oldBottom > keyHeight)){
//            //关闭
//            layChat.setVisibility(View.GONE);
//        }
//
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        //添加layout大小发生改变监听器
//        activity_video_chat_view.addOnLayoutChangeListener(this);
//    }

    //举报
    @SuppressLint({ "RtlHardcoded", "NewApi" })
    public void showPopupspWindow_rp(View parent) {
        // 加载布局
        LayoutInflater inflater = LayoutInflater.from(baseActivity);
        View layout = inflater.inflate(R.layout.pop_report_01160, null);
        //取消
        TextView quxiao;


        c1 = (CheckedTextView) layout.findViewById(R.id.c1);
        c2 = (CheckedTextView) layout.findViewById(R.id.c2);
        c3 = (CheckedTextView) layout.findViewById(R.id.c3);
        c4 = (CheckedTextView) layout.findViewById(R.id.c4);
        c5 = (CheckedTextView) layout.findViewById(R.id.c5);
        c6 = (CheckedTextView) layout.findViewById(R.id.c6);

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);

        quxiao = (TextView)layout.findViewById(R.id.quxiao);
        quxiao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                popupWindow.dismiss();
            }
        });


        popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 控制键盘是否可以获得焦点
        popupWindow.setFocusable(true);
        WindowManager.LayoutParams lp = baseActivity.getWindow().getAttributes();
        lp.alpha = 0.5f;
        baseActivity.getWindow().setAttributes(lp);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
        //popupWindow.showAsDropDown(parent, 0, 0,Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
        popupWindow.showAtLocation(parent, Gravity.CENTER_VERTICAL|Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                WindowManager.LayoutParams lp = baseActivity.getWindow().getAttributes();
                lp.alpha = 1f;
                baseActivity.getWindow().setAttributes(lp);
            }
        });
    }

    //--------------------------------------------------------------------
//  送红包
    abstract class redpkTimer implements Runnable {
        protected int curCnt = 0;
        protected int maxCnt = 3;

        public void clearCnt() {
            curCnt = 0;
        }
    }

    private void showRedpkLayout(String username, String value) {

        redpkUsername.setText(username+"用户");
        redpkValue.setText(value);

        if(layRedpk.getVisibility() == View.GONE) {

            layRedpk.setVisibility(View.VISIBLE);

            showRedpkThread = new redpkTimer(){

                @Override
                public void run() {

                    while(curCnt < maxCnt) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        curCnt++;
                    }
                    // 3秒后隐藏红包图片
                    baseActivity.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            layRedpk.setVisibility(View.GONE);
                        }
                    });
                }

                public void clearCnt() {
                    curCnt = 0;
                }
            };

            new Thread(showRedpkThread).start();
        } else {
            if(showRedpkThread != null) {
                showRedpkThread.clearCnt();
            }
        }
    }

    //==============================================================================================
    //==============================================================================================
    //==============================================================================================
    //==============================================================================================
    //==============================================================================================
    //==============================================================================================
    //==============================================================================================
    private LinearLayout mFilterLayout;
    private LinearLayout mFaceSurgeryLayout;
    protected SeekBar mFaceSurgeryFaceShapeSeek;
    protected SeekBar mFaceSurgeryBigEyeSeek;
    protected SeekBar mSkinSmoothSeek;
    protected SeekBar mSkinWihtenSeek;
    protected SeekBar mRedFaceSeek;



    private ArrayList<MenuBean> mStickerData;
    private RecyclerView mMenuView;
    private MenuAdapter mStickerAdapter;

    private RecyclerView mFilterListView;
    private FilterRecyclerViewAdapter mAdapter;
    private com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.GPUCamImgOperator GPUCamImgOperator;
    private boolean isRecording = false;
    private final int MODE_PIC = 1;
    private final int MODE_VIDEO = 2;
    private int mode = MODE_PIC;

    private ImageView btn_shutter;
    private ImageView btn_mode;

    private ObjectAnimator animator;

    private final com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.GPUCamImgOperator.GPUImgFilterType[] types = new GPUCamImgOperator.GPUImgFilterType[]{
            com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.GPUCamImgOperator.GPUImgFilterType.NONE,
            com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.GPUCamImgOperator.GPUImgFilterType.HEALTHY,
            com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.GPUCamImgOperator.GPUImgFilterType.NOSTALGIA,
            com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.GPUCamImgOperator.GPUImgFilterType.COOL,
            com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.GPUCamImgOperator.GPUImgFilterType.EMERALD,
            com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.GPUCamImgOperator.GPUImgFilterType.EVERGREEN,
            com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.GPUCamImgOperator.GPUImgFilterType.CRAYON
    };

    protected void init() {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_camera_with_filter);
        GPUCamImgOperator =  new GPUCamImgOperator();
        LuoGLCameraView luoGLCameraView = (LuoGLCameraView) videoHandler.getLocalSurfaceView(); //AgoraVideoManager.getLocalSurfaceView();
        //mCustomizedCameraRenderer = luoGLCameraView;

        GPUCamImgOperator.context = luoGLCameraView.getContext();
        GPUCamImgOperator.luoGLBaseView = luoGLCameraView;
        initView();


        //options
//        //optimization mode for video
//        XJGArSdkApi.XJGARSDKSetOptimizationMode(0);
        //optimization mode for video using asychronized thread
        XJGArSdkApi.XJGARSDKSetOptimizationMode(2);
        //show sticker papers
//        XJGArSdkApi.XJGARSDKSetShowStickerPapers(true);
        XJGArSdkApi.XJGARSDKSetShowStickerPapers(false);
    }

    private void initView(){
        mFilterLayout = (LinearLayout)fragmentView.findViewById(R.id.layout_filter);

        mFaceSurgeryLayout = (LinearLayout)fragmentView.findViewById(R.id.layout_facesurgery);
        mFaceSurgeryFaceShapeSeek = (SeekBar)fragmentView.findViewById(R.id.faceShapeValueBar);
        mFaceSurgeryFaceShapeSeek.setProgress(20);
        mFaceSurgeryBigEyeSeek = (SeekBar)fragmentView.findViewById(R.id.bigeyeValueBar);
        mFaceSurgeryBigEyeSeek.setProgress(50);

        mSkinSmoothSeek = (SeekBar)fragmentView.findViewById(R.id.skinSmoothValueBar);
        mSkinSmoothSeek.setProgress(100);
        mSkinWihtenSeek = (SeekBar)fragmentView.findViewById(R.id.skinWhitenValueBar);
        mSkinWihtenSeek.setProgress(20);
        mRedFaceSeek = (SeekBar)fragmentView.findViewById(R.id.redFaceValueBar);
        mRedFaceSeek.setProgress(80);
        XJGArSdkApi.XJGARSDKSetSkinSmoothParam(100);
        XJGArSdkApi.XJGARSDKSetWhiteSkinParam(20);
        XJGArSdkApi.XJGARSDKSetRedFaceParam(80);
        mFaceSurgeryFaceShapeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                value = i;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int strength = value;//(int)(value*(float)1.0/100);
                XJGArSdkApi.XJGARSDKSetThinChinParam(strength);
            }
        });
        mFaceSurgeryBigEyeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                value = i;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int strength = value;//(int)(value*(float)1.0/100);
                XJGArSdkApi.XJGARSDKSetBigEyeParam(strength);
            }
        });
        mSkinSmoothSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                value = i;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int level = value;//(int)(value/18);
                XJGArSdkApi.XJGARSDKSetSkinSmoothParam(level);
//                GPUCamImgOperator.setBeautyLevel(level);
            }
        });

        mSkinWihtenSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                value = i;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int level = value;//(int)(value/18);
                XJGArSdkApi.XJGARSDKSetWhiteSkinParam(level);
//                GPUCamImgOperator.setBeautyLevel(level);
            }
        });
        mRedFaceSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                value = i;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int level = value;//(int)(value/18);
                XJGArSdkApi.XJGARSDKSetRedFaceParam(level);
//                GPUCamImgOperator.setBeautyLevel(level);
            }
        });

        mFilterListView = (RecyclerView) fragmentView.findViewById(R.id.filter_listView);

        btn_shutter = (ImageView)fragmentView.findViewById(R.id.btn_camera_shutter);
        btn_mode = (ImageView)fragmentView.findViewById(R.id.btn_camera_mode);

        fragmentView.findViewById(R.id.btn_camera_filter).setOnClickListener(btn_listener);
        fragmentView.findViewById(R.id.btn_camera_closefilter).setOnClickListener(btn_listener);
        fragmentView.findViewById(R.id.btn_camera_shutter).setOnClickListener(btn_listener);
        fragmentView.findViewById(R.id.btn_camera_switch).setOnClickListener(btn_listener);
        fragmentView.findViewById(R.id.btn_camera_mode).setOnClickListener(btn_listener);
        fragmentView.findViewById(R.id.btn_camera_beauty).setOnClickListener(btn_listener);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(baseActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mFilterListView.setLayoutManager(linearLayoutManager);

        mAdapter = new FilterRecyclerViewAdapter(baseActivity, types);
        mFilterListView.setAdapter(mAdapter);
        mAdapter.setOnFilterChangeListener(onFilterChangeListener);

        animator = ObjectAnimator.ofFloat(btn_shutter,"rotation",0,360);
        animator.setDuration(500);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        Point screenSize = new Point();
        baseActivity.getWindowManager().getDefaultDisplay().getSize(screenSize);
//        LuoGLCameraView cameraView = (LuoGLCameraView)findViewById(R.id.glsurfaceview_camera);
        //RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) cameraView.getLayoutParams();
        //01107
//        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) cameraView.getLayoutParams();
//        params.width = screenSize.x;
//        params.height = screenSize.y;//screenSize.x * 4 / 3;
//        cameraView.setLayoutParams(params);



        mMenuView= (RecyclerView)fragmentView.findViewById(R.id.mMenuView);
        mStickerData=new ArrayList<>();
        mMenuView.setLayoutManager(new LinearLayoutManager(baseActivity, LinearLayoutManager.HORIZONTAL,false));
        mStickerAdapter=new MenuAdapter(baseActivity,mStickerData);
        mStickerAdapter.setOnClickListener(new ClickUtils.OnClickListener() {
            @Override
            public void onClick(final View v, int type, int pos, int child) {
                final MenuBean m=mStickerData.get(pos);
                final String name=m.name;
                String path = m.path;
                if (name.equals("无")) {
                    XJGArSdkApi.XJGARSDKSetShowStickerPapers(false);
                    mStickerAdapter.checkPos=pos;
                    v.setSelected(true);
//                }else if(name.equals("测试2")){
//
//                    mStickerAdapter.checkPos=pos;
//                    v.setSelected(true);
                    mStickerAdapter.notifyDataSetChanged();
                }else{
//                    String stickerPaperdir = XJGArSdkApi.getPrivateResDataDir(baseActivity.getApplicationContext());
//                    stickerPaperdir = stickerPaperdir +"/StickerPapers/"+ path;

                    if(type == R.id.mMenu) {
                        try {
                            Log.v("XXX","切换动效: "+getStickPaperDir().getAbsolutePath() + "/StickerPapers/" + path);
                            ZIP.unzipAStickPaperPackages(getStickPaperDir().getAbsolutePath() + "/StickerPapers/" + path);

                            XJGArSdkApi.XJGARSDKSetShowStickerPapers(true);
                            XJGArSdkApi.XJGARSDKChangeStickpaper(path);
                            mStickerAdapter.checkPos = pos;
                            v.setSelected(true);
                            mStickerAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Log.e("XXX","切换动效异常： "+getStackInfo(e));
                        }
                    } else if(type == R.id.laydownload) {
                        v.setEnabled(false);
                        final ImageView imgDownload = v.findViewById(R.id.imgdownload);
                        final ProgressBar pbDownload = v.findViewById(R.id.pbprogress);
                        imgDownload.setVisibility(View.GONE);
                        pbDownload.setVisibility(View.VISIBLE);
                        new Downloader(Util.url + "/res/" + path + ".zip",
                                getStickPaperDir().getAbsolutePath() + "/StickerPapers/"+path + ".zip",
                                new Downloader.DownloaderListener() {
                                    @Override
                                    public void onError(final int code, final String reason) {
                                        Log.v("XXX","onError["+code+"]: "+reason);
                                        baseActivity.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(baseActivity, "下载 "+name+"失败("+code+"): "+reason, Toast.LENGTH_SHORT).show();
                                                pbDownload.setVisibility(View.GONE);
                                                imgDownload.setVisibility(View.VISIBLE);
                                            }
                                        });
                                    }

                                    @Override
                                    public void onProgress(final int progress) {
                                        Log.v("XXX","onProgress: "+progress);
                                        baseActivity.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                pbDownload.setProgress(progress);
                                            }
                                        });

                                    }

                                    @Override
                                    public void onSuccess(long length, String filePath) {
                                        m.exist = true;
                                        m.isDownloading = false;
                                        Log.v("XXX","onSuccess: ("+length+") "+filePath);
                                        baseActivity.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                v.setVisibility(View.GONE);
                                            }
                                        });
                                    }
                                }).start();
                    }
                }

            }
        });
        mMenuView.setAdapter(mStickerAdapter);
        initEffectMenu();
    }

    //初始化特效按钮菜单
    protected void initEffectMenu() {
//        "stpaper900224"     ,"草莓猫"
//        "stpaper900639"     ,"米奇老鼠"
//        "angel"             ,"天使"
//        "caishen"           ,"财神爷"
//        "cangou"            ,"罐头狗"
//        "daxiongmao"        ,"大熊猫"
//        "diving"            ,"潜水镜"
//        "flowermustach"     ,"花胡子"
//        "huahuan"           ,"花环"
//        "huangyamotuo"      ,"黄鸭摩托"
//        "hunli"             ,"婚礼"
//        "leisi"             ,"蕾丝"
//        "lufei"             ,"路飞"
//        "lvhua"             ,"鹿花"
//        "mengtu"            ,"梦兔"
//        "rabbit"            ,"大白兔"
//        "shumeng"           ,"萌狗"
//        "strawberry"        ,"草莓"
//        "xuezunv"           ,"血族女"

//

        MenuBean bean=new MenuBean();
        bean.name="无";
        bean.path="";
        mStickerData.add(bean);

        bean=new MenuBean();
        bean.name="天使";
        bean.path="angel";
        mStickerData.add(bean);

        bean=new MenuBean();
        bean.name="财神爷";
        bean.path="caishen";
        mStickerData.add(bean);

        bean=new MenuBean();
        bean.name="罐头狗";
        bean.path="cangou";
        mStickerData.add(bean);

//        bean=new MenuBean();
//        bean.name="大熊猫";
//        bean.path="daxiongmao";
//        mStickerData.add(bean);

        bean=new MenuBean();
        bean.name="潜水镜";
        bean.path="diving";
        mStickerData.add(bean);

//        bean=new MenuBean();
//        bean.name="花胡子";
//        bean.path="flowermustach";
//        mStickerData.add(bean);

        bean=new MenuBean();
        bean.name="花环";
        bean.path="huahuan";
        mStickerData.add(bean);
//
//        bean=new MenuBean();
//        bean.name="黄鸭摩托";
//        bean.path="huangyamotuo";
//        mStickerData.add(bean);
//
//        bean=new MenuBean();
//        bean.name="婚礼";
//        bean.path="hunli";
//        mStickerData.add(bean);

        bean=new MenuBean();
        bean.name="蕾丝";
        bean.path="leisi";
        mStickerData.add(bean);

        bean=new MenuBean();
        bean.name="路飞";
        bean.path="lufei";
        mStickerData.add(bean);

        bean=new MenuBean();
        bean.name="鹿花";
        bean.path="lvhua";
        mStickerData.add(bean);

        bean=new MenuBean();
        bean.name="梦兔";
        bean.path="mengtu";
        mStickerData.add(bean);

//        bean=new MenuBean();
//        bean.name="大白兔";
//        bean.path="rabbit";
//        mStickerData.add(bean);
//
//        bean=new MenuBean();
//        bean.name="萌狗";
//        bean.path="shumeng";
//        mStickerData.add(bean);
//
//        bean=new MenuBean();
//        bean.name="草莓";
//        bean.path="strawberry";
//        mStickerData.add(bean);

        bean=new MenuBean();
        bean.name="血族女";
        bean.path="xuezunv";
        mStickerData.add(bean);


        bean=new MenuBean();
        bean.name=" 西瓜猫";
        bean.path="stpaper900224";
        mStickerData.add(bean);

//        bean=new MenuBean();
//        bean.name="米奇老鼠";
//        bean.path="stpaper900639";
//        mStickerData.add(bean);

        freshMenuData();

        mStickerAdapter.notifyDataSetChanged();
    }

    private FilterRecyclerViewAdapter.onFilterChangeListener onFilterChangeListener = new FilterRecyclerViewAdapter.onFilterChangeListener(){

        @Override
        public void onFilterChanged(com.net.yuesejiaoyou.redirect.ResolverB.interface4.xjg.GPUCamImgOperator.GPUImgFilterType filterType) {
//            GPUCamImgOperator.setFilter(filterType);
            String filterName = FilterTypeHelper.FilterType2FilterName(filterType);
            XJGArSdkApi.XJGARSDKChangeFilter(filterName);
        }
    };

    public void subPermissions(int requestCode, String[] permissions,
                               int[] grantResults) {

        //subPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length != 1 || grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if(mode == MODE_PIC)
                takePhoto();
            else
                takeVideo();
        } else {
            //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }


    }

    static boolean bShowFaceSurgery = false;
    static boolean bShowImgFilters = false;
    private View.OnClickListener btn_listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int buttonId = v.getId();
            if( buttonId == R.id.btn_camera_mode) {
                switchMode();
            }
            else if (buttonId == R.id.btn_camera_shutter) {
                if (PermissionChecker.checkSelfPermission(baseActivity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(baseActivity, new String[] {
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE },
                            v.getId());
                } else {
                    if(mode == MODE_PIC)
                        takePhoto();
                    else
                        takeVideo();
                }
            }
            else if (buttonId == R.id.btn_camera_filter) {
                bShowImgFilters = !bShowImgFilters;
                if(bShowImgFilters)
                    showFilters();
                else
                    hideFilters();
            }
            else if (buttonId == R.id.btn_camera_switch) {
                GPUCamImgOperator.switchCamera();
            }
            else if (buttonId == R.id.btn_camera_beauty) {
                bShowFaceSurgery = ! bShowFaceSurgery;
                if(bShowFaceSurgery)
                    showFaceSurgery();
                else
                    hideFaceSurgery();
            }
            else if (buttonId ==  R.id.btn_camera_closefilter) {
                if(bShowImgFilters) {
                    hideFilters();
                    bShowImgFilters = false;
                }
            }
        }
    };

    private void switchMode(){
        if(mode == MODE_PIC){
            mode = MODE_VIDEO;
            btn_mode.setImageResource(R.drawable.icon_camera);
        }else{
            mode = MODE_PIC;
            btn_mode.setImageResource(R.drawable.icon_video);
        }
    }

    private void takePhoto(){
        GPUCamImgOperator.savePicture();
    }

    private void takeVideo(){
        if(isRecording) {
            animator.end();
            GPUCamImgOperator.stopRecord();
        }else {
            animator.start();
            GPUCamImgOperator.startRecord();
        }
        isRecording = !isRecording;
    }

    //显示面部整形
    private void showFaceSurgery()
    {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mFaceSurgeryLayout, "translationY", mFaceSurgeryLayout.getHeight(), 0);
        animator.setDuration(200);
        animator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                fragmentView.findViewById(R.id.btn_camera_shutter).setClickable(false);
                mFaceSurgeryLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
        animator.start();

    }

    //隐藏面部整形
    private void hideFaceSurgery()
    {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mFaceSurgeryLayout, "translationY", 0 ,  mFaceSurgeryLayout.getHeight());
        animator.setDuration(200);
        animator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // TODO Auto-generated method stub
                mFaceSurgeryLayout.setVisibility(View.INVISIBLE);
                fragmentView.findViewById(R.id.btn_camera_shutter).setClickable(true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // TODO Auto-generated method stub
                mFaceSurgeryLayout.setVisibility(View.INVISIBLE);
                fragmentView.findViewById(R.id.btn_camera_shutter).setClickable(true);
            }
        });
        animator.start();

    }

    private void showFilters(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(mFilterLayout, "translationY", mFilterLayout.getHeight(), 0);
        animator.setDuration(200);
        animator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                fragmentView.findViewById(R.id.btn_camera_shutter).setClickable(false);
                mFilterLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
        animator.start();
    }

    private void hideFilters(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(mFilterLayout, "translationY", 0 ,  mFilterLayout.getHeight());
        animator.setDuration(200);
        animator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // TODO Auto-generated method stub
                mFilterLayout.setVisibility(View.INVISIBLE);
                fragmentView.findViewById(R.id.btn_camera_shutter).setClickable(true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // TODO Auto-generated method stub
                mFilterLayout.setVisibility(View.INVISIBLE);
                fragmentView.findViewById(R.id.btn_camera_shutter).setClickable(true);
            }
        });
        animator.start();
    }

    private File getStickPaperDir() {
//        File stickPaperDir = new File(Environment.getExternalStorageDirectory(), "yuesejiaoyou/StickerPapers");
//        if(stickPaperDir.exists() == false) {
//            stickPaperDir.mkdirs();
//        }
//
//        return stickPaperDir;
        File rootDir = baseActivity.getDir("Resource", 0);
        return rootDir;
    }

    private void freshMenuData() {
        for(int i = 0; i < mStickerData.size(); i++) {
            MenuBean bean = mStickerData.get(i);
            File beautyZip = new File(getStickPaperDir(), "StickerPapers/"+bean.path+".zip");
            if(beautyZip.exists()) {
                bean.exist = true;
            } else {
                bean.exist = false;
            }
        }
    }

    private String getStackInfo(Throwable e) {
        String info;
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        e.printStackTrace(pw);
        info = writer.toString();
        pw.close();
        try {
            writer.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return info;
    }
}
