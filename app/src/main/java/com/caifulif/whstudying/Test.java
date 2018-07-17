package com.caifulif.whstudying;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.ViewAnimator;

import com.caifulif.whstudying.BroadcastReceiver.TestBroadcast;
import com.caifulif.whstudying.baseActivity.BaseAcvtivty;
import com.caifulif.whstudying.utils.UmengShareUtils;

public class Test extends BaseAcvtivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        showDialog();
    }


    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    protected ViewAnimator initViewAnimator() {
        return null;
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title")
                .setMessage("Dialog Content")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Test.this,TestBroadcast.class));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UmengShareUtils shareUtils = new UmengShareUtils(Test.this,"哈哈","http://www.baidu.com","驱蚊器翁","");
                        shareUtils.share();
                    }
                })
                .show();
    }
}
