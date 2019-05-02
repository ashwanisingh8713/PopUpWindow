package com.mbtechpro.popupwindow;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

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

        TextView deviceType_Txt = findViewById(R.id.deviceType_Txt);
        deviceType_Txt.setText("Device Type :: "+getResources().getString(R.string.type_device));

    }


    private void showGenderPopUp() {

        final int genderWidth = getResources().getDimensionPixelSize(R.dimen.genderWidth);
        final int genderHeight = getResources().getDimensionPixelSize(R.dimen.genderHeight);

        genderLayoutET.setOnClickListener(v->{

            int[] locationOnWindow = new int[2];

            genderLayout.getLocationInWindow(locationOnWindow);

            int XPadding = locationOnWindow[0];
            int YPadding = locationOnWindow[1];
            int deviceHeight = StandardPopupWindow.getDeviceHeight(MainActivity.this);
            int layoutHeight = genderLayout.getHeight();

            int finalBottomPadding = deviceHeight-(YPadding+layoutHeight)-layoutHeight*2;
            int finalXPadding = XPadding-YPadding-YPadding/2;

            List<String> strList = new ArrayList<>();
            strList.add("Male");
            strList.add("Female");
            strList.add("Tran");

            final StandardPopupWindow window = new StandardPopupWindow(MainActivity.this, strList, genderWidth, genderHeight);
            window.show(genderLayout, Gravity.BOTTOM, finalXPadding, finalBottomPadding);

//            window.show(genderLayout, Gravity.BOTTOM, genderXPadding, genderYPadding);

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
        final int genderWidth = getResources().getDimensionPixelSize(R.dimen.countryWidth);
        final int genderHeight = getResources().getDimensionPixelSize(R.dimen.countryHeight);

        countryET.setOnClickListener(v->{

            int[] locationOnWindow = new int[2];

            countryLayout.getLocationInWindow(locationOnWindow);

            int XPadding = locationOnWindow[0];
            int YPadding = locationOnWindow[1];
            int deviceHeight = StandardPopupWindow.getDeviceHeight(MainActivity.this);
            int layoutHeight = countryLayout.getHeight();

            int finalBottomPadding = deviceHeight-(YPadding+layoutHeight)-layoutHeight*2;
            int finalXPadding = XPadding-YPadding;

            List<String> strList = new ArrayList<>();
            strList.add("INDIA");
            strList.add("AUSTRALIA");
            strList.add("NEWZELAND");
            strList.add("SWITZERLAND");

            final StandardPopupWindow window = new StandardPopupWindow(MainActivity.this, strList, genderWidth, genderHeight);

            window.show(countryLayout, Gravity.BOTTOM, finalXPadding, finalBottomPadding);

//            window.show(countryET, Gravity.BOTTOM, genderXPadding, genderYPadding);

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
