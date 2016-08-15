package com.example.enerjizeit.crimeintentex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private TextView date;
        private CheckBox solvedBox;
        private float x1, x2;
        private final int MIN_DISTANCE = 150;

        public MyViewHolder(final View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_item);
            date = (TextView) itemView.findViewById(R.id.date_item);
            solvedBox = (CheckBox) itemView.findViewById(R.id.checkbox_item);

            itemView.setOnClickListener(this);
            itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            x1 = event.getX();
                            break;
                        case MotionEvent.ACTION_UP:
                            x2 = event.getX();
                            float deltaX = x2 - x1;

                            if (Math.abs(deltaX) > MIN_DISTANCE) {
                                removeAt(getAdapterPosition());
                                Toast.makeText(context, "Remove crime", Toast.LENGTH_SHORT).show();
                                return true;

                            } else {
                                // consider as something else - a screen tap for example
                            }
                            break;
                    }
                    return false;
                }
            });
        }

        private void removeAt(int position) {
            crimeList.remove(position);
            CrimeLab.delCrime(crime);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
        }

        @Override
        public void onClick(View v) {
            context.startActivity(PagerActivity.newIntent(context, crime.getId()));
        }
    }


    private Crime crime;
    private List<Crime> crimeList;
    private Context context;
    private int prePos = 0;

    public RecyclerAdapter(List<Crime> crimeList, Context context) {
        this.crimeList = crimeList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        crime = crimeList.get(position);
        holder.title.setText(crime.getTitle());
        holder.date.setText(crime.getDate().toString());
        holder.solvedBox.setChecked(crime.isSolved());

        if (prePos > position) { /* Scroll down*/
            AnimationHelper.animate(holder, true);
        } else { /* Scroll up*/
            AnimationHelper.animate(holder, false);
        }
        prePos = position;
    }

    @Override
    public int getItemCount() {
        return crimeList.size();
    }

    public void setCrimes(List<Crime> crimes) {
        crimeList = crimes;
    }
}