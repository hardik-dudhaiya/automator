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
import android.widget.Toast;

import com.smileparser.automator.R;
import com.smileparser.automator.listener.OnConstraintMenuClickListener;

import java.util.ArrayList;
import java.util.List;

public class Inner_constraintadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> constraintList_name = new ArrayList<>();
    private List<String> constraint_img_label = new ArrayList<>();
    private OnConstraintMenuClickListener onConstraintMenuClickListener;

    public Inner_constraintadapter(Context mContext, List<String> constraintList_name, List<String> constraint_img_label, OnConstraintMenuClickListener onConstraintMenuClickListener) {
        this.mContext = mContext;
        this.constraintList_name = constraintList_name;
        this.constraint_img_label = constraint_img_label;
        this.onConstraintMenuClickListener = onConstraintMenuClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_inner_constraint, parent, false);
        return new Inner_constraintadapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Inner_constraintadapter.MyViewHolder myViewHolder = (Inner_constraintadapter.MyViewHolder) holder;
        myViewHolder.tv_constraint_inner.setText(constraintList_name.get(position));
        int id = mContext.getResources().getIdentifier(constraint_img_label.get(position).trim(), "drawable", mContext.getPackageName());

        myViewHolder.img_icon_constraint_inner.setBackgroundResource(id);

    }

    @Override
    public int getItemCount() {
        return constraintList_name.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView tv_constraint, tv_constraint_inner;
        ImageView img_icon_constraint, img_icon_constraint_inner;
        LinearLayout ll_contain;


        public MyViewHolder(View itemView) {
            super(itemView);
            ll_contain = itemView.findViewById(R.id.ll_contain);

            checkBox = itemView.findViewById(R.id.ck);
            img_icon_constraint = itemView.findViewById(R.id.img_icon_constraint);
            img_icon_constraint_inner = itemView.findViewById(R.id.img_icon_constraint_inner);
            tv_constraint = itemView.findViewById(R.id.tv_constraint);
            tv_constraint_inner = itemView.findViewById(R.id.tv_constraint_inner);


            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (checkBox.isChecked()) {
//                        ll_contain.setVisibility(View.VISIBLE);
                    onConstraintMenuClickListener.onConstraintMenuClicked(checkBox.isChecked(), getAdapterPosition());
//                    } else {
//                        ll_contain.setVisibility(View.GONE);
//                    }
                }
            });

        }
    }
}


