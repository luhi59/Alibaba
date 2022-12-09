package fr.luhi.moodlucasjava.Utils;

import java.util.Calendar;

public class Utils {

    public static final String SHARED_PREF_MOOD_COMMENT = "SHARED_PREF_MOOD_COMMENT";
    public static final String SHARED_PREF_MOOD = "SHARED_PREF_MOOD";
    public static int mCurrentDay;

    public static String getDayKey(Integer day) {
        return "SHARED_PREF_DAY" + day + "_KEY";
    }

    public static int getDayNumber() {
        Calendar calendar = Calendar.getInstance();
        mCurrentDay = calendar.get(Calendar.DAY_OF_WEEK);
        return mCurrentDay;
    }
}