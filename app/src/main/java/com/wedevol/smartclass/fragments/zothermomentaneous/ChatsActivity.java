package com.wedevol.smartclass.fragments.zothermomentaneous;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.wedevol.smartclass.R;

public class ChatsActivity extends AppCompatActivity {

    LinearLayout _chat1Panel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        _chat1Panel = (LinearLayout) findViewById(R.id.chat1_panel);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Chats");
        actionBar.setDisplayHomeAsUpEnabled(true);

        _chat1Panel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
