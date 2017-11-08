package com.res.pc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.Style;
import com.tencent.xinge.TimeInterval;
import com.tencent.xinge.XingeApp;
import com.tencent.xinge.TagTokenPair;

public class Demo {


	
	
	//单个设备下发透传消息
	protected JSONObject demoPushSingleDeviceMessage()
	{
		XingeApp xinge = new XingeApp(000, "secret_key");
		Message message = new Message();
		message.setTitle("title");
		message.setContent("content");
		message.setType(Message.TYPE_MESSAGE);
		message.setExpireTime(86400);
		JSONObject ret = xinge.pushSingleDevice("token", message);
		
		return ret;
	}
	
	//单个设备下发通知消息
	protected JSONObject demoPushSingleDeviceNotification()
	{	
		XingeApp xinge = new XingeApp(000, "secret_key");	
		JSONObject ret = xinge.pushSingleDevice("token", message1);
		return (ret);
	}
	
	//单个设备下发通知消息
	protected JSONObject demoPushSingleDeviceIOS(){
		MessageIOS message = new MessageIOS();
		message.setExpireTime(86400);
		message.setAlert("ios test");
		message.setBadge(1);
		message.setSound("beep.wav");
		TimeInterval acceptTime1 = new TimeInterval(0,0,23,59);
		message.addAcceptTime(acceptTime1);
		Map<String, Object> custom = new HashMap<String, Object>();
		custom.put("key1", "value1");
		custom.put("key2", 2);
		message.setCustom(custom);
		
		XingeApp xinge = new XingeApp(000L, "secret_key");	
		JSONObject ret = xinge.pushSingleDevice("token", message, XingeApp.IOSENV_DEV);
		return (ret);
	}
	
	//单个设备下发通知Intent
	//setIntent()的内容需要使用intent.toUri(Intent.URI_INTENT_SCHEME)方法来得到序列化后的Intent(自定义参数也包含在Intent内）
	//终端收到后可通过intent.parseUri()来反序列化得到Intent
	protected JSONObject demoPushSingleDeviceNotificationIntent()
	{
		XingeApp xinge = new XingeApp(000, "secret_key");	
		JSONObject ret = xinge.pushSingleDevice("token", message2);
		return (ret);
	}
	
	//下发单个账号
	protected JSONObject demoPushSingleAccount() {
		XingeApp xinge = new XingeApp(000, "secret_key");
		Message message = new Message();
		message.setExpireTime(86400);
		message.setTitle("title");
		message.setContent("content");
		message.setType(Message.TYPE_MESSAGE);
		JSONObject ret = xinge.pushSingleAccount(0, "joelliu", message);
		return (ret);
	}
	
	//下发多个账号
	protected JSONObject demoPushAccountList() {
		XingeApp xinge = new XingeApp(000, "secret_key");
		Message message = new Message();
		message.setExpireTime(86400);
		message.setTitle("title");
		message.setContent("content");
		message.setType(Message.TYPE_MESSAGE);
		List<String> accountList = new ArrayList<String>();
		accountList.add("joelliu");
		accountList.add("joelliu");
		JSONObject ret = xinge.pushAccountList(0, accountList, message);
		return (ret);
	}
	
	//下发IOS单个账号
	protected JSONObject demoPushSingleAccountIOS() {
		MessageIOS message = new MessageIOS();
		message.setExpireTime(86400);
		message.setAlert("ios test");
		message.setBadge(1);
		message.setSound("beep.wav");
		TimeInterval acceptTime1 = new TimeInterval(0,0,23,59);
		message.addAcceptTime(acceptTime1);
		Map<String, Object> custom = new HashMap<String, Object>();
		custom.put("key1", "value1");
		custom.put("key2", 2);
		message.setCustom(custom);
		
		XingeApp xinge = new XingeApp(000, "secret_key");
		JSONObject ret = xinge.pushSingleAccount(0, "joelliu", message, XingeApp.IOSENV_DEV);
		return (ret);
	}
	
