package com.factrack.studentBottomNavigation;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.factrack.R;
import com.factrack.login.LoginActivity;
import com.factrack.studentView.StudentView;
import com.factrack.teacherBottomNavigation.BottomNavigationBehavior;
import com.factrack.teacherBottomNavigation.TeacherListFragment;
import com.factrack.teacherView.TeacherView;
import com.google.firebase.auth.FirebaseAuth;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

public class StudentBottomNav extends AppCompatActivity {
    private FirebaseAuth auth;
    private Toolbar toolbar;
    Fragment fragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle(R.string.title_home);
                    fragment = new StudentView();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_dashboard:
                    toolbar.setTitle("Online Teachers");
                    fragment = new TeacherListFragmentStu();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_notifications:
                    toolbar.setTitle("Offline Teachers");
                    fragment = new TeacherListFragmentStuOffline();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_bottom_nav);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Your Profile");
        setSupportActionBar(toolbar);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        fragment = new StudentView();
        loadFragment(fragment);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        signOut();
        Intent intent = new Intent(StudentBottomNav.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    //sign out method
    public void signOut() {
        auth.signOut();
    }

}
