package com.bwie.litaiqin20161107.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.litaiqin20161107.R;
import com.bwie.litaiqin20161107.bean.mDataBean;
import com.bwie.litaiqin20161107.util.OkHttp;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

import static com.nostra13.universalimageloader.core.ImageLoader.getInstance;

/**
 * Created by w9072 on 2016/11/7.
 */

public class frag_2 extends Fragment {

    private RecyclerView mRecyclerView;
    private ImageLoader loader;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_two, null, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_view);

        loader = getInstance();
        loader.init(ImageLoaderConfiguration.createDefault(getActivity()));
        //请求网络数据
        getHttpData();


        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);

        return view;
    }

    public void getHttpData() {
        OkHttp.getAsync("http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17", new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                String json = result.toString();
                Gson gson = new Gson();
                mDataBean mDataBean = gson.fromJson(json, mDataBean.class);
                List<com.bwie.litaiqin20161107.bean.mDataBean.DataBean> data = mDataBean.getData();

                //设置布局管理器
                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                final HomeAdapter mAdapter;
                //添加适配器
                mRecyclerView.setAdapter(mAdapter = new HomeAdapter(data));
                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        swipeRefreshLayout.setRefreshing(false);
                        int page = 0;
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
        private List<mDataBean.DataBean> list;

        public HomeAdapter(List<mDataBean.DataBean> list) {
            this.list = list;
        }

        //初始化item视图
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.item1, parent,
                    false));

            return holder;
        }

        //对子布局控件进行绑定赋值
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(list.get(position).getGoods_name());
            loader.displayImage(list.get(position).getGoods_img(), holder.iv);
        }

        //返回item数量
        @Override
        public int getItemCount() {
            return list.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;
            ImageView iv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.item_tv);
                iv = (ImageView) view.findViewById(R.id.item_iv);
            }
        }

    }


}
