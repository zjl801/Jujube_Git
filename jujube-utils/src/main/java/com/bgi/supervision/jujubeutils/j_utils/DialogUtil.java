package com.bgi.supervision.jujubeutils.j_utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.bgi.supervision.jujubeutils.R;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by majian
 * on 2019/4/25
 * copyright:BGI
 * Describe:红马巨献
 */
public class DialogUtil {
    private static ProgressDialog progressDialog;
    private static AlertDialog alertDialog;
    private static Dialog dialog;

    public static void alertDialog(Context context, String title, String message) {
        alertDialog(context, null, null, title, message, true, null);
    }

    public static void alertDialog(Context context, String title, String message, boolean isCancel, final Runnable taskPositive) {
        alertDialog(context, "确认", "取消", title, message, isCancel, taskPositive);
    }

    public static void dismiss() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static void alertDialog(Context context, String positiveText, String negativeText, String title, String message, boolean isCancel, final Runnable taskPositive) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
        if (!TextUtils.isEmpty(title))
            builder.setTitle(title);
        if (!TextUtils.isEmpty(message))
            builder.setMessage(message);
        builder.setCancelable(isCancel);
        if (!TextUtils.isEmpty(positiveText)) {
            builder.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (taskPositive != null) {
                        taskPositive.run();
                    }
                    dialog.dismiss();
                }
            });
        }
        if (!TextUtils.isEmpty(negativeText)) {
            builder.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                }
            });
        }
        alertDialog = builder.create();
        alertDialog.show();
    }

    public static void loading(Context context, boolean isCancel) {
        ProgressBar progressBar = new ProgressBar(context);
        dialog = new Dialog(context, R.style.MyAlertDialogStyle);
        dialog.requestWindowFeature(1);
//        dialog.getWindow().setBackgroundDrawableResource(R.drawable.transparent_bg);
        Window window = dialog.getWindow();
        window.setAttributes(window.getAttributes());
        dialog.setContentView(progressBar);
        dialog.show();
    }

    public static void customView(Context context, View view, Runnable runTask) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
        builder.setView(view);
        if (runTask != null) {
            runTask.run();
        }
        alertDialog = builder.create();
        alertDialog.show();
    }

    public static void customView(Context context, @LayoutRes int view, Runnable runTask) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
        builder.setView(view);
        if (runTask != null) {
            runTask.run();
        }
        alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * 圆形进度转圈
     */
    public static void showLoadingDialog(Context context, String title, String message, Boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        progressDialog = ProgressDialog.show(context, title, message, false,
                cancelable, cancelListener);
    }

    /**
     * 圆形进度转圈
     */
    public static void showLoadingDialog(Context context, String title, String message, Boolean cancelable) {
        progressDialog = ProgressDialog.show(context, title, message, false,
                cancelable);
    }

    /**
     * 水平进度条
     */
    public static void showHorizhontalLoadingDialog(Context context, String title, String message, Boolean cancelable) {
        progressDialog = new ProgressDialog(context, R.style.MyAlertDialogStyle);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置水平进度条
        progressDialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        progressDialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        progressDialog.setIcon(null);// 设置提示的title的图标，默认是没有的
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setMax(100);
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        progressDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "中立",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        progressDialog.show();
        new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                while (i < 100) {
                    try {
                        Thread.sleep(200);
                        // 更新进度条的进度,可以在子线程中更新进度条进度
                        progressDialog.incrementProgressBy(1);
                        // progressDialog.incrementSecondaryProgressBy(10)//二级进度条更新方式
                        i++;
                    } catch (Exception e) {
                    }
                }
                // 在进度条走完时删除Dialog
                progressDialog.dismiss();
            }
        }).start();
    }