	//下发IOS多个账号
	protected JSONObject demoPushAccountListIOS() {
		MessageIOS message = new MessageIOS();
		message.setExpireTime(86400);
		message.setAlert("ios test");
		message.setBadge(1);
		message.setSound("beep.wav");
		
		XingeApp xinge = new XingeApp(000, "secret_key");
		List<String> accountList = new ArrayList<String>();
		accountList.add("joelliu");
		accountList.add("joelliu");
		JSONObject ret = xinge.pushAccountList(0, accountList, message, XingeApp.IOSENV_DEV);
		return (ret);
	}
	
	//下发所有设备
	protected JSONObject demoPushAllDevice()
	{
		XingeApp xinge = new XingeApp(000, "secret_key");
		
		JSONObject ret = xinge.pushAllDevice(0, message1);
		return (ret);
	}
	
	//下发标签选中设备
	protected JSONObject demoPushTags()
	{
		XingeApp xinge = new XingeApp(000, "secret_key");
		List<String> tagList = new ArrayList<String>();
		tagList.add("joelliu");
		tagList.add("phone");
		JSONObject ret = xinge.pushTags(0, tagList, "OR", message1);
		return (ret);
	}

    //大批量下发给帐号 // iOS 请构造MessageIOS消息
	protected JSONObject demoPushAccountListMultiple() {
		XingeApp xinge = new XingeApp(2100177361, "secret_key");
		Message message = new Message();
		message.setExpireTime(86400);
		message.setTitle("title");
		message.setContent("content");
		message.setType(Message.TYPE_NOTIFICATION);

		JSONObject ret = xinge.createMultipush(message);
		if (ret.getInt("ret_code") != 0)
            return (ret);
        else {
            JSONObject result = new JSONObject();

            List<String> accountList1 = new ArrayList<String>();
            accountList1.add("joelliu1");
            accountList1.add("joelliu2");
            // ...
            result.append("all", xinge.pushAccountListMultiple(ret.getJSONObject("result").getInt("push_id"), accountList1));

            List<String> accountList2 = new ArrayList<String>();
            accountList2.add("joelliu3");
            accountList2.add("joelliu4");
            // ...
            result.append("all", xinge.pushAccountListMultiple(ret.getJSONObject("result").getInt("push_id"), accountList2));
            return (result);
        }
	}

	//大批量下发给设备 // iOS 请构造MessageIOS消息
	public static JSONObject demoPushDeviceListMultiple(String title,String content,List<String> deviceList) {
		//XingeApp xinge = new XingeApp(2100177361, "5bd0fe9ec54ea0cd9158ddf301bae7f4");
	    XingeApp xinge = new XingeApp(2100257716, "d69ced669ed93d0ea1b222f9cb9c1b61");
	    Message message = new Message();
		message.setExpireTime(86400);
		message.setTitle(title);
		message.setContent(content);
		message.setType(Message.TYPE_NOTIFICATION);

		JSONObject ret = xinge.createMultipush(message);
		if (ret.getInt("ret_code") != 0)
            return (ret);
        else {
            JSONObject result = new JSONObject();
            long i=ret.getJSONObject("result").getLong("push_id");
           
            // ...
            //result.append("all", xinge.pushDeviceListMultiple(ret.getJSONObject("result").getInt("push_id"), deviceList));
            result.append("all", xinge.pushDeviceListMultiple(ret.getJSONObject("result").getLong("push_id"), deviceList));

            return (result);
        }
	}

	//查询消息推送状态
	protected JSONObject demoQueryPushStatus()
	{
		XingeApp xinge = new XingeApp(000, "secret_key");
		List<String> pushIdList = new ArrayList<String>();
		pushIdList.add("390");
		pushIdList.add("389");
		JSONObject ret = xinge.queryPushStatus(pushIdList);
		return (ret);
	}
	
	//查询设备数量
	protected JSONObject demoQueryDeviceCount()
	{
		XingeApp xinge = new XingeApp(000, "secret_key");
		JSONObject ret = xinge.queryDeviceCount();
		return (ret);
	}
	
	//查询标签
	protected JSONObject demoQueryTags()
	{
		XingeApp xinge = new XingeApp(000, "secret_key");
		JSONObject ret = xinge.queryTags();
		return (ret);
	}
	
