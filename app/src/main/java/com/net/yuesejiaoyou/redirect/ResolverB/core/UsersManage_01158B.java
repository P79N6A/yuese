package com.net.yuesejiaoyou.redirect.ResolverB.core;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import com.net.yuesejiaoyou.redirect.ResolverB.getset.Page;
import com.net.yuesejiaoyou.redirect.ResolverB.getset.photo_01162;
import com.net.yuesejiaoyou.redirect.ResolverB.interface4.HelpManager_01158B;

import com.net.yuesejiaoyou.classroot.interface2.OkHttp;
import com.net.yuesejiaoyou.classroot.interface4.LogDetect;

public class UsersManage_01158B {


	HelpManager_01158B helpmanager = null;
	OkHttp okhttp=null;
	public UsersManage_01158B() {
		//httpclient = new HttpClientManage();
		okhttp=new OkHttp();
		helpmanager = new HelpManager_01158B();
	}
  
	//登陆
	public String login(String[] params) throws IOException {
		String a = "memberB?mode=A-user-add&mode2=login";//含有两个数据用户名和密码
		String json = okhttp.requestPostBySyn(a, params);
		return json;
	}
	public ArrayList<photo_01162> activity_search(String[] params) throws IOException {
		String a = "memberB?mode=A-user-search&mode2=activity_search";//含有两个数据用户名和密码
		String json = okhttp.requestPostBySyn(a, params);
		LogDetect.send(LogDetect.DataType.basicType,"01160 zhubo_online ",json);
		ArrayList<photo_01162> info=helpmanager.activity_search(json);
		return info;
	}

	public String zhubo_online(String[] params){
		String a = "memberB?mode=A-user-search&mode2=zhubo_online";
		String json = okhttp.requestPostBySyn(a, params);
		LogDetect.send(LogDetect.DataType.basicType,"01160 zhubo_online ",json);
		return json;
	}

	public String mod_online(String[] params){
		String a = "memberB?mode=A-user-mod&mode2=mod_online";
		String json = okhttp.requestPostBySyn(a, params);
		LogDetect.send(LogDetect.DataType.basicType,"01160 mod_online ",json);
		return json;
	}
	public String mod_online1(String[] params){
		String a = "memberB?mode=A-user-mod&mode2=mod_online1";
		String json = okhttp.requestPostBySyn(a, params);
		LogDetect.send(LogDetect.DataType.basicType,"01160 mod_online ",json);
		return json;
	}


	public String kou_frist(String[] params){
		String a = "memberB?mode=A-user-mod&mode2=kou_frist";
		LogDetect.send(LogDetect.DataType.basicType,"01160 kou_frist-params ",params);
		String json = okhttp.requestPostBySyn(a, params);
		LogDetect.send(LogDetect.DataType.basicType,"01160 kou_frist ",json);
		return json;
	}

	public String kou_zhubo(String[] params){
		String a = "memberB?mode=A-user-mod&mode2=kou_zhubo";
		LogDetect.send(LogDetect.DataType.basicType,"01160 kou_zhubo-params ",params);
		String json = okhttp.requestPostBySyn(a, params);
		LogDetect.send(LogDetect.DataType.basicType,"01160 kou_zhubo ",json);
		return json;
	}

	public String xfliaotian(String[] params){
		String a = "memberB?mode=A-user-mod&mode2=xfliaotian";
		String json = okhttp.requestPostBySyn(a,params);
		return json;
	}

	public String xiugai(String[] params){
		String a = "memberB?mode=A-user-search&mode2=xiugai";
		String json = okhttp.requestPostBySyn(a,params);
		return json;
	}

	public String setlike(String[] params){
		String a = "memberB?mode=A-user-mod&mode2=setlike";
		String json = okhttp.requestPostBySyn(a,params);
		return json;
	}

	public String getfreevideo(String[] params){
		String a = "memberB?mode=A-user-search&mode2=getfreevideo";
		String json = okhttp.requestPostBySyn(a,params);
		return json;
	}

	public String getrewardvideo(String[] params){
		String a = "memberB?mode=A-user-search&mode2=getrewardvideo";
		String json = okhttp.requestPostBySyn(a,params);
		return json;
	}

	public Page getmyvideo(String[] params){
        String a="memberB?mode=A-user-search&mode2=getmyvideo";
        String json=okhttp.requestPostBySyn(a,params);
        Page mblist = helpmanager.videolist(json);
        return  mblist;
	}
	public String mod_mang(String[] params){
		String a="memberB?mode=A-user-mod&mode2=mod_mang";
		String json=okhttp.requestPostBySyn(a,params);

		return  json;
	}

	public String guke_online(String[] params) {
		String a="memberB?mode=A-user-search&mode2=guke_online";
		String json=okhttp.requestPostBySyn(a,params);
		return  json;
	}

    public String mod_return(String[] params) {
		String a="memberB?mode=A-user-mod&mode2=mod_return";
		String json=okhttp.requestPostBySyn(a,params);

		return  json;
    }

	public String checkyue(String[] params){
		String a = "memberB?mode=A-user-search&mode2=checkyue";
		LogDetect.send(LogDetect.DataType.basicType,"01160 checkyue-params ",params);
		String json = okhttp.requestPostBySyn(a, params);
		LogDetect.send(LogDetect.DataType.basicType,"01160 checkyue ",json);
		return json;
	}

	public String modezhubostate(String[] params) {
		String a = "memberB?mode=A-user-mod&mode2=modezhubostate";
		LogDetect.send(LogDetect.DataType.basicType,"01160 kou_zhubo-params ",params);
		String json = okhttp.requestPostBySyn(a, params);
		LogDetect.send(LogDetect.DataType.basicType,"01160 kou_zhubo ",json);
		return json;
	}
	public String pushp2pvideo(String[] params) {
		String a = "memberB?mode=A-user-add&mode2=pushp2pvideo";
		LogDetect.send(LogDetect.DataType.basicType,"01160 kou_zhubo-params ",params);
		Log.v("TTT","---------------------------------------------");
		Log.v("TTT","pushp2pvideo()-1");
		String json = okhttp.requestPostBySyn(a, params);
		LogDetect.send(LogDetect.DataType.basicType,"01160 kou_zhubo ",json);
		return json;
	}

	public String removep2pvideo(String[] params) {
		String a = "memberB?mode=A-user-delete&mode2=removep2pvideo";
		LogDetect.send(LogDetect.DataType.basicType,"01160 kou_zhubo-params ",params);
		String json = okhttp.requestPostBySyn(a, params);
		LogDetect.send(LogDetect.DataType.basicType,"01160 kou_zhubo ",json);
		return json;
	}

	public String statuschange(String[] params) {
		String a = "memberB?mode=A-user-mod&mode2=statuschange";
		LogDetect.send(LogDetect.DataType.basicType,"01160 kou_zhubo-params ",params);
		String json = okhttp.requestPostBySyn(a, params);
		LogDetect.send(LogDetect.DataType.basicType,"01160 kou_zhubo ",json);
		return json;
	}

	public String pushcmdmsg(String[] params) {
		String a = "memberB?mode=A-user-add&mode2=pushcmdmsg";
		String json = okhttp.requestPostBySyn(a, params);
		return json;
	}
}
