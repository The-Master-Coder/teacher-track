package com.factrack.teacherView;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import com.factrack.R;

public class TeacherView extends AppCompatActivity {

    Button monday, tuesday, wednesday, thursday, friday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view);

        monday = findViewById(R.id.btn_monday);
        tuesday = findViewById(R.id.btn_tuesday);
        wednesday = findViewById(R.id.btn_wednesday);
        thursday = findViewById(R.id.btn_thursday);
        friday = findViewById(R.id.btn_friday);

        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> mcategories = new ArrayList<String>();
                mcategories.add("9:00 AM - 11:00 AM\t\t5207 CC3");
                mcategories.add("12:00 AM - 1:00 PM\t\t5005 CC3");
                mcategories.add("4:00 PM - 6:00 PM\t\t5054 CC3");
                //Create sequence of items
                final CharSequence[] Categories = mcategories.toArray(new String[mcategories.size()]);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(TeacherView.this);
                dialogBuilder.setTitle("Monday");
                DialogInterface.OnClickListener listener = null;
                dialogBuilder.setItems(Categories,  listener);
                //Create alert dialog object via builder
                AlertDialog alertDialogObject = dialogBuilder.create();
                //Show the dialog
                alertDialogObject.show();
            }
        });

        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> mcategories = new ArrayList<String>();
                mcategories.add("9:00 AM - 11:00 AM\t\t5207 CC3");
                mcategories.add("12:00 AM - 1:00 PM\t\t5005 CC3");
                mcategories.add("4:00 PM - 6:00 PM\t\t5054 CC3");
                //Create sequence of items
                final CharSequence[] Categories = mcategories.toArray(new String[mcategories.size()]);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(TeacherView.this);
                dialogBuilder.setTitle("Tuesday");
                DialogInterface.OnClickListener listener = null;
                dialogBuilder.setItems(Categories,  listener);
                //Create alert dialog object via builder
                AlertDialog alertDialogObject = dialogBuilder.create();
                //Show the dialog
                alertDialogObject.show();
            }
        });

        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> mcategories = new ArrayList<String>();
                mcategories.add("9:00 AM - 11:00 AM\t\t5207 CC3");
                mcategories.add("12:00 AM - 1:00 PM\t\t5005 CC3");
                mcategories.add("4:00 PM - 6:00 PM\t\t5054 CC3");
                //Create sequence of items
                final CharSequence[] Categories = mcategories.toArray(new String[mcategories.size()]);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(TeacherView.this);
                dialogBuilder.setTitle("Wednesday");
                DialogInterface.OnClickListener listener = null;
                dialogBuilder.setItems(Categories,  listener);
                //Create alert dialog object via builder
                AlertDialog alertDialogObject = dialogBuilder.create();
                //Show the dialog
                alertDialogObject.show();
            }
        });

        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> mcategories = new ArrayList<String>();
                mcategories.add("9:00 AM - 11:00 AM\t\t5207 CC3");
                mcategories.add("12:00 AM - 1:00 PM\t\t5005 CC3");
                mcategories.add("4:00 PM - 6:00 PM\t\t5054 CC3");
                //Create sequence of items
                final CharSequence[] Categories = mcategories.toArray(new String[mcategories.size()]);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(TeacherView.this);
                dialogBuilder.setTitle("Thursday");
                DialogInterface.OnClickListener listener = null;
                dialogBuilder.setItems(Categories,  listener);
                //Create alert dialog object via builder
                AlertDialog alertDialogObject = dialogBuilder.create();
                //Show the dialog
                alertDialogObject.show();
            }
        });

        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> mcategories = new ArrayList<String>();
                mcategories.add("9:00 AM - 11:00 AM\t\t5207 CC3");
                mcategories.add("12:00 AM - 1:00 PM\t\t5005 CC3");
                mcategories.add("4:00 PM - 6:00 PM\t\t5054 CC3");
                //Create sequence of items
                final CharSequence[] Categories = mcategories.toArray(new String[mcategories.size()]);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(TeacherView.this);
                dialogBuilder.setTitle("Friday");
                DialogInterface.OnClickListener listener = null;
                dialogBuilder.setItems(Categories,  listener);
                //Create alert dialog object via builder
                AlertDialog alertDialogObject = dialogBuilder.create();
                //Show the dialog
                alertDialogObject.show();
            }
        });


    }
}
