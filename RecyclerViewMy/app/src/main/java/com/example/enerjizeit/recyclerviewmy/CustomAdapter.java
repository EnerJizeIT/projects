package com.example.enerjizeit.recyclerviewmy;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    /* Чтобы создать адаптер для RecyclerView, наследуемся от RecyclerView.Adapter.
 Этот адаптер представляет шаблон проектирования viewholder, подразумевающий использование
 пользовательского класса, который расширяет RecyclerView.ViewHolder. Эта паттерн сводит к минимуму
  количество обращений к дорогостоящему в плане ресурсов методу findViewById. */
    static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView title, pas;
        ImageView image;
        MyViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            title = (TextView)itemView.findViewById(R.id.title);
            pas = (TextView)itemView.findViewById(R.id.pas);
            image = (ImageView)itemView.findViewById(R.id.image);
        }
    }

    private List<ItemLab> items;

    CustomAdapter(List<ItemLab> items) {
        this.items = items;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*   Как следует из названия, этот метод вызывается, когда кастомный ViewHolder должен быть
         инициализирован. Мы указываем макет для каждого элемента RecyclerView. Затем LayoutInflater
         заполняет макет, и передает его в конструктор ViewHolder.*/
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /* Этот метод очень похож на метод getView  элемента адаптера ListView. */
        holder.image.setImageResource(items.get(position).getImage());
        holder.title.setText(items.get(position).getTitle());
        holder.title.setText(items.get(position).getPass());

        setAnimation(holder.itemView);

    }

    @Override
    public int getItemCount() {
        /* Он вернет количество элементов, присутствующих в данных. */
        return items.size();
    }

    private void setAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(200);
        view.startAnimation(anim);
    }
}
