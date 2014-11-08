package com.gur.apps.hidedialer;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gur.apps.hidedialer.utils.SoundManager;

/**
 * Created by gur on 07/11/14.
 */
public class IncallActivity extends Activity
{
    private SoundManager sndManger;
    protected TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incall);

        String phonenum = getIntent().getStringExtra(MainActivity.PHONE_NUM_KEY);

        this.textView = (TextView) findViewById(R.id.incall_phonenum_text);
        this.textView.setText(phonenum);

        this.sndManger = new SoundManager(this, AudioManager.STREAM_VOICE_CALL);
    }

    public void invokeDisturbance(View view)
    {

    }
}
