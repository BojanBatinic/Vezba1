package com.example.android.vezba.activities;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.support.design.widget.Snackbar;

import java.util.ArrayList;

import com.example.android.vezba.R;
import com.example.android.vezba.adapters.DrawerAdapter;
import com.example.android.vezba.dialogs.AboutDialog;
import com.example.android.vezba.fragments.DetailFragment;
import com.example.android.vezba.fragments.MasterFragment;
import com.example.android.vezba.model.NavigationItem;

// Each activity extends Activity class or AppCompatActivity class
public class MainActivity extends AppCompatActivity implements MasterFragment.OnItemSelectedListener {

  //  boolean landscape = false;

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItemFromDrawer(position);



          /*  if (position == 0) {
                // FirstActivity is already shown
            } else if (position == 1) {
                Intent settings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settings);
            } else if (position == 2) {
                if (dialog == null) {
                    dialog = new AboutDialog(MainActivity.this).prepareDialog();
                } else {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
                // To not create dialog class
                // call it from here.
                dialog.show();
            }

            drawerList.setItemChecked(position, true);
            setTitle(drawerItems.get(position).getTitle());
            drawerLayout.closeDrawer(drawerPane); */
        }

    }

    // Attributes used by NavigationDrawer
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private RelativeLayout drawerPane;
    private CharSequence drawerTitle;
    private ArrayList<NavigationItem> drawerItems = new ArrayList<NavigationItem>();

    // Attributes used by Dialog
    private AlertDialog dialog;

    // Attributes representing the activity's state
    private boolean landscapeMode = false; // Is the device in the landscape mode?
    private boolean masterShown = false; // Is the MasterFragment fragment shown?
    private boolean detailShown = false; // Is the DetailFragment fragment shown?

    private int itemId = 0; // selected item id

    // onCreate method is a lifecycle method called when he activity is starting
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Each lifecycle method should call the method it overrides
        super.onCreate(savedInstanceState);
        // setContentView method draws UI
        //setContentView(R.layout.activity_main);

        // Shows a toast message (a pop-up message)
      /*  Toast toast = Toast.makeText(getBaseContext(), "MainActivity.onCreate()", Toast.LENGTH_SHORT);
        toast.show(); */

        //draws activity`s layout
        setContentView(R.layout.activity_main);

        // Manages ActionBar

        // Enables ActionBar app icon to behave as action to toggle NavigationDrawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_drawer);
            actionBar.setHomeButtonEnabled(true);
            actionBar.show();
        }

        //Manages NavigationDrawer

        //Populates a list of NavigationDrawer items
        drawerItems.add(new NavigationItem(getString(R.string.drawer_home), getString(R.string.drawer_home_long), R.drawable.ic_action_product));
        drawerItems.add(new NavigationItem(getString(R.string.drawer_settings), getString(R.string.drawer_settings_long), R.drawable.ic_action_settings));
        drawerItems.add(new NavigationItem(getString(R.string.drawer_about), getString(R.string.drawer_about_long), R.drawable.ic_action_about));

        drawerTitle = getTitle();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerList = (ListView) findViewById(R.id.navList);

        // Populates NavigtionDrawer with options
        drawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        DrawerAdapter adapter = new DrawerAdapter(this, drawerItems);

        // Sets a custom shadow that overlays the main content when NavigationDrawer opens
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        drawerList.setAdapter(adapter);

        drawerToggle = new ActionBarDrawerToggle(
                this,          /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,              /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open, /* "open drawer" description for accessibility */
                R.string.drawer_close /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu(); // Creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu(); // Creates call to onPrepareOptionsMenu()
            }

        };

        // Manages fragments

        // If the activity is started for the first time create master fragment
        if(savedInstanceState == null) {
            // FragmentTransaction is a set of changes (e.g. adding, removing and replacing fragments) that you want to perform at the same time.
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            MasterFragment masterFragment = new MasterFragment();
            ft.add(R.id.master_view, masterFragment, "Master_Fragment_1");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }

        // If the device is in the landscape mode and the detail fragment is null create detail fragment
        if(findViewById(R.id.detail_view) != null){
            landscapeMode = true;
            getFragmentManager().popBackStack();

            DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detail_view);
            if(detailFragment == null){
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                detailFragment = new DetailFragment();
                ft.replace(R.id.detail_view, detailFragment, "Detail_Fragment_1");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
                detailShown = true;
            }
        }

        masterShown = true;
        detailShown = false;
        itemId = 0;


        //Loads glumci from array resource1
       // final String[] glumci = getResources().getStringArray(R.array.glumci);


        //Loads glumci from array resource2
        //final List<String> glumacNames = GlumacProvider.getGlumacNames();

        //Creates an ArrayAdaptar from the arra od String
        /*ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.list_item, glumacNames);
        ListView listView = (ListView) findViewById(R.id.listofGlumci);

        //assigns ArrayAdapter to ListView
        listView.setAdapter(dataAdapter);

        //starts the SecondActivity and sends it the selected URL as an extra data
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
          public void onItemClick(AdapterView<?> parent, View view, int position, long id){
           Intent intent = new Intent (MainActivity.this, SecondActivity.class);
           intent.putExtra("position", position);
           startActivity(intent);
          }
        }); */
    }

    // onStart method is a lifecycle method called after onCreate (or after onRestart when the
    // activity had been stopped, but is now again being displayed to the user)
 /*   @Override
    protected void onStart() {
        super.onStart();
        // Shows a toast message (a pop-up message)
        Toast toast = Toast.makeText(getBaseContext(), "MainActivity.onStart()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onRestart method is a lifecycle method called after onStop when the current activity is
    // being re-displayed to the user
    @Override
    protected void onRestart() {
        super.onRestart();
        //Shows a toast message (a pop-up message)
        Toast toast = Toast.makeText(getBaseContext(), "MainActivity.onRestart()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onResume method is a lifecycle method called after onRestoreInstanceState, onRestart, or
    // onPause, for your activity to start interacting with the user
    @Override
    protected void onResume() {
        super.onResume();
        //Shows a toast message (a pop-up message)
        Toast toast = Toast.makeText(getBaseContext(), "MainActivity.onResume()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onPause method is a lifecycle method called when an activity is going into the background,
    // but has not (yet) been killed
    @Override
    protected void onPause() {
        super.onPause();
        //Shows a toast message (a pop-up message)
        Toast toast = Toast.makeText(getBaseContext(), "MainActivity.onPause()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onStop method is a lifecycle method called when the activity are no longer visible to the user
    @Override
    protected void onStop() {
        super.onStop();
        //Shows a toast message (a pop-up message)
        Toast toast = Toast.makeText(getBaseContext(), "MainActivity.onStop()", Toast.LENGTH_SHORT);
        toast.show();
    }

    // onDestroy method is a lifecycle method that perform any final cleanup before an activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Shows a toast message ( a pop-up message)
        Toast toast = Toast.makeText(getBaseContext(), "MainActivity.on Destroy()", Toast.LENGTH_SHORT);
        toast.show();
    } */
