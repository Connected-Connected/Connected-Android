package com.beta.connected.connected.MainFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.beta.connected.connected.ChattingDetailActivity;
import com.beta.connected.connected.R;
import com.beta.connected.connected.RecyclerView.BlogRecyclerAdapter;
import com.beta.connected.connected.RecyclerView.BlogRow;
import com.beta.connected.connected.RecyclerView.ChattingRecyclerAdapter;
import com.beta.connected.connected.RecyclerView.ChattingRow;
import com.beta.connected.connected.RecyclerView.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private RelativeLayout relativeLayout;
    private List<BlogRow> blogList;

    public BlogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blog, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        linearLayout = (LinearLayout)view.findViewById(R.id.mainLayout);
        relativeLayout = (RelativeLayout)view.findViewById(R.id.infoLayout);


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Toast.makeText(getActivity(),"id가 "+ blogList.get(position).getId() +"인 웹뷰로 이동할 것임",Toast.LENGTH_LONG).show();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        linearLayout.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.INVISIBLE);
        initData();

        return view;
    }

    private void initData(){

        //recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        blogList = new ArrayList<BlogRow>();

        for(int i = 1 ; i < 10 ; i++){

            BlogRow blogRow = new BlogRow();

            blogRow.setTitle("제목");
            blogRow.setId(i + "");
            //new ImageFromUrl().execute("http://kirkee2.cafe24.com/roadImage/road"+jsonObject.getString("id")+".png");

            //album.setImage("http://kirkee2.cafe24.com/roadImage/road"+jsonObject.getString("id")+".png");
            //aquery.id( thumbNailImage ).image("http://kirkee2.cafe24.com/memberImage/"+kakaoId+".jpg" );

            blogList.add(blogRow);

        }

        recyclerView.setAdapter(new BlogRecyclerAdapter(blogList,R.layout.blog_row));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

}
