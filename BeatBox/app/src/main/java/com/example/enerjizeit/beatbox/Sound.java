package com.example.enerjizeit.beatbox;

public class Sound {
    private String assetPath;
    private String name;
    private Integer soundId; /* Объявление mSoundId с типом Integer вместо int позволяет представить
    неопределенное состояние Sound — для этого mSoundId присваивается null */

    public Sound(String assetPath) {
        this.assetPath = assetPath;
        String[] components = assetPath.split("/");
        String filename = components[components.length - 1];
        name = filename.replace(".wav", "");
    }

    public void setSoundId(Integer soundId) {
        this.soundId = soundId;
    }

    public Integer getSoundId() {
        return soundId;
    }
    public String getAssetPath() {
        return assetPath;
    }
    public String getName() {
        return name;
    }
}