// Method(s) that manage Fragments.

    // onItemSelected method handles selection of items in the MasterFragment fragment
    @Override
    public void onItemSelected(int id){

        itemId = id;

        // Shows a toast message (a pop-up message)
        //Toast.makeText(getBaseContext(), "MainActivity.onItemSecected()", Toast.LENGTH_SHORT).show();

        if(landscapeMode){
            // If the device is in the landscape mode updates detail fragment's content.
            DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detail_view);
            detailFragment.updateContent(id);
        }else{
            // If the device is in the portrait mode sets detail fragment's content and replaces master fragment with detail fragment in a fragment transaction.
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setContent(id);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.master_view, detailFragment, "Detail_Fragment_2");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();

            masterShown = false;
            detailShown = true;
        }
    }

    // onBackPressed method is called when the activity has detected the user's press of the back key (it has to be overrided because fragment's behavior has to be implemented manualy).
    @Override
    public void onBackPressed(){

        if (landscapeMode){
            finish();
        }else if(masterShown == true) {
            finish();
        }else if(detailShown == true){
            getFragmentManager().popBackStack();
            MasterFragment masterFragment = new MasterFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.master_view, masterFragment, "Master_Fragment");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            masterShown = true;
            detailShown = false;
        }
    }

    // Called when btnStart button is clicked
  /*  public void btnStartActivityClicked(View view) {
        // This is an explicit intent (class property is specified)
        Intent intent = new Intent (MainActivity.this, SecondActivity.class);
        // startActivity method starts an activity
        startActivity(intent);
    }

    //Called when btnOpen is clicked
    public void btnOpenBrowserClicked(View view) {
        //This is an implicit intent
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com"));
        //startActivity method starts an activity
        startActivity(i);
    }*/
    // Method(s) that manage Toolbar.

    // onCreateOptionsMenu method initialize the contents of the Activity's Toolbar.
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // onOptionsItemSelected method is called whenever an item in the Toolbar is selected.
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_create:
                Toast.makeText(this, "Action " + getString(R.string.fragment_master_action_create) + " executed.", Toast.LENGTH_SHORT ).show();
                break;
            case R.id.action_update:
                Toast.makeText(this, "Action " + getString(R.string.fragment_detail_action_update) + " executed.", Toast.LENGTH_SHORT ).show();
                break;
            case R.id.action_delete:
                Toast.makeText(this, "Action " + getString(R.string.fragment_detail_action_delete) + "executed.", Toast.LENGTH_SHORT).show();
                Snackbar.make(findViewById(R.id.master_view), "Obrisano biće to što piše tu!", Snackbar.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Overrides setTitle method to support older api levels
    @Override
    public void setTitle(CharSequence title){
        getSupportActionBar().setTitle(title);
    }

    // Method(s) that manage NavigationDrawer.
    // onPostCreate method is called ofthen onRestoreInstanceState to synchronize toggle state
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    // onConfigurationChanged method is called when the device configuration changes to pass configuration change to the drawer toggle
    @Override
    public void onConfigurationChanged(Configuration configuration){
        super.onConfigurationChanged(configuration);
        // Pass any configuration change to the drawer toggle
        drawerToggle.onConfigurationChanged(configuration);
    }

    // selectItemFromDrawer method is called when NavigationDrawer item is selected to update UI accordingly
    private void selectItemFromDrawer(int position) {
        if (position == 0) {
            // FirstActivity is already shown
        } else if (position == 1) {
            Intent settings = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(settings);
        } else if (position == 2) {
            if (dialog == null) {
                dialog = new AboutDialog(MainActivity.this).prepareDialog();
            } else {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
            // To not create dialog class
            // call it from here.
            dialog.show();
        }

        drawerList.setItemChecked(position, true);
        setTitle(drawerItems.get(position).getTitle());
        drawerLayout.closeDrawer(drawerPane);

    }

}
