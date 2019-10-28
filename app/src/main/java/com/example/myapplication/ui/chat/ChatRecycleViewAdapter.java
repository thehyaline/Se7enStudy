package com.example.myapplication.ui.chat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.im.ConversationInfo;

import java.util.ArrayList;

public class ChatRecycleViewAdapter extends RecyclerView.Adapter<ChatRecycleViewAdapter.ViewHolder>{

    private Context context;
    private ArrayList<ConversationInfo> messageEntityList;
    private ChatRecycleViewAdapter.OnItemClickListener onItemClickListener;



    //构造方法，传入数据
    public ChatRecycleViewAdapter(Context context, ArrayList<ConversationInfo> messageEntityList){
        this.context = context;
        this.messageEntityList = messageEntityList;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    //定义一个设置点击监听器的方法
    public void setOnItemClickListener(ChatRecycleViewAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ChatRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建ViewHolder，返回每一项的布局
        View itemView = View.inflate(context, R.layout.conversation_list_item,null);
        return new ChatRecycleViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatRecycleViewAdapter.ViewHolder holder, int position) {
        //根据点击位置并绑定数据
        final ConversationInfo data = messageEntityList.get(position);
        holder.mItemMsg.setText("用户ID：" + data.getId() + "消息：" + data.getLastMessage().getExtra()); //获取实体类中的nickname字段并设置

        //对RecyclerView的每一个itemView设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, pos);
                }
                //表示此事件已经消费，不会触发单击事件
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        //返回Item总条数
        return messageEntityList.size();
    }


    //定义ViewHolder。内部类，绑定控件
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mItemMsg;
        //CircleImageView avatar;

        public ViewHolder(View itemView){
            super(itemView);
            mItemMsg = (TextView) itemView.findViewById(R.id.my_msg);
            mItemMsg = (TextView) itemView.findViewById(R.id.other_msg);
        }


    }
}
