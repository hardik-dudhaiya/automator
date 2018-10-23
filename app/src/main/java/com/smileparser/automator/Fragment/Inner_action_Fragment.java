package com.smileparser.automator.Fragment;


import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smileparser.automator.Activity.CreateActivity;
import com.smileparser.automator.R;
import com.smileparser.automator.adapter.Inner_actionadapter;
import com.smileparser.automator.adapter.Inner_createnewadapter;
import com.smileparser.automator.database.DatabaseHelper;
import com.smileparser.automator.listener.OnActionMenuClickListener;
import com.smileparser.automator.triggeractionmanager.Action;
import com.smileparser.automator.triggeractionmanager.ActionDetails;
import com.smileparser.automator.triggeractionmanager.AppInfo;
import com.smileparser.automator.triggeractionmanager.AppLaunchActionDialogManager;
import com.smileparser.automator.triggeractionmanager.DialogManager;
import com.smileparser.automator.triggeractionmanager.EventValue;
import com.smileparser.automator.triggeractionmanager.Macro;
import com.smileparser.automator.triggeractionmanager.MacroManager;
import com.smileparser.automator.triggeractionmanager.Trigger;
import com.smileparser.automator.triggeractionmanager.TriggerDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Inner_action_Fragment extends Fragment implements OnActionMenuClickListener {


    RecyclerView inner_rv_action;
    Inner_actionadapter adapter;
    List<String> list_name = new ArrayList<>();
    List<String> title_arrylist_img = new ArrayList<>();
    String sName;
    String[] actionimg_name;
    String[] actionimg_img;
    String[] title_img;
    List<String> img_label = new ArrayList<>();
    TextView tv_action;
    ImageView img_icon_action;

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
            title_arrylist_img.add(sImg);

            title_img = getResources().getString(R.string.action_icon_img).split(",");
            Log.e("Img", title_img[1]);



            Log.e( "Img", title_img[1] );
            if (sName.endsWith("Applications")) {
                id = getResources().getIdentifier(title_img[0], "drawable", getActivity().getPackageName());
                actionimg_name = getResources().getString(R.string.action_application_data).split(",");
                actionimg_img = getResources().getString(R.string.action_application_img).split(",");
            }
            if (sName.endsWith("Camera/Photo")) {
                id = getResources().getIdentifier( title_img[1], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.action_camera_photo_data ).split( "," );
                actionimg_img = getResources().getString( R.string.action_camera_photo_img ).split( "," );
            }
            if (sName.endsWith( "Connectivity" )) {
                id = getResources().getIdentifier( title_img[2], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.action_connectivity_data ).split( "," );
                actionimg_img = getResources().getString( R.string.action_connectivity_img ).split( "," );
            }
            if (sName.endsWith( "Date/Time" )) {
                id = getResources().getIdentifier( title_img[3], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.action_date_time_data ).split( "," );
                actionimg_img = getResources().getString( R.string.action_datetime_img ).split( "," );
            }
            if (sName.endsWith( "Device Actions" )) {
                id = getResources().getIdentifier( title_img[4], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.action_device_action_data ).split( "," );
                actionimg_img = getResources().getString( R.string.action_device_action_img ).split( "," );
            }
            if (sName.endsWith( " Device Settings" )) {
                id = getResources().getIdentifier( title_img[5], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.action_device_setting_data ).split( "," );
                actionimg_img = getResources().getString( R.string.action_device_setting_img ).split( "," );
            }
            if (sName.endsWith( "Location" )) {
                id = getResources().getIdentifier( title_img[6], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.action_location_data ).split( "," );
                actionimg_img = getResources().getString( R.string.action_location_img ).split( "," );
            }
            if (sName.endsWith( "Media" )) {
                id = getResources().getIdentifier( title_img[7], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.action_media_data ).split( "," );
                actionimg_img = getResources().getString( R.string.action_media_img ).split( "," );
            }
            if (sName.endsWith( "Messaging" )) {
                id = getResources().getIdentifier( title_img[8], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.action_messaging_data ).split( "," );
                actionimg_img = getResources().getString( R.string.action_messanging_img ).split( "," );
            }
            if (sName.endsWith( "Phone" )) {
                id = getResources().getIdentifier( title_img[9], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.action_phone_data ).split( "," );
                actionimg_img = getResources().getString( R.string.action_phone_img ).split( "," );
            }
            if (sName.endsWith("Screen")) {
                id = getResources().getIdentifier( title_img[10], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.action_screen_data ).split( "," );
                actionimg_img = getResources().getString( R.string.action_screen_img ).split( "," );
            }
            if (sName.endsWith("Volume")) {
                id = getResources().getIdentifier( title_img[11], "drawable", getActivity().getPackageName() );
                actionimg_name = getResources().getString( R.string.action_volume_data ).split( "," );
                actionimg_img = getResources().getString( R.string.action_voume_img ).split( "," );
            }


            for (int i = 0; i < actionimg_name.length; i++) {
                Log.e("Data", String.valueOf(actionimg_name.toString()));
                list_name.add(actionimg_name[i]);
                img_label.add(actionimg_img[i]);
            }


            img_icon_action.setImageResource(id);
            tv_action.setText(sName);
            adapter = new Inner_actionadapter(getActivity(), list_name, img_label, this);
            inner_rv_action.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inner_action_, container, false);
        inner_rv_action = rootView.findViewById(R.id.inner_rv_action);
        tv_action = rootView.findViewById(R.id.tv_action);
        img_icon_action = rootView.findViewById(R.id.img_icon_action);
        inner_rv_action.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

    @Override
    public void onActionMenuClicked(boolean isSelected, int position) {
        switch (position) {
            case 4:
                DialogManager appLaunchDialogManager = new AppLaunchActionDialogManager(getActivity());
                appLaunchDialogManager.show();
                break;
        }
    }
}





