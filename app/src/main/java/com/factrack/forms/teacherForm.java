package com.factrack.forms;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.factrack.R;
import com.factrack.containers.Schedule;
import com.factrack.containers.Slot;
import com.factrack.containers.studentFormData;
import com.factrack.containers.teacherFormData;
import com.factrack.login.SignupActivity;
<<<<<<< HEAD
import com.factrack.studentBottomNavigation.StudentBottomNav;
import com.factrack.teacherBottomNavigation.TeacherBottomNav;
||||||| merged common ancestors
=======
import com.factrack.teacherBottomNavigation.TeacherBottomNav;
>>>>>>> 9ff7573128c1aa289df5e0655ef23a01fd0ecaaa
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class teacherForm extends AppCompatActivity {

    private EditText roomNo;
    private Spinner building;
    private Button btnStartTime, btnEndTime;

    public int startHour, startMinute, endHour, endMinute;
    public String Day, Building, RoomNo;

    DatabaseReference root, faculty;
    StorageReference ref;
    private String userId;
    private EditText name, email, designation, mobileNo, officeNo, homepage;
    private Button btnUpload, submitButton;
    private ImageButton plusButton1, plusButton2, plusButton3, plusButton4, plusButton5;
    private Spinner department;
    private static final int CAMERA_REQUEST = 1888;
    private static final int GALLERY_REQUEST = 2;
    private String Department;
    private Schedule scheduleObj;


    private teacherFormData teacherFormDataObj;

    String generatedFilePath;
    Bitmap photo;
    Uri selectedImage;

    private RecyclerView recyclerView1,recyclerView2,recyclerView3,recyclerView4,recyclerView5;
    private Recycler_View_Adapter mAdapter1,mAdapter2,mAdapter3,mAdapter4,mAdapter5;
    public List<scheduleData> list1,list2,list3,list4,list5;

    private List<Slot> l1, l2, l3, l4, l5;


    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form);

        l1 = new ArrayList<Slot>();
        l2 = new ArrayList<Slot>();
        l3 = new ArrayList<Slot>();
        l4 = new ArrayList<Slot>();
        l4 = new ArrayList<Slot>();


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        designation = findViewById(R.id.designation);
        department = findViewById(R.id.department);
        roomNo = findViewById(R.id.roomNo);
        building = findViewById(R.id.building);
        mobileNo = findViewById(R.id.mobileNo);
        officeNo =  findViewById(R.id.officeNo);
        homepage = findViewById(R.id.homepage);
        submitButton = findViewById(R.id.submitButton);

        plusButton1 = findViewById(R.id.plusButton1);
        plusButton2 = findViewById(R.id.plusButton2);
        plusButton3 = findViewById(R.id.plusButton3);
        plusButton4 = findViewById(R.id.plusButton4);
        plusButton5 = findViewById(R.id.plusButton5);

        btnUpload = findViewById(R.id.uploadImage);
        //btnGetSchedule = findViewById(R.id.getSchedule);


        plusButton1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //Intent intent = new Intent( getSchedule.this, DialogActivity.class);
                //startActivity(intent);
                MyAlertDialog1(view);
            }
        });

        plusButton2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //Intent intent = new Intent( getSchedule.this, DialogActivity.class);
                //startActivity(intent);
                MyAlertDialog2(view);
            }
        });

        plusButton3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //Intent intent = new Intent( getSchedule.this, DialogActivity.class);
                //startActivity(intent);
                MyAlertDialog3(view);
            }
        });

        plusButton4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //Intent intent = new Intent( getSchedule.this, DialogActivity.class);
                //startActivity(intent);
                MyAlertDialog4(view);
            }
        });

        plusButton5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //Intent intent = new Intent( getSchedule.this, DialogActivity.class);
                //startActivity(intent);
                MyAlertDialog5(view);
            }
        });

        //department.setOnItemSelectedListener(new addScheduleItemSelectedListener());

        building.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Your code here

                Building = String.valueOf(building.getSelectedItem());
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                Building = "Not selected";
                return;
            }
        });

        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Your code here

                Department = String.valueOf(department.getSelectedItem());
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                Department = "Not selected";
                return;
            }
        });

        setUpRecyclerViews();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleObj = new Schedule();
                scheduleObj.schedules.put("monday", l1);
                scheduleObj.schedules.put("tuesday", l2);
                scheduleObj.schedules.put("wednesday", l3);
                scheduleObj.schedules.put("thursday", l4);
                scheduleObj.schedules.put("friday", l5);
                saveData();
