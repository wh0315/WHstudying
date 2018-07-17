package com.caifulif.whstudying.recycleview;

import com.caifulif.whstudying.R;

import java.util.List;

/**
 * Created by 皓 on 2018/3/19.
 */

public class RecycleAdapter extends BaseAdapter<String> {
    public RecycleAdapter(List<String> list) {
        super(R.layout.item_recycler, list);
    }
    @Override
    protected void convert(BaseHolder holder, String item) {
        holder.setText(R.id.tv_item,item);

    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }
}