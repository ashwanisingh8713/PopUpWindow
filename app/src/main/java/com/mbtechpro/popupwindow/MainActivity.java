package com.mbtechpro.popupwindow;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout countryLayout;
    private TextInputEditText countryET;

    private TextInputLayout genderLayout;
    private TextInputEditText genderLayoutET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genderLayout = findViewById(R.id.genderLayout);
        genderLayoutET = findViewById(R.id.genderLayoutET);

        countryLayout = findViewById(R.id.countryLayout);
        countryET = findViewById(R.id.countryET);

        countryET.setFocusable(false);
        countryET.setClickable(true);

        genderLayoutET.setFocusable(false);
        genderLayoutET.setClickable(true);


        showGenderPopUp();
        showCountryPopUp();

    }


    private void showGenderPopUp() {
        final int genderWidth = 180;
        final int genderHeight = 120;
        final int genderXPadding = -280;
        final int genderYPadding = 1000;

        genderLayoutET.setOnClickListener(v->{

            int[] tableLayoutCorners = new int[2];

            genderLayoutET.getLocationInWindow(tableLayoutCorners);

            List<String> strList = new ArrayList<>();
            strList.add("Male");
            strList.add("Female");
            strList.add("Tran");
//            strList.add("TTTTT");
//            strList.add("TTTTT");
//            strList.add("TTTTT");

            final StandardPopupWindow window = new StandardPopupWindow(MainActivity.this, strList, genderWidth, genderHeight);
//            window.show(genderLayoutET, Gravity.BOTTOM, -500,
//                    CommonUtil.getDeviceHeight(getActivity())-tableLayoutCorners[1]);

            window.show(genderLayout, Gravity.BOTTOM, genderXPadding, genderYPadding);

            window.setOnStandardPopupItemSelect(new StandardPopupWindow.OnStandardPopupItemSelect() {
                @Override
                public void onStandardPopupItemSelect(String standard) {
                    genderLayoutET.setText(standard);
                    window.dismiss();
                }
            });

        });
    }


    private void showCountryPopUp() {
        final int genderWidth = 180;
        final int genderHeight = 120;
        final int genderXPadding = -280;
        final int genderYPadding = 1000;

        countryET.setOnClickListener(v->{

            int[] tableLayoutCorners = new int[2];

            countryET.getLocationInWindow(tableLayoutCorners);

            List<String> strList = new ArrayList<>();
            strList.add("INDIA");
            strList.add("AUSTRALIA");
            strList.add("NEWZELAND");
            strList.add("SWITZERLAND");

            final StandardPopupWindow window = new StandardPopupWindow(MainActivity.this, strList, genderWidth, genderHeight);
//            window.show(genderLayoutET, Gravity.BOTTOM, -500,
//                    CommonUtil.getDeviceHeight(getActivity())-tableLayoutCorners[1]);

            window.show(countryET, Gravity.BOTTOM, genderXPadding, genderYPadding);

            window.setOnStandardPopupItemSelect(new StandardPopupWindow.OnStandardPopupItemSelect() {
                @Override
                public void onStandardPopupItemSelect(String standard) {
                    countryET.setText(standard);
                    window.dismiss();
                }
            });

        });
    }
}
