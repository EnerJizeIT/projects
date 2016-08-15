package it.enerjize.fragments.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.enerjize.fragments.R;

public class FragmentButton extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /* мы переопределяем стандарный return для того чтобы fragment раздумал layout.
         * Второй параметр это контейнер, и трейтий будем ли мы использовать контейнер для подкл к активити. */
        return inflater.inflate(R.layout.button_layout, container, false);
    }
}
