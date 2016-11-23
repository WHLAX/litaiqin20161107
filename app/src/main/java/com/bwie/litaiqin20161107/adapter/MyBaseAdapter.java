package com.bwie.litaiqin20161107.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwie.litaiqin20161107.R;
import com.bwie.litaiqin20161107.bean.Data_Bean_1;

import java.util.List;

/**
 * Created by w9072 on 2016/11/7.
 */

public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private List<Data_Bean_1.ResultBean.DataBean> list;

    public MyBaseAdapter(Context context, List<Data_Bean_1.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh = null;
        if (view == null) {
            view = view.inflate(context, R.layout.item, null);
            vh = new ViewHolder();
            vh.frag_tv_1 = (TextView) view.findViewById(R.id.frag_tv_1);
            vh.frag_tv_2 = (TextView) view.findViewById(R.id.frag_tv_2);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.frag_tv_1.setText(list.get(i).getContent());
        vh.frag_tv_2.setText(list.get(i).getUpdatetime());
        return view;
    }

    class ViewHolder {
        TextView frag_tv_1, frag_tv_2;
    }
}
