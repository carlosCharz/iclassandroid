package com.wedevol.smartclass.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.utils.interfaces.Constants;

/** Created by paolo on 12/20/16.*/
public class PayCoursesActivity extends AppCompatActivity{
    //private SearchView sv_search_course;
    private Activity self;
    private ImageView iv_toolbar_back;
    private TextView tv_detail_title;
    private TextView tv_pick_bank;
    private Button b_confirm;
    private EditText et_transaction_id;
    private EditText et_amount;
    private ToggleButton tb_currency;
    private ImageView iv_toolbar_actual_screen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_courses);
        setElements();
        setActions();
    }

    private void setElements() {
        self = this;

        iv_toolbar_back = (ImageView) findViewById(R.id.iv_toolbar_back);
        tv_detail_title = (TextView) findViewById(R.id.tv_detail_title);
        iv_toolbar_actual_screen = (ImageView) findViewById(R.id.iv_toolbar_actual_screen);

        tv_detail_title.setText("Pagar Curso");
        iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_pay_course_black));

        tv_pick_bank = (TextView) findViewById(R.id.tv_pick_bank);
        et_amount = (EditText) findViewById(R.id.et_amount);
        tb_currency = (ToggleButton) findViewById(R.id.tb_currency);
        et_transaction_id = (EditText) findViewById(R.id.et_transaction_id);
        b_confirm = (Button) findViewById(R.id.b_confirm);
    }

    private void setActions() {
        iv_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.finish();
            }
        });

        tv_pick_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(self, ListBankActivity.class);
                startActivityForResult(intent, Constants.CHOOSEN_BANK);
            }
        });

        b_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == Constants.CHOOSEN_BANK) && (resultCode == Activity.RESULT_OK)) {
            String courseName = data.getStringExtra(Constants.BUNDLE_BANK_NAME);
            tv_pick_bank.setText(courseName);
        }
    }
}