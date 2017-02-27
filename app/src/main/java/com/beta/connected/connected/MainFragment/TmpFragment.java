package com.beta.connected.connected.MainFragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beta.connected.connected.MessageDetailActivity;
import com.beta.connected.connected.R;
import com.beta.connected.connected.RecyclerView.MessageRecyclerAdapter;
import com.beta.connected.connected.RecyclerView.MessageRow;
import com.beta.connected.connected.RecyclerView.RecyclerItemClickListener;
import com.beta.connected.connected.RecyclerView.SimpleDividerItemDecoration;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TmpFragment extends Fragment {

    private ArrayList<MessageRow> messageList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;


    TwinklingRefreshLayout refreshLayout;
    public TmpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tmp, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), MessageDetailActivity.class);

                        intent.putExtra("id",messageList.get(position).getId());

                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        initData();


        refreshLayout = (TwinklingRefreshLayout)view.findViewById(R.id.refreshLayout);

         refreshLayout.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshData();
                        refreshLayout.finishRefreshing();
                    }
                },2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadData();
                        refreshLayout.finishLoadmore();
                    }
                },2000);
            }
        });



        return view;
    }

    private void loadData(){
        for(int i = 1 ; i < 10 ; i++){

            MessageRow messageRow = new MessageRow();

            messageRow.setMessage("메세지");
            messageRow.setUserId("건준");
            messageRow.setLocation("상도동");
            messageRow.setIsNew(true);
            messageRow.setIsCamera(true);
            messageRow.setComment(10);
            messageRow.setWatch(30);
            messageRow.setId(i + "");
            //new ImageFromUrl().execute("http://kirkee2.cafe24.com/roadImage/road"+jsonObject.getString("id")+".png");

            //album.setImage("http://kirkee2.cafe24.com/roadImage/road"+jsonObject.getString("id")+".png");
            //aquery.id( thumbNailImage ).image("http://kirkee2.cafe24.com/memberImage/"+kakaoId+".jpg" );

            messageList.add(messageRow);

        }

        mAdapter.notifyItemInserted(messageList.size() - 1);

    }

    private void refreshData(){

        //recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        messageList = new ArrayList<MessageRow>();

        for(int i = 1 ; i < 10 ; i++){

            MessageRow messageRow = new MessageRow();

            messageRow.setMessage("refresh 메세지");
            messageRow.setUserId("refresh 건준");
            messageRow.setLocation("refresh 상도동");
            messageRow.setIsNew(true);
            messageRow.setIsCamera(true);
            messageRow.setComment(10);
            messageRow.setWatch(30);
            messageRow.setId(i + "");
            //new ImageFromUrl().execute("http://kirkee2.cafe24.com/roadImage/road"+jsonObject.getString("id")+".png");

            //album.setImage("http://kirkee2.cafe24.com/roadImage/road"+jsonObject.getString("id")+".png");
            //aquery.id( thumbNailImage ).image("http://kirkee2.cafe24.com/memberImage/"+kakaoId+".jpg" );

            messageList.add(messageRow);

        }

        mAdapter = new MessageRecyclerAdapter(messageList,R.layout.layout_message_row);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData(){

        //recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        messageList = new ArrayList<MessageRow>();

        for(int i = 1 ; i < 10 ; i++){

            MessageRow messageRow = new MessageRow();

            messageRow.setMessage("메세지");
            messageRow.setUserId("건준");
            messageRow.setLocation("상도동");
            messageRow.setIsNew(true);
            messageRow.setIsCamera(true);
            messageRow.setComment(10);
            messageRow.setWatch(30);
            messageRow.setId(i + "");
            //new ImageFromUrl().execute("http://kirkee2.cafe24.com/roadImage/road"+jsonObject.getString("id")+".png");

            //album.setImage("http://kirkee2.cafe24.com/roadImage/road"+jsonObject.getString("id")+".png");
            //aquery.id( thumbNailImage ).image("http://kirkee2.cafe24.com/memberImage/"+kakaoId+".jpg" );

            messageList.add(messageRow);

        }

        mAdapter = new MessageRecyclerAdapter(messageList,R.layout.layout_message_row);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
