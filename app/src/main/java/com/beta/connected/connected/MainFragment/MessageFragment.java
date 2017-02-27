package com.beta.connected.connected.MainFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.beta.connected.connected.MessageDetailActivity;
import com.beta.connected.connected.R;
import com.beta.connected.connected.RecyclerView.MessageRecyclerAdapter;
import com.beta.connected.connected.RecyclerView.MessageRow;
import com.beta.connected.connected.RecyclerView.RecyclerItemClickListener;
import com.beta.connected.connected.RecyclerView.SimpleDividerItemDecoration;
import com.beta.connected.connected.WriteMessageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {
    private RecyclerView recyclerView;
    private RelativeLayout mainRelativeLayout;
    private RelativeLayout relativeLayout;
    private List<MessageRow> messageList;
    private FloatingActionButton btWriteMessage;
    private SwipeRefreshLayout swipeRefreshLayout;


    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);


        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        mainRelativeLayout = (RelativeLayout)view.findViewById(R.id.mainLayout);
        relativeLayout = (RelativeLayout)view.findViewById(R.id.infoLayout);

        btWriteMessage = (FloatingActionButton)view.findViewById(R.id.btWriteMessage);

        btWriteMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WriteMessageActivity.class));
            }
        });

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

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });

        mainRelativeLayout.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.INVISIBLE);
        initData();

        return view;
    }

    void refreshItems() {
        refreshData();

        swipeRefreshLayout.setRefreshing(false);
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

        recyclerView.setAdapter(new MessageRecyclerAdapter(messageList,R.layout.message_row));
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

        recyclerView.setAdapter(new MessageRecyclerAdapter(messageList,R.layout.message_row));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
