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
import com.gur.apps.hidedialer.views.EffectButtonWrapper;

/**
 * Created by gur on 07/11/14.
 */
public class IncallActivity extends Activity
{
    private SoundManager sndManger;
    protected TextView textView;
    protected EffectButtonWrapper ebw;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incall);

        String phoneNum = getIntent().getStringExtra(MainActivity.PHONE_NUM_KEY);

        this.textView = (TextView) findViewById(R.id.incall_phonenum_text);
        this.textView.setText(phoneNum);

        this.sndManger = new SoundManager(this, AudioManager.STREAM_VOICE_CALL);
        // load all sounds, to be made dynamic later.
        this.sndManger.loadSound(this, R.raw.whitesound, 1);

        this.ebw = new EffectButtonWrapper(this, sndManger, R.raw.whitesound, (Button) findViewById(R.id.incall_whitesound_button));

    }

    public void invokeDisturbance(View view)
    {
        this.sndManger.playSound(this.sndManger.getSoundId(R.raw.whitesound), 1, 1);

    }
}
