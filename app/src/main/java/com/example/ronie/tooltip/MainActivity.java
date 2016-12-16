package com.example.ronie.tooltip;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        TextView textViewAnchor = (TextView) findViewById(R.id.text_view_anchor);

        Tooltip tooltip = new Tooltip.Builder().title("Tooltip").build();
        Tooltip.make(this, frameLayout, textViewAnchor, tooltip).show();
    }
}
