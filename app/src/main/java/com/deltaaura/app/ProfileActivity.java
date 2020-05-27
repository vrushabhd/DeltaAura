package com.deltaaura.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    public ImageView imageView;
    private static final String MENU_ITEM = "menu_item";
    private int menuItemId,ifonce=1;
    public NavigationView navigationView;
    public ActionBarDrawerToggle mToggle;
    public DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //  imageView = findViewById(R.id.image_view);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);


//        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);
        init();
//        mToggle = new ActionBarDrawerToggle(ProfileActivity.this, mDrawerLayout, R.string.open, R.string.close);
//        mDrawerLayout.addDrawerListener(mToggle);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mToggle.syncState();

//        if (savedInstanceState == null) {//does not load default fragment on rotating the device.
////            Navigation.findNavController((Activity) getApplicationContext(), R.id.fragment).navigate(R.id.defaultFragment);
//        }
        //    navigationView.setCheckedItem(R.id.);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch ( item.getItemId()) {
                    case R.id.ProfileFragment:
                        NavOptions navOptions = new NavOptions.Builder()
                                .setPopUpTo(R.id.nav_graph, true)
                                .build();
                        Navigation.findNavController(ProfileActivity.this, R.id.fragment).
                                navigate(R.id.ProfileFragment,null,navOptions);

                        break;

                    case R.id.Home_Fragment:
                        if(isValidDestination(R.id.Home_Fragment)) {
                            Navigation.findNavController(ProfileActivity.this, R.id.fragment).navigate(R.id.Home_Fragment);
                        }

                        break;


                    case R.id.team_fragment:
                        if(isValidDestination(R.id.team_fragment)) {
                            Navigation.findNavController(ProfileActivity.this, R.id.fragment).navigate(R.id.team_fragment);
                        }

                        break;



                    case R.id.youtube_fragment:
                        if(isValidDestination(R.id.youtube_fragment)) {
                            Navigation.findNavController(ProfileActivity.this, R.id.fragment).navigate(R.id.youtube_fragment);
                        }
                        break;

                    case R.id.work_fragment:
                        if(isValidDestination(R.id.work_fragment)) {
                            Navigation.findNavController(ProfileActivity.this, R.id.fragment).navigate(R.id.work_fragment);
                        }
                        break;

                    case R.id.latest_fragment:
                        if(isValidDestination(R.id.latest_fragment)) {
                            Navigation.findNavController(ProfileActivity.this, R.id.fragment).navigate(R.id.latest_fragment);
                        }
                        break;
                    case R.id.Forum_Fragment:
                        if(isValidDestination(R.id.forumFragment)) {
                            Navigation.findNavController(ProfileActivity.this, R.id.fragment).navigate(R.id.forumFragment);
                        }
                        break;

                    case R.id.DzoneFragment:
                        if(isValidDestination(R.id.dzoneFragment)) {
                            Navigation.findNavController(ProfileActivity.this, R.id.fragment).navigate(R.id.dzoneFragment);
                        }
                        break;
                    case R.id.Contact_us_Fragement:
                        if(isValidDestination(R.id.contact_us_Fragement)) {
                            Navigation.findNavController(ProfileActivity.this, R.id.fragment).navigate(R.id.contact_us_Fragement);
                        }
                        break;

                    case R.id.career_fragment:
                        openweb();
                       break;


                    case R.id.aboutus:

                        open();
                        break;

                    case R.id.share:


                        share();
                        break;
                }
              //  item.setChecked(true);
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });



        // button =findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                finish();
//                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//            }
//        });
//

















    }

    private void openweb() {
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://real-resume.netlify.app/"));
        startActivity(webIntent);

    }


    @Override
    public boolean onSupportNavigateUp() {


        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.fragment), mDrawerLayout);
    }

    private void init() {

        NavController navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mDrawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if (mToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
        switch (item.getItemId()) {
            case R.id.action_logout:

                MaterialAlertDialogBuilder a_builder =  new MaterialAlertDialogBuilder(ProfileActivity.this);
                a_builder.setMessage("DO YOU WANT TO CLOSE THIS APP !!").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                FirebaseAuth.getInstance().signOut();
                                finish();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();




//                AlertDialog.Builder a_builder = new AlertDialog.Builder(ProfileActivity.this);
//                a_builder.setMessage("DO YOU WANT TO CLOSE THIS APP !!").setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                                FirebaseAuth.getInstance().signOut();
//                                finish();
//                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                            }
//                        })
//                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.cancel();
//                            }
//                        });
//                AlertDialog alert = a_builder.create();
//                alert.setTitle("Are you Sure !!");
//                alert.show();

                break;

            case R.id.home:
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {

                    mDrawerLayout.closeDrawer(GravityCompat.START);

                    return true;

                } else {

                    return false; //this click will be consume and handle by navigation up method.
                    //so we returning false so that it will not be consumed the click.

                }


        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        return true;
    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
                    super.onBackPressed();


//            if(menuItemId!=R.id.ProfileFragment ||ifonce==1) {
//                        Navigation.findNavController(ProfileActivity.this, R.id.fragment).
//                                navigate(R.id.ProfileFragment, null);
//
//                    }


              }

            }





    public void open() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.deltatheinnovators.com/about"));
        startActivity(browserIntent);
    }

    public void share() {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);


    }
    private boolean isValidDestination(int destination){

  return destination!= Navigation.findNavController(this,R.id.fragment).getCurrentDestination().getId();

    }







}
