package com.gur.apps.hidedialer.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.widget.Toast;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gur on 07/11/14.
 * <p/>
 * This object is meant to be a complementary object to an {@link android.app.Activity}
 */
public class SoundManager
{
    protected SoundPool soundPool;
    protected float actVol, maxVol, vol;
    protected AudioManager audioManager;
    protected Map<Integer, Integer> resIdSoundIdMap;
    protected BiMap<Integer, Integer> soundIdStreamIdMap;
    protected int streamTypeId;


    /**
     * @param context
     * @param soundStreamId {@link android.media.AudioManager} STREAMS
     */
    public SoundManager(Context context, int soundStreamId)
    {
        super();
        this.streamTypeId = soundStreamId;

        this.audioManager = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);

        this.actVol = this.audioManager.getStreamVolume(this.streamTypeId);
        this.maxVol = this.audioManager.getStreamMaxVolume(this.streamTypeId);
        this.vol = this.actVol / this.maxVol;

        // must use deprecated constructor to support API 15
        this.soundPool = new SoundPool(10, this.streamTypeId, 0);

        this.resIdSoundIdMap = new HashMap<Integer, Integer>();
        this.soundIdStreamIdMap = HashBiMap.create(10);

    }

    protected void refreshAudioManager(Context context)
    {
        this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        this.actVol = this.audioManager.getStreamVolume(this.streamTypeId);
        this.maxVol = this.audioManager.getStreamMaxVolume(this.streamTypeId);
        this.vol = this.actVol / this.maxVol;

    }

    /**
     * @param context  the context in which the file is loaded, usually the activity it origins from, or through a button and its given context
     * @param resId    the resId of the sound file from context
     * @param priority usually 1, unless otherwise required.
     */
    public void loadSound(Context context, int resId, int priority)
    {
        int soundId = this.soundPool.load(context, resId, priority);
        this.resIdSoundIdMap.put(resId, soundId);
    }

    public int getSoundId(int resId)
    {
        return this.resIdSoundIdMap.get(resId);
    }

    protected int getStreamId(int soundId)
    {
        return this.soundIdStreamIdMap.get(soundId);
    }

    /**
     *
     * @param soundId the {@link java.lang.Integer} that reflects on the associated soundId with the resId
     * @param priority
     * @param loopCount how many times will the sound repeat itself, to repeat itself without limit use -1
     */
    public void playSound(int soundId, int priority, int loopCount)
    {
        if (this.soundIdStreamIdMap.containsKey(soundId))
        {
            return; //already playing, double playing would simply double register the stream in the soundIdStreamIdMap.
        }
        int streamId = this.soundPool.play(soundId, this.vol, this.vol, priority, loopCount, 1.0F);
        this.soundIdStreamIdMap.put(soundId, streamId);

    }

    public void pauseSound(int streamId)
    {
        soundPool.pause(streamId);
    }

    public void resumeSound (int streamId)
    {
        this.soundPool.resume(streamId);
    }

    public void stopSound(int streamId)
    {
        this.soundPool.stop(streamId);
        this.soundIdStreamIdMap.inverse().remove(streamId);

    }

}
