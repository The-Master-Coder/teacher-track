package com.factrack.studentView;


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
import com.factrack.containers.studentFormData;
import com.factrack.containers.teacherFormData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentView extends Fragment {


    private Context context;
    TextView name,email,hostel,mobile,rollNo,roomNo;
    CircleImageView image;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference(), faculty;
    private String userId;
    private Schedule teacherSchedule;
    public StudentView() {

    }

    public static com.factrack.studentView.StudentView newInstance() {
        com.factrack.studentView.StudentView fragment = new com.factrack.studentView.StudentView();
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
        View view = inflater.inflate(R.layout.activity_student_view,container,false);
        name = view.findViewById(R.id.student_name);
        rollNo = view.findViewById(R.id.student_rollno);
        hostel = view.findViewById(R.id.student_hostel);
        mobile = view.findViewById(R.id.student_mobile);
        email = view.findViewById((R.id.student_email));
        roomNo = view.findViewById(R.id.student_roomno);
        image = view.findViewById(R.id.student_image);



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
        faculty = root.child("student").child(userId);
//

        faculty.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentFormData student_info =  dataSnapshot.getValue(studentFormData.class);
                name.setText(student_info.name);
                rollNo.setText(student_info.roomNo);
                hostel.setText(student_info.hostel);
                mobile.setText(student_info.mobileNo);
                email.setText(student_info.email);
                roomNo.setText(student_info.roomNo);
                Glide.with(context)
                        .load(student_info.imgLink)
                        .apply(RequestOptions.circleCropTransform())
                        .into(image);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
