package com.smileparser.automator.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smileparser.automator.R;
import com.smileparser.automator.activity.ActivityCreateMacroPage;
import com.smileparser.automator.adapter.AdapterMain;

import java.util.ArrayList;


public class FragmentMainPage extends Fragment {

    RecyclerView rv_create;
    ArrayList<String> img_name = new ArrayList<>();
    ArrayList<String> img_label = new ArrayList<>();
    String[] image_array;
    String[] strings_array;
    AdapterMain adapterMain;
    View rootView;
    String page = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_load_view, container, false);
        assignViews();

        adapterMain.setOnTriggerEvent(position -> {
            String sName = img_label.get(position);
            String sImg = img_name.get(position);
            Bundle arguments = new Bundle();
            if (sName != null) {
                switch (page) {
                    case "1": {//Triggers
                        if (!sName.equalsIgnoreCase("AppsMount/Specific")) {
                            arguments.putString("Key", sName);
                            arguments.putString("Key_img", sImg);
                        } else {
                            arguments.putString("Key", "");
                            arguments.putString("Key_img", "");
                        }
                    }
                    break;

                    case "2": {//Actions
                        if (!sName.equalsIgnoreCase("AppsMount/Specific")) {
                            arguments.putString("Key", sName);
                            arguments.putString("Key_img", sImg);
                        }
                    }
                    break;

                    case "3": { //Constraints
                        if (!sName.equalsIgnoreCase("Bettery")) {
                            arguments.putString("Key", sName);
                            arguments.putString("Key_img", sImg);
                        }
                    }
                    break;
                }
            }
            arguments.putString("callingPage", page);

            ActivityCreateMacroPage.getInstance().loadInnerFragment(arguments, new FragmentChildPage(), page.equals("1") ? "Create_Triggers" : page.equals("2") ? "Create_Actions" : "Create_Constraints");

            /*ActivityCreateMacroPage.getInstance().loadInnerFragmentByType(arguments, page, page.equals("1") ? "Create_Triggers" : page.equals("2") ? "Create_Actions" : "Create_Constraints");*/
        });

        return rootView;
    }

    private void assignViews() {
        rv_create = rootView.findViewById(R.id.rv_create);

        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.getString("callingPage") != null) {
                page = bundle.getString("callingPage");
                if (page != null) {

                    switch (page) {
                        case "1": { //Triggers
                            image_array = getResources().getString(R.string.trigger_images).split(",");
                            strings_array = getResources().getString(R.string.trigger_labels).split(",");
                        }
                        break;
                        case "2": { //Actions
                            image_array = getResources().getString(R.string.action_images).split(",");
                            strings_array = getResources().getString(R.string.actions_labels).split(",");
                        }
                        break;
                        case "3": { //Constraints
                            image_array = getResources().getString(R.string.constraint_images).split(",");
                            strings_array = getResources().getString(R.string.constraint_labels).split(",");
                        }
                        break;
                    }
                }
            }
        }
        img_name.clear();
        img_label.clear();

        for (int i = 0; i < image_array.length; i++) {
            img_name.add(image_array[i]);
            img_label.add(strings_array[i]);
        }

        rv_create.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapterMain = new AdapterMain(getActivity(), img_name, img_label, page);
        rv_create.setAdapter(adapterMain);
    }
}