package com.smileparser.automator.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smileparser.automator.R;
import com.smileparser.automator.adapter.createnewadapter;
import com.smileparser.automator.utils.GridAutofitLayoutManager;

import java.util.ArrayList;
import java.util.List;


public class Triggerfrag extends Fragment {
    RecyclerView rv_create;
    List<String> img_name = new ArrayList<>();
    List<String> img_label = new ArrayList<>();

    private GridAutofitLayoutManager GLayout;
    createnewadapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.frage_createnew, container, false );
        rv_create = rootView.findViewById( R.id.rv_create );





        String[] createnew_frgment_img = getResources().getString( R.string.createnew_frgment_img ).split( "," );
        String[] createnew_fragment_data = getResources().getString( R.string.createnew_fragment_data ).split( "," );
        img_name.clear();
        img_label.clear();
        for (int i = 0; i < createnew_frgment_img.length; i++) {
            img_name.add( createnew_frgment_img[i] );
            img_label.add( createnew_fragment_data[i] );
        }
        GLayout = new GridAutofitLayoutManager( getActivity(), 150 );
        rv_create.setHasFixedSize( true );
        rv_create.setLayoutManager( GLayout );

        //Adapter set//
        adapter = new createnewadapter( getActivity(), img_name, img_label, createnewadapterEvent );
        rv_create.setAdapter( adapter );
        return rootView;


    }

    createnewadapter.CreateNewAdapterEvent createnewadapterEvent = new createnewadapter.CreateNewAdapterEvent() {

        @Override
        public void Display(int adapterposition) {
            String sName = img_label.get( adapterposition );
            String sImg = img_name.get( adapterposition );


            Inner_createnew_Fragment frag = new Inner_createnew_Fragment();

            if (sName != null) {
                Bundle arguments = new Bundle();

               if (sName.equalsIgnoreCase( "AppsMount/Specific" )) {
                } else {
                    arguments.putSerializable( "Key", sName );
                    arguments.putSerializable( "Key_img", sImg );
                    frag.setArguments( arguments );

                }
            }


            getFragmentManager().beginTransaction()
                    .replace( R.id.frm_container, frag )
                    .addToBackStack(Triggerfrag.class.getName())
                    .commit();
        }
    };


}
