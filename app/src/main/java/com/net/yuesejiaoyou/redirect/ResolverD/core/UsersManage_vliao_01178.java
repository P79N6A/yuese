package com.net.yuesejiaoyou.redirect.ResolverD.core;




import com.net.yuesejiaoyou.classroot.interface2.OkHttp;
import com.net.yuesejiaoyou.classroot.interface4.LogDetect;
import com.net.yuesejiaoyou.redirect.ResolverD.getset.vliaofans_01168;
import com.net.yuesejiaoyou.redirect.ResolverD.interface4.HelpManager_vliao_01178;

import java.util.ArrayList;


public class UsersManage_vliao_01178 {
	HelpManager_vliao_01178 helpmanager = null;
	OkHttp okhttp=null;
	public UsersManage_vliao_01178() {
		//httpclient = new HttpClientManage();
		okhttp=new OkHttp();
		helpmanager = new HelpManager_vliao_01178();

	}

     //支付
	public String pay(String[] params) {
		LogDetect.send(LogDetect.DataType.specialType, "--UsersManage_vliao_01178--:", "01178");
		String a = "vliaopay?mode=A-user-add&mode2=pay";
		LogDetect.send(LogDetect.DataType.specialType, "--UsersManage_vliao_01178--a:--", a);
		String json = okhttp.requestPostBySyn(a, params);
		return json;
	}

/*
	public Page wodeqimibang(String[] params) {
		String a = "vliaopay?mode=A-user-search&mode2=wodeqimibang";
		String json = okhttp.requestPostBySyn(a, params);
		Page mblist = helpmanager.wodeqimibang(json);

		return mblist;
	}


	public String vcurrencyconfirm(String[] params) {
		String a = "vliaopay?mode=A-user-add&mode2=vcurrencyconfirm";
		String json = okhttp.requestPostBySyn(a, params);
		return json;
	}
	public ArrayList<vliaofans_01168> vcurrency(String[] params) {
		String a = "vliaopay?mode=A-user-search&mode2=vcurrency";
		String json = okhttp.requestPostBySyn(a, params);
		ArrayList<vliaofans_01168> mblist = helpmanager.vcurrency(json);
		return mblist;
	}*/


	public ArrayList<vliaofans_01168> payprice(String[] params) {
		String a = "rp?mode=A-user-search&mode2=payprice";
		String json = okhttp.requestPostBySyn(a, params);
		ArrayList<vliaofans_01168> mblist = helpmanager.payprice(json);
		return mblist;
	}
	
	
}