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

import com.smileparser.automator.R;
import com.smileparser.automator.activity.ActivityCreateMacroPage;
import com.smileparser.automator.adapter.AdapterChild;
import com.smileparser.automator.triggeractionmanager.BatteryLevelTrigger;
import com.smileparser.automator.triggeractionmanager.EventManagementUtil;
import com.smileparser.automator.triggeractionmanager.EventValue;
import com.smileparser.automator.utils.AlertDialogUtils;
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

                Log.e("Android->>", "assignViews: " + eventName);
                Log.e("Android->>", "assignViews: " + page);

                txtHeader.setText(eventName);

                if (page != null && eventName != null) {
                    switch (page) {
                        case "1": { //Inner Triggers
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
                        break;

                        case "2": { //Inner Actions
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
                        break;

                        case "3": { //Inner Constraints
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

                    case "Device Events": {
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

                            case 9: {

                            }
                            break;
                        }
                    }
                    break;

                    case "Location": {
                        switch (position) {
                            case 0: {
                                AlertDialogUtils.showLocationChangeDialog(ActivityCreateMacroPage.getInstance(), "Select Option");
                            }
                            break;
                        }
                    }
                    break;

                    case "AppsMount/Specific": {
                    }
                    break;

                    default:
                        break;

                }
            }

            @Override
            public void onActionMenuClicked(boolean isSelected, int position) {

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

    private void launchConnectivityTriggerEvents(boolean isSelected, int position) {
        switch (position) {
            case 0: {
                if (isSelected) {
                    AlertDialogUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Bluetooth Enable", "Bluetooth Disable", "Device Connected", "Device Disconnected"))), new AlertDialogUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogUtils.dismisDialog();
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
                    AlertDialogUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Data Available", "No Data Connection"))), new AlertDialogUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogUtils.dismisDialog();
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
                if(isSelected){
                    AlertDialogUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Headphone Inserted", "Headphone Removed"))), new AlertDialogUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogUtils.dismisDialog();
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
                if(isSelected){
                    AlertDialogUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Wifi Enabled", "Wifi Disabled", "Connect to Network", "Disconnect from Network"))), new AlertDialogUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogUtils.dismisDialog();
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
                                    AlertDialogUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Select Contact(s)", "Select Group(s)", "Select Number"))), new AlertDialogUtils.okListener() {
                                        @Override
                                        public void onOkClick() {
                                            AlertDialogUtils.dismisDialog();
                                        }

                                        @Override
                                        public void onItemClick(Object object) {
                                            if (object != null && object instanceof Integer) {
                                                int selectedItem = (Integer) object;
                                                AlertDialogUtils.dismisDialog();
                                                switch (selectedItem) {
                                                    case 0: {
                                                        loadContactList(4);
                                                    }
                                                    break;

                                                    case 1: {
                                                        //select group
                                                        AlertDialogUtils.showMultiChoiceDialog(ActivityCreateMacroPage.getInstance(), "Call Active", Utility.getContactGroups(ActivityCreateMacroPage.getInstance()), new AlertDialogUtils.okListener() {
                                                            @Override
                                                            public void onOkClick() {
                                                                AlertDialogUtils.dismisDialog();
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
                                AlertDialogUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Select Contact(s)", "Select Group(s)", "Select Number"))), new AlertDialogUtils.okListener() {
                                    @Override
                                    public void onOkClick() {
                                        AlertDialogUtils.dismisDialog();
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
                                                    AlertDialogUtils.showMultiChoiceDialog(ActivityCreateMacroPage.getInstance(), "Call Active", Utility.getContactGroups(ActivityCreateMacroPage.getInstance()), new AlertDialogUtils.okListener() {
                                                        @Override
                                                        public void onOkClick() {
                                                            AlertDialogUtils.dismisDialog();
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
                                AlertDialogUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Select Contact(s)", "Select Group(s)", "Select Number"))), new AlertDialogUtils.okListener() {
                                    @Override
                                    public void onOkClick() {
                                        AlertDialogUtils.dismisDialog();
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
                                                    AlertDialogUtils.showMultiChoiceDialog(ActivityCreateMacroPage.getInstance(), "Call Active", Utility.getContactGroups(ActivityCreateMacroPage.getInstance()), new AlertDialogUtils.okListener() {
                                                        @Override
                                                        public void onOkClick() {
                                                            AlertDialogUtils.dismisDialog();
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
                AlertDialogUtils.showDialPhoneDialog(ActivityCreateMacroPage.getInstance(), "Dial Phone Number");
            }
            break;

            case 6: {
                new PermissionUtil(ActivityCreateMacroPage.getInstance())
                        .withListener(new PermissionUtil.setListener() {
                            @Override
                            public void onAllGranted() {
                                AlertDialogUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Select Contact(s)", "Select Group(s)", "Select Number"))), new AlertDialogUtils.okListener() {
                                    @Override
                                    public void onOkClick() {
                                        AlertDialogUtils.dismisDialog();
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
                                                    AlertDialogUtils.showMultiChoiceDialog(ActivityCreateMacroPage.getInstance(), "Call Active", Utility.getContactGroups(ActivityCreateMacroPage.getInstance()), new AlertDialogUtils.okListener() {
                                                        @Override
                                                        public void onOkClick() {
                                                            AlertDialogUtils.dismisDialog();
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

    private void launchBatterPowerConstraintEvent(boolean isSelected, int position) {
        switch (position) {
            case 0: {
                if (isSelected) {
                    AlertDialogUtils.showBatteryLevelConstraintDialog(ActivityCreateMacroPage.getInstance(), "Increases/Decreases To");
                }
            }
            break;

            case 1: {
                if (isSelected) {
                    AlertDialogUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Enable", "Disable"))), new AlertDialogUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogUtils.dismisDialog();
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
                    AlertDialogUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Power Connected", "Power Disconnected"))), new AlertDialogUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogUtils.dismisDialog();
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

    private void launchBatteryPowerTriggerEvents(boolean isSelected, int position) {
        switch (position) {
            case 0: {
                if (isSelected) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add("Increase/Decrease");
                    arrayList.add("Any change");
                    AlertDialogUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_1, new ArrayList<>(Arrays.asList("Increase/Decrease", "Any change"))), new AlertDialogUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogUtils.dismisDialog();
                        }

                        @Override
                        public void onItemClick(Object object) {
                            if (object != null && object instanceof Integer) {
                                int selectedItem = (Integer) object;
                                switch (selectedItem) {
                                    case 0: {
                                        AlertDialogUtils.showBatteryLevelTriggerDialog(ActivityCreateMacroPage.getInstance(), "Increases/Decreases To", 1);
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
                    AlertDialogUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Enable", "Disable"))), new AlertDialogUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogUtils.dismisDialog();
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
                    AlertDialogUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("3", "4", "5"))), new AlertDialogUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogUtils.dismisDialog();
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
                    AlertDialogUtils.showAlertDialog(ActivityCreateMacroPage.getInstance(), "Select Option", new ArrayAdapter<>(ActivityCreateMacroPage.getInstance(), android.R.layout.simple_list_item_single_choice, new ArrayList<>(Arrays.asList("Power Connected", "Power Disconnected"))), new AlertDialogUtils.okListener() {
                        @Override
                        public void onOkClick() {
                            AlertDialogUtils.dismisDialog();
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
        AlertDialogUtils.showMultiChoiceDialog(ActivityCreateMacroPage.getInstance(), "Call Active", arrayList, new AlertDialogUtils.okListener() {
            @Override
            public void onOkClick() {
                AlertDialogUtils.dismisDialog();
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