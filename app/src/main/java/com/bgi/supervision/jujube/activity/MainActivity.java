package com.bgi.supervision.jujube.activity;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bgi.supervision.jujube.R;
import com.bgi.supervision.jujubeutils.j_utils.DialogUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DialogUtil.alertDialog(MainActivity.this, "dd", "dd", false, new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("----title----");
//            }
//        });

//        DialogUtil.loading(this,false);

//        DialogUtil.showHorizhontalLoadingDialog(this,"加载","请稍后",false);

//        DialogUtil.customView(this, R.layout.head_view, new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });

//        DialogUtil.showLoadingDialog(this, "dd", "sdadasda", false, new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//
//            }
//        });

//        String[] items = {"我是1", "我是2", "我是3", "我是4", "我是1", "我是2", "我是3", "我是4", "我是1", "我是2", "我是3", "我是4"};
//        DialogUtil.showSingleChoiceAlertDialog(items, null, this, "d");
    }

}
