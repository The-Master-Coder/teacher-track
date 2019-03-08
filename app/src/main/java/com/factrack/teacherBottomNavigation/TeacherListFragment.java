package com.factrack.teacherBottomNavigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.factrack.R;
import com.factrack.recyclerView.TeacherAdapter;
import com.factrack.teacherData.TeacherData;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeacherListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeacherListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeacherListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = TeacherListFragment.class.getSimpleName();

    // TODO: Rename and change types of parameters
    private RecyclerView recyclerView;
    private List<TeacherData> teacherList;
    private TeacherAdapter mAdapter;

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
    public static TeacherListFragment newInstance(String param1, String param2) {
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
        teacherList = new ArrayList<>();
        mAdapter = new TeacherAdapter(getActivity(),teacherList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        // TODO : add animation
        recyclerView.setNestedScrollingEnabled(false);

        fetchTeacherList();

        return view;
    }

    private void fetchTeacherList() {

        for(int i = 0;i < 10;i++) {
            mAdapter.insert(0,new TeacherData("nidheesh","https://www.gstatic.com/webp/gallery/1.jpg","student","bh1","259"));
        }

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
