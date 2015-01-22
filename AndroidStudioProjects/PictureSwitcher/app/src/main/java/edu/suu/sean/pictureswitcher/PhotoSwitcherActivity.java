package edu.suu.sean.pictureswitcher;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;


public class PhotoSwitcherActivity extends ActionBarActivity {

    private ImageView imageSwitcher;
    private Switch s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);

        imageSwitcher = new ImageView(this);
        imageSwitcher.setImageResource(R.drawable.materialdesign2);
        layout.addView(imageSwitcher);

        s = new Switch(this);
        s.setTextOff("#2");
        s.setTextOn("#1");
        layout.addView(s);

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s.isChecked()){
                    //Material Design 1
                    imageSwitcher.setImageResource(R.drawable.materialdesign1);

                }
                else{
                    //Material Design 2
                    imageSwitcher.setImageResource(R.drawable.materialdesign2);

                }
            }
        });

        setContentView(layout);
    }
}
