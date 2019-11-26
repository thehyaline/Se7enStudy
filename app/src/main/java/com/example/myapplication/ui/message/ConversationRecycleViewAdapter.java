package com.example.myapplication.ui.message;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.im.ConversationInfo;

import java.util.ArrayList;

public class ConversationRecycleViewAdapter extends RecyclerView.Adapter<ConversationRecycleViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ConversationInfo> messageEntityList;
    private OnItemClickListener onItemClickListener;
    private itemInterface itemInterface;

    //定义点击回调接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    //定义一个设置点击监听器的方法
    public void setOnItemClickListener(ConversationRecycleViewAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    //构造方法，传入数据
    public ConversationRecycleViewAdapter(Context context, ArrayList<ConversationInfo> messageEntityList){
        this.context = context;
        this.messageEntityList = messageEntityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建ViewHolder，返回每一项的布局
        View itemView = View.inflate(context, R.layout.conversation_list_item,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        //根据点击位置并绑定数据
        final ConversationInfo data = messageEntityList.get(position);
        holder.mItemNickName.setText("用户ID：" + data.getId()); //获取实体类中的nickname字段并设置
        holder.mItemLastMsg.setText("最后消息：" + data.getLastMessage().getExtra());//获取实体类中的lastmsg字段并设置
    }

    @Override
    public int getItemCount() {
        //返回Item总条数
        return messageEntityList.size();
    }


    //定义ViewHolder。内部类，绑定控件
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mItemNickName;
        private TextView mItemLastMsg;
        //CircleImageView avatar;

        public ViewHolder(View itemView){
            super(itemView);
            mItemNickName = (TextView) itemView.findViewById(R.id.chat_nickname);
            mItemLastMsg = (TextView)  itemView.findViewById(R.id.chat_lastmsg);
        }


    }

    public void conSetOnclick(){
        this.itemInterface = itemInterface;

    }

    public interface itemInterface{
        public void onlick(View view , int position);
    }

    //https://my.oschina.net/u/3730650/blog/2253403
}
