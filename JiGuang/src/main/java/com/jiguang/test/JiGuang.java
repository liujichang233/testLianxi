package com.jiguang.test;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @author Administrator
 */
public class JiGuang {



    public static void main(String[] args) {
        push();
    }
    private  static void push(){
          String AppKey = "3c0b34253932cf03bb60b72d";
          String Master_Secret = "2ff8a48a2c299dad5fd282b2";
        //创建极光推送对象
        JPushClient jPushClient = new JPushClient(Master_Secret,AppKey);
        PushPayload pushPayload = PushPayload.newBuilder()
                //指定要推送的平台all代表当前应用配置了的所有平台
                .setPlatform(Platform.all())
                //指定要推送的接收对象，all代表当前应用配置了的所有平台
                .setAudience(Audience.all())
                //jpush的通知
                .setNotification(Notification.alert("send advice"))
                //自定义消息
                .setMessage(Message.content("message"))
                .build();
        try {
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println("success");
            System.out.println(pushResult.msg_id);
            System.out.println(pushResult.sendno);

        } catch (APIConnectionException e) {
            System.out.println("connection error");
            e.printStackTrace();
        } catch (APIRequestException e) {
            System.out.println("request error");
            e.printStackTrace();
        }


    }

}
