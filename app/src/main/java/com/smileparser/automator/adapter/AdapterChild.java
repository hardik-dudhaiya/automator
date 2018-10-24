package com.smileparser.automator.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.smileparser.automator.R;
import com.smileparser.automator.utils.SecondaryTextView;

import java.util.ArrayList;

public class AdapterChild extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private EventListener onActionMenuClickListener;
    private Context mContext;
    private ArrayList<String> actionList_name;
    private ArrayList<String> action_img_label;
    private String callingPage = "";

    public AdapterChild(Context mContext, ArrayList<String> actionList_name, ArrayList<String> action_img_label, String page) {
        this.mContext = mContext;
        this.actionList_name = actionList_name;
        this.action_img_label = action_img_label;
        this.callingPage = page;
    }

    public void setOnActionMenuClickListener(EventListener clickListener) {
        this.onActionMenuClickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_child, parent, false);
        return new AdapterChild.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AdapterChild.MyViewHolder myViewHolder = (AdapterChild.MyViewHolder) holder;
        myViewHolder._txtActionName.setText(actionList_name.get(position));
        myViewHolder._imgIcon.setBackgroundResource(mContext.getResources().getIdentifier(action_img_label.get(position).trim(), "drawable", mContext.getPackageName()));

        switch (callingPage) {
            case "1"://Inner Triggers
                myViewHolder._parentBox.setBackground(mContext.getResources().getDrawable(R.drawable.red_textarea));
                myViewHolder._txtActionName.setTextColor(ContextCompat.getColor(mContext, R.color.dark_red));
                myViewHolder._bgCut.setBackground(mContext.getResources().getDrawable(R.drawable.red_cut));
                CompoundButtonCompat.setButtonTintList(myViewHolder._checkbox, mContext.getResources().getColorStateList(R.color.dark_red));
                break;

            case "2": //Inner Actions
                myViewHolder._parentBox.setBackground(mContext.getResources().getDrawable(R.drawable.action_button_border));
                myViewHolder._txtActionName.setTextColor(ContextCompat.getColor(mContext, R.color.blue));
                myViewHolder._bgCut.setBackground(mContext.getResources().getDrawable(R.drawable.blue_cut));
                CompoundButtonCompat.setButtonTintList(myViewHolder._checkbox, mContext.getResources().getColorStateList(R.color.blue));
                break;

            case "3": //Inner Constraints
                myViewHolder._parentBox.setBackground(mContext.getResources().getDrawable(R.drawable.green_textarea));
                myViewHolder._txtActionName.setTextColor(ContextCompat.getColor(mContext, R.color.green));
                myViewHolder._bgCut.setBackground(mContext.getResources().getDrawable(R.drawable.green_cut));
                CompoundButtonCompat.setButtonTintList(myViewHolder._checkbox, mContext.getResources().getColorStateList(R.color.green));
                break;
        }

        myViewHolder._checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            switch (callingPage) {
                case "1": {//Inner Triggers
                    onActionMenuClickListener.onTriggerMenuClicked(myViewHolder._checkbox.isChecked(), position);
                }
                break;

                case "2": {//Inner Actions
                    onActionMenuClickListener.onActionMenuClicked(myViewHolder._checkbox.isChecked(), position);
                }
                break;
                case "3": {//Inner Constraints
                    onActionMenuClickListener.onConstraintMenuClicked(myViewHolder._checkbox.isChecked(), position);
                }
                break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return actionList_name.size();
    }

    public interface EventListener {
        void onTriggerMenuClicked(boolean isSelected, int position);

        void onActionMenuClicked(boolean isSelected, int position);

        void onConstraintMenuClicked(boolean isSelected, int position);
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout _parentBox;
        private AppCompatCheckBox _checkbox;
        private SecondaryTextView _txtActionName;
        private LinearLayout _bgCut;
        private ImageView _imgIcon;

        MyViewHolder(View itemView) {
            super(itemView);
            _parentBox = itemView.findViewById(R.id.parent_box);
            _checkbox = itemView.findViewById(R.id.checkbox);
            _txtActionName = itemView.findViewById(R.id.txt_action_name);
            _bgCut = itemView.findViewById(R.id.bg_cut);
            _imgIcon = itemView.findViewById(R.id.img_icon);
        }
    }
}