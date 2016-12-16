package com.example.ronie.tooltip.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ronie.tooltip.R;

public class TooltipView extends FrameLayout implements
        ViewTreeObserver.OnScrollChangedListener {

    private static final String TAG = TooltipView.class.getSimpleName();

    private static final int COORD_Y = 1;
    private static final int COORD_X = 0;

    private View anchor;
    private Context context;
    private View layout;
    private TextView textView;
    private ImageView background;

    public TooltipView(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    public void init(){
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(layoutParams);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        layout = inflater.inflate(R.layout.tooltip, this, false);
        addView(layout);

        //textView = (TextView) layout.findViewById(R.id.text_view);
        //background = (ImageView) layout.findViewById(R.id.background);
    }

    public void setAnchor(@NonNull View anchor) {
        this.anchor = anchor;
    }

    public void setTitle(String title) {
        textView.setText(title);
    }

    public void show() {
        setVisibility(View.VISIBLE);
    }

    public void hide() {
        setVisibility(View.GONE);
    }

    public void setBackgroundColor(int color){
       //background.setBackgroundColor(color);
        super.setBackgroundColor(color);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnScrollChangedListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnScrollChangedListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int i, int i1, int i2, int i3) {
        if(!changed){
            return ;
        }

        int width = getWidth();
        int height = getHeight();

        int[] actualLocation = new int[2];
        anchor.getLocationOnScreen(actualLocation);

        int x = actualLocation[COORD_X];
        int y = actualLocation[COORD_Y];
        Log.d(TAG, String.format("#onLayout# width:%s height:%s x:%s y:%s", width,
                height, x, y));

        setLeft(x);
        setRight(x + width);
        setTop(y);
        setBottom(y + height);

        Log.d(TAG, "**************************************************************************");
        Log.d(TAG, String.format(
                "#Anchor{onLayout}# [left:%s, right:%s, top:%s, bottom:%s, x:%s, y:%s]",
                anchor.getLeft(), anchor.getRight(), anchor.getTop(), anchor.getBottom(), x, y));
        Log.d(TAG, String.format("#Tooltip{onLayout}# [left:%s, right:%s, top:%s, bottom:%s]",
                getLeft(), getRight(), getTop(), getBottom()));
        Log.d(TAG, "**************************************************************************");
    }

    @Override
    public void onScrollChanged() {
        int width = getWidth();
        int height = getHeight();

        int[] actualLocation = new int[2];
        anchor.getLocationOnScreen(actualLocation);

        int x = actualLocation[COORD_X];
        int y = actualLocation[COORD_Y];
        Log.d(TAG, String.format("#onLayoutChange# width:%s height:%s x:%s y:%s", width,
                height, x, y));

        setLeft(x);
        setRight(x + width);
        setTop(y);
        setBottom(y + height);

        Log.d(TAG, "**************************************************************************");
        Log.d(TAG, String.format(
                "#Anchor{onLayout}# [left:%s, right:%s, top:%s, bottom:%s, x:%s, y:%s]",
                anchor.getLeft(), anchor.getRight(), anchor.getTop(), anchor.getBottom(), x, y));
        Log.d(TAG, String.format("#Tooltip{onLayout}# [left:%s, right:%s, top:%s, bottom:%s]",
                getLeft(), getRight(), getTop(), getBottom()));
        Log.d(TAG, "**************************************************************************");
    }
}
