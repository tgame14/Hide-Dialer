package com.gur.apps.hidedialer.views;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.gur.apps.hidedialer.utils.SoundManager;

/**
 * Created by gur on 07/11/14.
 *
 * a format of a Wrapper around Android 'widgets' and using the wrapper as the standard
 * method of describing the behaviour of the widget.
 */
public class EffectButtonWrapper implements View.OnLongClickListener, View.OnClickListener
{

    protected SoundManager sndManager;
    protected int rawResId;
    protected Button button;

    public EffectButtonWrapper(Context context, SoundManager sndManager, int resId, Button b)
    {
        this.sndManager = sndManager;
        this.rawResId = resId;

        b.setOnLongClickListener(this);
        b.setOnClickListener(this);
    }

    @Override
    public boolean onLongClick(View v)
    {
        this.sndManager.playSound(this.sndManager.getSoundId(this.rawResId), 1, -1);
        return false;
    }

    @Override
    public void onClick(View v)
    {
        this.sndManager.playSound(this.sndManager.getSoundId(this.rawResId), 1, 1);
    }
}
