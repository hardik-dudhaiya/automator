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
import com.smileparser.automator.adapter.Inner_createnewadapter;
import com.smileparser.automator.listener.OnTriggerMenuClickListener;
import com.smileparser.automator.triggeractionmanager.BatteryLevelTriggerDialogManager;
import com.smileparser.automator.triggeractionmanager.BatterySaverTriggerDialogManager;
import com.smileparser.automator.triggeractionmanager.DialogManager;
import com.smileparser.automator.triggeractionmanager.PowerButtonTriggerDialogManager;
import com.smileparser.automator.triggeractionmanager.PowerConnectionTriggerDialogManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Inner_createnew_Fragment extends Fragment implements OnTriggerMenuClickListener {


    RecyclerView inner_rv_create;
    Inner_createnewadapter adapter;
    List<String> list_name = new ArrayList<>();
    List<String> title_arrylist_img = new ArrayList<>();
    String sName;
    String[] actionimg_name;
    String[] actionimg_img;
    String[] title_img;
    List<String> img_label = new ArrayList<>();
    TextView tv_trigger;
    ImageView img_icon_trigger;

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


            title_img = getResources().getString(R.string.trigger_list_images).split(",");

            Log.e( "Img", title_img[1] );
            if (sName.endsWith( "Battery/power" )) {
                id = getResources().getIdentifier( title_img[0], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.tr_battery_labels).split( "," );
                actionimg_img = getResources().getString( R.string.tr_battery_images).split( "," );
            }
            if (sName.endsWith("Call/SMS")) {
                id = getResources().getIdentifier(title_img[1], "drawable", getActivity().getPackageName());
                actionimg_name = getResources().getString(R.string.tr_call_sms_labels).split(",");
                actionimg_img = getResources().getString(R.string.tr_call_sms_images).split(",");
            }

            if (sName.endsWith( "Connectivity" )) {
                id = getResources().getIdentifier( title_img[2], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.tr_connectivity_labels).split( "," );
                actionimg_img = getResources().getString( R.string.tr_connectivity_images).split( "," );
            }

            if (sName.endsWith("Date/Time")) {
                id = getResources().getIdentifier(title_img[2], "drawable", getActivity().getPackageName());
                actionimg_name = getResources().getString(R.string.tr_date_time_labels).split(",");
                actionimg_img = getResources().getString(R.string.tr_date_time_images).split(",");
            }
            if  (sName.endsWith( "Device Events" )) {
                id = getResources().getIdentifier( title_img[4], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.tr_device_event_labels).split( "," );
                actionimg_img = getResources().getString( R.string.tr_device_event_image).split( "," );
            }
            if (sName.endsWith("Location")) {
                id = getResources().getIdentifier(title_img[5], "drawable", getActivity().getPackageName());
                actionimg_name = getResources().getString(R.string.tr_location_labels).split(",");
                actionimg_img = getResources().getString(R.string.tr_location_image).split(",");
            }
            if(sName.endsWith( "AppsMount/Specific" )) {
                id = getResources().getIdentifier( title_img[6], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.tr_location_labels).split( "," );
                actionimg_img = getResources().getString( R.string.tr_date_time_labels).split( "," );


            }




            for (int i = 0; i < actionimg_name.length; i++) {
                Log.e("DAta", String.valueOf(actionimg_name.toString()));
                list_name.add(actionimg_name[i]);
                img_label.add(actionimg_img[i]);
            }


            img_icon_trigger.setImageResource(id);
            tv_trigger.setText(sName);
            adapter = new Inner_createnewadapter(getActivity(), list_name, img_label, this);
            inner_rv_create.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inner_createnew_, container, false);
        inner_rv_create = rootView.findViewById(R.id.inner_rv_create);


        tv_trigger = rootView.findViewById(R.id.tv_trigger);
        img_icon_trigger = rootView.findViewById(R.id.img_icon_trigger);
        inner_rv_create.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;


    }


    @Override
    public void onTriggerMenuClicked(boolean isSelected, int position) {
        DialogManager dialogManager = null;
        switch (position) {
            case 0:
                dialogManager = new BatteryLevelTriggerDialogManager(getContext());
                break;

            case 1:
                dialogManager = new BatterySaverTriggerDialogManager(getContext());
                break;

            case 2:
                dialogManager = new PowerButtonTriggerDialogManager(getContext());
                break;

            case 3:
                dialogManager = new PowerConnectionTriggerDialogManager(getContext());
                break;
        }

        if (dialogManager != null)
            dialogManager.show();

    }
}








