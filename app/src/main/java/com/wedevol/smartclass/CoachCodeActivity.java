package com.wedevol.smartclass;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
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
        actionBar.setDisplayHomeAsUpEnabled(true);

        _validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateCode();
            }
        });

        _code1Text.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (_code1Text.getText().toString().length() == 1) {
                    _code1Text.clearFocus();
                    _code2Text.requestFocus();
                    _code2Text.setCursorVisible(true);
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

        _code2Text.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (_code2Text.getText().toString().length() == 1) {
                    _code2Text.clearFocus();
                    _code3Text.requestFocus();
                    _code3Text.setCursorVisible(true);
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

        _code3Text.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (_code3Text.getText().toString().length() == 1) {
                    _code3Text.clearFocus();
                    _code4Text.requestFocus();
                    _code4Text.setCursorVisible(true);
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

        _code4Text.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (_code4Text.getText().toString().length() == 1) {
                    _code4Text.clearFocus();
                    _code5Text.requestFocus();
                    _code5Text.setCursorVisible(true);
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

        _code5Text.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (_code5Text.getText().toString().length() == 1) {
                    _code5Text.clearFocus();
                    _code6Text.requestFocus();
                    _code6Text.setCursorVisible(true);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
                }, 1000);
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
