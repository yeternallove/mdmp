package com.eternallove.mdmp.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.eternallove.mdmp.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/3/20 19:43
 */
public class RunOnUiThreadUtil {
    private static String APP_NAME;
    private static String NETWORK;

    public static void showToast(Context context, String content) {
        if (APP_NAME == null) {
            APP_NAME = context.getString(R.string.app_name);
        }
        Toast.makeText(context, APP_NAME + ":" + content, Toast.LENGTH_SHORT).show();
    }

    public static void showNetworkToast(Context context) {
        if (NETWORK == null) {
            NETWORK = context.getString(R.string.network);
        }
       showToast(context,NETWORK);
    }

    public static PopupWindow showPopup(Activity activity) {
        View mPopupView = activity.getLayoutInflater().inflate(R.layout.view_popupwindow, null);
        PopupWindow mPopupWindow = new PopupWindow(mPopupView, MATCH_PARENT, WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(activity.getResources(), (Bitmap) null));

        mPopupWindow.getContentView().setFocusableInTouchMode(true);
        mPopupWindow.getContentView().setFocusable(true);
        return mPopupWindow;
    }

    public static View.OnKeyListener getKeyListener(PopupWindow mPopupWindow){
        return (v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0
                    && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
                return true;
            }
            return false;
        };
    }
//    private void getDatePickerDialog() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        mDataPicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//
//                mCalendar.set(year, monthOfYear, dayOfMonth);
//                tvDay.setText(dateFormat.format(mCalendar.getTime()));
//            }
//        }, calendar.getTask(Calendar.YEAR), calendar.getTask(Calendar.MONTH), calendar.getTask(Calendar.DAY_OF_MONTH));
//        mDataPicker.getDatePicker().setMaxDate(System.currentTimeMillis());
//        mDataPicker.show();
//    }
//
//    private void getTimePickerDialog() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                mCalendar.set(Calendar.MINUTE, minute);
//                tvTime.setText(timeFormat.format(mCalendar.getTime()));
//            }
//        }, calendar.getTask(Calendar.HOUR_OF_DAY), calendar.getTask(Calendar.MINUTE), true);
//        mTimePicker.show();
//    }
}