<<<<<<< HEAD
                //uploadImage();
                startActivity(new Intent(teacherForm.this, TeacherBottomNav.class));
||||||| merged common ancestors
                startActivity(new Intent(teacherForm.this, teacherForm.class));
=======
                startActivity(new Intent(teacherForm.this, TeacherBottomNav.class));
>>>>>>> 9ff7573128c1aa289df5e0655ef23a01fd0ecaaa
                finish();
            }
        });

    }
    public void setUpRecyclerViews() {
        recyclerView1 = findViewById(R.id.recyclerview1);
        list1 = new ArrayList<>();
        mAdapter1 = new Recycler_View_Adapter(list1, getApplication());
        recyclerView1.setAdapter(mAdapter1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        recyclerView2 = findViewById(R.id.recyclerview2);
        list2 = new ArrayList<>();
        mAdapter2 = new Recycler_View_Adapter(list2, getApplication());
        recyclerView2.setAdapter(mAdapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        recyclerView3 = findViewById(R.id.recyclerview3);
        list3 = new ArrayList<>();
        mAdapter3 = new Recycler_View_Adapter(list3, getApplication());
        recyclerView3.setAdapter(mAdapter3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));

        recyclerView4 = findViewById(R.id.recyclerview4);
        list4 = new ArrayList<>();
        mAdapter4 = new Recycler_View_Adapter(list4, getApplication());
        recyclerView4.setAdapter(mAdapter4);
        recyclerView4.setLayoutManager(new LinearLayoutManager(this));

        recyclerView5 = findViewById(R.id.recyclerview5);
        list5 = new ArrayList<>();
        mAdapter5 = new Recycler_View_Adapter(list5, getApplication());
        recyclerView5.setAdapter(mAdapter5);
        recyclerView5.setLayoutManager(new LinearLayoutManager(this));
    }
    public void MyAlertDialog1 (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_add_schedule, null);
        builder.setView(dialogView);

        btnStartTime = (Button)dialogView.findViewById(R.id.btnStartTime);
        btnEndTime = (Button)dialogView.findViewById(R.id.btnEndTime);
        building = (Spinner)dialogView.findViewById(R.id.building);
        roomNo = dialogView.findViewById(R.id.roomNo);


        btnStartTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();

                //Toast.makeText(getApplicationContext(), "STRING MESSAGE", Toast.LENGTH_LONG).show();

                startHour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                startMinute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(teacherForm.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        btnStartTime.setText( selectedHour + ":" + String.format("%02d", selectedMinute));
                        startHour = selectedHour;
                        startMinute = selectedMinute;
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
                mTimePicker = new TimePickerDialog(teacherForm.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        btnEndTime.setText( selectedHour + ":" + String.format("%02d", selectedMinute));
                        endHour = selectedHour;
                        endMinute = selectedMinute;
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
                //Toast.makeText(getApplicationContext(), startHour, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), startMinute, Toast.LENGTH_SHORT).show();
                scheduleData scheduleDataObj = new scheduleData("Monday", Building, RoomNo, startHour, startMinute, endHour, endMinute);
                mAdapter1.insert(0,scheduleDataObj);
                Slot s = new Slot(RoomNo, Building, startHour,startMinute, endHour, endMinute );
                l1.add(s);

//
//        schedules.put("monday", Arrays.asList(s1, s2, s3));
//
//        Schedule s = new Schedule(schedules);
//
//        teacherFormData data = new teacherFormData("Ayush", "a@a.com", "one man army", "NA", "8687314230", "NA", "CC3", "251", "NA", "NA", s);
//
//        faculty.setValue(data);

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

    public void MyAlertDialog2 (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_add_schedule, null);
        builder.setView(dialogView);

        btnStartTime = (Button)dialogView.findViewById(R.id.btnStartTime);
        btnEndTime = (Button)dialogView.findViewById(R.id.btnEndTime);
        building = (Spinner)dialogView.findViewById(R.id.building);
        roomNo = dialogView.findViewById(R.id.roomNo);

        btnStartTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();

                Toast.makeText(getApplicationContext(), "STRING MESSAGE", Toast.LENGTH_LONG).show();

                startHour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                startMinute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(teacherForm.this, new TimePickerDialog.OnTimeSetListener() {
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
                mTimePicker = new TimePickerDialog(teacherForm.this, new TimePickerDialog.OnTimeSetListener() {
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
                scheduleData scheduleDataObj = new scheduleData("Tuesday", Building, RoomNo, startHour, startMinute, endHour, endMinute);
                mAdapter2.insert(0,scheduleDataObj);
                Slot s = new Slot(RoomNo, Building, startHour,startMinute, endHour, endMinute );
                l2.add(s);

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

    public void MyAlertDialog3 (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_add_schedule, null);
        builder.setView(dialogView);

        btnStartTime = (Button)dialogView.findViewById(R.id.btnStartTime);
        btnEndTime = (Button)dialogView.findViewById(R.id.btnEndTime);
        building = (Spinner)dialogView.findViewById(R.id.building);
        roomNo = dialogView.findViewById(R.id.roomNo);

        btnStartTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();

                Toast.makeText(getApplicationContext(), "STRING MESSAGE", Toast.LENGTH_LONG).show();

                startHour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                startMinute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(teacherForm.this, new TimePickerDialog.OnTimeSetListener() {
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
                mTimePicker = new TimePickerDialog(teacherForm.this, new TimePickerDialog.OnTimeSetListener() {
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
                scheduleData scheduleDataObj = new scheduleData("Wednesday", Building, RoomNo, startHour, startMinute, endHour, endMinute);
                mAdapter3.insert(0,scheduleDataObj);
                Slot s = new Slot(RoomNo, Building, startHour,startMinute, endHour, endMinute );
                l3.add(s);

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

    public void MyAlertDialog4 (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_add_schedule, null);
        builder.setView(dialogView);

        btnStartTime = (Button)dialogView.findViewById(R.id.btnStartTime);
        btnEndTime = (Button)dialogView.findViewById(R.id.btnEndTime);
        building = (Spinner)dialogView.findViewById(R.id.building);
        roomNo = dialogView.findViewById(R.id.roomNo);

        btnStartTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();

                Toast.makeText(getApplicationContext(), "STRING MESSAGE", Toast.LENGTH_LONG).show();

                startHour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                startMinute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(teacherForm.this, new TimePickerDialog.OnTimeSetListener() {
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
                mTimePicker = new TimePickerDialog(teacherForm.this, new TimePickerDialog.OnTimeSetListener() {
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
                scheduleData scheduleDataObj = new scheduleData("Thursday", Building, RoomNo, startHour, startMinute, endHour, endMinute);
                mAdapter4.insert(0,scheduleDataObj);
                Slot s = new Slot(RoomNo, Building, startHour,startMinute, endHour, endMinute );
                l4.add(s);
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

    public void MyAlertDialog5 (View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_add_schedule, null);
        builder.setView(dialogView);

        btnStartTime = (Button)dialogView.findViewById(R.id.btnStartTime);
        btnEndTime = (Button)dialogView.findViewById(R.id.btnEndTime);
        building = (Spinner)dialogView.findViewById(R.id.building);
        roomNo = dialogView.findViewById(R.id.roomNo);

        btnStartTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();

                Toast.makeText(getApplicationContext(), "STRING MESSAGE", Toast.LENGTH_LONG).show();

                startHour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                startMinute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(teacherForm.this, new TimePickerDialog.OnTimeSetListener() {
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
                mTimePicker = new TimePickerDialog(teacherForm.this, new TimePickerDialog.OnTimeSetListener() {
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
                scheduleData scheduleDataObj = new scheduleData("Friday", Building, RoomNo, startHour, startMinute, endHour, endMinute);
                mAdapter5.insert(0,scheduleDataObj);
                Slot s = new Slot(RoomNo, Building, startHour,startMinute, endHour, endMinute );
                l5.add(s);
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

    public void saveData() {
        final String teacher_name = name.getText().toString().trim();
        final String teacher_email = email.getText().toString().trim();
        final String teacher_department = String.valueOf(department.getSelectedItem());
        final String teacher_designation = designation.getText().toString().trim();
        final String teacher_roomNo = roomNo.getText().toString().trim();
        final String teacher_mobileNo = mobileNo.getText().toString().trim();
        final String teacher_officeNo = officeNo.getText().toString().trim();
        final String teacher_homepage = homepage.getText().toString().trim();
        //final String teacher_imageLink = uploadImage();
        final String teacher_imageLink = "hardcoded_image_link";
        final Schedule teacher_schedule = scheduleObj;

<<<<<<< HEAD



||||||| merged common ancestors
<<<<<<< HEAD
        teacherFormData teacher_info = new teacherFormData(teacher_name, teacher_email, teacher_designation, teacher_department, teacher_mobileNo, teacher_officeNo, Building, teacher_roomNo, teacher_homepage, teacher_imageLink, teacher_schedule);
=======
=======
>>>>>>> 9ff7573128c1aa289df5e0655ef23a01fd0ecaaa
        teacherFormData teacher_info = new teacherFormData(teacher_name, teacher_email, teacher_designation,
                teacher_department, teacher_mobileNo, teacher_officeNo, Building,
                teacher_roomNo, teacher_homepage, teacher_imageLink, scheduleObj);
<<<<<<< HEAD

||||||| merged common ancestors
>>>>>>> a8ec446bdd62010d9f57600879e9023e10f8ef9d
=======
>>>>>>> 9ff7573128c1aa289df5e0655ef23a01fd0ecaaa
        root = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        faculty = root.child("faculty");
        faculty.child(userId).setValue(teacher_info);

    }

    public void getImg(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    private void uploadImage() {


        final String teacher_name = name.getText().toString().trim();
        final String teacher_email = email.getText().toString().trim();
        final String teacher_department = String.valueOf(department.getSelectedItem());
        final String teacher_designation = designation.getText().toString().trim();
        final String teacher_roomNo = roomNo.getText().toString().trim();
        final String teacher_mobileNo = mobileNo.getText().toString().trim();
        final String teacher_officeNo = officeNo.getText().toString().trim();
        final String teacher_homepage = homepage.getText().toString().trim();
        final Schedule teacher_schedule = scheduleObj;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        if(selectedImage != null)
        {
            ref = storageReference.child("faculty").child(userId);

            ref.putFile(selectedImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    // getting image uri and converting into string
                                    Uri downloadUrl = uri;
                                    generatedFilePath = downloadUrl.toString();

                                    final String teacher_imageLink = generatedFilePath;

                                    teacherFormData teacher_info = new teacherFormData(teacher_name, teacher_email, teacher_designation,
                                            teacher_department, teacher_mobileNo, teacher_officeNo, Building,
                                            teacher_roomNo, teacher_homepage, teacher_imageLink, scheduleObj);
                                    root = FirebaseDatabase.getInstance().getReference();

                                    faculty = root.child("faculty");
                                    faculty.child(userId).setValue(teacher_info);

                                    Toast.makeText(teacherForm.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(teacherForm.this, TeacherBottomNav.class));
                                    finish();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //progressDialog.dismiss();
                            Toast.makeText(teacherForm.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            Toast.makeText(this, "SelectedImage is null", Toast.LENGTH_SHORT).show();
        }

    }
    /*private String uploadImage() {


        if(selectedImage != null)
        {

            final StorageReference ref = storageReference.child("faculty").child(userId);

            ref.putFile(selectedImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //progressDialog.dismiss();
                            generatedFilePath = ref.getDownloadUrl().toString();
                            Toast.makeText(teacherForm.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //progressDialog.dismiss();
                            Toast.makeText(teacherForm.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

            return generatedFilePath;
        } else {
            Toast.makeText(this, "SelectedImage is null", Toast.LENGTH_SHORT).show();
            return "";
        }

    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView Imview = (ImageView) findViewById(R.id.view_image);
            Imview.setImageBitmap(imageBitmap);

            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            selectedImage = getImageUri(getApplicationContext(), imageBitmap);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            Toast.makeText(StudentForm.this,"Here "+ getRealPathFromURI(selectedImage), Toast.LENGTH_LONG).show();
        }
        else */
        if(resultCode == Activity.RESULT_OK && requestCode == GALLERY_REQUEST) {
            selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ImageView carImage = (ImageView) findViewById(R.id.view_image);
                carImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                Log.i("TAG", "Some exception " + e);
            }
        }
    }
}
