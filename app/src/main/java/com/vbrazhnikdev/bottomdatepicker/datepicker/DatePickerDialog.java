package com.vbrazhnikdev.bottomdatepicker.datepicker;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.vbrazhnikdev.bottomdatepicker.R;

public class DatePickerDialog extends DialogFragment {

    private static final int DEFAULT_PAGES_COUNT = 200 * 12; //from 1900 to 2100

    private static final int DEFAULT_OFFSET = CalendarDate.getCurrentDate().getYear() * 12 + CalendarDate.getCurrentDate().getMonth() - 1900 * 12;

    private ViewPager pager;

    private int offset = DEFAULT_OFFSET;

    private DateListener calendarListener;

    @NonNull
    @Override
    public DatePicker onCreateDialog(Bundle savedInstanceState) {
        return new DatePicker(getContext());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.calendar, container, false);

        pager = (ViewPager) view.findViewById(R.id.calendar_pager);

        setAdapter();

        return view;
    }

    public void setCalendarListener(DateListener calendarListener) {
        this.calendarListener = calendarListener;
    }

    public DateListener getCalendarListener() {
        return calendarListener;
    }

    private void setAdapter() {
        DatePickerAdapter adapter = new DatePickerAdapter(getChildFragmentManager(), DEFAULT_PAGES_COUNT, offset);
        adapter.setCalendarListener(calendarListener);
        pager.setAdapter(adapter);
        pager.setCurrentItem(offset, false);
    }

    private class DatePicker extends Dialog {

        public DatePicker(Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setCancelable(true);
            setCanceledOnTouchOutside(true);
            getWindow().setGravity(Gravity.BOTTOM);
            getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        }

    }
}
