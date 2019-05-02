package com.mbtechpro.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.List;


public class StandardPopupWindow extends android.widget.PopupWindow {
    private ListView mListView;
    private View popupView;
    private OnStandardPopupItemSelect mOnStandardPopupItemSelect;

    public interface OnStandardPopupItemSelect {
        void onStandardPopupItemSelect(String standard);

    }

    public void setOnStandardPopupItemSelect(OnStandardPopupItemSelect onStandardPopupItemSelect) {
        mOnStandardPopupItemSelect = onStandardPopupItemSelect;
    }

    public StandardPopupWindow(Context context, List<String> standardList, int widht, int height) {
        super(context);
        popupView = LayoutInflater.from(context).inflate(R.layout.popup_layout,null);
        setContentView(popupView);

        mListView = popupView.findViewById(R.id.listView);

        if(widht != -1) {
            int w = (int) pxFromDp(context, widht);
            setWidth(w);
        } else {
            setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        }
        if(height != -1) {
            int h = (int) pxFromDp(context, height);
            setHeight(h);
        } else {
            setHeight(getDeviceHeight((Activity)context)/2);
        }

        // Closes the popup window when touch outside of it - when looses focus
        setOutsideTouchable(true);
        setFocusable(true);

        // Removes default black background
        setBackgroundDrawable(new BitmapDrawable());

        StandardAdapter adapter = new StandardAdapter(context, R.layout.item_standard_list_popup, standardList);
        mListView.setAdapter(adapter);



    } // End constructor

    // Attaches the view to its parent anchor-view at position x and y
    public void show(View anchor, int gravity, int x, int y) {
        showAtLocation(anchor, gravity, x, y);
    }


    private class StandardAdapter extends ArrayAdapter<String> {

        private List<String> mStandardList;
        private LayoutInflater inflater=null;

        public StandardAdapter(@NonNull Context context, int resource, List<String> standardList) {
            super(context, resource, standardList);
            mStandardList = standardList;
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            if(mStandardList == null) {
                return 0;
            }
            return mStandardList.size();
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View vi=convertView;
            if(convertView==null) {
                vi = inflater.inflate(R.layout.item_standard_list_popup, null);
            }

            TextView title = (TextView)vi.findViewById(R.id.text);

            title.setText(getItem(position));

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnStandardPopupItemSelect != null) {
                        mOnStandardPopupItemSelect.onStandardPopupItemSelect(getItem(position));
                    }
                }
            });

            return vi;
        }


    }


    public static float dpFromPx(Context context, float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static int getDeviceHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }


    public static int getDeviceWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

}
