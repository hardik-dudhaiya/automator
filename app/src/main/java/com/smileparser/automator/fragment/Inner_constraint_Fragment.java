package com.smileparser.automator.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smileparser.automator.R;
import com.smileparser.automator.adapter.Inner_constraintadapter;
import com.smileparser.automator.listener.OnConstraintMenuClickListener;
import com.smileparser.automator.triggeractionmanager.BatteryLevelConstraintDialogManager;
import com.smileparser.automator.triggeractionmanager.BatterySaverConstraintDialogManager;
import com.smileparser.automator.triggeractionmanager.DialogManager;
import com.smileparser.automator.triggeractionmanager.PowerConnectionConstraintDialogManager;

import java.util.ArrayList;
import java.util.List;

public class Inner_constraint_Fragment extends Fragment implements OnConstraintMenuClickListener {

    RecyclerView inner_rv_constraint;
    Inner_constraintadapter adapter;
    List<String> list_name = new ArrayList<>();
    List<String> title_arrylist_img = new ArrayList<>();
    String sName;
    String[] actionimg_name;
    String[] actionimg_img;
    String[] title_img;
    List<String> img_label = new ArrayList<>();
    TextView tv_constraint;
    ImageView img_icon_constraint;
    int id;
    List<String> title_arrylist_img_id = new ArrayList<>();
    String sImg;

    @Override
    public void onResume() {
        super.onResume();
        Bundle args = getArguments();
        if (args != null) {
            sName = args.getString("Key");
            sImg = args.getString("Key_img");


            title_img = getResources().getString(R.string.constraint_icon_img).split(",");


            Log.e("Img", title_img[1]);

            if (sName.endsWith("Battery/Power")) {
                id = getResources().getIdentifier(title_img[0], "drawable", getActivity().getPackageName());
                actionimg_name = getResources().getString(R.string.constraint_bettery).split(",");
                actionimg_img = getResources().getString(R.string.constraint_bettrey_img).split(",");
            }
            if (sName.endsWith( "Connectivity" )) {
                id = getResources().getIdentifier( title_img[1], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.constraint_connectivity_data ).split( "," );
                actionimg_img = getResources().getString( R.string.constraint_connectivity_img ).split( "," );
            }
            if (sName.endsWith( "Date/Time" )) {
                id = getResources().getIdentifier( title_img[2], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.constraint_date_time_data ).split( "," );
                actionimg_img = getResources().getString( R.string.constraint_date_time_img ).split( "," );
            }
            if (sName.endsWith("Device State")) {
                id = getResources().getIdentifier( title_img[3], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.constraint_device_state_data ).split( "," );
                actionimg_img = getResources().getString( R.string.constraint_device_state_img ).split( "," );
            }
            if (sName.endsWith( "Media" )) {
                id = getResources().getIdentifier( title_img[4], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.constraint_media_data ).split( "," );
                actionimg_img = getResources().getString( R.string.constraint_media_img ).split( "," );
            }
            if (sName.endsWith( "Phone" )) {
                id = getResources().getIdentifier( title_img[5], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.constraint_phone_data ).split( "," );
                actionimg_img = getResources().getString( R.string.constraint_phone_img ).split( "," );
            }
            if (sName.endsWith( "Screen and Speaker" )) {
                id = getResources().getIdentifier( title_img[6], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.constraint_screen_sensor_data ).split( "," );
                actionimg_img = getResources().getString( R.string.constraint_screen_sensor_img ).split( "," );
            }
            if (sName.endsWith( "Sensors" )) {
                id = getResources().getIdentifier( title_img[7], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.constraint_sensors_data ).split( "," );
                actionimg_img = getResources().getString( R.string.constraint_sensors_img ).split( "," );
           }


            for (int i = 0; i < actionimg_name.length; i++) {
                Log.e("DAta", String.valueOf(actionimg_name.toString()));
                list_name.add(actionimg_name[i]);
                img_label.add(actionimg_img[i]);
            }

            img_icon_constraint.setImageResource(id);
            tv_constraint.setText(sName);
            adapter = new Inner_constraintadapter(getActivity(), list_name, img_label, this);
            inner_rv_constraint.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inner_constraint_, container, false);
        inner_rv_constraint = rootView.findViewById(R.id.inner_rv_constraint);
        tv_constraint = rootView.findViewById(R.id.tv_constraint);
        img_icon_constraint = rootView.findViewById(R.id.img_icon_constraint);
        inner_rv_constraint.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

    @Override
    public void onConstraintMenuClicked(boolean isSelected, int position) {
        DialogManager dialogManager = null;
        switch (position) {
            case 0:
                dialogManager = new BatteryLevelConstraintDialogManager(getActivity());
                break;

            case 1:
                dialogManager = new BatterySaverConstraintDialogManager(getActivity());
                break;

            case 2:
                dialogManager = new PowerConnectionConstraintDialogManager(getActivity());
                break;
        }
        dialogManager.show();
    }
}





