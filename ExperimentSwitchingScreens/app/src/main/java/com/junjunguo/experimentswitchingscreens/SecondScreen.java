package com.junjunguo.experimentswitchingscreens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by GuoJunjun on 19.01.15.
 */
public class SecondScreen extends Activity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        Intent activityThatCalled = getIntent();

        String previousActivity = activityThatCalled.getExtras().getString("callingActivity");

        TextView callingActivityMessage = (TextView) findViewById(R.id.call_activity_info_text_view);
        callingActivityMessage.append(" " + previousActivity);
    }

    public void onSecondUsersName(View view) {
        EditText usersNameET = (EditText) findViewById(R.id.users_name_edit_text);
        String userName = String.valueOf(usersNameET.getText());

        Intent goingBack = new Intent();

        goingBack.putExtra("UsersName", userName);
        setResult(RESULT_OK, goingBack);
        finish();
    }
}
