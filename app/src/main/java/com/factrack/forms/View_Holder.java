package com.factrack.forms;

import android.support.v7.widget.CardView;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.factrack.R;

public class View_Holder extends RecyclerView.ViewHolder{
    CardView cv;
    TextView day;
    TextView startTime,endTime, building, roomNo;

    View_Holder(View itemView)  {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        day = (TextView) itemView.findViewById(R.id.day);
        startTime = (TextView) itemView.findViewById(R.id.startTime);
        endTime = (TextView) itemView.findViewById(R.id.endTime);
        building= (TextView) itemView.findViewById(R.id.building);
        roomNo = (TextView) itemView.findViewById(R.id.roomNo);
    }
}
