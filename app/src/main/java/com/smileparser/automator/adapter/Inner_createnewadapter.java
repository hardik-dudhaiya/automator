package com.smileparser.automator.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smileparser.automator.R;
import com.smileparser.automator.listener.OnTriggerMenuClickListener;

import java.util.ArrayList;
import java.util.List;

public class Inner_createnewadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> triggerList_name = new ArrayList<>();
    private List<String> trigger_img_label = new ArrayList<>();
    OnTriggerMenuClickListener onTriggerMenuClickListener;


    public Inner_createnewadapter(Context mContext, List<String> list_name, List<String> triggerList_img, OnTriggerMenuClickListener onTriggerMenuClickListener) {
        this.mContext = mContext;
        this.triggerList_name = list_name;
        this.trigger_img_label = triggerList_img;
        this.onTriggerMenuClickListener = onTriggerMenuClickListener;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_inner_createnew, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        try {

            myViewHolder.tv_trigger_inner.setText(triggerList_name.get(position));
            int id = mContext.getResources().getIdentifier(trigger_img_label.get(position).trim(), "drawable", mContext.getPackageName());

            myViewHolder.img_icon_trigger_inner.setBackgroundResource(id);


        } catch (Exception e) {
            e.getMessage();
        }

    }

    @Override
    public int getItemCount() {
        return triggerList_name.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        TextView tv_trigger, tv_trigger_inner;
        ImageView img_icon_trigger, img_icon_trigger_inner;

        LinearLayout ll_contain;

        public MyViewHolder(View itemView) {
            super(itemView);


            ll_contain = itemView.findViewById(R.id.ll_contain);
            checkBox = itemView.findViewById(R.id.ck);
            img_icon_trigger = itemView.findViewById(R.id.img_icon_trigger);
            img_icon_trigger_inner = itemView.findViewById(R.id.img_icon_trigger_inner);
            tv_trigger = itemView.findViewById(R.id.tv_trigger);
            tv_trigger_inner = itemView.findViewById(R.id.tv_trigger_inner);


            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (checkBox.isChecked()) {
//                        ll_contain.setVisibility(View.VISIBLE);
                    onTriggerMenuClickListener.onTriggerMenuClicked(checkBox.isChecked(), getAdapterPosition());
//                    } else {
//                        ll_contain.setVisibility(View.GONE);
//                    }
                }
            });

            /*if (triggerList_name.get(0).endsWith( "bettery level" )) {
                if (checkBox.isChecked()) {
                    ll_contain.setVisibility( View.VISIBLE );
                }else{
                    ll_contain.setVisibility( View.GONE );
                }
            }
            else{
                ll_contain.setVisibility( View.GONE );
            }*/
        }

    }
}

