package com.factrack.forms;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.factrack.R;

import java.util.Calendar;

public class addSchedule extends AppCompatActivity {

    private EditText roomNo;
    private Spinner building;
    private Button btnStartTime, btnEndTime;

    int startHour, startMinute, endHour, endMinute;
    private String Day, Building, RoomNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        btnStartTime = (Button)findViewById(R.id.btnStartTime);
        btnEndTime = (Button)findViewById(R.id.btnEndTime);
        building = (Spinner)findViewById(R.id.building);
        roomNo = (EditText)findViewById(R.id.roomNo);

        RoomNo = roomNo.getText().toString().trim();




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

        btnStartTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                startHour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                startMinute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(addSchedule.this, new TimePickerDialog.OnTimeSetListener() {
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
                mTimePicker = new TimePickerDialog(addSchedule.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        btnEndTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, endHour, endMinute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
    }
}
