package it.enerjize.believeornot;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_IS_TRUE = "it.enerjize.believeornot.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "it.enerjize.believeornot.answer_shown";

    public boolean mAnswerIsTrue;

    private TextView mAnswerTextView, mApiShow;
    private Button mShowAnswer;

    // этот метод задаёт шаблон интента и более никакой функции не несёт.
    public static Intent newIntent(Context packageContext, boolean answerIsTrue){
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        // в значение mAnswerIsTrue мы получаем интент из QuizActivity с ответом на вопрос.
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false); // содержит имя ключа и значение по-умолчанию

        mAnswerTextView = (TextView)findViewById(R.id.answer_text_view);
        mApiShow = (TextView)findViewById(R.id.api_show);
        mApiShow.setText("Build.VERSION_CODES.LOLLIPOP");

        mShowAnswer = (Button)findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // если в mAnswerIsTrue содержится тру
                if(mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                }else{
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
                inSave = 1;
                // Данный код добавляет анимацию на  API 21
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                int cx = mShowAnswer.getWidth() / 2;
                int cy = mShowAnswer.getHeight() / 2;
                float radius = mShowAnswer.getWidth();
                Animator anim = ViewAnimationUtils.createCircularReveal(mShowAnswer, cx, cy, radius, 0);
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mAnswerTextView.setVisibility(View.VISIBLE);
                        mShowAnswer.setVisibility(View.INVISIBLE);
                    }
                });
                anim.start();
            } else {
                mAnswerTextView.setVisibility(View.VISIBLE);
                mAnswerTextView.setVisibility(View.INVISIBLE);
            }
                // пример из книги
            }
        });
        if(savedInstanceState != null){
            setAnswerShownResult(true);
        }
    }
    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }
    private int inSave;
    private static final String KEY = "index";
        @Override
        protected void onSaveInstanceState (Bundle outState){
            super.onSaveInstanceState(outState);
            outState.putInt(KEY, inSave);
        }


}
