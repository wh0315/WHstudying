package com.caifulif.whstudying.recycleview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ViewAnimator;

import com.caifulif.whstudying.R;
import com.caifulif.whstudying.baseActivity.BaseAcvtivty;

import java.util.ArrayList;
import java.util.List;

public class TestRecycleView extends BaseAcvtivty {

    private List<String> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycle_view);

        initview();

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    protected ViewAnimator initViewAnimator() {
        return null;
    }


    private void initview ()
    {
        //获取rv控件
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //设置分割线
        rv.addItemDecoration(new DividerItemDecoration(TestRecycleView.this, DividerItemDecoration.VERTICAL_LIST));
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add( i + " " );
        }
        //设置Adapter
        RecycleAdapter adapter = new RecycleAdapter(list);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }



}
