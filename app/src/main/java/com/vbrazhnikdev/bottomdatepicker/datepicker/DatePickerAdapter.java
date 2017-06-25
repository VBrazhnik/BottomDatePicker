package com.vbrazhnikdev.bottomdatepicker.datepicker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.Calendar;

public class DatePickerAdapter extends FragmentStatePagerAdapter {

    private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    private int pagesCount;

    private int offset;

    private DateListener calendarListener;

    public DatePickerAdapter(FragmentManager fragmentManager, int pagesCount, int offset) {
        super(fragmentManager);
        this.pagesCount = pagesCount;
        this.offset = offset;
    }

    public void setCalendarListener(DateListener calendarListener) {
        this.calendarListener = calendarListener;
    }

    @Override
    public Fragment getItem(int i) {
        return createFragment(getPositionWithOffset(i));
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return pagesCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return CalendarDate.getFormattedMonthAndYear(calculateDateTime(getPositionWithOffset(position)).getMonth(),calculateDateTime(getPositionWithOffset(position)).getYear());
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    private MonthFragment createFragment(int month) {
        MonthFragment fragment = new MonthFragment();
        fragment.setMonthYear(calculateDateTime(month).getMonth(),calculateDateTime(month).getYear());
        fragment.setOnClickDate(calendarListener);
        return fragment;
    }

    private int getPositionWithOffset(int adapterPosition) {
        return adapterPosition - offset;
    }


    private CalendarDate calculateDateTime(int positionWithOffset) {

        Calendar calendar = Calendar.getInstance();
        if (positionWithOffset < 0) {
            calendar.add(Calendar.MONTH, positionWithOffset);
        } else if (positionWithOffset > 0) {
            calendar.add(Calendar.MONTH, positionWithOffset);
        }

        return new CalendarDate(1, calendar.get(Calendar.MONTH),calendar.get(Calendar.YEAR));
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


}