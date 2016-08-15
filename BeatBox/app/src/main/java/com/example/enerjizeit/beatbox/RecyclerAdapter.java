package com.example.enerjizeit.beatbox;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.SoundHolder>{
    class SoundHolder extends RecyclerView.ViewHolder{
        private Button button;
        private Sound sound;
        public SoundHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.item_sound, container, false));
            button = (Button)itemView.findViewById(R.id.sound_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BeatBox.play(sound);
                }
            });
        }
        public void bindSound(Sound sound){
            this.sound = sound;
            button.setText(sound.getName());
        }
    }

    private Context context;
    private List<Sound> sounds;
    public RecyclerAdapter(Context context, List<Sound> sounds) {
        this.context = context;
        this.sounds = sounds;
    }

    @Override
    public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new SoundHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(SoundHolder holder, int position) {
        Sound sound = sounds.get(position);
        holder.bindSound(sound);
    }

    @Override
    public int getItemCount() {
        return sounds.size();
    }
}
