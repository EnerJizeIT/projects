package com.example.enerjizeit.crimeintentex;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

public class AnimationHelper {
    public static void animate(RecyclerView.ViewHolder holder, boolean scroll){

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator myAnim = ObjectAnimator.ofFloat(holder.itemView, "myAnim", scroll == true ? 200 : - 200, 0);
        myAnim.setDuration(1000);

        animatorSet.playTogether(myAnim);
        animatorSet.start();
    }
}
