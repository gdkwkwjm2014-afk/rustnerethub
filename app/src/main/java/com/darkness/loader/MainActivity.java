package com.darkness.loader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.graphics.Color;

public class MainActivity extends Activity {

    private TextView statusText;
    private Button btnStart, btnStop;
    private Spinner versionSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.status);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        versionSelector = findViewById(R.id.versionSelector);

        String[] versions = {"PUBG Global", "PUBG Korea", "PUBG Vietnam", "PUBG Taiwan"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, versions);
        versionSelector.setAdapter(adapter);

        final Animation pulse = new AlphaAnimation(1.0f, 0.5f);
        pulse.setDuration(800);
        pulse.setRepeatMode(Animation.REVERSE);
        pulse.setRepeatCount(Animation.INFINITE);

        btnStart.setOnClickListener(v -> {
            String selected = versionSelector.getSelectedItem().toString();
            String pkg = "com.tencent.ig"; 
            if (selected.equals("PUBG Korea")) pkg = "com.pubg.krmobile";
            if (selected.equals("PUBG Vietnam")) pkg = "com.vng.pubgmobile";

            statusText.setText("STATUS: CHEAT ACTIVE");
            statusText.setTextColor(Color.RED);
            statusText.startAnimation(pulse);

            Intent launchIntent = getPackageManager().getLaunchIntentForPackage(pkg);
            if (launchIntent != null) {
                startActivity(launchIntent);
            } else {
                Toast.makeText(this, "Game not found!", Toast.LENGTH_SHORT).show();
            }
        });

        btnStop.setOnClickListener(v -> {
            statusText.clearAnimation();
            statusText.setText("STATUS: CLEANED");
            statusText.setTextColor(Color.GRAY);
            Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show();
        });
    }
}

