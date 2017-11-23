package com.example.android.vezba.fragments;

import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.vezba.R;
import com.example.android.vezba.provider.FilmProvider;
import com.example.android.vezba.provider.GlumacProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by BBLOJB on 21.11.2017..
 */

// Each fragment extends Fragment class
public class DetailFragment extends Fragment{

    private static int NOTIFICATION_ID = 1;

    // Position of the item to be displayed in the detail fragment
    private int position = 0;

    // onCreate method is a life-cycle method that is called when creating the fragment.
 /*   @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        // Shows a toast message (a pop-up message)
    Toast.makeText(getActivity(),"DetailFragment.onCreate()", Toast.LENGTH_SHORT).show();
    } */
    // onActivityCreated method is a life-cycle method that is called when the fragment's activity has been created and this fragment's view hierarchy instantiated.
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Shows a toast message (a pop-up message)
       // Toast.makeText(getActivity(), "DetailFragment.onActivityCreated()", Toast.LENGTH_SHORT).show();

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("posotion", 0);
            }

        //Finds "ivImage" ImageView and sets "imageDrawable" property
        ImageView ivImage = (ImageView) getView().findViewById(R.id.iv_image);
        InputStream is = null;
        try {
            is = getActivity().getAssets().open(GlumacProvider.getGlumacById(position).getImage());
            Drawable drawable = Drawable.createFromStream(is, null);
            ivImage.setImageDrawable(drawable);
        } catch (IOException e){
            e.printStackTrace();
        }

        //finds "tvName" TextView and sets "text" property
        TextView tvName = (TextView) getView().findViewById(R.id.tv_name);
        tvName.setText(GlumacProvider.getGlumacById(position).getName());

        //finds "tvBiografija" TextView and sets "text" property
        TextView tvBiografija = (TextView) getView().findViewById(R.id.tv_biografija);
        tvBiografija.setText(GlumacProvider.getGlumacById(position).getBiografija());

        //finds "spFilm" Spiner and sets "selection" property
        Spinner film = (Spinner) getView().findViewById(R.id.sp_film);
        List<String> filmovi = FilmProvider.getFilmNames();
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, filmovi);
        film.setAdapter(adapter);
        film.setSelection((int)GlumacProvider.getGlumacById(position).getFilm().getId());

        //finds "rbRating" RatingBar and sets "rating" property
        RatingBar rbRating = (RatingBar) getView().findViewById(R.id.rb_rating);
        rbRating.setRating(GlumacProvider.getGlumacById(position).getRating());

        //finds "btnLike" Button and sets "onClickListener" listener
       // Button btnLike = (Button) getView().findViewById(R.id.btn_like);
        FloatingActionButton btnLike = (FloatingActionButton) getView().findViewById(R.id.btn_like);
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Toast toast = Toast.makeText(v.getContext(), "You've liked " + GlumacProvider.getGlumacById(position).getName() + "!", Toast.LENGTH_LONG);
                toast.show(); */
               showNotification();
            }
        });

    }

    //onSaveInstanceState method is a life-cycle method is salled to ask the fragment to save its current dynamic state, si it can later be reconstructed in a new instance of its process is restarted.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //shows a toast message ( a pop-up message)
       // Toast.makeText(getActivity(), "DetailFragment.onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        savedInstanceState.putInt("position", position);
    }
    // onCreateView method is a life-cycle method that is called to have the fragment instantiate its user interface view.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //shows a toast message (a pop-up message)
        //Toast.makeText(getActivity(), "DetailFragment.onCreateView()", Toast.LENGTH_SHORT).show();
        //finds view in the view hierarchy and returns it.
        View view = inflater.inflate(R.layout.fragment_detail, container, false );
        return view;
    }

    //onDestroyView method is s life-cycle method that is calles when the view previously created by onCreateView(LayoutInfkater, ViewGroup, Bundle) has been detached from the fragment.
 /*   @Override
    public void onDestroyView() {
        super.onDestroyView();
        //shows a toast message (a pop-up message)
        Toast.makeText(getActivity(), "DetailFragment.onDestroyView()", Toast.LENGTH_SHORT).show();
    }*/

    //called to set fragment`s content.
    public void setContent(final int position) {
        this.position = position;
        Log.v("DetailFragment", "setContent() sets position to " + position);
    }
    //called to update fragment`s content.
    public void updateContent(final int position) {
        this.position = position;

        Log.v("DetailFragment", "updateContent() sets position to " + position);

        // finds "ivImage" ImageView and sets "imageDrawable" property
        ImageView ivImage = (ImageView) getView().findViewById(R.id.iv_image);
        InputStream is = null;
        try {
            is = getActivity().getAssets().open(GlumacProvider.getGlumacById(position).getImage());
            Drawable drawable = Drawable.createFromStream(is, null);
            ivImage.setImageDrawable(drawable);
        }catch (IOException e){
            e.printStackTrace();
        }

        //finds "tvName" TextView and sets "text" property
        //finds "tvName" TextView and sets "text" property
        TextView tvName = (TextView) getView().findViewById(R.id.tv_name);
        tvName.setText(GlumacProvider.getGlumacById(position).getName());

        //finds "tvBiografija" TextView and sets "text" property
        TextView tvBiografija = (TextView) getView().findViewById(R.id.tv_biografija);
        tvBiografija.setText(GlumacProvider.getGlumacById(position).getBiografija());

        //finds "spFilm" Spiner and sets "selection" property
        Spinner film = (Spinner) getView().findViewById(R.id.sp_film);
        List<String> filmovi = FilmProvider.getFilmNames();
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, filmovi);
        film.setAdapter(adapter);
        film.setSelection((int)GlumacProvider.getGlumacById(position).getFilm().getId());

        //finds "rbRating" RatingBar and sets "rating" property
        RatingBar rbRating = (RatingBar) getView().findViewById(R.id.rb_rating);
        rbRating.setRating(GlumacProvider.getGlumacById(position).getRating());

        //finds "btnLike" Button and sets "onClickListener" listener
      //  Button btnLike = (Button) getView().findViewById(R.id.btn_like);
        FloatingActionButton btnLike = (FloatingActionButton) getView().findViewById(R.id.btn_like);
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Toast toast = Toast.makeText(v.getContext(), "You've liked " + GlumacProvider.getGlumacById(position).getName() + "!", Toast.LENGTH_LONG);
                toast.show(); */
              showNotification();
            }
        });
    }

    private void showNotification(){
        // Creates notification with the notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity());
        Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_action_like);
        builder.setSmallIcon(R.drawable.ic_action_like);
        builder.setContentTitle(getActivity().getString(R.string.notification_title));
        builder.setContentText(getActivity().getString(R.string.notification_text));
        builder.setLargeIcon(bitmap);

        // Shows notification with the notification manager (notification ID is used to update the notification later on)
        NotificationManager manager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }
}
