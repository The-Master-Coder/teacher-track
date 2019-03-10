package com.factrack.teacherBottomNavigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.factrack.R;
import com.factrack.containers.Slot;
import com.factrack.containers.teacherFormData;
import com.factrack.recyclerView.TeacherAdapter;
import com.factrack.recyclerView.teacherClickListener;
import com.factrack.recyclerView.teacherTouchListener;
import com.factrack.studentBottomNavigation.StudentBottomNav;
import com.factrack.teacherData.TeacherData;
import com.factrack.teacherView.TeacherProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeacherListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeacherListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeacherListFragment extends Fragment   {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = TeacherListFragment.class.getSimpleName();

    // TODO: Rename and change types of parameters
    private RecyclerView recyclerView;
    private List<TeacherData> teacherList;
    private TeacherAdapter mAdapter;
    private TextView mEmptyView;
    private Context context;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference(), faculty;
    private String userId;
  //  private OnFragmentInteractionListener mListener;

    public TeacherListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeacherListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeacherListFragment newInstance() {
        TeacherListFragment fragment = new TeacherListFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teacher_list,container,false);
        recyclerView = view.findViewById(R.id.recycler_view);
        mEmptyView = view.findViewById(R.id.emptyView);

       /* teacherList = new ArrayList<>();
        mAdapter = new TeacherAdapter(getActivity(),teacherList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        // TODO : add animation
        recyclerView.setNestedScrollingEnabled(false);

        fetchTeacherList();
        recyclerViewEmpty();*/
        setRetainInstance(true);
        return view;
    }

    private void recyclerViewEmpty() {
        if (teacherList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
        }
    }
    private void setCal(Calendar cal,int hour,int minute) {
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
    }
    private void fetchTeacherList() {

        userId = user.getUid();
        faculty = root.child("faculty");
        faculty.keepSynced(true);

        faculty.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final ArrayList <TeacherData > items = new ArrayList<>();
                Calendar curr = Calendar.getInstance();
                Map<String, List<Slot>> schedule ;
                Map <Integer,String> mapDay = new HashMap<>();
                mapDay.put(1,"monday");
                mapDay.put(2,"tuesday");
                mapDay.put(3,"wednesday");
                mapDay.put(6,"saturday");
                mapDay.put(4,"thursday");
                mapDay.put(5,"friday");
                mapDay.put(7,"sunday");

                for(DataSnapshot faculties: dataSnapshot.getChildren()) {
                    teacherFormData teacher_info = faculties.getValue(teacherFormData.class);
                    if(teacher_info.schedule != null) {
                        schedule = teacher_info.schedule.schedules;
                    }
                    else
                        continue;
                    int day = curr.DAY_OF_WEEK;
                    Calendar start = Calendar.getInstance();
                    Calendar end = Calendar.getInstance();
                    Log.e(mapDay.get(day),teacher_info.name);
                    List <Slot> slots = schedule.get(mapDay.get(day));
                    boolean flag = false;
                    if(slots != null) {
                        for (int i = 0; i < slots.size(); i++) {
                            setCal(start, slots.get(i).startHour, slots.get(i).startMinute);
                            setCal(end, slots.get(i).endHour, slots.get(i).endMinute);

                            if (curr.after(start) && curr.before(end)) {
                                flag = true;
                                break;
                            }
                        }
                    }

                    if(flag)
                        items.add(0,new TeacherData(teacher_info.name,teacher_info.imgLink,teacher_info.designation,teacher_info.building,teacher_info.roomNo,faculties.getKey()) );
                    //Log.e("name",teacher_info.imageLink);
                }
                teacherList.clear();
                teacherList.addAll(items);
                // Log.e("list",""+ teacherList.size());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
      /*  for(int i = 0;i < 3;i++) {
            items.add(0,new TeacherData("nidheesh","https://www.gstatic.com/webp/gallery/1.jpg","student","bh1","259"));
        }
        for(int i = 0;i < 3;i++) {
            items.add(0,new TeacherData("shreyansh","https://www.gstatic.com/webp/gallery/1.jpg","student","bh1","259"));

        }
        for(int i = 0;i < 3;i++) {
            items.add(0,new TeacherData("ayush","https://www.gstatic.com/webp/gallery/1.jpg","student","bh1","259"));

        }
        for(int i = 0;i < 3;i++) {
            items.add(0,new TeacherData("abhishek","https://www.gstatic.com/webp/gallery/1.jpg","student","bh1","259"));

        }
        */

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        init();
    }

    public void init() {
        teacherList = new ArrayList<>();
        mAdapter = new TeacherAdapter(getActivity(),teacherList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        // TODO : add animation
        recyclerView.setNestedScrollingEnabled(false);
        setUpRecyclerViewClick();
        fetchTeacherList();
        //recyclerViewEmpty();
    }

    void setUpRecyclerViewClick() {

        recyclerView.addOnItemTouchListener(new teacherTouchListener(getActivity(), recyclerView, new teacherClickListener() {
            @Override
            public void onClick(View view, int position) {
                TeacherAdapter.MyViewHolder viewHolder = (TeacherAdapter.MyViewHolder) recyclerView.findViewHolderForAdapterPosition(position);
                TeacherData clickedTeacher = teacherList.get(position);
                Intent intent = new Intent(getActivity(), TeacherProfileActivity.class);
                intent.putExtra("uid",clickedTeacher.getUid());
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = getActivity();

    }
    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.action_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView sv = new SearchView(((TeacherBottomNav) getActivity()).getSupportActionBar().getThemedContext());

        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, sv);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println("search query submit");
                mAdapter.getFilter().filter(newText);
               // recyclerViewEmpty();
                Log.e("madapter","" + mAdapter.getItemCount());
                return true;
            }
        });
    }
    public void onResume() {
        super.onResume();
    }
   /* // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /* * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
   /*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    */
}
