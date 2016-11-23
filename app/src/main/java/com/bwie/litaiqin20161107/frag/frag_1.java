package com.bwie.litaiqin20161107.frag;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bwie.litaiqin20161107.R;
import com.bwie.litaiqin20161107.adapter.MyBaseAdapter;
import com.bwie.litaiqin20161107.bean.Data_Bean_1;
import com.bwie.litaiqin20161107.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by w9072 on 2016/11/7.
 */

public class frag_1 extends Fragment {

    private View view;
    private ListView frag_lv_view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_one, null, false);
        infoView();
        //请求网络数据
        getHttp();

        return view;
    }

    private void infoView() {
        frag_lv_view = (ListView) view.findViewById(R.id.frag_lv_view);
    }

    public void getHttp() {
        AsyncTask<String, String, String> task = new AsyncTask<String, String, String>() {

            private String json;
            //请求操作
            @Override
            protected String doInBackground(String... params) {
                HttpUtil util = new HttpUtil();
                try {
                    json = util.getjsonData(params[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            //请求成功后操作
            @Override
            protected void onPostExecute(String aVoid) {
                super.onPostExecute(aVoid);
                //Toast.makeText(getActivity(), json, Toast.LENGTH_SHORT).show();
                List<Data_Bean_1.ResultBean.DataBean> data_list = new ArrayList<Data_Bean_1.ResultBean.DataBean>();
                //解析数据
                JSONObject object = null;
                try {
                    object = new JSONObject(json);
                    JSONObject result = object.getJSONObject("result");
                    JSONArray data = result.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject jsonObject = data.getJSONObject(i);
                        String content = jsonObject.getString("content");
                        String updatetime = jsonObject.getString("updatetime");
                        Data_Bean_1.ResultBean.DataBean data_bean = new Data_Bean_1.ResultBean.DataBean();
                        data_bean.setContent(content);
                        data_bean.setUpdatetime(updatetime);
                        data_list.add(data_bean);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

              /*  Gson gson = new Gson();
                Data_Bean_1 data_bean_1 = gson.fromJson(json, Data_Bean_1.class);
                Data_Bean_1.ResultBean result = data_bean_1.getResult();
                List<Data_Bean_1.ResultBean.DataBean> data = result.getData();*/
                frag_lv_view.setAdapter(new MyBaseAdapter(getActivity(), data_list));
            }
        }.execute("http://japi.juhe.cn/joke/content/list.from?key=%20874ed931559ba07aade103eee279bb37%20&page=1&pagesize=10&sort=asc&time=1418745237");
    }
}
