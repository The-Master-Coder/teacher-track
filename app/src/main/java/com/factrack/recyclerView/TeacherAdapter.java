package com.factrack.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Filterable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.factrack.R;
import com.factrack.teacherBottomNavigation.TeacherListFragment;
import com.factrack.teacherData.TeacherData;

import java.util.ArrayList;
import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.MyViewHolder> implements  Filterable{
    private Context context;
    private List<TeacherData> teacherList ;
    private List<TeacherData> teacherListFiltered ;

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


    public TeacherAdapter(Context context, List<TeacherData> teacherList ) {
        this.context = context;
        this.teacherListFiltered = teacherList;
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
        final TeacherData teacher = teacherListFiltered.get(position);
        holder.name.setText( teacher.getName());
        holder.designation.setText( teacher.getDesignation());
        holder.building.setText( teacher.getBuilding());
        holder.roomNo.setText( teacher.getRoomNo());
        //TODO :
        Glide.with(context)
                .load( teacher.getImage())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return teacherListFiltered.size();
    }

   /* public void insert(int position, TeacherData teacher) {
        teacherListFiltered.add(position, teacher);
        notifyItemInserted(position);
    }*/

   /* public void remove(TeacherData teacher) {

        int position = teacherList.indexOf(teacher);
        if(position != -1) {
            teacherList.remove(position);
            notifyItemRemoved(position);
        }
    }
*/
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    teacherListFiltered = teacherList;
                } else {
                    List<TeacherData> filteredList = new ArrayList<>();
                    for (TeacherData row : teacherList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    teacherListFiltered = filteredList;

                }
                Log.e("adp","" + teacherListFiltered.size());
                FilterResults filterResults = new FilterResults();
                filterResults.values = teacherListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                teacherListFiltered = (ArrayList<TeacherData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
