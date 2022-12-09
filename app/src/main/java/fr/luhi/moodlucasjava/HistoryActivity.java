package fr.luhi.moodlucasjava;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.view.View;
import android.widget.LinearLayout;

import fr.luhi.moodlucasjava.Utils.Utils;

public class HistoryActivity extends AppCompatActivity {

    private ImageButton mSundayButton;
    private ImageButton mMondayButton;
    private ImageButton mTuesdayButton;
    private ImageButton mWednesdayButton;
    private ImageButton mThursdayButton;
    private ImageButton mFridayButton;
    private ImageButton mSaturdayButton;

    private LinearLayout mSundayLayout;
    private LinearLayout mMondayLayout;
    private LinearLayout mTuesdayLayout;
    private LinearLayout mWednesdayLayout;
    private LinearLayout mThursdayLayout;
    private LinearLayout mFridayLayout;
    private LinearLayout mSaturdayLayout;

    //-------------------------
    // ON CREATE
    //-------------------------

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history_bis);

        //INIT
        mSundayButton = findViewById(R.id.button_Sunday);
        mMondayButton = findViewById(R.id.button_Monday);
        mTuesdayButton = findViewById(R.id.button_Tuesday);
        mWednesdayButton = findViewById(R.id.button_Wednesday);
        mThursdayButton = findViewById(R.id.button_Thursday);
        mFridayButton = findViewById(R.id.button_Friday);
        mSaturdayButton = findViewById(R.id.button_Saturday);

        mSundayLayout = findViewById(R.id.SundayLayout);
        mMondayLayout = findViewById(R.id.MondayLayout);
        mTuesdayLayout = findViewById(R.id.TuesdayLayout);
        mWednesdayLayout = findViewById(R.id.WednesdayLayout);
        mThursdayLayout = findViewById(R.id.ThursdayLayout);
        mFridayLayout = findViewById(R.id.FridayLayout);
        mSaturdayLayout = findViewById(R.id.SaturdayLayout);

        //VALUE
        //Color (Integer)
        int MoodColorSunday = getSharedPreferences(Utils.getDayKey(1), MODE_PRIVATE).getInt((Utils.SHARED_PREF_MOOD), 0);
        int MoodColorMonday = getSharedPreferences(Utils.getDayKey(2), MODE_PRIVATE).getInt((Utils.SHARED_PREF_MOOD), 0);
        int MoodColorTuesday = getSharedPreferences(Utils.getDayKey(3), MODE_PRIVATE).getInt((Utils.SHARED_PREF_MOOD), 0);
        int MoodColorWednesday = getSharedPreferences(Utils.getDayKey(4), MODE_PRIVATE).getInt((Utils.SHARED_PREF_MOOD), 0);
        int MoodColorThursday = getSharedPreferences(Utils.getDayKey(5), MODE_PRIVATE).getInt((Utils.SHARED_PREF_MOOD), 0);
        int MoodColorFriday = getSharedPreferences(Utils.getDayKey(6), MODE_PRIVATE).getInt((Utils.SHARED_PREF_MOOD), 0);
        int MoodColorSaturday = getSharedPreferences(Utils.getDayKey(7), MODE_PRIVATE).getInt((Utils.SHARED_PREF_MOOD), 0);

        //Comment (String)
        String MoodCommentSunday = getSharedPreferences(Utils.getDayKey(1), MODE_PRIVATE).getString((Utils.SHARED_PREF_MOOD_COMMENT), null);
        String MoodCommentMonday = getSharedPreferences(Utils.getDayKey(2), MODE_PRIVATE).getString((Utils.SHARED_PREF_MOOD_COMMENT), null);
        String MoodCommentTuesday = getSharedPreferences(Utils.getDayKey(3), MODE_PRIVATE).getString((Utils.SHARED_PREF_MOOD_COMMENT), null);
        String MoodCommentWednesday = getSharedPreferences(Utils.getDayKey(4), MODE_PRIVATE).getString((Utils.SHARED_PREF_MOOD_COMMENT), null);
        String MoodCommentThursday = getSharedPreferences(Utils.getDayKey(5), MODE_PRIVATE).getString((Utils.SHARED_PREF_MOOD_COMMENT), null);
        String MoodCommentFriday = getSharedPreferences(Utils.getDayKey(6), MODE_PRIVATE).getString((Utils.SHARED_PREF_MOOD_COMMENT), null);
        String MoodCommentSaturday = getSharedPreferences(Utils.getDayKey(7), MODE_PRIVATE).getString((Utils.SHARED_PREF_MOOD_COMMENT), null);

        manageViewColor(MoodColorSunday, mSundayLayout);
        manageViewColor(MoodColorMonday, mMondayLayout);
        manageViewColor(MoodColorTuesday, mTuesdayLayout);
        manageViewColor(MoodColorWednesday, mWednesdayLayout);
        manageViewColor(MoodColorThursday, mThursdayLayout);
        manageViewColor(MoodColorFriday, mFridayLayout);
        manageViewColor(MoodColorSaturday, mSaturdayLayout);

        manageViewWeight(MoodColorSunday, mSundayLayout);
        manageViewWeight(MoodColorMonday, mMondayLayout);
        manageViewWeight(MoodColorTuesday, mTuesdayLayout);
        manageViewWeight(MoodColorWednesday, mWednesdayLayout);
        manageViewWeight(MoodColorThursday, mThursdayLayout);
        manageViewWeight(MoodColorFriday,mFridayLayout);
        manageViewWeight(MoodColorSaturday,mSaturdayLayout);

        //BUTTON
        mSundayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
                builder.setTitle(R.string.Comment);
                builder.setMessage(MoodCommentSunday)
                        .create().show();
            }
        });

        mMondayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
                builder.setTitle(R.string.Comment);
                builder.setMessage(MoodCommentMonday)
                    .create().show();
            }
        });
        mTuesdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
                builder.setTitle(R.string.Comment);
                builder.setMessage(MoodCommentTuesday)
                        .create().show();
            }
        });
        mWednesdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
                builder.setTitle(R.string.Comment);
                builder.setMessage(MoodCommentWednesday)
                        .create().show();

            }
        });
        mThursdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
                builder.setTitle(R.string.Comment);
                builder.setMessage(MoodCommentThursday)
                        .create().show();
            }
        });
        mFridayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
                builder.setTitle(R.string.Comment);
                builder.setMessage(MoodCommentFriday)
                        .create().show();
            }
        });
        mSaturdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
                builder.setTitle(R.string.Comment);
                builder.setMessage(MoodCommentSaturday)
                        .create().show();
            }
        });
    }

    //-------------------------
    // VOID
    //-------------------------

    public void manageViewColor(int mood, LinearLayout dayView) {
        switch (mood) {
            case 0:
                dayView.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
                break;
            case 1:
                dayView.setBackgroundColor(getResources().getColor(R.color.faded_red));
                break;
            case 2:
                dayView.setBackgroundColor(getResources().getColor(R.color.light_sage));
                break;
            case 3:
                dayView.setBackgroundColor(getResources().getColor(R.color.warm_grey));
                break;
            case 4:
                dayView.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65));
                break;
            default:
                dayView.setBackgroundColor(getResources().getColor(R.color.faded_red));
                break;
        }
    }

    public void manageViewWeight(int mood, LinearLayout dayView) {
        ViewGroup.LayoutParams params = dayView.getLayoutParams();
        Display ecran = getWindowManager().getDefaultDisplay();
        int mWidth= ecran.getWidth();
        System.out.println(mWidth);
        switch (mood) {
            case 0:
                params.width = mWidth;
                break;
            case 1:
                params.width = mWidth*(80)/100;
                break;
            case 2:
                params.width = mWidth*(60)/100;
                break;
            case 3:
                params.width = mWidth*(40)/100;
                break;
            case 4:
                params.width = mWidth*(20)/100;
                break;
            default:
                params.width = ecran.getWidth();
        }
        dayView.setLayoutParams(params);
    }

}