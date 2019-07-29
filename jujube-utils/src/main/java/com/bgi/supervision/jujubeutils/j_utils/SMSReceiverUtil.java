package com.bgi.supervision.jujubeutils.j_utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by majian
 * on 2019/4/25
 * copyright:BGI
 * Describe:
 */
public class SMSReceiverUtil extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage msg = null;
        if (null != bundle) {
            Object[] smsObj = (Object[]) bundle.get("pdus");
            for (Object object : smsObj) {
                msg = SmsMessage.createFromPdu((byte[]) object);
                Date date = new Date(msg.getTimestampMillis());// 时间
                SimpleDateFormat format = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                String receiveTime = format.format(date);
//                Log.i("接收", "address:" + msg.getOriginatingAddress()
//                        + "   body:" + msg.getDisplayMessageBody() + "  time:"
//                        + msg.getTimestampMillis());
//                Toast.makeText(context,"接收到短信来自：" +
//                        "\n" + msg.getOriginatingAddress()
//                        + "\n内容：\n" + msg.getDisplayMessageBody()
//                        + "\n时间：\n+" + receiveTime,Toast.LENGTH_LONG).show();
            }
        }

    }
}
