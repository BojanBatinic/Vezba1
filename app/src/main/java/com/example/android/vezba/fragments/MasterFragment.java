package com.example.android.vezba.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.vezba.R;
import com.example.android.vezba.provider.GlumacProvider;

import java.util.List;

/**
 * Created by BBLOJB on 21.11.2017..
 */
// Each Fragment extends Fragment class
public class MasterFragment extends Fragment {

    OnItemSelectedListener listener;

    // onCreate method is a life-cycle method that is called when creating the fragment.
  /*  @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //shows a toast message ( a pop-up message)
        Toast.makeText(getActivity(), "MasterFragment.onCreate()", Toast.LENGTH_SHORT).show();
    }*/

    // onActivityCreated method is a life-cycle method that is called when the fragment's activity has been created and this fragment's view hierarchy instantiated.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Shows a toast message (a pop-up message)
        //Toast.makeText(getActivity(), "MasterFragemnt.onActivityCreated()", Toast.LENGTH_SHORT).show();

        //Loads glumce from array resource
        final List<String> glumacNames = GlumacProvider.getGlumacNames();

        //creates an ArrayAdapter from the array of String
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, glumacNames);
        ListView listView = (ListView) getActivity().findViewById(R.id.listofGlumci);

        //assigns ArrayAdapter to ListView
        listView.setAdapter(dataAdapter);

        //updates MasterFragment
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onItemSelected(position);
            }
        });

    }

    // onCreateView method is a life-cycle method that is called to have the fragment instantiate its user interface view.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //shows a toast message ( a pop-up message)
       // Toast.makeText(getActivity(), "MasterFragment.onCreateView()", Toast.LENGTH_SHORT).show();
        if (container == null) {
            return null;
        }
        View view = inflater.inflate(R.layout.fragment_master, container, false);
        return view;
    }

    // onDestroyView method is a life-cycle method that is called when the view previously created by onCreateView(LayoutInflater, ViewGroup, Bundle) has been detached from the fragment.

  /*  @Override
    public void onDestroyView() {
        super.onDestroyView();

        //shows a toast message (a pop-up messsage)
        Toast.makeText(getActivity(), "MasterFragment.onDestroyView()", Toast.LENGTH_SHORT).show();
    } */

    // onAttach method is a life-cycle method that is called when a fragment is first attached to its context.
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        //shows a toast messsage (a pop-up message)
       // Toast.makeText(getActivity(), "MasterFragment.onAttach()", Toast.LENGTH_SHORT).show();
        try {
            listener = (OnItemSelectedListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement OnItemSelectedListener");
        }
    }

    // Container activity must implement this interface
    public interface OnItemSelectedListener{

        public void onItemSelected(int position);
    }

}
