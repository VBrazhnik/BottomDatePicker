package com.vbrazhnikdev.bottomdatepicker;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.vbrazhnikdev.bottomdatepicker.datepicker.CalendarDate;
import com.vbrazhnikdev.bottomdatepicker.datepicker.DateListener;
import com.vbrazhnikdev.bottomdatepicker.datepicker.DatePickerDialog;


public class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity);

        final Button pickDateButton = (Button) findViewById(R.id.pick_date_button);
        pickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog fragment = new DatePickerDialog();

                DateListener listener = new DateListener() {
                    @Override
                    public void onDateClicked(CalendarDate date) {
                        pickDateButton.setText(date.toFormattedString());
                        fragment.dismiss();
                    }
                };

                fragment.setCalendarListener(listener);
                FragmentManager fm = getSupportFragmentManager();
                fragment.show(fm, "Date Picker");
            }
        });
    }
}
