package com.factrack.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.factrack.R;
import com.factrack.teacherData.TeacherData;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.MyViewHolder> {
    private Context context;
    private List<TeacherData> teacherList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, designation,building, roomNo;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            designation = view.findViewById(R.id.designation);
            image = view.findViewById(R.id.image);
            building = view.findViewById(R.id.building);
            roomNo = view.findViewById(R.id.roomNo);
        }
    }


    public TeacherAdapter(Context context, List<TeacherData> teacherList) {
        this.context = context;
        this.teacherList = teacherList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.teacher_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final TeacherData movie = teacherList.get(position);
        holder.name.setText(movie.getName());
        holder.designation.setText(movie.getDesignation());
        holder.building.setText(movie.getBuilding());
        holder.roomNo.setText(movie.getRoomNo());
        //TODO :
        Glide.with(context)
                .load(movie.getImage())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    public void insert(int position, TeacherData TeacherData) {
        teacherList.add(position, TeacherData);
        notifyItemInserted(position);
    }
}
