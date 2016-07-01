package com.ning.demosky.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import com.ning.demosky.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yorki on 2016/6/17.
 */
public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });

    }

    private String day;
    private String hour;
    private String minute;

    private void dialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomDatePickerDialog);

        final Dialog dialog = new Dialog(this, R.style.CustomDatePickerDialog);
        dialog.setTitle("Custom Dialog");

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_datepicker, null);

        dialog.setContentView(view);

        dialog.setCanceledOnTouchOutside(true);

        WheelView wheelViewDay = (WheelView) view.findViewById(R.id.wheel_view_day);
        wheelViewDay.setOffset(1);
        wheelViewDay.setItems(getDayData());
        wheelViewDay.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                super.onSelected(selectedIndex, item);
                day = item;
            }
        });

//        WheelView wheelViewHour = (WheelView) view.findViewById(R.id.wheel_view_hour);
//        wheelViewHour.setOffset(1);
//        wheelViewHour.setItems(getHourData());
//        wheelViewHour.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
//            @Override
//            public void onSelected(int selectedIndex, String item) {
//                super.onSelected(selectedIndex, item);
//                hour = item;
//            }
//        });
//
//        WheelView wheelViewMinute = (WheelView) view.findViewById(R.id.wheel_view_minute);
//        wheelViewMinute.setOffset(1);
//        wheelViewMinute.setItems(getMinuteData());
//        wheelViewMinute.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
//            @Override
//            public void onSelected(int selectedIndex, String item) {
//                super.onSelected(selectedIndex, item);
//                minute = item;
//            }
//        });


        ImageButton dialogImageButtonTrue = (ImageButton) view.findViewById(R.id.dialog_dashboard_date_accept);
        dialogImageButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Log.e("data ", day + " " + hour +  " " + minute);
            }
        });

        ImageButton dialogImageButtonFalse = (ImageButton) view.findViewById(R.id.dialog_dashboard_date_cancel);
        dialogImageButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });


//        builder.setTitle("Custom Dialog");
//        builder.setView(R.layout.dialog_datepicker);
//        Dialog dialog = builder.create();


        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //wlp.height = (int) (this.getWindowManager().getDefaultDisplay().getHeight() * 0.3);
        window.setAttributes(wlp);

        dialog.show();

    }

    private List<String> getDayData() {

        List<String> list = new ArrayList<>();
        list.add("今天");
        list.add("明天");

        return list;
    }

    private List<String> getHourData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            list.add(i + "点");
        }
        return list;
    }

    private List<String> getMinuteData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 61; i++) {
            list.add(i + "分");
        }
        return list;
    }
}
