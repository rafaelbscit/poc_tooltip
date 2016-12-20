package com.example.ronie.tooltip.ui;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.ronie.tooltip.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Tooltip {

    // Declaration of the Gravity annotation and list of accepted constants
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT})
    public @interface Gravity {
    }

    // Gravity constant values
    public static final int TOP_LEFT = 0;
    public static final int TOP_RIGHT = 1;
    public static final int BOTTOM_LEFT = 2;
    public static final int BOTTOM_RIGHT = 3;

    private final String title;
    private final int titleColor;
    private final int titleSize;
    private final String message;
    private final int messageColor;
    private final int messageSize;
    private final int backgroundColor;

    private Tooltip(Builder builder) {
        title = builder.title;
        titleColor = builder.titleColor;
        titleSize = builder.titleSize;
        message = builder.message;
        messageColor = builder.messageColor;
        messageSize = builder.messageSize;
        backgroundColor = builder.backgroundColor;
    }

    public static TooltipView make(Context context,
                                   FrameLayout root,
                                   View anchor,
                                   Tooltip.Builder builder) {

        Tooltip tooltip = builder.build();

        TooltipView tooltipView = new TooltipView(context);
        tooltipView.hide();
        tooltipView.setAnchor(anchor);
        tooltipView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        tooltipView.setTitle(tooltip.getTitle());
        tooltipView.setPadding(16, 16, 16, 16);

        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        root.addView(tooltipView, layoutParams);

        return tooltipView;
    }

    public String getTitle() {
        return title;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public int getTitleSize() {
        return titleSize;
    }

    public String getMessage() {
        return message;
    }

    public int getMessageColor() {
        return messageColor;
    }

    public int getMessageSize() {
        return messageSize;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public static final class Builder {

        private String title;
        private int titleColor;
        private int titleSize;
        private String message;
        private int messageColor;
        private int messageSize;
        private int backgroundColor;

        public Builder() {
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder titleColor(int val) {
            titleColor = val;
            return this;
        }

        public Builder titleSize(int val) {
            titleSize = val;
            return this;
        }

        public Builder message(String val) {
            message = val;
            return this;
        }

        public Builder messageColor(int val) {
            messageColor = val;
            return this;
        }

        public Builder messageSize(int val) {
            messageSize = val;
            return this;
        }

        public Builder backgroundColor(int val) {
            backgroundColor = val;
            return this;
        }

        public Tooltip build() {
            return new Tooltip(this);
        }
    }
}
