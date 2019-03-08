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
                List<String> mcategories = new ArrayList<String>();
                mcategories.add("9:00 AM - 11:00 AM\t\t5207 CC3");
                mcategories.add("12:00 AM - 1:00 PM\t\t5005 CC3");
                mcategories.add("4:00 PM - 6:00 PM\t\t5054 CC3");
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
                List<String> mcategories = new ArrayList<String>();
                mcategories.add("9:00 AM - 11:00 AM\t\t5207 CC3");
                mcategories.add("12:00 AM - 1:00 PM\t\t5005 CC3");
                mcategories.add("4:00 PM - 6:00 PM\t\t5054 CC3");
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
                List<String> mcategories = new ArrayList<String>();
                mcategories.add("9:00 AM - 11:00 AM\t\t5207 CC3");
                mcategories.add("12:00 AM - 1:00 PM\t\t5005 CC3");
                mcategories.add("4:00 PM - 6:00 PM\t\t5054 CC3");
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
                List<String> mcategories = new ArrayList<String>();
                mcategories.add("9:00 AM - 11:00 AM\t\t5207 CC3");
                mcategories.add("12:00 AM - 1:00 PM\t\t5005 CC3");
                mcategories.add("4:00 PM - 6:00 PM\t\t5054 CC3");
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
                List<String> mcategories = new ArrayList<String>();
                mcategories.add("9:00 AM - 11:00 AM\t\t5207 CC3");
                mcategories.add("12:00 AM - 1:00 PM\t\t5005 CC3");
                mcategories.add("4:00 PM - 6:00 PM\t\t5054 CC3");
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
