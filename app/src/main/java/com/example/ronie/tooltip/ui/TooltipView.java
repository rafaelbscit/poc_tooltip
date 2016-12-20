package com.example.ronie.tooltip.ui;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import static android.R.attr.width;

public class TooltipView extends TextView implements
        //View.OnLayoutChangeListener,
        ViewTreeObserver.OnScrollChangedListener {

    private static final String TAG = TooltipView.class.getSimpleName();

    private static final int COORD_Y = 1;
    private static final int COORD_X = 0;
    private static final int MARGIN_LEFT = 30;
    private static final int MARGIN_RIGHT = 30;
    private static final int MARGIN_TOP = -30;

    private View anchor;
    private Context context;

    public TooltipView(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    public void init() {
    }

    public void setAnchor(@NonNull View anchor) {
        this.anchor = anchor;
    }

    public void setTitle(String title) {
        setText(title);
    }

    public void show() {
        setVisibility(View.VISIBLE);
    }

    public void hide() {
        setVisibility(View.GONE);
    }

    public void setBackgroundColor(int color) {
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
    public void onScrollChanged() {
        int height = getHeight();

        int[] actualLocation = new int[2];
        anchor.getLocationOnScreen(actualLocation);

        int y = actualLocation[COORD_Y] + MARGIN_TOP;
        Log.d(TAG, String.format("#onScrollChanged# height:%s y:%s", height, y));

        setTop(y);
        setBottom(y + height);

        Log.d(TAG, "**************************************************************************");
        Log.d(TAG, String.format(
                "#Anchor{onScrollChanged}# [left:%s, right:%s, top:%s, bottom:%s, y:%s]",
                anchor.getLeft(), anchor.getRight(), anchor.getTop(), anchor.getBottom(), y));
        Log.d(TAG, String.format("#Tooltip{onScrollChanged}# [left:%s, right:%s, top:%s, " +
                        "bottom:%s]",
                getLeft(), getRight(), getTop(), getBottom()));
        Log.d(TAG, "**************************************************************************");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (!changed) {
            Log.d(TAG, "onLayout: ************ No changed!!!");
            return;
        }
        int height = getHeight();
        int newWidth = getNewWidth();

        int[] actualLocation = new int[2];
        anchor.getLocationOnScreen(actualLocation);

        int x = 0 + MARGIN_LEFT;
        int y = actualLocation[COORD_Y] + MARGIN_TOP;
        Log.d(TAG, String.format("#onLayout# width:%s height:%s x:%s y:%s", width,
                height, x, y));

        setLeft(x);
        setRight(x + newWidth);
        setTop(y);
        setBottom(y + height);

        setWidth(newWidth);

        Log.d(TAG, "**************************************************************************");
        Log.d(TAG, String.format(
                "#Anchor{onLayout}# [left:%s, right:%s, top:%s, bottom:%s, x:%s, y:%s]",
                anchor.getLeft(), anchor.getRight(), anchor.getTop(), anchor.getBottom(), x, y));
        Log.d(TAG, String.format("#Tooltip{onLayout}# [left:%s, right:%s, top:%s, bottom:%s]",
                getLeft(), getRight(), getTop(), getBottom()));
        Log.d(TAG, "**************************************************************************");
    }

    private int getNewWidth() {
        int width = getWidth();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int widthScreen = displaymetrics.widthPixels;

        int newWidth = width;
        if (widthScreen < (width + MARGIN_LEFT + MARGIN_RIGHT)) {
            newWidth = width - MARGIN_RIGHT;
        }

        return newWidth;
    }

}
