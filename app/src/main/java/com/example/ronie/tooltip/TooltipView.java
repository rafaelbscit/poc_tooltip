package com.example.ronie.tooltip;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.Locale;

class TooltipView extends TextView implements
//        View.OnLayoutChangeListener,
        ViewTreeObserver.OnScrollChangedListener {

    private static final String TAG = TooltipView.class.getSimpleName();

    private View anchor;

    public TooltipView(Context context) {
        super(context);
    }

    public void setAnchor(@NonNull View anchor) {
        this.anchor = anchor;
    }

    public void setTitle(String title) {
        super.setText(title);
    }

    public void show() {
        setVisibility(View.VISIBLE);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        addOnLayoutChangeListener(this);
        getViewTreeObserver().addOnScrollChangedListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        removeOnLayoutChangeListener(this);
        getViewTreeObserver().removeOnScrollChangedListener(this);
    }

//    @Override
//    public void onLayoutChange(View v, int left, int top, int right, int bottom,
//                               int oldLeft, int oldTop, int oldRight, int oldBottom) {
//        int width = getRight() - getLeft();
//        int height = getBottom() - getTop();
//        Log.d(TAG, String.format(Locale.getDefault(), "width:%s height:%s", width, height));
//
//        setRight(anchor.getRight());
//        setLeft(getRight() - width);
//        setBottom(anchor.getBottom() + height);
//        setTop(getBottom() - height);
//    }

    @Override
    public void onScrollChanged() {
        int width = getRight() - getLeft();
        int height = getBottom() - getTop();
        Log.d(TAG, String.format(Locale.getDefault(), "width:%s height:%s", width, height));

        setRight(anchor.getRight());
        setLeft(getRight() - width);
        setBottom(anchor.getBottom() + height);
        setTop(getBottom() - height);
    }
}
