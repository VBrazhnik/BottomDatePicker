package com.vbrazhnikdev.bottomdatepicker.datepicker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.vbrazhnikdev.bottomdatepicker.R;

public class MonthFragmentAdapter extends RecyclerView.Adapter<MonthFragmentAdapter.ViewHolder> {

    private int maxDay, firstDay, month, year;

    private int cellWidth;

    public MonthFragmentAdapter(int maxDay, int firstDay, int month, int year, int cellWidth) {
        this.maxDay = maxDay;
        this.firstDay = firstDay;
        this.month = month;
        this.year = year;
        this.cellWidth = cellWidth;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.month_view_cell, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (position > (firstDay - 2)) {
            int day = (position - (firstDay - 2));
            holder.dateText.setText(String.valueOf(day));
        } else {
            holder.dateText.setText("");
        }

        holder.cellLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position > (firstDay - 2)) {

                    int day = (position - (firstDay - 2));

                    CalendarDate date = new CalendarDate(day, month, year);

                    if (MonthFragment.staticClickInterface() != null) {
                        MonthFragment.staticClickInterface().onDateClicked(date);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (firstDay > 7) {
            firstDay = firstDay - 7;
        }
        return maxDay + firstDay - 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dateText;
        FrameLayout cellLayout;

        public ViewHolder(View view) {
            super(view);

            dateText = (TextView) view.findViewById(R.id.cell_date);
            cellLayout = (FrameLayout) view.findViewById(R.id.cell);

            cellLayout.getLayoutParams().width = cellWidth;
        }
    }

}

