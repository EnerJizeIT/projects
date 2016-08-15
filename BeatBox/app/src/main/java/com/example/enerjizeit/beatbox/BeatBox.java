package com.example.enerjizeit.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUND_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager assetManager;
    private List<Sound> sounds;
    private static SoundPool soundPool;

    public BeatBox(Context context) {
        assetManager = context.getAssets();

        soundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        /* Первый параметр определяет, сколько звуков может воспроизводиться в любой момент времени.
         * Второй параметр определяет тип аудиопотока, который может воспроизводиться объектом SoundPool.
         * Третий задает качество дискретизации. Этот параметр игнорируется, поэтому в нем можно передать 0.*/
        loadSounds();
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = assetManager.list(SOUND_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException ioe) {
            Log.i(TAG, "Could not list assets", ioe);
            return;
        }
        sounds = new ArrayList<Sound>();
        for (String filename : soundNames) {
            try {
                String assetPath = SOUND_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                sounds.add(sound);
            } catch (IOException e) {
                Log.i(TAG, "Could not load sound " + filename, e);
            }
        }
    }

    public List<Sound> getSounds() {
        return sounds;
    }

    private void load(Sound sound) throws IOException {
        AssetFileDescriptor assetFileDescriptor = assetManager.openFd(sound.getAssetPath());
        int soundId = soundPool.load(assetFileDescriptor, 1);
        sound.setSoundId(soundId);
        /* Вызов mSoundPool.load(AssetFileDescriptor, int) загружает файл в SoundPool для последующего воспроизведения. */
    }
    public void release() {
        soundPool.release();
    }
    public static void play(Sound sound){
        Integer soundId = sound.getSoundId();
        if(soundId == null){
            return;
        }
        soundPool.play(soundId, 1.0f, 1.0f, 1, 1, 1.0f);
        /* Параметры содержат соответственно: идентификатор звука, громкость слева, громкость справа, приоритет
(игнорируется), признак циклического воспроизведения и скорость воспроизведения. */
    }
}
