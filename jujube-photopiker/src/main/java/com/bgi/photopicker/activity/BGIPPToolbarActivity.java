package com.bgi.photopicker.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ViewStubCompat;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.bgi.photopicker.R;


/**
 * Created by majian
 * on 2019/4/26
 * copyright:BGI
 * Describe:
 */
public abstract class BGIPPToolbarActivity extends AppCompatActivity {
    protected String TAG;
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();

        initView(savedInstanceState);
        setListener();
        processLogic(savedInstanceState);
    }

    /**
     * 初始化布局以及View控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 给View控件添加事件监听器
     */
    protected abstract void setListener();

    /**
     * 处理业务逻辑，状态恢复等操作
     *
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.bga_pp_toolbar_viewstub);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewStubCompat viewStub = findViewById(R.id.viewStub);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) viewStub.getLayoutParams();
        lp.addRule(RelativeLayout.BELOW, R.id.toolbar);

        viewStub.setLayoutResource(layoutResID);
        viewStub.inflate();
    }

    public void setNoLinearContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.bga_pp_toolbar_viewstub);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ViewStubCompat viewStub = findViewById(R.id.viewStub);
        viewStub.setLayoutResource(layoutResID);
        viewStub.inflate();
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}