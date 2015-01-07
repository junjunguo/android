package com.junjunguo.experimentlifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class Lifecycle extends Activity {
    public class ExperimentLifecycle extends Activity {
        private final String LOG_TAG = ExperimentLifecycle.class.getSimpleName();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lifecycle);
            Log.i(LOG_TAG,
                    "onCreate called:--> Called when the activity is first created. This is where you should do all " +
                            "of " +
                            "your " +
                            "normal static set up — create views, bind data to lists, " +
                            "and so on. This method is passed a " +
                            "Bundle object containing the activity's previous state, if that state was captured");
        }


        protected void onStart() {
            super.onStart();
            Log.i(LOG_TAG, "onStart called:--> Called just before the activity becomes visible to the user.\n" +
                    "Followed by onResume() if the activity comes to the foreground, " +
                    "or onStop() if it becomes hidden.");
        }

        protected void onResume() {
            super.onResume();
            Log.i(LOG_TAG,
                    "onResume called:--> Called just before the activity starts interacting with the user. At this " +
                            "point the activity is at the top of the activity stack, with user input going to it.\n" +
                            "Always followed by onPause().");
        }

        protected void onPause() {
            super.onPause();
            Log.i(LOG_TAG,
                    "onPause called:--> Called when the system is about to start resuming another activity. This " +
                            "method is typically used to commit unsaved changes to persistent data, " +
                            "stop animations and other things that may be consuming CPU, " +
                            "and so on. It should do whatever it does" +
                            " very quickly, because the next activity will not be resumed until it returns.\n" +
                            "Followed either by onResume() if the activity returns back to the front, " +
                            "or by onStop() if it becomes invisible to the user. (Killable)");
        }

        protected void onStop() {
            super.onStop();
            Log.i(LOG_TAG,
                    "onStop called:--> Called when the activity is no longer visible to the user. This may happen " +
                            "because it is being destroyed, or because another activity (either an existing one or a " +
                            "new one) has" +
                            " been resumed and is covering it.\n" +
                            "Followed either by onRestart() if the activity is coming back to interact with the user," +
                            " " +
                            "or by onDestroy() if this activity is going away. (Killable)");
        }

        protected void onRestart() {
            super.onRestart();
            Log.i(LOG_TAG,
                    "onRestart called:--> Called after the activity has been stopped, just prior to it being started " +
                            "again.\n" +
                            "Always followed by onStart()");
        }

        protected void onDestroy() {
            super.onDestroy();
            Log.i(LOG_TAG,
                    "onDestroy called:--> Called before the activity is destroyed. This is the final call that the " +
                            "activity " +
                            "will receive. It could be called either because the activity is finishing (someone " +
                            "called " +
                            "finish() on it), or because the system is temporarily destroying this instance of the " +
                            "activity to save space. You can distinguish between these two scenarios with the " +
                            "isFinishing" +
                            "() method. (Killable)");
        }
    }
}
