package fr.luhi.moodlucasjava;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import fr.luhi.moodlucasjava.Utils.Utils;

public class MainActivity extends AppCompatActivity {

    private GestureDetector mDetector;
    private Button mLastMoodButton;
    private ImageButton mCommentButton;
    private ImageButton mMoodHistoryButton;
    private ImageView mSmiley;
    private ImageView mLastMoodSmiley;
    private RelativeLayout mView;
    private int mCurrentDay;
    private int mCurrentColor;
    private int mCurrentSmiley;
    private int mPosition;
    private int mSWIPE_MIN_DISTANCE = 12;
    private int mSWIPE_MAX_OFF_PATH = 150;
    private int mSWIPE_THRESHOLD_VELOCITY= 100;

    public static int[] mSmileyList = {
        R.drawable.smiley_super_happy,
        R.drawable.smiley_happy,
        R.drawable.smiley_normal,
        R.drawable.smiley_disappointed,
        R.drawable.smileysad
    };
    public static int[] mColorList = {
        R.color.banana_yellow,
        R.color.faded_red,
        R.color.light_sage,
        R.color.warm_grey,
        R.color.cornflower_blue_65
    };

    //-------------------------
    // ON CREATE
    //-------------------------

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INIT
        int MoodColorSunday = getSharedPreferences(Utils.getDayKey(1), MODE_PRIVATE).getInt((Utils.SHARED_PREF_MOOD), 0);
        int MoodColorMonday = getSharedPreferences(Utils.getDayKey(2), MODE_PRIVATE).getInt((Utils.SHARED_PREF_MOOD), 0);
        int MoodColorTuesday = getSharedPreferences(Utils.getDayKey(3), MODE_PRIVATE).getInt((Utils.SHARED_PREF_MOOD), 0);
        int MoodColorWednesday = getSharedPreferences(Utils.getDayKey(4), MODE_PRIVATE).getInt((Utils.SHARED_PREF_MOOD), 0);
        int MoodColorThursday = getSharedPreferences(Utils.getDayKey(5), MODE_PRIVATE).getInt((Utils.SHARED_PREF_MOOD), 0);
        int MoodColorFriday = getSharedPreferences(Utils.getDayKey(6), MODE_PRIVATE).getInt((Utils.SHARED_PREF_MOOD), 0);
        int MoodColorSaturday = getSharedPreferences(Utils.getDayKey(7), MODE_PRIVATE).getInt((Utils.SHARED_PREF_MOOD), 0);

        mCommentButton = findViewById(R.id.button_comment);
        mMoodHistoryButton = findViewById(R.id.mood_record);
        mLastMoodButton = findViewById(R.id.LastMoodButton);
        mLastMoodSmiley = findViewById(R.id.LastMoodSmiley);
        mView = findViewById(R.id.parent_relative_layout);
        mSmiley = findViewById(R.id.my_mood);
        mPosition = 0;

        mDetector = new GestureDetector(this, new MyGestureDetector()); //a expliquer

