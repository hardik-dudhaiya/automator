package com.smileparser.automator.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smileparser.automator.R;
import com.smileparser.automator.utils.SecondaryTextView;

import java.util.ArrayList;
import java.util.List;

public class actionadapter extends RecyclerView.Adapter<actionadapter.ViewHolder> {


    private Context context;
    List<String> img_name = new ArrayList<>();
    List<String> img_label = new ArrayList<>();
    actionadapter.ActionAdapterEvent actionAdapterEvent=null;

    public actionadapter(Context context, List<String> img_name, List<String> img_label, ActionAdapterEvent actionAdapterEvent) {
        this.context = context;
        this.img_name = img_name;
        this.img_label = img_label;
        this.actionAdapterEvent = actionAdapterEvent;
    }

    public interface ActionAdapterEvent {
        void Display(int adapterposition);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_action, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        int id = context.getResources().getIdentifier(img_name.get(position).trim(), "drawable", context.getPackageName());

        viewHolder.iv_setimg.setBackgroundResource(id);
        viewHolder.tv_settext.setText(img_label.get(position));
        viewHolder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAdapterEvent.Display( position );






            }
        } );
    }



    @Override
    public int getItemCount() {
        return img_name.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_setimg;
        SecondaryTextView tv_settext;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_setimg = itemView.findViewById(R.id.iv_setimg);
            tv_settext = itemView.findViewById(R.id.tv_settext);

        }


    }

}

