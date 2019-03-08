package com.factrack.teacherView;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import com.factrack.R;

public class TeacherView extends Fragment {

    Button monday, tuesday, wednesday, thursday, friday;
    private Context context;

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

        return  view;
    }
}