//**************************************************************************************************
    /**
     * 常用多选对话框
     *
     * @param title
     * @param items
     * @return
     */
    static LinkedList<String> linkedList = new LinkedList<>();

    public static void showMutiAlertDialog(final String[] items, String hadString, final EditText edittext, Context context, final String id) {
        final boolean[] checkedItems =getBooleanArray(hadString, items);

        linkedList.clear();
        for (int i = 0; i < checkedItems.length; i++) {
            if (checkedItems[i]) {
                linkedList.add(items[i]);
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedItems[which] = isChecked;
                if (isChecked) {
                    linkedList.add(items[which]);
                } else {
                    linkedList.remove(items[which]);
                }
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuffer b = new StringBuffer();
                for (int i = 0; i < linkedList.size(); i++) {
                    b.append(linkedList.get(i) + ",");
                }
                String s = b.toString();
                if (s.length() > 0) {
                    s = s.substring(0, s.length() - 1);
                }
                edittext.setText(s);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setCancelable(true);
        builder.show();
    }


    /**
     * 多选有回调
     * @param items
     * @param hadString
     * @param edittext
     * @param context
     * @param callBack
     */
    public static void showMutiAlertDialog(final String[] items, String hadString, final EditText edittext, Context context,  final MutiCallBack callBack) {
        final boolean[] checkedItems =getBooleanArray(hadString, items);

        linkedList.clear();
        for (int i = 0; i < checkedItems.length; i++) {
            if (checkedItems[i]) {
                linkedList.add(items[i]);
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedItems[which] = isChecked;
                if (isChecked) {
                    linkedList.add(items[which]);
                } else {
                    linkedList.remove(items[which]);
                }
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuffer b = new StringBuffer();
                for (int i = 0; i < linkedList.size(); i++) {
                    b.append(linkedList.get(i) + ",");
                }
                String s = b.toString();
                if (s.length() > 0) {
                    s = s.substring(0, s.length() - 1);
                }
                edittext.setText(s);

                if (callBack != null) {
                    callBack.getResult(linkedList, which);
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setCancelable(true);
        builder.show();
    }


    /**
     * 常用单选对话框-有回调
     */
    public static void showSingleChoiceAlertDialog(final String[] items, final EditText edittext, final Context context, final String id, final CallBack callBack) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(items, new DialogInterface.OnClickListener() {/*设置列表的点击事件*/
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (edittext != null) {
                    edittext.setText(items[which]);
                }
                if (callBack != null) {
                    callBack.getResult(items[which], which);
                }
            }
        });
        builder.setCancelable(true);
        builder.show();
    }

    /**
     * 常用单选对话框
     */
    public static void showSingleChoiceAlertDialog(final String[] items, final EditText edittext, final Context context, final String id) {
        showSingleChoiceAlertDialog(items, edittext, context, id, null);
    }


    /**
     * 获取boolean数组
     *
     * @param str
     * @param items
     * @return
     */
    public static boolean[] getBooleanArray(String str, String[] items) {
        boolean[] array = new boolean[items.length];
        for (int i = 0; i < items.length; i++) {
            array[i] = str.contains(items[i]);
        }
        return array;
    }

    /**
     * 回调接口
     */
    public interface CallBack {
        void getResult(String result, int which);
    }

    /**
     * 回调接口
     */
    public interface MutiCallBack {
        void getResult(LinkedList<String> result, int which);
    }

    /**
     * 自定义对话框
     */
    public static void showCustomizeDialog(final Context context, final String guid, final boolean upLoad, final boolean upLoadGov) {
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(context);
        final View dialogView = LayoutInflater.from(context)
                .inflate(R.layout.dialog_customize, null);
        customizeDialog.setTitle("正在使用数据网络");
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("继续上传",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CheckBox checkBox = dialogView.findViewById(R.id.cb_need_show);
                        if (checkBox.isChecked()) {
                            ShareUtil.putBoolean(context, "needShow", false);
                        }
                    }
                });
        customizeDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        customizeDialog.show();
    }

    public static void showNormalDialog(Context context, String title, String message, boolean isForce, final CallBack callBack) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setTitle(title);
        normalDialog.setMessage(message);
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callBack.getResult("更新", 1);
                    }
                });
        if (!isForce) {
            normalDialog.setNegativeButton("关闭",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            callBack.getResult("不更新", 0);
                        }
                    });
        } else {
            normalDialog.setCancelable(false);
        }
        normalDialog.show();
    }

}
