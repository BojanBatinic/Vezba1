package com.example.android.vezba.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
 * Created by BBLOJB on 17.11.2017..
 */
// Each activity extends Activity class
public class SecondActivity extends AppCompatActivity {

 /*   private static final int PERMISSIONS_REQUSET_INTERNET = 0;

    private int position = 0; */

  /*  private Glumac[] glumci = new Glumac[] {
            new Glumac(0,"velimir.jpg", "Velimir Bata Živojinović", "Legenda jugoslovenskog i srpskog glumišta, uvek partizan...", 5.0f),
            new Glumac(1,"dragan.jpg", "Dragan Gaga Nikolić", "Večni mangup jugoslovesnkog i srpskog glumišta, čime postaje legenda", 5.0f),
            new Glumac(2,"zoran.jpg", "Zoran Radmilović", "Zauvek Radovan III, retko duhovit i dosetljiv, legenda među legendama", 5.0f)
    };*/

    // onCreate method is a lifecycle method called when he activity is starting

/*    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Each lifecycle method should call the method it overrides
        super.onCreate(savedInstanceState);
        // setContentView method draws UI
        setContentView(R.layout.activity_second_relative);
        //Shows a toast message (a pop.up message)
        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onCreate()", Toast.LENGTH_SHORT);
        toast.show();

        //Loads an URL in to the WebView
        final int position = getIntent().getIntExtra("position", 0);

        //finds "ivImagew" ImageView and sets "imageDrawable" property
        ImageView ivImage = (ImageView) findViewById(R.id.iv_image);
        InputStream is = null;
        try {
            is = getAssets().open(GlumacProvider.getGlumacById(position).getImage());
            Drawable drawable = Drawable.createFromStream(is, null);
            ivImage.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //finds tvName TextView and sets "text" property
        TextView tvName = (TextView) findViewById(R.id.tv_name);
        tvName.setText(GlumacProvider.getGlumacById(position).getName());

        //finds "tvBiografija" TextView and sets "text" property
        TextView tvBiografija = (TextView) findViewById(R.id.tv_biografija);
        tvBiografija.setText(GlumacProvider.getGlumacById(position).getBiografija());

        // Finds "spCategory" Spiner and sets "selection" property
        Spinner film = (Spinner) findViewById(R.id.sp_film);
        List<String> filmovi = FilmProvider.getFilmNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, filmovi);
        film.setAdapter(adapter);
        film.setSelection((int)GlumacProvider.getGlumacById(position).getFilm().getId());

        //finds "rbRating" Rating Bar and sets "rating" property
        RatingBar rbRating = (RatingBar) findViewById(R.id.rb_rating);
        rbRating.setRating(GlumacProvider.getGlumacById(position).getRating());



        //Finds "btnLike" Button and sets "onClickListener" listener
        Button btnLike = (Button) findViewById(R.id.btn_like);
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "You've liked " + GlumacProvider.getGlumacById(position).getName() + "!", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        // Checks for permission dynamically (Manifest.permission.INTERNET is a normal permission that is granted automatically,
        // but for the sake of explanation ...)
      /*  if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
           if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
               // Show an explanation to the user *asynchronously* -- don't block this thread waiting for the user's response!
           }else{
               ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PERMISSIONS_REQUSET_INTERNET);
               // PERMISSIONS_REQUEST_READ_CONTACTS is an app-defined int constant. The callback method gets the result of the request.
           }
        }else{
            //Loads an URL into the WebView
            String URL = getIntent().getStringExtra("URL");
            if (!URL.trim().equalsIgnoreCase("")){
                WebView myWebView = (WebView) findViewById(R.id.pageInfo);
                myWebView.getSettings().setJavaScriptEnabled(true);
                myWebView.setWebViewClient(new MyWebViewClient());
                myWebView.loadUrl(URL.trim());
            }
        }*/
  //  }

    // onStart method is a lifecycle method called after onCreate (or after onRestart when the
    // activity had been stopped, but is now again being displayed to the user)
 //   @Override
 /*   protected void onStart() {
        super.onStart();
        //Shows a toast message (a pop-up message)
        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onStart()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onRestart method is a lifecycle method called after onStop when the current activity is
    // being re-displayed to the user
    @Override
    protected void onRestart() {
        super.onRestart();
        //Shows a toast message (a POP-up message)
        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onRestart()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onResume method is a lifecycle method called after onRestoreInstanceState, onRestart, or
    // onPause, for your activity to start interacting with the user
    @Override
    protected void onResume() {
        super.onResume();
        //Shows a toast message (a pop-up message)
        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onResume", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onPause method is a lifecycle method called when an activity is going into the background,
    // but has not (yet) been killed
    @Override
    protected void onPause() {
        super.onPause();
        //Shows a toast message (a pop-up message)
        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onPause", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onStop method is a lifecycle method called when the activity are no longer visible to the user
    @Override
    protected void onStop() {
        super.onStop();
        //Shows a toast message ( a pop-up messsage)
        Toast toast = Toast.makeText(getBaseContext(), "SecondActivity.onStop()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onDestroy method is a lifecycle method that perform any final cleanup before an activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //shows a toast message (a pop-up message)
        Toast toast = Toast.makeText(getBaseContext(), "SeconmActivity.onDestroy()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // Inner class needed to loads an URL into the WebView
   /* private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUSET_INTERNET: {
                //If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission was granted
                    //loads an URL into the WebView
                    String URL = getIntent().getStringExtra("URL");
                    if (!URL.trim().equalsIgnoreCase("")) ; {
                    WebView myWebView = (WebView) findViewById(R.id.pageInfo);
                    myWebView.getSettings().setJavaScriptEnabled(true);
                    myWebView.setWebViewClient(new MyWebViewClient());
                    myWebView.loadUrl(URL.trim());
                }
            }else{
                // Permission denied
            }
            return;

        }
// other 'case' lines to check for other permissions this app might request
     }
    }*/
}
