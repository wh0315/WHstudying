package com.caifulif.whstudying;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewAnimator;

import com.caifulif.whstudying.PageConn.PageConnUtils;
import com.caifulif.whstudying.banner.BannerActivity;
import com.caifulif.whstudying.baseActivity.BaseAcvtivty;
import com.caifulif.whstudying.edittext.EditTextActivity;
import com.caifulif.whstudying.empty2listvieworgridview.ListOrGrid;
import com.caifulif.whstudying.eventbus.EventBusActivity;
import com.caifulif.whstudying.hot.HotPointActivity;
import com.caifulif.whstudying.modle.ErrorBean;
import com.caifulif.whstudying.mvp.modle.User;
import com.caifulif.whstudying.mvp.view.LoginActivity;
import com.caifulif.whstudying.recycleview.TestRecycleView;
import com.caifulif.whstudying.speech.SpeechUtils;
import com.caifulif.whstudying.udp.UDPActivity;
import com.caifulif.whstudying.utils.PermissionUtil;
import com.caifulif.whstudying.utils.ToastUtil;
import com.caifulif.whstudying.utils.log.JsonLog;
import com.caifulif.whstudying.utils.network.AsyncHttpClient;
import com.caifulif.whstudying.utils.network.HttpUtil;
import com.caifulif.whstudying.widget.MaterialDialog;
import com.google.gson.Gson;

public class MainActivity extends BaseAcvtivty {

    private static String TAG = "MainActivity" ;
    private MaterialDialog mDialog ;
    private ViewAnimator stateLayout;
    private View error_content_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stateLayout = (ViewAnimator) findViewById(R.id.state_layout);
        stateLayout.setVisibility(View.GONE);
        error_content_layout = findViewById(R.id.error_content_layout);
        error_content_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                stateLayout.setVisibility(View.GONE);
                showProgressBar();
            }
        });
        //测试EventBus
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EventBusActivity.class);
                startActivity(intent);
            }
        });

        //测试RecycleView
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestRecycleView.class);
                startActivity(intent);
            }
        });

        //Dialog 显示影藏
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showProgressBar();

//                MainActivity.this.showLoading();
                //MainActivity.this.hideLoading();
            }
        });

        //Dialog弹出框
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.show();
            }
        });
        mDialog = new MaterialDialog(this);
        mDialog.setTitle("温馨提示");
        mDialog.setMessage("哈哈哈哈哈哈");
        mDialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        mDialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Test.class));
            }
        });

        //网络请求
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url= "http://140.207.2.182:1989/BranchWeChat/app/partyFee/getPartyFlow.action";
                HttpUtil.get(url, new AsyncHttpClient() {
                    @Override
                    public void onMySuccess(String result) {
                        Gson gson = new Gson();8
                        ErrorBean errorBean = gson.fromJson(result,ErrorBean.class);
                        JsonLog.printJson( TAG , errorBean.getCode().toString() ,"11111111");
                    }
                    @Override
                    public void onMyFailure(Throwable arg0) {
                        ToastUtil.show("网络出错了");
                    }
                });
            }
        });

        //UDP
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UDPActivity.class);
                startActivity(intent);
            }
        });
        //Retrofit + Rxjava + okhttp
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PageConnUtils.mHoueIndex(MainActivity.this, new PageConnUtils.OnFisrtPageLintener() {
                    @Override
                    public void pageLintener(User errorBean) {

                    }

                    @Override
                    public void errorBeanLintener(String codeDesc) {

                    }

                });
            }
        });

        findViewById(R.id.speech).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpeechUtils.getInstance(MainActivity.this).speakText("黑化黑灰化肥黑灰会挥发发灰黑化肥黑灰化肥挥发");
            }
        });

        //测试RecycleView
        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //banner
        findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BannerActivity.class);
                startActivity(intent);
            }
        });
        //放淘宝 热点头条
        findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HotPointActivity.class);
                startActivity(intent);
            }
        });

        //EditText
        findViewById(R.id.button12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditTextActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListOrGrid.class);
                startActivity(intent);
            }
        });
        PermissionUtil.requestPerssions(this, 0, Manifest.permission.CAMERA);
        PermissionUtil.getCameraPermissions(this, 0);
    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    protected ViewAnimator initViewAnimator() {
        return stateLayout;
    }


}
