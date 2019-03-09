package com.factrack.forms;

import android.support.v7.widget.CardView;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.factrack.R;

public class View_Holder extends RecyclerView.ViewHolder{
    CardView cv;

    TextView startTime,endTime, building, roomNo;

    View_Holder(View itemView)  {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);

        startTime =  itemView.findViewById(R.id.startTime);
        endTime =  itemView.findViewById(R.id.endTime);
        building =  itemView.findViewById(R.id.building);
        roomNo =  itemView.findViewById(R.id.roomNo);
    }
}
