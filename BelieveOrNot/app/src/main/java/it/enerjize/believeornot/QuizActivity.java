package it.enerjize.believeornot;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton, mFalseButton, mNextButton, mCheatButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.ques1, true),
            new Question(R.string.ques2, false),
            new Question(R.string.ques3, true),
            new Question(R.string.ques4, true),
            new Question(R.string.ques5, true),
            new Question(R.string.ques6, false),
            new Question(R.string.ques7, false),
            new Question(R.string.ques8, true),
            new Question(R.string.ques9, true),
            new Question(R.string.ques10, false),
            new Question(R.string.ques11, false),
            new Question(R.string.ques12, true),
            new Question(R.string.ques13, true),
            new Question(R.string.ques14, false),
            new Question(R.string.ques15, true),
            new Question(R.string.ques16, true),
            new Question(R.string.ques17, false),
            new Question(R.string.ques18, true),
            new Question(R.string.ques19, true),
            new Question(R.string.ques20, false),
            new Question(R.string.ques21, true),
            new Question(R.string.ques22, true),
            new Question(R.string.ques23, false),
            new Question(R.string.ques24, true),
            new Question(R.string.ques25, false),
            new Question(R.string.ques26, true),
            new Question(R.string.ques27, true),
            new Question(R.string.ques28, true),
            new Question(R.string.ques29, false),
            new Question(R.string.ques30, true),
            new Question(R.string.ques31, true),
            new Question(R.string.ques32, false),
            new Question(R.string.ques33, true),
            new Question(R.string.ques34, false),
            new Question(R.string.ques35, false),
            new Question(R.string.ques36, false),
            new Question(R.string.ques37, true),
    };

    private int mCurrentIndex = 0;
    private static final int REQUEST_CODE_CHEAT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsCheater){
                    Toast.makeText(QuizActivity.this, "Жулик..", Toast.LENGTH_SHORT).show();
                mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion(); }
            }
        });

        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(i, REQUEST_CODE_CHEAT);
            }
        });

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY, 0);
            mIsCheater = savedInstanceState.getBoolean(STATE, false);
        }
        updateQuestion();
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;
        if(mIsCheater){
            messageResId = R.string.judgment_toast;
        }else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(QuizActivity.this, messageResId, Toast.LENGTH_SHORT).show();
    }

    private static final String KEY = "index";
    private static final String STATE = "index";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY,mCurrentIndex);
        outState.putBoolean(STATE, mIsCheater);
    }

    private boolean mIsCheater;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // если код успешного открытия активности не равен заданной константе, return
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        // если ответ пришёл из заданной на ответ активности
        if(requestCode == REQUEST_CODE_CHEAT){
            // если интент не пришёл(он не придёт если пользователь не смотрел ответ)
            if(data == null){
                return;
            }
            // если посмотрел mIsCheater будет равен значениею false
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }
}
