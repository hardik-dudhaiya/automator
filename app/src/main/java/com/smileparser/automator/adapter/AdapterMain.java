package com.smileparser.automator.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.smileparser.automator.R;
import com.smileparser.automator.utils.SecondaryTextView;

import java.util.ArrayList;

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.ViewHolder> {

    private EventInterface anInterface;
    private ArrayList<String> img_name;
    private ArrayList<String> img_label;
    private Context context;
    private String callingPage = "";

    public AdapterMain(Context context, ArrayList<String> img_name, ArrayList<String> img_label, String page) {
        super();
        this.context = context;
        this.img_name = img_name;
        this.img_label = img_label;
        this.callingPage = page;
    }

    public void setOnTriggerEvent(EventInterface event) {
        anInterface = event;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_main, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        int id = context.getResources().getIdentifier(img_name.get(position).trim(), "drawable", context.getPackageName());

        switch (callingPage) {
            case "1":// Triggers
                viewHolder.bgView.setBackground(context.getDrawable(R.drawable.bgtrigger));
                break;

            case "2": //Actions
                viewHolder.bgView.setBackground(context.getDrawable(R.drawable.bluebg));
                break;

            case "3": //Constraints
                viewHolder.bgView.setBackground(context.getDrawable(R.drawable.green_bg));
                break;
        }
        viewHolder.iv_setimg.setBackgroundResource(id);

        viewHolder.tv_settext.setText(img_label.get(position));
        viewHolder.itemView.setOnClickListener(view -> {
            if (anInterface != null) {
                anInterface.loadEvent(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return img_name.size();
    }

    public interface EventInterface {
        void loadEvent(int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_setimg;
        SecondaryTextView tv_settext;
        LinearLayout bgView;

        ViewHolder(View itemView) {
            super(itemView);
            iv_setimg = itemView.findViewById(R.id.iv_setimg);
            tv_settext = itemView.findViewById(R.id.tv_settext);
            bgView = itemView.findViewById(R.id.bg_view);
        }
    }
}