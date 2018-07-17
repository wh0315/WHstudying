package com.caifulif.whstudying.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.caifulif.whstudying.R;

/**
 * Created by 皓 on 2018/3/20.
 */

public class ProgressDialog {

    private MaterialDialog mMaterialDialog;//进度对话框

    public ProgressDialog(Context context) {
        mMaterialDialog = new MaterialDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.include_progress_dialog_layout,
                null);
        mMaterialDialog.setView(view);
        mMaterialDialog.setBackground(context.getResources().getDrawable(R.color.transparent));
        mMaterialDialog.setCanceledOnTouchOutside(false);
        mMaterialDialog.setCancelable(true);
    }

    /**
     * 点击返回键是否可以取消进度提示
     * @param isCancelable true为可以，false不可以
     */
    public void setCancelable(boolean isCancelable) {
        mMaterialDialog.setCancelable(isCancelable);
    }

    /**
     * 显示进度条
     */
    public void showDialog() {
        mMaterialDialog.show();
    }

    /**
     * 隐藏进度条
     */
    public void dismissDialog() {
        mMaterialDialog.dismiss();
    }
}

