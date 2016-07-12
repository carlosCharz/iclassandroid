package com.wedevol.smartclass;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoachCodeActivity extends AppCompatActivity {

    private static final String TAG = "CoachCodeActivity";

    @BindView(R.id.code1Text)
    EditText _code1Text;
    @BindView(R.id.code2Text)
    EditText _code2Text;
    @BindView(R.id.code3Text)
    EditText _code3Text;
    @BindView(R.id.code4Text)
    EditText _code4Text;
    @BindView(R.id.code5Text)
    EditText _code5Text;
    @BindView(R.id.code6Text)
    EditText _code6Text;
    @BindView(R.id.btn_validate)
    Button _validateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_code);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.coach_title);

        _validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateCode();
            }
        });

        ((EditText) findViewById(R.id.code1Text)).addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (((EditText) findViewById(R.id.code1Text)).getText().toString().length() == 1) {
                    ((EditText) findViewById(R.id.code1Text)).clearFocus();
                    ((EditText) findViewById(R.id.code2Text)).requestFocus();
                    ((EditText) findViewById(R.id.code2Text)).setCursorVisible(true);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        ((EditText) findViewById(R.id.code2Text)).addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (((EditText) findViewById(R.id.code2Text)).getText().toString().length() == 1) {
                    ((EditText) findViewById(R.id.code2Text)).clearFocus();
                    ((EditText) findViewById(R.id.code3Text)).requestFocus();
                    ((EditText) findViewById(R.id.code3Text)).setCursorVisible(true);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        ((EditText) findViewById(R.id.code3Text)).addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (((EditText) findViewById(R.id.code3Text)).getText().toString().length() == 1) {
                    ((EditText) findViewById(R.id.code3Text)).clearFocus();
                    ((EditText) findViewById(R.id.code4Text)).requestFocus();
                    ((EditText) findViewById(R.id.code4Text)).setCursorVisible(true);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        ((EditText) findViewById(R.id.code4Text)).addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (((EditText) findViewById(R.id.code4Text)).getText().toString().length() == 1) {
                    ((EditText) findViewById(R.id.code4Text)).clearFocus();
                    ((EditText) findViewById(R.id.code5Text)).requestFocus();
                    ((EditText) findViewById(R.id.code5Text)).setCursorVisible(true);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        ((EditText) findViewById(R.id.code5Text)).addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (((EditText) findViewById(R.id.code5Text)).getText().toString().length() == 1) {
                    ((EditText) findViewById(R.id.code5Text)).clearFocus();
                    ((EditText) findViewById(R.id.code6Text)).requestFocus();
                    ((EditText) findViewById(R.id.code6Text)).setCursorVisible(true);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void validateCode() {
        Log.d(TAG, "Validate coach code");

        _validateButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(CoachCodeActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Validando código ...");
        progressDialog.show();

        String code1 = _code1Text.getText().toString();
        String code2 = _code2Text.getText().toString();
        String code3 = _code3Text.getText().toString();
        String code4 = _code4Text.getText().toString();
        String code5 = _code5Text.getText().toString();
        String code6 = _code6Text.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onValidationSuccess();
                        //onValidationFailed();
                        progressDialog.dismiss();
                    }
                }, 2000);
    }


    public void onValidationSuccess() {
        _validateButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onValidationFailed() {
        Toast.makeText(getBaseContext(), "Código incorrecto", Toast.LENGTH_LONG).show();

        _validateButton.setEnabled(true);
    }
}
