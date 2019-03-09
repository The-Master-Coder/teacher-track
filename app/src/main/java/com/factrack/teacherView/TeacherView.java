package com.factrack.teacherView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.factrack.R;
import com.factrack.containers.Schedule;
import com.factrack.containers.Slot;
import com.factrack.containers.teacherFormData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeacherView extends Fragment {

    Button monday, tuesday, wednesday, thursday, friday;
    private Context context;
    TextView name,designation,address,phone,mobile,email,homepage;
    CircleImageView image;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference(), faculty;
    private String userId;
    private Schedule teacherSchedule;
    public TeacherView() {

    }

    public static TeacherView newInstance() {
        TeacherView fragment = new TeacherView();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {

      //  setContentView(R.layout.activity_teacher_view);
        View view = inflater.inflate(R.layout.activity_teacher_view,container,false);
        monday = view.findViewById(R.id.btn_monday);
        tuesday = view.findViewById(R.id.btn_tuesday);
        wednesday = view.findViewById(R.id.btn_wednesday);
        thursday = view.findViewById(R.id.btn_thursday);
        friday = view.findViewById(R.id.btn_friday);
        name = view.findViewById(R.id.faculty_name);
        designation = view.findViewById(R.id.faculty_designation);
        address = view.findViewById(R.id.faculty_address);
        phone = view.findViewById(R.id.faculty_phone);
        mobile = view.findViewById(R.id.faculty_mobile);
        email = view.findViewById((R.id.faculty_email));
        homepage = view.findViewById(R.id.faculty_homepage);
        image = view.findViewById(R.id.profile_image);

        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Slot> monday_slots = teacherSchedule.schedules.get("monday");
                List<String> mcategories = new ArrayList<String>();
                if(monday_slots != null) {
                    for (int i = 0; i < monday_slots.size(); i++) {
                        String startTime = Integer.toString(monday_slots.get(i).startHour) + ":" + Integer.toString(monday_slots.get(i).startMinute);
                        String endTime = Integer.toString(monday_slots.get(i).endHour) + ":" + Integer.toString(monday_slots.get(i).endMinute);
                        String roomNo = monday_slots.get(i).roomNo;
                        String building = monday_slots.get(i).building;
                        String slot = startTime + " - " + endTime + "\t\t" + roomNo + " " + building;
                        mcategories.add(slot);
                    }
                }
                //Create sequence of items
                final CharSequence[] Categories = mcategories.toArray(new String[mcategories.size()]);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
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
                List<Slot> tuesday_slots = teacherSchedule.schedules.get("tuesday");
                List<String> mcategories = new ArrayList<String>();
                if(tuesday_slots != null) {
                    for (int i = 0; i < tuesday_slots.size(); i++) {
                        String startTime = Integer.toString(tuesday_slots.get(i).startHour) + ":" + Integer.toString(tuesday_slots.get(i).startMinute);
                        String endTime = Integer.toString(tuesday_slots.get(i).endHour) + ":" + Integer.toString(tuesday_slots.get(i).endMinute);
                        String roomNo = tuesday_slots.get(i).roomNo;
                        String building = tuesday_slots.get(i).building;
                        String slot = startTime + " - " + endTime + "\t\t" + roomNo + " " + building;
                        mcategories.add(slot);
                    }
                }
                //Create sequence of items
                final CharSequence[] Categories = mcategories.toArray(new String[mcategories.size()]);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
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
                List<Slot> wednesday_slots = teacherSchedule.schedules.get("wednesday");
                List<String> mcategories = new ArrayList<String>();
                if(wednesday_slots != null) {
                    for (int i = 0; i < wednesday_slots.size(); i++) {
                        String startTime = Integer.toString(wednesday_slots.get(i).startHour) + ":" + Integer.toString(wednesday_slots.get(i).startMinute);
                        String endTime = Integer.toString(wednesday_slots.get(i).endHour) + ":" + Integer.toString(wednesday_slots.get(i).endMinute);
                        String roomNo = wednesday_slots.get(i).roomNo;
                        String building = wednesday_slots.get(i).building;
                        String slot = startTime + " - " + endTime + "\t\t" + roomNo + " " + building;
                        mcategories.add(slot);
                    }
                }
                //Create sequence of items
                final CharSequence[] Categories = mcategories.toArray(new String[mcategories.size()]);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
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
                List<Slot> thursday_slots = teacherSchedule.schedules.get("thursday");
                List<String> mcategories = new ArrayList<String>();
                if(thursday_slots != null) {
                    for(int i=0; i<thursday_slots.size(); i++) {
                        String startTime = Integer.toString(thursday_slots.get(i).startHour) + ":" + Integer.toString(thursday_slots.get(i).startMinute);
                        String endTime = Integer.toString(thursday_slots.get(i).endHour) + ":" + Integer.toString(thursday_slots.get(i).endMinute);
                        String roomNo = thursday_slots.get(i).roomNo;
                        String building = thursday_slots.get(i).building;
                        String slot = startTime + " - " + endTime +"\t\t" + roomNo + " "+ building;
                        mcategories.add(slot);

                    }
                }
                //Create sequence of items
                final CharSequence[] Categories = mcategories.toArray(new String[mcategories.size()]);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
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
                List<Slot> friday_slots = teacherSchedule.schedules.get("friday");
                List<String> mcategories = new ArrayList<String>();
                if(friday_slots != null) {
                    for (int i = 0; i < friday_slots.size(); i++) {
                        String startTime = Integer.toString(friday_slots.get(i).startHour) + ":" + Integer.toString(friday_slots.get(i).startMinute);
                        String endTime = Integer.toString(friday_slots.get(i).endHour) + ":" + Integer.toString(friday_slots.get(i).endMinute);
                        String roomNo = friday_slots.get(i).roomNo;
                        String building = friday_slots.get(i).building;
                        String slot = startTime + " - " + endTime + "\t\t" + roomNo + " " + building;
                        mcategories.add(slot);
                    }
                }
                //Create sequence of items
                final CharSequence[] Categories = mcategories.toArray(new String[mcategories.size()]);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                dialogBuilder.setTitle("Friday");
                DialogInterface.OnClickListener listener = null;
                dialogBuilder.setItems(Categories,  listener);
                //Create alert dialog object via builder
                AlertDialog alertDialogObject = dialogBuilder.create();
                //Show the dialog
                alertDialogObject.show();
            }
        });

        setRetainInstance(true);
        return  view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = getActivity();

    }
    public void init() {
        userId = user.getUid();
        faculty = root.child("faculty").child(userId);
//        Slot s1 = new Slot("251", "CC3", 2,35, 3, 20 );
//        Slot s2 = new Slot("251", "CC3", 4,45, 6, 40 );
//        Slot s3 = new Slot("251", "CC3", 7,55, 8, 60 );
//        Map<String, List<Slot>> schedules = new HashMap<String, List<Slot>>();;
//        schedules.put("monday", Arrays.asList(s1, s2, s3));
//
//        Schedule s = new Schedule(schedules);
//
//        teacherFormData data = new teacherFormData("Ayush", "a@a.com", "one man army", "NA", "8687314230", "NA", "CC3", "251", "NA", "NA", s);
//
//        faculty.setValue(data);

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
                teacherSchedule = teacher_info.schedule;
                Glide.with(context)
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