	//查询某个tag下token的数量
	protected JSONObject demoQueryTagTokenNum()
	{
		XingeApp xinge = new XingeApp(000, "secret_key");
		JSONObject ret = xinge.queryTagTokenNum("tag");
		return (ret);
	}
	
	//查询某个token的标签
	protected JSONObject demoQueryTokenTags()
	{
		XingeApp xinge = new XingeApp(000, "secret_key");
		JSONObject ret = xinge.queryTokenTags("token");
		return (ret);
	}
	
	//取消定时任务
	protected JSONObject demoCancelTimingPush()
	{
		XingeApp xinge = new XingeApp(000, "secret_key");
		JSONObject ret = xinge.cancelTimingPush("32");
		return (ret);
	}

	// 设置标签
	protected JSONObject DemoBatchSetTag()
    {
        XingeApp xinge = new XingeApp(000, "secret_key");

        List<TagTokenPair> pairs = new ArrayList<TagTokenPair>();

        // 切记把这里的示例tag和示例token修改为你的真实tag和真实token
	    pairs.add(new TagTokenPair("tag1","token00000000000000000000000000000000001"));
        pairs.add(new TagTokenPair("tag2","token00000000000000000000000000000000001"));

        JSONObject ret = xinge.BatchSetTag(pairs);
        return (ret);
    }

    // 删除标签
    protected JSONObject DemoBatchDelTag()
    {
        XingeApp xinge = new XingeApp(000, "secret_key");

        List<TagTokenPair> pairs = new ArrayList<TagTokenPair>();

        // 切记把这里的示例tag和示例token修改为你的真实tag和真实token
        pairs.add(new TagTokenPair("tag1","token00000000000000000000000000000000001"));
        pairs.add(new TagTokenPair("tag2","token00000000000000000000000000000000001"));
        
        JSONObject ret = xinge.BatchDelTag(pairs);
        
        return (ret);
    }
	
	//查询某个token的信息
	protected JSONObject DemoQueryInfoOfToken()
	{
		XingeApp xinge = new XingeApp(000, "secret_key");
		JSONObject ret = xinge.queryInfoOfToken("token");
		return (ret);
	}

	//查询某个account绑定的token
	protected JSONObject DemoQueryTokensOfAccount()
	{
		XingeApp xinge = new XingeApp(000, "secret_key");
		JSONObject ret = xinge.queryTokensOfAccount("nickName");
		return (ret);
	}

	//删除某个account绑定的token
	protected JSONObject DemoDeleteTokenOfAccount()
	{
		XingeApp xinge = new XingeApp(000, "secret_key");
		JSONObject ret = xinge.deleteTokenOfAccount("nickName", "token");
		return (ret);
	}
	
	//删除某个account绑定的所有token
	protected JSONObject DemoDeleteAllTokensOfAccount()
	{
		XingeApp xinge = new XingeApp(000, "secret_key");
		JSONObject ret = xinge.deleteAllTokensOfAccount("nickName");
		return (ret);
	}
	
	public Demo(){
		message1 = new Message();
		message1.setType(Message.TYPE_NOTIFICATION);
		Style style = new Style(1);
		style = new Style(3,1,0,1,0);
		ClickAction action = new ClickAction();
		action.setActionType(ClickAction.TYPE_URL);
		action.setUrl("http://xg.qq.com");
		Map<String, Object> custom = new HashMap<String, Object>();
		custom.put("key1", "value1");
		custom.put("key2", 2);
		message1.setTitle("title");
		message1.setContent("大小");
		message1.setStyle(style);
		message1.setAction(action);
		message1.setCustom(custom);
		TimeInterval acceptTime1 = new TimeInterval(0,0,23,59);
		message1.addAcceptTime(acceptTime1);
		
		message2 = new Message();
		message2.setType(Message.TYPE_NOTIFICATION);
		message2.setTitle("title");
		message2.setContent("通知点击执行Intent测试");
		style = new Style(1);
		action = new ClickAction();
		action.setActionType(ClickAction.TYPE_INTENT);
		action.setIntent("intent:10086#Intent;scheme=tel;action=android.intent.action.DIAL;S.key=value;end");
		message2.setStyle(style);
		message2.setAction(action);
	}
	
	private Message message1;
	private Message message2;
}
