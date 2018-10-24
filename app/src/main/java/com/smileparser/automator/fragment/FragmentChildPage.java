package com.smileparser.automator.fragment;


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
import android.widget.ImageView;
import android.widget.TextView;

import com.smileparser.automator.R;
import com.smileparser.automator.adapter.AdapterChild;
import com.smileparser.automator.utils.Util;

import java.util.ArrayList;


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
                            imageList = Util.getResource().getString(R.string.trigger_icon_img).split(",");
                            switch (eventName.trim()) {
                                case "Battery/power": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[0], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.beterry_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.bettry_img).split(",");
                                }
                                break;
                                case "Call/SMS": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[1], "drawable", getActivity().getPackageName()));

                                    Log.e("Android->>", "assignViews: " + Util.getResource().getString(R.string.call_sma_data));

                                    actionLabel = Util.getResource().getString(R.string.call_sma_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.call_sms_img).split(",");

                                }
                                break;
                                case "Connectivity": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[2], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.connectivity_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.connectivity_img).split(",");
                                }
                                break;
                                case "Date/Time": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[3], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.date_time_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.date_time_img).split(",");
                                }
                                break;
                                case "Device Events": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[4], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.device_event_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.device_event_img).split(",");
                                }
                                break;
                                case "Location": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[5], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.location_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.location_img).split(",");
                                }
                                break;
                                case "AppsMount/Specific": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[6], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.location_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.date_time_data).split(",");
                                }
                                break;
                            }
                        }
                        break;

                        case "2": { //Inner Actions
                            imageList = Util.getResource().getString(R.string.action_icon_img).split(",");
                            switch (eventName.trim()) {
                                case "Applications": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[0], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.action_application_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.action_application_img).split(",");
                                }
                                break;

                                case "Camera/Photo": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[1], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.action_camera_photo_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.action_camera_photo_img).split(",");
                                }
                                break;

                                case "Connectivity": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[2], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.action_connectivity_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.action_connectivity_img).split(",");
                                }
                                break;

                                case "Date/Time": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[3], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.action_date_time_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.action_datetime_img).split(",");
                                }
                                break;

                                case "Device Actions": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[4], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.action_device_action_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.action_device_action_img).split(",");
                                }
                                break;

                                case "Device Settings": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[5], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.action_device_setting_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.action_device_setting_img).split(",");
                                }
                                break;

                                case "Location": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[6], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.action_location_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.action_location_img).split(",");
                                }
                                break;

                                case "Media": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[7], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.action_media_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.action_media_img).split(",");
                                }
                                break;

                                case "Messaging": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[8], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.action_messaging_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.action_messanging_img).split(",");
                                }
                                break;

                                case "Phone": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[9], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.action_phone_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.action_phone_img).split(",");
                                }
                                break;

                                case "Screen": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[10], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.action_screen_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.action_screen_img).split(",");
                                }
                                break;

                                case "Volume": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[11], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.action_volume_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.action_voume_img).split(",");
                                }
                                break;

                            }
                        }
                        break;

                        case "3": { //Inner Constraints
                            imageList = Util.getResource().getString(R.string.constraint_icon_img).split(",");
                            switch (eventName.trim()) {
                                case "Battery/Power": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[0], "drawable", getActivity().getPackageName()));
                                    actionImage = Util.getResource().getString(R.string.constraint_bettery).split(",");
                                    actionImage = Util.getResource().getString(R.string.constraint_bettrey_img).split(",");
                                }
                                break;

                                case "Connectivity": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[1], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.constraint_connectivity_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.constraint_connectivity_img).split(",");
                                }
                                break;

                                case "Date/Time": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[2], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.constraint_date_time_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.constraint_date_time_img).split(",");
                                }
                                break;

                                case "Device State": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[3], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.constraint_device_state_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.constraint_device_state_img).split(",");
                                }
                                break;

                                case "Media": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[4], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.constraint_media_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.constraint_media_img).split(",");
                                }
                                break;

                                case "Phone": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[5], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.constraint_phone_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.constraint_phone_img).split(",");
                                }
                                break;

                                case "Screen and Speaker": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[6], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.constraint_screen_sensor_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.constraint_screen_sensor_img).split(",");
                                }
                                break;

                                case "Sensors": {
                                    imgIcon.setImageResource(Util.getResource().getIdentifier(imageList[7], "drawable", getActivity().getPackageName()));
                                    actionLabel = Util.getResource().getString(R.string.constraint_sensors_data).split(",");
                                    actionImage = Util.getResource().getString(R.string.constraint_sensors_img).split(",");
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

            }

            @Override
            public void onActionMenuClicked(boolean isSelected, int position) {

            }

            @Override
            public void onConstraintMenuClicked(boolean isSelected, int position) {

            }
        });
    }
}