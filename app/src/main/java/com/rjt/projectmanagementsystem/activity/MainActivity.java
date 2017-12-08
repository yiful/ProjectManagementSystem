package com.rjt.projectmanagementsystem.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rjt.projectmanagementsystem.R;
import com.rjt.projectmanagementsystem.events.ContactsFragment;
import com.rjt.projectmanagementsystem.events.EventsFragment;
import com.rjt.projectmanagementsystem.model.firebase_model.User;
import com.rjt.projectmanagementsystem.posts.activity.BaseActivity;
import com.rjt.projectmanagementsystem.posts.activity.SignInActivity;
import com.rjt.projectmanagementsystem.project.NewProjectFragment;
import com.rjt.projectmanagementsystem.project.ProjectFragment;
import com.squareup.picasso.Picasso;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String userName;
    private String userEmail;
    private String userPhoto;
    private TextView tvUserName;
    private TextView tvUserEmail;
    private ImageView ivUser;
    private FloatingActionMenu menuRed;
    private com.github.clans.fab.FloatingActionButton fab1;
    private com.github.clans.fab.FloatingActionButton fab2;
    private com.github.clans.fab.FloatingActionButton fab3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        menuRed = findViewById(R.id.menu_red);
        menuRed.setClosedOnTouchOutside(true);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewProjectFragment fragment = new NewProjectFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.fragmentContainer, fragment)
                        .addToBackStack("addNewProjectFrag")
                        .commit();
                menuRed.close(true);
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
                menuRed.close(true);
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
                menuRed.close(true);
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, NoteActivity.class);
            startActivity(intent);
                *//*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*//*
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);
        tvUserEmail = headerview.findViewById(R.id.userEmail);
        tvUserName = headerview.findViewById(R.id.userName);
        ivUser = headerview.findViewById(R.id.userImg);
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        userEmail = intent.getStringExtra("userEmail");
        userPhoto = intent.getStringExtra("userImg");
        Log.i("main", "photo url: "+userPhoto);
        tvUserName.setText(userName);
        tvUserEmail.setText(userEmail);
        Picasso.with(this).load(userPhoto).into(ivUser);
        //default page is project fragment.
        ProjectFragment  fragment = new ProjectFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment, "ProjectFragment")
                .commit();

        tryRegisterFirebaseAccountRegistered();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(menuRed.isOpened()) {
            menuRed.close(true);
        }else{
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.attach){
            Intent intent=new Intent(MainActivity.this,AttachementActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_project) {
            // Handle the project action
            Log.i("main", "project");
            ProjectFragment  fragment = new ProjectFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, fragment, "ProjectFragment")
                    .commit();
        } else if (id == R.id.nav_gallery) {
            //Events
            EventsFragment fragment = new EventsFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            Log.i("main", "event fragment");
            transaction.replace(R.id.fragmentContainer, fragment, "EventFragment")
                    .commit();

        } else if (id == R.id.nav_slideshow) {
            //Contacts
            ContactsFragment fragment = new ContactsFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, fragment, "ContactsFragment")
                    .commit();
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }else if(id == R.id.logout){
            signout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void tryRegisterFirebaseAccountRegistered(){
        showProgressDialog();
        mAuth.createUserWithEmailAndPassword(userEmail, userEmail)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                        // hideProgressDialog();

                        if (task.isSuccessful()) {
                            //register firebase user.
                            onAuthSuccess(task.getResult().getUser());
                            Log.i(TAG, "Firebase is now registered!");
                 //           Toast.makeText(LoginActivity.this, "Firebase account created", Toast.LENGTH_SHORT).show();
                            hideProgressDialog();
                        } else {
                            Log.i(TAG, "Firebase is registered before!");
                            //        mAuth.signInWithEmailAndPassword(userEmail, userEmail);
                            signInFirebaseAccount();
                        }
                    }
                });
        //    return false;
    }

    private void signInFirebaseAccount() {
        mAuth.signInWithEmailAndPassword(userEmail, userEmail)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signIn:onComplete:" + task.isSuccessful());
                        //   hideProgressDialog();
                        hideProgressDialog();
                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                            //  Toast.makeText(LoginActivity.this, "Firebase account signed in", Toast.LENGTH_SHORT).show();
                            Log.i(TAG,"Firebase account signed in!");
                            Toast.makeText(MainActivity.this, "Firebase account signed in", Toast.LENGTH_SHORT).show();

                        } else {
                            Log.i(TAG, "Firebase account failed to sign in");
                            // hideProgressDialog();
                        }
                    }
                });
    }

    private void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());

        // Write new user
        writeNewUser(user.getUid(), username, user.getEmail());

        // Go to MainActivity
        //    startActivity(new Intent(SignInActivity.this, MainActivity.class));
    }
    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private void signout() {
        if(LoginActivity.mGoogleSignInClient!=null) {
            LoginActivity.mGoogleSignInClient.signOut()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
        }
        if( FirebaseAuth.getInstance()!=null){
            FirebaseAuth.getInstance().signOut();
        }
        finish();
        Toast.makeText(this, "You have logged out!", Toast.LENGTH_SHORT).show();
    }
}