package com.smileparser.automator.fragment;


import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import com.smileparser.automator.R;
import com.smileparser.automator.activity.ActivityCreateMacroPage;
import com.smileparser.automator.adapter.AdapterChild;
import com.smileparser.automator.triggeractionmanager.BatteryLevelTrigger;
import com.smileparser.automator.triggeractionmanager.EventManagementUtil;
import com.smileparser.automator.triggeractionmanager.EventValue;
import com.smileparser.automator.utils.AlertDialogActionUtils;
import com.smileparser.automator.utils.AlertDialogConstraintsUtils;
import com.smileparser.automator.utils.AlertDialogTriggerUtils;
import com.smileparser.automator.utils.PermissionUtil;
import com.smileparser.automator.utils.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class FragmentChildPage extends Fragment {
    RecyclerView rv_create;
    TextView txtHeader;
    ImageView imgIcon;
    View rootView;
    String page = "", eventName = "";
    String[] imageList, actionImage, actionLabel;
    ArrayList<String> images = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();
    AdapterChild adapterChild;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_load_view, container, false);
        assignViews();
        setAdapterClicks();
        return rootView;
    }

    private void assignViews() {
        rv_create = rootView.findViewById(R.id.rv_create);
        txtHeader = rootView.findViewById(R.id.txt_header);
        imgIcon = rootView.findViewById(R.id.img_icon);
        rootView.findViewById(R.id.load_child).setVisibility(View.VISIBLE);

        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.getString("callingPage") != null) {
                page = bundle.getString("callingPage");
                eventName = bundle.getString("Key");
                txtHeader.setText(eventName);
                if (page != null && eventName != null) {
                    switch (page) {
                        case "1": { //Inner Triggers
                            loadInnerTriggerData();
                        }
                        break;

                        case "2": { //Inner Actions
                            loadInnerActionData();
                        }
                        break;

                        case "3": { //Inner Constraints
                            loadInnerConstraintData();
                        }
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < actionImage.length; i++) {
            images.add(actionImage[i]);
            labels.add(actionLabel[i]);
        }

        rv_create.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterChild = new AdapterChild(getContext(), labels, images, page);
        rv_create.setAdapter(adapterChild);
        adapterChild.notifyDataSetChanged();
    }

    private void loadInnerConstraintData() {
        imageList = Utility.getResource().getString(R.string.constraint_icon_img).split(",");
        switch (eventName.trim()) {
            case "Battery/Power": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[0], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.constraint_bettery).split(",");
                actionImage = Utility.getResource().getString(R.string.constraint_bettrey_img).split(",");
                Log.e("Android->", "assignViews: ");
            }
            break;

            case "Connectivity": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[1], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.constraint_connectivity_data).split(",");
                actionImage = Utility.getResource().getString(R.string.constraint_connectivity_img).split(",");
            }
            break;

            case "Date/Time": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[2], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.constraint_date_time_data).split(",");
                actionImage = Utility.getResource().getString(R.string.constraint_date_time_img).split(",");
            }
            break;

            case "Device State": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[3], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.constraint_device_state_data).split(",");
                actionImage = Utility.getResource().getString(R.string.constraint_device_state_img).split(",");
            }
            break;

            case "Media": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[4], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.constraint_media_data).split(",");
                actionImage = Utility.getResource().getString(R.string.constraint_media_img).split(",");
            }
            break;

            case "Phone": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[5], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.constraint_phone_data).split(",");
                actionImage = Utility.getResource().getString(R.string.constraint_phone_img).split(",");
            }
            break;

            case "Screen and Speaker": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[6], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.constraint_screen_sensor_data).split(",");
                actionImage = Utility.getResource().getString(R.string.constraint_screen_sensor_img).split(",");
            }
            break;

            case "Sensors": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[7], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.constraint_sensors_data).split(",");
                actionImage = Utility.getResource().getString(R.string.constraint_sensors_img).split(",");
            }
            break;

            default: {
                actionLabel = new String[0];
                actionImage = new String[0];
            }
            break;
        }
    }

    private void loadInnerActionData() {
        imageList = Utility.getResource().getString(R.string.action_icon_img).split(",");
        switch (eventName.trim()) {
            case "Applications": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[0], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.action_application_data).split(",");
                actionImage = Utility.getResource().getString(R.string.action_application_img).split(",");
            }
            break;

            case "Camera/Photo": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[1], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.action_camera_photo_data).split(",");
                actionImage = Utility.getResource().getString(R.string.action_camera_photo_img).split(",");
            }
            break;

            case "Connectivity": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[2], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.action_connectivity_data).split(",");
                actionImage = Utility.getResource().getString(R.string.action_connectivity_img).split(",");
            }
            break;

            case "Date/Time": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[3], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.action_date_time_data).split(",");
                actionImage = Utility.getResource().getString(R.string.action_datetime_img).split(",");
            }
            break;

            case "Device Actions": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[4], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.action_device_action_data).split(",");
                actionImage = Utility.getResource().getString(R.string.action_device_action_img).split(",");
            }
            break;

            case "Device Settings": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[5], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.action_device_setting_data).split(",");
                actionImage = Utility.getResource().getString(R.string.action_device_setting_img).split(",");
            }
            break;

            case "Location": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[6], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.action_location_data).split(",");
                actionImage = Utility.getResource().getString(R.string.action_location_img).split(",");
            }
            break;

            case "Media": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[7], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.action_media_data).split(",");
                actionImage = Utility.getResource().getString(R.string.action_media_img).split(",");
            }
            break;

            case "Messaging": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[8], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.action_messaging_data).split(",");
                actionImage = Utility.getResource().getString(R.string.action_messanging_img).split(",");
            }
            break;

            case "Phone": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[9], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.action_phone_data).split(",");
                actionImage = Utility.getResource().getString(R.string.action_phone_img).split(",");
            }
            break;

            case "Screen": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[10], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.action_screen_data).split(",");
                actionImage = Utility.getResource().getString(R.string.action_screen_img).split(",");
            }
            break;

            case "Volume": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[11], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.action_volume_data).split(",");
                actionImage = Utility.getResource().getString(R.string.action_voume_img).split(",");
            }
            break;

            default: {
                actionLabel = new String[0];
                actionImage = new String[0];
            }

        }
    }

    private void loadInnerTriggerData() {
        imageList = Utility.getResource().getString(R.string.trigger_icon_img).split(",");
        switch (eventName.trim()) {
            case "Battery/power": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[0], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.beterry_data).split(",");
                actionImage = Utility.getResource().getString(R.string.bettry_img).split(",");
            }
            break;
            case "Call/SMS": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[1], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.call_sma_data).split(",");
                actionImage = Utility.getResource().getString(R.string.call_sms_img).split(",");

            }
            break;
            case "Connectivity": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[2], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.connectivity_data).split(",");
                actionImage = Utility.getResource().getString(R.string.connectivity_img).split(",");
            }
            break;
            case "Date/Time": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[3], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.date_time_data).split(",");
                actionImage = Utility.getResource().getString(R.string.date_time_img).split(",");
            }
            break;
            case "Device Events": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[4], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.device_event_data).split(",");
                actionImage = Utility.getResource().getString(R.string.device_event_img).split(",");
            }
            break;
            case "Location": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[5], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.location_data).split(",");
                actionImage = Utility.getResource().getString(R.string.location_img).split(",");
            }
            break;
            case "AppsMount/Specific": {
                imgIcon.setImageResource(Utility.getResource().getIdentifier(imageList[6], "drawable", getActivity().getPackageName()));
                actionLabel = Utility.getResource().getString(R.string.location_data).split(",");
                actionImage = Utility.getResource().getString(R.string.date_time_data).split(",");
            }
            break;
            default: {
                actionLabel = new String[0];
                actionImage = new String[0];
            }
        }
    }

    private void setAdapterClicks() {
        adapterChild.setOnActionMenuClickListener(new AdapterChild.EventListener() {
            @Override
            public void onTriggerMenuClicked(boolean isSelected, int position) {
                Log.e("Android->", "onTriggerMenuClicked: " + position + " ==" + isSelected);
                Log.e("Android->", "onTriggerMenuClicked: " + eventName);

                switch (eventName) {
                    case "Battery/power": {
                        launchBatteryPowerTriggerEvents(isSelected, position);
                    }
                    break;

                    case "Call/SMS": {
                        launchCallSmsTriggerEvents(isSelected, position);
                    }
                    break;

                    case "Connectivity": {
                        launchConnectivityTriggerEvents(isSelected, position);
                    }
                    break;

                    case "Date/Time": {
                        loadDateTimeTriggerEvents(isSelected, position);
                    }
                    break;

                    case "Device Events": {
                        launchDeviceEventsTriggers(isSelected, position);
                    }
                    break;

                    case "Location": {
                        switch (position) {
                            case 0: {
                                AlertDialogTriggerUtils.showLocationChangeDialog(ActivityCreateMacroPage.getInstance(), "Select Option");
                            }
                            break;
                        }
                    }
                    break;

                    case "AppsMount/Specific": {
                        if (isSelected) {
                            Toast.makeText(getActivity(), "AppsMount/Specific", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;

                    default:
                        break;

                }
            }

            @Override
            public void onActionMenuClicked(boolean isSelected, int position) {
                switch (eventName.trim()) {
                    case "Applications": {
                        switch (position) {
                            case 0: {
                            }
                            break;
                            case 1: {
                            }
                            break;
                            case 2: {
                            }
                            break;
                            case 3: {
                            }
                            break;
                            case 4: {
                            }
                            break;
                            case 5: {
                            }
                            break;
                            case 6: {
                            }
                            break;
                            case 7: {
                            }
                            break;
                            case 8: {
                            }
                            break;
                        }
                    }
                    break;

                    case "Camera/Photo": {
                        launchCameraPhotoActionEvents(position, isSelected);
                    }
                    break;

                    case "Connectivity": {
                        launchConnectivityActionEvents(isSelected, position);
                    }
                    break;

                    case "Date/Time": {
                        launchDateTimeActionEvents(isSelected, position);
                    }
                    break;

                    case "Device Actions": {
                        launchDeviceActionsEvents(isSelected, position);
                    }
                    break;

                    case "Device Settings": {
                        launchDeviceSettingActionsEvents(isSelected, position);
                    }
                    break;

                    case "Location": {
                        switch (position) {
                            case 0: {
                                if (isSelected) {
                                    //Location Update
                                }
                            }
                            break;
                            case 1: {
                                if (isSelected) {
                                    AlertDialogActionUtils.showAlertDialogSingleChoiceOnly(ActivityCreateMacroPage.getInstance(), "Select Action", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("GPS On", "GPS Off", "Toggle"))), new AlertDialogActionUtils.okCancelListener() {
                                        @Override
                                        public void onOkClick() {

                                        }

                                        @Override
                                        public void onCancelClick() {

                                        }

                                        @Override
                                        public void onItemClick(Object object) {
                                            if (object != null && object instanceof Integer) {
                                            }
                                        }
                                    });
                                }
                            }
                            break;
                            case 2: {
                                if (isSelected) {
                                    AlertDialogActionUtils.showAlertDialogSingleChoiceOnly(ActivityCreateMacroPage.getInstance(), "Select Action", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Location Service Off", "High Accuracy", "Battery Saving", "Device Only"))), new AlertDialogActionUtils.okCancelListener() {
                                        @Override
                                        public void onOkClick() {

                                        }

                                        @Override
                                        public void onCancelClick() {

                                        }

                                        @Override
                                        public void onItemClick(Object object) {
                                            if (object != null && object instanceof Integer) {
                                            }
                                        }
                                    });
                                }
                            }
                            break;
                            case 3: {
                                if (isSelected) {
                                    AlertDialogActionUtils.showAlertDialogSingleChoiceOnly(ActivityCreateMacroPage.getInstance(), "Select Action", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("5 Seconds","10 Seconds","30 Seconds","1 Minutes","2 Minutes","5 Minutes","10 Minutes","15 Minutes","20 Minutes","30 Minutes","45 Minutes","60 Minutes"))), new AlertDialogActionUtils.okCancelListener() {
                                        @Override
                                        public void onOkClick() {

                                        }

                                        @Override
                                        public void onCancelClick() {

                                        }

                                        @Override
                                        public void onItemClick(Object object) {
                                            if (object != null && object instanceof Integer) {
                                            }
                                        }
                                    });
                                }
                            }
                            break;
                            case 4: {
                                if(isSelected){
                                    //todo share location
                                }
                            }
                            break;
                        }
                    }
                    break;

                    case "Media": {
                        switch (position) {
                            case 0: {
                            }
                            break;
                            case 1: {
                            }
                            break;
                            case 2: {
                            }
                            break;
                        }
                    }
                    break;

                    case "Messaging": {
                        switch (position) {
                            case 0: {
                            }
                            break;
                            case 1: {
                            }
                            break;
                            case 2: {
                            }
                            break;
                            case 3: {
                            }
                            break;
                        }
                    }
                    break;

                    case "Phone": {
                        switch (position) {
                            case 0: {
                            }
                            break;
                            case 1: {
                            }
                            break;
                            case 2: {
                            }
                            break;
                            case 3: {
                            }
                            break;
                            case 4: {
                            }
                            break;
                            case 5: {
                            }
                            break;
                        }
                    }
                    break;

                    case "Screen": {
                        switch (position) {
                            case 0: {
                            }
                            break;
                            case 1: {
                            }
                            break;
                            case 2: {
                            }
                            break;
                            case 3: {
                            }
                            break;
                            case 4: {
                            }
                            break;
                            case 5: {
                            }
                            break;
                        }
                    }
                    break;

                    case "Volume": {
                        switch (position) {
                            case 0: {
                            }
                            break;
                            case 1: {
                            }
                            break;
                            case 2: {
                            }
                            break;
                            case 3: {
                            }
                            break;
                            case 4: {
                            }
                            break;
                        }
                    }
                    break;

                    default: {

                    }

                }
            }

            @Override
            public void onConstraintMenuClicked(boolean isSelected, int position) {

                switch (eventName.trim()) {
                    case "Battery/Power": {
                        launchBatterPowerConstraintEvent(isSelected, position);
                    }
                    break;

                    case "Connectivity": {

                    }
                    break;

                    case "Date/Time": {

                    }
                    break;

                    case "Device State": {

                    }
                    break;

                    case "Media": {

                    }
                    break;

                    case "Phone": {

                    }
                    break;

                    case "Screen and Speaker": {

                    }
                    break;

                    case "Sensors": {

                    }
                    break;

                    default: {

                    }
                    break;
                }
            }
        });
    }

    private void launchDeviceSettingActionsEvents(boolean isSelected, int position) {
        switch (position) {
            case 0: {
                if (isSelected) {
                    AlertDialogActionUtils.showAlertDialogSingleChoiceOnly(ActivityCreateMacroPage.getInstance(), "Select Action", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Auto Rotate On", "Auto Rotate Off", "Toggle"))), new AlertDialogActionUtils.okCancelListener() {
                        @Override
                        public void onOkClick() {

                        }

                        @Override
                        public void onCancelClick() {

                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                            }
                        }
                    });
                }
            }
            break;
            case 1: {
                if (isSelected) {
                    AlertDialogActionUtils.showAlertDialogSingleChoiceOnly(ActivityCreateMacroPage.getInstance(), "Select Action", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Battery Saver On", "Battery Saver Off", "Toggle"))), new AlertDialogActionUtils.okCancelListener() {
                        @Override
                        public void onOkClick() {

                        }

                        @Override
                        public void onCancelClick() {

                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                            }
                        }
                    });
                }
            }
            break;
        }
    }

    private void launchDeviceActionsEvents(boolean isSelected, int position) {
        switch (position) {
            case 0: {
                if (isSelected) {
                    //Launch Home
                }
            }
            break;
            case 1: {
                if (isSelected) {
                }
                //Press Back // Accessibility feature
            }
            break;
            case 2: {
                AlertDialogActionUtils.showAlertDialogSingleChoiceOnly(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Reboot", "Power Off", "Soft Reboot", "Power Off (Alternative)"))), new AlertDialogActionUtils.okCancelListener() {
                    @Override
                    public void onOkClick() {

                    }

                    @Override
                    public void onCancelClick() {

                    }

                    @Override
                    public void onItemClick(Object object) {
                        if (object != null && object instanceof Integer) {
                            //save here...
                        }
                    }
                });
            }
            break;
            case 3: {
                if (isSelected) {
                    AlertDialogActionUtils.showSpeechTestDialog(ActivityCreateMacroPage.getInstance(), "Speak Test");
                }
            }
            break;
            case 4: {
                AlertDialogActionUtils.showAlertDialogSingleChoiceOnly(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Blip", "Short Buzz", "Long Buzz", "Rapid", "Slow", "Increasing", "Constant", "Decreasing", "Final Fantasy", "Star Wars"))), new AlertDialogActionUtils.okCancelListener() {
                    @Override
                    public void onOkClick() {

                    }

                    @Override
                    public void onCancelClick() {

                    }

                    @Override
                    public void onItemClick(Object object) {
                        if (object != null && object instanceof Integer) {
                            //save here...
                        }
                    }
                });
            }
            break;
        }
    }

    private void launchDateTimeActionEvents(boolean isSelected, int position) {
        switch (position) {
            case 0: {
                if (isSelected) {
                    AlertDialogActionUtils.showAlertDialogSingleChoiceOnly(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Set Alarm", "Disable Alarm", "Dismiss Active Alarm"))), new AlertDialogActionUtils.okCancelListener() {
                        @Override
                        public void onOkClick() {

                        }

                        @Override
                        public void onCancelClick() {

                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                switch ((Integer) object) {
                                    case 0: {
                                    }
                                    break;
                                    case 1: {
                                    }
                                    break;
                                    case 2: {
                                    }
                                    break;
                                }
                            }
                        }
                    });
                }
            }
            break;
            case 1: {
                if (isSelected) {
                    AlertDialogActionUtils.showAlertDialogSingleChoiceOnly(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("24 Hour", "12 Hour"))), new AlertDialogActionUtils.okCancelListener() {
                        @Override
                        public void onOkClick() {

                        }

                        @Override
                        public void onCancelClick() {

                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                //Save Item...
                            }
                        }
                    });
                }
            }
            break;
            case 2: {
                if (isSelected) {
                    Toast.makeText(getActivity(), "Stop Watch", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }

    private void launchConnectivityActionEvents(boolean isSelected, int position) {
        switch (position) {
            case 0: {
                if (isSelected) {
                    AlertDialogActionUtils.showAlertDialogSingleChoiceOnly(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Airplane Mode On", "Airplane Mode Off", "Airplane Mode Toggle"))), new AlertDialogActionUtils.okCancelListener() {
                        @Override
                        public void onOkClick() {

                        }

                        @Override
                        public void onCancelClick() {

                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                //Save Item...
                            }
                        }
                    });
                }
            }
            break;
            case 1: {
                if (isSelected) {
                    AlertDialogActionUtils.showAlertDialogSingleChoiceOnly(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Auto Sync On", "Auto Sync Off", "Auto Sync Toggle"))), new AlertDialogActionUtils.okCancelListener() {
                        @Override
                        public void onOkClick() {

                        }

                        @Override
                        public void onCancelClick() {

                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                //Save Item...
                            }
                        }
                    });
                }
            }
            break;
            case 2: {
                if (isSelected) {
                    AlertDialogActionUtils.showAlertDialogSingleChoiceOnly(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Enable Bluetooth", "Disable Bluetooth", "Toggle Bluetooth", "Connect Audio Device", "Disconnect Audio Device"))), new AlertDialogActionUtils.okCancelListener() {
                        @Override
                        public void onOkClick() {

                        }

                        @Override
                        public void onCancelClick() {

                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                //Save Item...
                                //on Connect Disconnect on / off bluetooth
                            }
                        }
                    });
                }
            }
            break;
            case 3: {
                if (isSelected) {
                    AlertDialogActionUtils.showAlertDialogSingleChoiceOnly(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Mobile Data On", "Mobile Data Off", "Mobile Data Toggle"))), new AlertDialogActionUtils.okCancelListener() {
                        @Override
                        public void onOkClick() {

                        }

                        @Override
                        public void onCancelClick() {

                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                //Save Item...
                            }
                        }
                    });
                }
            }
            break;
        }
    }


    /*Trigger Events Start*/

    private void launchBatteryPowerTriggerEvents(boolean isSelected, int position) {
        switch (position) {
            case 0: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_1, new ArrayList<>(Arrays.asList("Increase/Decrease", "Any change"))), new AlertDialogTriggerUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogTriggerUtils.dismissDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                switch (selectedItem) {
                                    case 0: {
                                        AlertDialogTriggerUtils.showBatteryLevelTriggerDialog(ActivityCreateMacroPage.getInstance(), "Increases/Decreases To", 1);
                                    }
                                    break;

                                    case 1: {
                                        EventManagementUtil.addTriggerEvent(ActivityCreateMacroPage.getInstance(), 1, new EventValue(BatteryLevelTrigger.BATTERY_LEVEL_ANY, null));
                                    }
                                    break;
                                }
                            }
                        }
                    });
                }
            }
            break;

            case 1: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Enable", "Disable"))), new AlertDialogTriggerUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogTriggerUtils.dismissDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                switch (selectedItem) {
                                    case 0: {
                                        //
                                    }
                                    break;

                                    case 1: {
                                        //
                                    }
                                    break;
                                }
                            }
                        }
                    });
                }
            }
            break;

            case 2: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("3", "4", "5"))), new AlertDialogTriggerUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogTriggerUtils.dismissDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                switch (selectedItem) {
                                    case 0: {
                                        //
                                    }
                                    break;

                                    case 1: {
                                        //
                                    }
                                    break;

                                    case 2: {
                                        //
                                    }
                                    break;
                                }
                            }
                        }
                    });
                }
            }
            break;

            case 3: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Power Connected", "Power Disconnected"))), new AlertDialogTriggerUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogTriggerUtils.dismissDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                switch (selectedItem) {
                                    case 0: {
                                        //
                                    }
                                    break;

                                    case 1: {
                                        //
                                    }
                                    break;
                                }
                            }
                        }
                    });
                }
            }
            break;
        }
    }

    private void launchCallSmsTriggerEvents(boolean isSelected, int position) {
        switch (position) {
            case 0: {
                if (isSelected) {
                    new PermissionUtil(ActivityCreateMacroPage.getInstance())
                            .withListener(new PermissionUtil.setListener() {
                                @Override
                                public void onAllGranted() {
                                    loadContactList(0);
                                }

                                @Override
                                public void onAllDenied() {

                                }

                                @Override
                                public void onAskAgain(ArrayList<String> rationalePermissions) {

                                }
                            })
                            .withPermission(Manifest.permission.READ_CALL_LOG,
                                    Manifest.permission.CALL_PHONE,
                                    Manifest.permission.PROCESS_OUTGOING_CALLS,
                                    Manifest.permission.READ_CONTACTS);
                }
            }
            break;

            case 1: {
                if (isSelected) {
                    new PermissionUtil(ActivityCreateMacroPage.getInstance())
                            .withListener(new PermissionUtil.setListener() {
                                @Override
                                public void onAllGranted() {
                                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Select Contact(s)", "Select Group(s)", "Select Number"))), new AlertDialogTriggerUtils.okListener() {
                                        @Override
                                        public void onOkClick() {
                                            AlertDialogTriggerUtils.dismissDialog();
                                        }

                                        @Override
                                        public void onItemClick(Object object) {
                                            if (object != null && object instanceof Integer) {
                                                int selectedItem = (Integer) object;
                                                AlertDialogTriggerUtils.dismissDialog();
                                                switch (selectedItem) {
                                                    case 0: {
                                                        loadContactList(4);
                                                    }
                                                    break;

                                                    case 1: {
                                                        //select group
                                                        AlertDialogTriggerUtils.showMultiChoiceDialog(ActivityCreateMacroPage.getInstance(), "Call Active", Utility.getContactGroups(ActivityCreateMacroPage.getInstance()), new AlertDialogTriggerUtils.okListener() {
                                                            @Override
                                                            public void onOkClick() {
                                                                AlertDialogTriggerUtils.dismissDialog();
                                                            }

                                                            @Override
                                                            public void onItemClick(Object object) {
                                                                if (object != null) {
                                                                    Log.e("Android->", "onItemClick: " + object.toString());
                                                                }
                                                            }
                                                        });
                                                    }
                                                    break;

                                                    case 2: {
                                                        //select number
                                                        //todo need discussion about select number
                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void onAllDenied() {

                                }

                                @Override
                                public void onAskAgain(ArrayList<String> rationalePermissions) {

                                }
                            })
                            .withPermission(Manifest.permission.READ_CALL_LOG,
                                    Manifest.permission.CALL_PHONE,
                                    Manifest.permission.PROCESS_OUTGOING_CALLS,
                                    Manifest.permission.READ_CONTACTS);
                }

            }
            break;

            case 2: {
                new PermissionUtil(ActivityCreateMacroPage.getInstance())
                        .withListener(new PermissionUtil.setListener() {
                            @Override
                            public void onAllGranted() {
                                AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Select Contact(s)", "Select Group(s)", "Select Number"))), new AlertDialogTriggerUtils.okListener() {
                                    @Override
                                    public void onOkClick() {
                                        AlertDialogTriggerUtils.dismissDialog();
                                    }

                                    @Override
                                    public void onItemClick(Object object) {
                                        if (object != null && object instanceof Integer) {
                                            int selectedItem = (Integer) object;
                                            switch (selectedItem) {
                                                case 0: {
                                                    loadContactList(5);
                                                }
                                                break;

                                                case 1: {
                                                    AlertDialogTriggerUtils.showMultiChoiceDialog(ActivityCreateMacroPage.getInstance(), "Call Active", Utility.getContactGroups(ActivityCreateMacroPage.getInstance()), new AlertDialogTriggerUtils.okListener() {
                                                        @Override
                                                        public void onOkClick() {
                                                            AlertDialogTriggerUtils.dismissDialog();
                                                        }

                                                        @Override
                                                        public void onItemClick(Object object) {
                                                            if (object != null) {
                                                                Log.e("Android->", "onItemClick: " + object.toString());
                                                            }
                                                        }
                                                    });
                                                }
                                                break;

                                                case 2: {
                                                    //select number
                                                    //todo need discussion about select number
                                                }
                                                break;
                                            }
                                        }
                                    }
                                });
                            }

                            @Override
                            public void onAllDenied() {

                            }

                            @Override
                            public void onAskAgain(ArrayList<String> rationalePermissions) {

                            }
                        })
                        .withPermission(Manifest.permission.READ_CALL_LOG,
                                Manifest.permission.CALL_PHONE,
                                Manifest.permission.PROCESS_OUTGOING_CALLS,
                                Manifest.permission.READ_CONTACTS);

            }
            break;

            case 3: {
                new PermissionUtil(ActivityCreateMacroPage.getInstance())
                        .withListener(new PermissionUtil.setListener() {
                            @Override
                            public void onAllGranted() {
                                loadContactList(3);
                            }

                            @Override
                            public void onAllDenied() {

                            }

                            @Override
                            public void onAskAgain(ArrayList<String> rationalePermissions) {

                            }
                        })
                        .withPermission(Manifest.permission.READ_CALL_LOG,
                                Manifest.permission.CALL_PHONE,
                                Manifest.permission.PROCESS_OUTGOING_CALLS,
                                Manifest.permission.READ_CONTACTS);

            }
            break;

            case 4: {
                new PermissionUtil(ActivityCreateMacroPage.getInstance())
                        .withListener(new PermissionUtil.setListener() {
                            @Override
                            public void onAllGranted() {
                                AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Select Contact(s)", "Select Group(s)", "Select Number"))), new AlertDialogTriggerUtils.okListener() {
                                    @Override
                                    public void onOkClick() {
                                        AlertDialogTriggerUtils.dismissDialog();
                                    }

                                    @Override
                                    public void onItemClick(Object object) {
                                        if (object != null && object instanceof Integer) {
                                            int selectedItem = (Integer) object;
                                            switch (selectedItem) {
                                                case 0: {
                                                    loadContactList(6);
                                                }
                                                break;

                                                case 1: {
                                                    AlertDialogTriggerUtils.showMultiChoiceDialog(ActivityCreateMacroPage.getInstance(), "Call Active", Utility.getContactGroups(ActivityCreateMacroPage.getInstance()), new AlertDialogTriggerUtils.okListener() {
                                                        @Override
                                                        public void onOkClick() {
                                                            AlertDialogTriggerUtils.dismissDialog();
                                                        }

                                                        @Override
                                                        public void onItemClick(Object object) {
                                                            if (object != null) {
                                                                Log.e("Android->", "onItemClick: " + object.toString());
                                                            }
                                                        }
                                                    });
                                                }
                                                break;

                                                case 2: {
                                                    //select number
                                                    //todo need discussion about select number
                                                }
                                                break;
                                            }
                                        }
                                    }
                                });
                            }

                            @Override
                            public void onAllDenied() {

                            }

                            @Override
                            public void onAskAgain(ArrayList<String> rationalePermissions) {

                            }
                        })
                        .withPermission(Manifest.permission.READ_CALL_LOG,
                                Manifest.permission.CALL_PHONE,
                                Manifest.permission.PROCESS_OUTGOING_CALLS,
                                Manifest.permission.READ_CONTACTS);
            }
            break;

            case 5: {
                //todo load input dialog
                AlertDialogTriggerUtils.showDialPhoneDialog(ActivityCreateMacroPage.getInstance(), "Dial Phone Number");
            }
            break;

            case 6: {
                new PermissionUtil(ActivityCreateMacroPage.getInstance())
                        .withListener(new PermissionUtil.setListener() {
                            @Override
                            public void onAllGranted() {
                                AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Select Contact(s)", "Select Group(s)", "Select Number"))), new AlertDialogTriggerUtils.okListener() {
                                    @Override
                                    public void onOkClick() {
                                        AlertDialogTriggerUtils.dismissDialog();
                                    }

                                    @Override
                                    public void onItemClick(Object object) {
                                        if (object != null && object instanceof Integer) {
                                            int selectedItem = (Integer) object;
                                            switch (selectedItem) {
                                                case 0: {
                                                    loadContactList(7);
                                                }
                                                break;

                                                case 1: {
                                                    AlertDialogTriggerUtils.showMultiChoiceDialog(ActivityCreateMacroPage.getInstance(), "Call Active", Utility.getContactGroups(ActivityCreateMacroPage.getInstance()), new AlertDialogTriggerUtils.okListener() {
                                                        @Override
                                                        public void onOkClick() {
                                                            AlertDialogTriggerUtils.dismissDialog();
                                                        }

                                                        @Override
                                                        public void onItemClick(Object object) {
                                                            if (object != null) {
                                                                Log.e("Android->", "onItemClick: " + object.toString());
                                                            }
                                                        }
                                                    });
                                                }
                                                break;

                                                case 2: {
                                                    //select number
                                                    //todo need discussion about select number
                                                }
                                                break;
                                            }
                                        }
                                    }
                                });
                            }

                            @Override
                            public void onAllDenied() {

                            }

                            @Override
                            public void onAskAgain(ArrayList<String> rationalePermissions) {

                            }
                        })
                        .withPermission(Manifest.permission.SEND_SMS,
                                Manifest.permission.RECEIVE_SMS);
            }
            break;

            case 7: {
                new PermissionUtil(ActivityCreateMacroPage.getInstance())
                        .withListener(new PermissionUtil.setListener() {
                            @Override
                            public void onAllGranted() {
                                loadContactList(7);
                            }

                            @Override
                            public void onAllDenied() {

                            }

                            @Override
                            public void onAskAgain(ArrayList<String> rationalePermissions) {

                            }
                        })
                        .withPermission(Manifest.permission.READ_CALL_LOG,
                                Manifest.permission.CALL_PHONE,
                                Manifest.permission.PROCESS_OUTGOING_CALLS,
                                Manifest.permission.READ_CONTACTS);
            }
            break;
        }
    }

    private void launchConnectivityTriggerEvents(boolean isSelected, int position) {
        switch (position) {
            case 0: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Bluetooth Enable", "Bluetooth Disable", "Device Connected", "Device Disconnected"))), new AlertDialogTriggerUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogTriggerUtils.dismissDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                switch (selectedItem) {
                                    case 0: {
                                        //
                                    }
                                    break;

                                    case 1: {
                                        //
                                    }
                                    break;
                                    case 2: {
                                        //
                                    }
                                    break;
                                    case 3: {
                                        //
                                    }
                                    break;
                                }
                            }
                        }
                    });
                }
            }
            break;

            case 1: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Data Available", "No Data Connection"))), new AlertDialogTriggerUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogTriggerUtils.dismissDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                switch (selectedItem) {
                                    case 0: {
                                        //
                                    }
                                    break;

                                    case 1: {
                                        //
                                    }
                                    break;

                                }
                            }
                        }
                    });
                }
            }
            break;

            case 2: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Headphone Inserted", "Headphone Removed"))), new AlertDialogTriggerUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogTriggerUtils.dismissDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                switch (selectedItem) {
                                    case 0: {
                                        //
                                    }
                                    break;

                                    case 1: {
                                        //
                                    }
                                    break;

                                }
                            }
                        }
                    });
                }
            }
            break;

            case 3: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Wifi Enabled", "Wifi Disabled", "Connect to Network", "Disconnect from Network"))), new AlertDialogTriggerUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogTriggerUtils.dismissDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                switch (selectedItem) {
                                    case 0: {
                                        //
                                    }
                                    break;

                                    case 1: {
                                        //
                                    }
                                    break;

                                    case 2: {
                                        //
                                    }
                                    break;

                                    case 3: {
                                        //
                                    }
                                    break;

                                }
                            }
                        }
                    });
                }
            }
            break;
        }
    }

    private void launchDeviceEventsTriggers(boolean isSelected, int position) {
        switch (position) {
            case 0: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Enable", "Disable"))), new AlertDialogTriggerUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogTriggerUtils.dismissDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                switch (selectedItem) {
                                    case 0: {
                                        //enable
                                    }
                                    break;

                                    case 1: {
                                        //disable
                                    }
                                    break;
                                }
                            }
                        }
                    });
                }
            }
            break;

            case 1: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Enable", "Disable"))), new AlertDialogTriggerUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogTriggerUtils.dismissDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                switch (selectedItem) {
                                    case 0: {
                                        //enable
                                    }
                                    break;

                                    case 1: {
                                        //disable
                                    }
                                    break;
                                }
                            }
                        }
                    });
                }
            }
            break;

            case 2: {
                if (isSelected) {
                    Toast.makeText(getActivity(), "Device boot...", Toast.LENGTH_SHORT).show();
                }
            }
            break;

            case 3: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("GPS  Enable", "GPS Disable"))), new AlertDialogTriggerUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogTriggerUtils.dismissDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                switch (selectedItem) {
                                    case 0: {
                                        //enable
                                    }
                                    break;

                                    case 1: {
                                        //disable
                                    }
                                    break;
                                }
                            }
                        }
                    });
                }
            }
            break;

            case 4: {
                if (isSelected) {
                    Toast.makeText(getActivity(), "Failed Login Attempt...", Toast.LENGTH_SHORT).show();
                }
            }
            break;

            case 5: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Started", "Stopped"))), new AlertDialogTriggerUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogTriggerUtils.dismissDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                switch (selectedItem) {
                                    case 0: {
                                        //enable
                                    }
                                    break;

                                    case 1: {
                                        //disable
                                    }
                                    break;
                                }
                            }
                        }
                    });
                }
            }
            break;

            case 6: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Notification Received", "Notification Cleared"))), new AlertDialogTriggerUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogTriggerUtils.dismissDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                switch (selectedItem) {
                                    case 0: {
                                        //enable
                                    }
                                    break;

                                    case 1: {
                                        //disable
                                    }
                                    break;
                                }
                            }
                        }
                    });
                }
            }
            break;

            case 7: {
                if (isSelected) {
                    Toast.makeText(getActivity(), "Screen Unlocked...", Toast.LENGTH_SHORT).show();
                }
            }
            break;

            case 8: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Screen on", "Screen off"))), new AlertDialogTriggerUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogTriggerUtils.dismissDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                switch (selectedItem) {
                                    case 0: {
                                        //enable
                                    }
                                    break;

                                    case 1: {
                                        //disable
                                    }
                                    break;
                                }
                            }
                        }
                    });
                }
            }
            break;
        }
    }

    private void loadDateTimeTriggerEvents(boolean isSelected, int position) {
        switch (position) {
            case 0: {
                //Calendar Events
                Toast.makeText(getActivity(), "Calendar Event", Toast.LENGTH_SHORT).show();
            }
            break;

            case 1: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Day of month", "Day of week"))), new AlertDialogTriggerUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogTriggerUtils.dismissDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                AlertDialogTriggerUtils.showDayOfWeekMonthTriggerDialog(ActivityCreateMacroPage.getInstance(), selectedItem == 0 ? "Day Of Week" : "Day Of Month", String.valueOf(selectedItem));
                            }
                        }
                    });
                }
            }
            break;

            case 2: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showDayTimeTriggerDialog(ActivityCreateMacroPage.getInstance(), "Day/Time Trigger");
                }
            }
            break;

            case 3: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showRegularIntervalTriggerDialog(ActivityCreateMacroPage.getInstance(), "Regular Interval Trigger");
                }
            }
            break;

            case 4: {
                Toast.makeText(getActivity(), "Stop Watch", Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }

    /*Trigger Events End*/

    /******************************************************************************************************************************************************************/
    /*Action Event Start*/
    private void launchCameraPhotoActionEvents(int position, boolean isSelected) {
        switch (position) {
            case 0: {
                if (isSelected) {
                    AlertDialogActionUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_1, new ArrayList<>(Arrays.asList("Twitter", "Email", "Via Intent"))), new AlertDialogActionUtils.okCancelListener() {
                        @Override
                        public void onOkClick() {

                        }

                        @Override
                        public void onCancelClick() {

                        }

                        @Override
                        public void onItemClick(Object object) {

                        }
                    });
                }
            }
            break;
            case 1: {
                if (isSelected) {
                    AlertDialogActionUtils.showAlertDialogSingleChoiceOnly(ActivityCreateMacroPage.getInstance(), "Take Picture", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Front Facing", "Rear Facing"))), new AlertDialogActionUtils.okCancelListener() {
                        @Override
                        public void onOkClick() {

                        }

                        @Override
                        public void onCancelClick() {

                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {

                                switch ((Integer) object) {

                                    case 0: {//Front Facing
                                        AlertDialogActionUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Display Icon ? ", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Show Camera Icon", "Hide Camera Icon"))), new AlertDialogActionUtils.okCancelListener() {
                                            @Override
                                            public void onOkClick() {

                                            }

                                            @Override
                                            public void onCancelClick() {

                                            }

                                            @Override
                                            public void onItemClick(Object object) {
                                                //todo show Alert Dialog to save picture
                                            }
                                        });
                                    }
                                    break;

                                    case 1: {//Back Facing
                                        AlertDialogActionUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Flash Mode", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Flash On", "Flash Off", "Flash Auto"))), new AlertDialogActionUtils.okCancelListener() {
                                            @Override
                                            public void onOkClick() {

                                            }

                                            @Override
                                            public void onCancelClick() {

                                            }

                                            @Override
                                            public void onItemClick(Object object) {

                                                AlertDialogActionUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Display Icon ? ", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Show Camera Icon", "Hide Camera Icon"))), new AlertDialogActionUtils.okCancelListener() {
                                                    @Override
                                                    public void onOkClick() {

                                                    }

                                                    @Override
                                                    public void onCancelClick() {

                                                    }

                                                    @Override
                                                    public void onItemClick(Object object) {
                                                        //todo show Alert Dialog to save picture
                                                    }
                                                });
                                            }
                                        });
                                    }
                                    break;
                                }
                            }
                        }
                    });
                }
            }
            break;
            case 2: {
                if (isSelected) {
                    AlertDialogActionUtils.showAlertDialogSingleChoiceOnly(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Save to Device", "Send via Email", "Share Via Intent"))), new AlertDialogActionUtils.okCancelListener() {
                        @Override
                        public void onOkClick() {

                        }

                        @Override
                        public void onCancelClick() {

                        }

                        @Override
                        public void onItemClick(Object object) {
                            int selectedPosition = 0;
                            if (object != null && object instanceof Integer) {
                                if ((Integer) object == 1) {
                                    selectedPosition = 1;
                                }
                            }
                            int finalSelectedPosition = selectedPosition;
                            AlertDialogActionUtils.showAlertDialogSingleChoiceOnly(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Mechanism 1", "Mechanism 2", "Mechanism 3", "Mechanism 4"))), new AlertDialogActionUtils.okCancelListener() {
                                @Override
                                public void onOkClick() {

                                }

                                @Override
                                public void onCancelClick() {

                                }

                                @Override
                                public void onItemClick(Object object) {
                                    if (finalSelectedPosition == 1) {//LoadEmailDialog
                                        AlertDialogActionUtils.showInputAlertDialog(ActivityCreateMacroPage.getInstance(), "Send Via Email", new AlertDialogActionUtils.okListener() {
                                            @Override
                                            public void onOkClick() {

                                            }

                                            @Override
                                            public void onItemClick(Object object) {

                                            }
                                        });
                                    }
                                }
                            });
                        }
                    });
                }
            }
            break;
        }
    }

    /*Action Event End*/
    private void launchBatterPowerConstraintEvent(boolean isSelected, int position) {
        switch (position) {
            case 0: {
                if (isSelected) {
                    AlertDialogConstraintsUtils.showBatteryLevelConstraintDialog(ActivityCreateMacroPage.getInstance(), "Increases/Decreases To");
                }
            }
            break;

            case 1: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Enable", "Disable"))), new AlertDialogTriggerUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogTriggerUtils.dismissDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                switch (selectedItem) {
                                    case 0: {
                                        //
                                    }
                                    break;

                                    case 1: {
                                        //
                                    }
                                    break;
                                }
                            }
                        }
                    });
                }
            }
            break;

            case 2: {
                if (isSelected) {
                    AlertDialogTriggerUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Power Connected", "Power Disconnected"))), new AlertDialogTriggerUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogTriggerUtils.dismissDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                switch (selectedItem) {
                                    case 0: {
                                        //
                                    }
                                    break;

                                    case 1: {
                                        //
                                    }
                                    break;
                                }
                            }
                        }
                    });
                }
            }
            break;

        }
    }

    private void loadContactList(int triggerCallType) {
        //todo check triggerCallType while implementing....
        //Note check triggerCallType
        HashMap<String, String> contactList = Utility.getContactList(ActivityCreateMacroPage.getInstance());
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("[Any Contact]");
        arrayList.add("[Any Number]");
        arrayList.add("[Non Contact]");
        arrayList.add("[Unknown Caller]");
        for (Map.Entry<String, String> contact : contactList.entrySet()) {
            arrayList.add(contact.getKey());
        }
        AlertDialogTriggerUtils.showMultiChoiceDialog(ActivityCreateMacroPage.getInstance(), "Call Active", arrayList, new AlertDialogTriggerUtils.okListener() {
            @Override
            public void onOkClick() {
                AlertDialogTriggerUtils.dismissDialog();
            }

            @Override
            public void onItemClick(Object object) {
                if (object != null) {
                    Log.e("Android->", "onItemClick: " + object.toString());
                }
            }
        });
    }
}