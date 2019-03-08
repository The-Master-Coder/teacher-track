package com.factrack.forms;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.factrack.R;



public class DialogActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_get_schedule);
        showAlertDialog();
    }

    private void showAlertDialog() {
        FragmentManager fm = getSupportFragmentManager();
        MyAlertDialogFragment alertDialog = MyAlertDialogFragment.newInstance("Some title");
        alertDialog.show(fm, "fragment_alert");
    }
}