        //BUTTON
        mCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { displayCommentDialog(); }
        });

        mLastMoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mCurrentDay) {
                    case 1:
                        LastMood(MoodColorSunday);
                        break;
                    case 2:
                        LastMood(MoodColorMonday);
                        break;
                    case 3:
                        LastMood(MoodColorTuesday);
                        break;
                    case 4:
                        LastMood(MoodColorWednesday);
                        break;
                    case 5:
                        LastMood(MoodColorThursday);
                        break;
                    case 6:
                        LastMood(MoodColorFriday);
                        break;
                    case 7:
                        LastMood(MoodColorSaturday);
                        break;
                }
            }
        });

        mMoodHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);
            }
        });

        //LISTENER
        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDetector.onTouchEvent(event);
                return true;
            }

        });
    }

    //-------------------------
    // CLASS
    //-------------------------

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener  {
        @Override
        public boolean onDown(MotionEvent event) { return true; }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                if(Math.abs(e1.getX() - e2.getX()) > mSWIPE_MAX_OFF_PATH) {
                    return false;
                }
                //swipe top
                else if (e2.getY() - e1.getY() > mSWIPE_MIN_DISTANCE && Math.abs(velocityY) > mSWIPE_THRESHOLD_VELOCITY) {
                    onTopSwipe();
                    return false;
                }
                //swipe bot
                else if(e1.getY() - e2.getY() > mSWIPE_MIN_DISTANCE && Math.abs(velocityY) > mSWIPE_THRESHOLD_VELOCITY) {
                    onBotSwipe();
                    return false;
                }
            }   catch (Exception e) {
            }
            return false; //retourner false or true ???
        }
    }

    //-------------------------
    // VOID
    //-------------------------

    private void displayCommentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final EditText editText = new EditText(MainActivity.this);

        builder.setTitle(R.string.Comment_your_mood);
        builder.setView(editText);

        builder.setPositiveButton(R.string.Validate, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                getDayNumber();
                getSharedPreferences(Utils.getDayKey(mCurrentDay), MODE_PRIVATE)
                        .edit()
                        .putString(Utils.SHARED_PREF_MOOD_COMMENT, editText.getText().toString())
                        .apply();
            }
        });
        builder.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .create().show();
    }

    public void getDayNumber() {
        Calendar calendar = Calendar.getInstance();
        mCurrentDay = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(mCurrentDay);
    }

    private void onBotSwipe() {
        if (mPosition < 5){
            mPosition++;
            mCurrentColor = mColorList[mPosition];
            mCurrentSmiley = mSmileyList[mPosition];
            mView.setBackgroundColor(getResources().getColor(mCurrentColor));
            mSmiley.setImageResource(mCurrentSmiley);
            getDayNumber();
            getSharedPreferences(Utils.getDayKey(mCurrentDay), MODE_PRIVATE)
                    .edit()
                    .putInt(Utils.SHARED_PREF_MOOD, mPosition)
                    .apply();
        }
    }

    private void onTopSwipe() {
        if (mPosition > 0){
            mPosition--;
            mCurrentColor = mColorList[mPosition];
            mCurrentSmiley = mSmileyList[mPosition];
            mView.setBackgroundColor(getResources().getColor(mCurrentColor));
            mSmiley.setImageResource(mCurrentSmiley);
            getDayNumber();
            getSharedPreferences(Utils.getDayKey(mCurrentDay), MODE_PRIVATE)
                    .edit()
                    .putInt(Utils.SHARED_PREF_MOOD, mPosition)
                    .apply();
        }
    }

    public void LastMood(int mood) {
            switch (mood) {
                case 0:
                    mLastMoodButton.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
                    mLastMoodButton.setText("You were");
                    mLastMoodSmiley.setImageResource(R.drawable.smiley_super_happy);
                    mLastMoodSmiley.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
                    break;
                case 1:
                    mLastMoodButton.setBackgroundColor(getResources().getColor(R.color.faded_red));
                    mLastMoodButton.setText("You were");
                    mLastMoodSmiley.setImageResource(R.drawable.smiley_happy);
                    mLastMoodSmiley.setBackgroundColor(getResources().getColor(R.color.faded_red));
                    break;
                case 2:
                    mLastMoodButton.setBackgroundColor(getResources().getColor(R.color.light_sage));
                    mLastMoodButton.setText("You were");
                    mLastMoodSmiley.setImageResource(R.drawable.smiley_normal);
                    mLastMoodSmiley.setBackgroundColor(getResources().getColor(R.color.light_sage));
                    break;
                case 3:
                    mLastMoodButton.setBackgroundColor(getResources().getColor(R.color.warm_grey));
                    mLastMoodButton.setText("You were");
                    mLastMoodSmiley.setImageResource(R.drawable.smiley_disappointed);
                    mLastMoodSmiley.setBackgroundColor(getResources().getColor(R.color.warm_grey));
                    break;
                case 4:
                    mLastMoodButton.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65));
                    mLastMoodButton.setText("You were");
                    mLastMoodSmiley.setImageResource(R.drawable.smileysad);
                    mLastMoodSmiley.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65));
                    break;
                default:
                    mLastMoodButton.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65));
                    mLastMoodButton.setText("Aucun Précédent Mood");
                    break;
            }

    }
}
