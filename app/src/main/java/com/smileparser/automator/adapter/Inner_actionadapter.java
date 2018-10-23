package com.smileparser.automator.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smileparser.automator.R;
import com.smileparser.automator.listener.OnActionMenuClickListener;

import java.util.ArrayList;
import java.util.List;

public class Inner_actionadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final OnActionMenuClickListener onActionMenuClickListener;
    private Context mContext;
    private List<String> actionList_name = new ArrayList<>();
    private List<String> action_img_label = new ArrayList<>();


    public Inner_actionadapter(Context mContext, List<String> actionList_name, List<String> action_img_label,
                               OnActionMenuClickListener onActionMenuClickListener) {
        this.mContext = mContext;
        this.actionList_name = actionList_name;
        this.action_img_label = action_img_label;
        this.onActionMenuClickListener = onActionMenuClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_inner_action, parent, false);
        return new Inner_actionadapter.MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Inner_actionadapter.MyViewHolder myViewHolder = (Inner_actionadapter.MyViewHolder) holder;
        myViewHolder.tv_action_inner.setText(actionList_name.get(position));
        int id = mContext.getResources().getIdentifier(action_img_label.get(position).trim(), "drawable", mContext.getPackageName());
        myViewHolder.img_icon_action_inner.setBackgroundResource(id);

    }

    @Override
    public int getItemCount() {
        return actionList_name.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView tv_action, tv_action_inner;
        ImageView img_icon_action, img_icon_action_inner;
        LinearLayout ll_contain;


        public MyViewHolder(View itemView) {
            super(itemView);
            ll_contain = itemView.findViewById(R.id.ll_contain);

            checkBox = itemView.findViewById(R.id.ck);
            img_icon_action = itemView.findViewById(R.id.img_icon_action);
            img_icon_action_inner = itemView.findViewById(R.id.img_icon_action_inner);
            tv_action = itemView.findViewById(R.id.tv_action);
            tv_action_inner = itemView.findViewById(R.id.tv_action_inner);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (checkBox.isChecked()) {
//                        ll_contain.setVisibility(View.VISIBLE);
                    onActionMenuClickListener.onActionMenuClicked(checkBox.isChecked(), getAdapterPosition());
//                    } else {
//                        ll_contain.setVisibility(View.GONE);
//                    }
                }
            });

        }
    }
}


