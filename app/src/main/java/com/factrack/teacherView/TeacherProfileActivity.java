package com.factrack.teacherView;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.factrack.R;
import com.factrack.containers.teacherFormData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeacherProfileActivity extends AppCompatActivity {

    Button monday, tuesday, wednesday, thursday, friday;
    TextView name,designation,address,phone,mobile,email,homepage;
    CircleImageView image;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference(), faculty;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view);
        Intent intent = getIntent();
        userId = intent.getStringExtra("uid");
        monday = findViewById(R.id.btn_monday);
        tuesday = findViewById(R.id.btn_tuesday);
        wednesday = findViewById(R.id.btn_wednesday);
        thursday = findViewById(R.id.btn_thursday);
        friday = findViewById(R.id.btn_friday);
        name = findViewById(R.id.faculty_name);
        designation = findViewById(R.id.faculty_designation);
        address = findViewById(R.id.faculty_address);
        phone = findViewById(R.id.faculty_phone);
        mobile = findViewById(R.id.faculty_mobile);
        email = findViewById((R.id.faculty_email));
        homepage = findViewById(R.id.faculty_homepage);
        image = findViewById(R.id.profile_image);
        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> mcategories = new ArrayList<String>();
                mcategories.add("9:00 AM - 11:00 AM\t\t5207 CC3");
                mcategories.add("12:00 AM - 1:00 PM\t\t5005 CC3");
                mcategories.add("4:00 PM - 6:00 PM\t\t5054 CC3");
                //Create sequence of items
                final CharSequence[] Categories = mcategories.toArray(new String[mcategories.size()]);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(TeacherProfileActivity.this);
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
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(TeacherProfileActivity.this);
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
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(TeacherProfileActivity.this);
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
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(TeacherProfileActivity.this);
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
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(TeacherProfileActivity.this);
                dialogBuilder.setTitle("Friday");
                DialogInterface.OnClickListener listener = null;
                dialogBuilder.setItems(Categories,  listener);
                //Create alert dialog object via builder
                AlertDialog alertDialogObject = dialogBuilder.create();
                //Show the dialog
                alertDialogObject.show();
            }
        });

        faculty = root.child("faculty").child(userId);

        faculty.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                teacherFormData teacher_info =  dataSnapshot.getValue(teacherFormData.class);
                name.setText(teacher_info.name);
                designation.setText(teacher_info.designation);
                address.setText(teacher_info.building + " (" + teacher_info.roomNo + ")");
                phone.setText(teacher_info.officeNo);
                mobile.setText(teacher_info.mobileNo);
                email.setText(teacher_info.email);
                homepage.setText(teacher_info.homepage);

                Glide.with(TeacherProfileActivity.this)
                        .load(teacher_info.imageLink)
                        .apply(RequestOptions.circleCropTransform())
                        .into(image);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}