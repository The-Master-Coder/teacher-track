package com.factrack.recyclerView;

import android.view.View;

public interface teacherClickListener {
    public void onClick(View view, int position);

    public void onLongClick(View view, int position);
}