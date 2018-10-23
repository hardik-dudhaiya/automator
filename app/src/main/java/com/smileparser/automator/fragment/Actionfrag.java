package com.smileparser.automator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smileparser.automator.R;
import com.smileparser.automator.adapter.actionadapter;
import com.smileparser.automator.utils.GridAutofitLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class Actionfrag extends Fragment {
    RecyclerView rv_action;
    List<String> img_name = new ArrayList<>();
    List<String> img_label = new ArrayList<>();


    private GridAutofitLayoutManager GLayout;
    actionadapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.frage_action, container, false );
        rv_action = rootView.findViewById( R.id.rv_action );


        String[] action_fragment_img = getResources().getString( R.string.action_fragment_img ).split( "," );
        String[] action_fragment_data = getResources().getString( R.string.action_fragment_data ).split( "," );

        img_name.clear();
        img_label.clear();

        for (int i = 0; i < action_fragment_img.length; i++) {
            img_name.add( action_fragment_img[i] );
            img_label.add( action_fragment_data[i] );
            }

            GLayout = new GridAutofitLayoutManager( getActivity(), 150 );
        rv_action.setHasFixedSize( true );
        rv_action.setLayoutManager( GLayout );

        //adapter set//
        adapter = new actionadapter( getActivity(), img_name, img_label,actionAdapterEvent );
        rv_action.setAdapter( adapter );
        return rootView;
    }

    actionadapter.ActionAdapterEvent actionAdapterEvent= new actionadapter.ActionAdapterEvent() {
        @Override
        public void Display(int adapterposition) {
            String sName = img_label.get( adapterposition );
            String sImg = img_name.get( adapterposition );
            Inner_action_Fragment frag = new Inner_action_Fragment();



            if (sName != null) {
                Bundle arguments = new Bundle();

                if (sName.equalsIgnoreCase( "AppsMount/specific" )) {
                } else {
                    arguments.putSerializable( "Key", sName );
                    arguments.putSerializable( "Key_img", sImg );
                    frag.setArguments( arguments );

                }
            }


            getFragmentManager().beginTransaction()
                    .replace( R.id.frm_container, frag )
                    .addToBackStack(Actionfrag.class.getName())
                    .commit();
        }
    };


}






