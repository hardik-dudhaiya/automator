package com.smileparser.automator.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smileparser.automator.R;
import com.smileparser.automator.adapter.Inner_constraintadapter;
import com.smileparser.automator.adapter.constraintadapter;
import com.smileparser.automator.adapter.createnewadapter;
import com.smileparser.automator.utils.GridAutofitLayoutManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by varsha on 1/23/2017.
 */

public class Constraintfrag extends Fragment {
    RecyclerView rv_constraint;
    List<String> img_name = new ArrayList<>();
    List<String> img_label = new ArrayList<>();

    private GridAutofitLayoutManager GLayout;
    constraintadapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.frage_constraint, container, false );
        rv_constraint = rootView.findViewById( R.id.rv_constraint );

        String[] constraint_fragment_img = getResources().getString( R.string.constraint_fragment_img ).split( "," );
        String[] constraint_fragment_data = getResources().getString( R.string.constraint_fragment_data ).split( "," );


        img_name.clear();
        img_label.clear();

        for (int i = 0; i < constraint_fragment_img.length; i++) {
            img_name.add( constraint_fragment_img[i] );
            img_label.add( constraint_fragment_data[i] );
        }
        GLayout = new GridAutofitLayoutManager( getActivity(), 150 );
        rv_constraint.setHasFixedSize( true );
        rv_constraint.setLayoutManager( GLayout );

        //Adapter set//
        adapter = new constraintadapter( getActivity(), img_name, img_label, constraintAdapterEvent );
        rv_constraint.setAdapter( adapter );
        return rootView;


    }


    constraintadapter.ConstraintAdapterEvent constraintAdapterEvent=new constraintadapter.ConstraintAdapterEvent() {
        @Override
        public void Display(int adapterposition) {
            String sName = img_label.get( adapterposition );
            String sImg = img_name.get( adapterposition );
            Inner_constraint_Fragment frag = new Inner_constraint_Fragment();

            if (sName != null) {
                Bundle arguments = new Bundle();

                if (sName.equalsIgnoreCase( "Bettery" )) {
                }else {
                    arguments.putSerializable( "Key", sName );
                    arguments.putSerializable( "Key_img", sImg );
                    frag.setArguments( arguments );

                }
            }


            getFragmentManager().beginTransaction()
                    .replace( R.id.frm_container, frag )
                    .addToBackStack(Constraintfrag.class.getName())
                    .commit();
        }
    };


}


