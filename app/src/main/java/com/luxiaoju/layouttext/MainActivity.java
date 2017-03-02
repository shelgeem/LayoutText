package com.luxiaoju.layouttext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout  mLayout_btn_zhengwen,mLayout_btn_attachment,mLayout_btn_relate;
    private LinearLayout mLayout_content_zheng,mLayout_content_attachment,mlayout_content_relate;
    private TextView mItem_name,mItem_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout_btn_zhengwen = (LinearLayout) findViewById(R.id.layout_btn_zhengwen);
        mLayout_btn_attachment = (LinearLayout) findViewById(R.id.layout_btn_attachment);
        mLayout_btn_relate = (LinearLayout) findViewById(R.id.layout_btn_relate);

        mLayout_content_zheng = (LinearLayout) findViewById(R.id.layout_content_zhengwen);
        mLayout_content_attachment  = (LinearLayout) findViewById(R.id.layout_content_attachment);
        mlayout_content_relate = (LinearLayout) findViewById(R.id.layout_content_relate);

        mItem_name = (TextView) findViewById(R.id.item_name);
        mItem_user = (TextView) findViewById(R.id.item_user);


        mLayout_btn_zhengwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayout_content_attachment.setVisibility(View.GONE);
                mlayout_content_relate.setVisibility(View.GONE);
                mLayout_content_zheng.setVisibility(View.VISIBLE);
                View zhengView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main_item,null,false);
                mLayout_content_zheng.addView(zhengView);
                mItem_name.setText("这个是正文的doc文件.doc");
                mItem_user.setText("崔勇");

            }
        });

        mLayout_btn_attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayout_content_zheng.setVisibility(View.GONE);
                mlayout_content_relate.setVisibility(View.GONE);
                mLayout_content_attachment.setVisibility(View.VISIBLE);
                for(int i=0;i<4;i++) {
                    View attaView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main_item,null,false);
                    mItem_name.setText("这个崔勇添加的附件"+i);
                    mItem_user.setText("崔勇");
                    mLayout_content_attachment.addView(attaView);
                }

            }
        });

        mLayout_btn_relate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayout_content_zheng.setVisibility(View.GONE);
                mlayout_content_relate.setVisibility(View.VISIBLE);
                mLayout_content_attachment.setVisibility(View.GONE);
                for(int i=0;i<4;i++) {
                    View attaView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main_item,null,false);
                    mItem_name.setText("这个崔勇添加的相关文件"+i);
                    mItem_user.setText("崔勇");
                    mLayout_content_attachment.addView(attaView);
                }
            }
        });

    }

   class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
       List<MessageInfo> dataList = new ArrayList<>();
       LayoutInflater layoutInflater;

       public MyRecyclerAdapter(LayoutInflater layoutInflater) {
           this.layoutInflater = layoutInflater;
           this.dataList = dataList;
       }

       public void setDataList(List<MessageInfo> dataList) {
           this.dataList = dataList;
       }

       @Override
       public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           View view = layoutInflater.inflate(R.layout.activity_main_item,parent,false);
           ViewHolder vh = new ViewHolder(view);
           return vh;
       }

       @Override
       public void onBindViewHolder(MyRecyclerAdapter.ViewHolder holder, int position) {
           MessageInfo item = dataList.get(position);
           holder.mItemTitle.setText(item.title);
           int imageRes;
           switch (item.type) {
               case 0:
                   imageRes = R.drawable.doc;
                   break;
               case 1:
                   imageRes = R.drawable.attachment;
                   break;
               case 2:
               default:
                   imageRes = R.drawable.relate;
                   break;
           }
           holder.mItemImage.setImageResource(imageRes);
       }

       @Override
       public int getItemCount() {
           return dataList == null? 0:dataList.size();
       }

       class ViewHolder extends RecyclerView.ViewHolder {
           ImageView mItemImage;
           TextView mItemTitle;
           RecyclerView mItem_list;

           public ViewHolder(View itemView) {
               super(itemView);
               mItemImage = (ImageView) itemView.findViewById(R.id.img_left);
//               mItem_list = (RecyclerView) itemView.findViewById(R.id.item_content_recycler);
//               mItemTitle = (TextView) itemView.findViewById(R.id.item_content_title);
           }
       }
   }


}
