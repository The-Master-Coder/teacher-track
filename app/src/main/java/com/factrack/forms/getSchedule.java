package com.factrack.forms;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.factrack.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class getSchedule extends AppCompatActivity {

    private EditText roomNo;
    private Spinner building;
    private Button btnStartTime, btnEndTime;

    int startHour, startMinute, endHour, endMinute;
    private String Day, Building, RoomNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_schedule);




        android.support.design.widget.FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //Intent intent = new Intent( getSchedule.this, DialogActivity.class);
                //startActivity(intent);
                MyAlertDialog(view);
            }
        });





    }

    public void MyAlertDialog (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_add_schedule, null);
        builder.setView(dialogView);

        btnStartTime = (Button)dialogView.findViewById(R.id.btnStartTime);
        btnEndTime = (Button)dialogView.findViewById(R.id.btnEndTime);
        building = (Spinner)dialogView.findViewById(R.id.building);
        roomNo = (EditText)findViewById(R.id.roomNo);

        btnStartTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();

                Toast.makeText(getApplicationContext(), "STRING MESSAGE", Toast.LENGTH_LONG).show();

                startHour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                startMinute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getSchedule.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        btnStartTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, startHour, startMinute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        btnEndTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                endHour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                endMinute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getSchedule.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        btnEndTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, endHour, endMinute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        building.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Your code here
                Building = String.valueOf(building.getSelectedItem());
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                Building = "Not seleceted";
                return;
            }
        });

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                        RoomNo = roomNo.getText().toString().trim();

                    }
                });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //LoginDialogFragment.this.getDialog().cancel();
                //this.finish();
            }
        });
        AlertDialog b = builder.create();
        b.show();
    }
}
