package com.wedevol.smartclass.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wedevol.smartclass.R;

public class LoginActivity extends AppCompatActivity {
    private static final int REQUEST_SIGNUP = 1;
    private EditText et_email;
    private EditText et_password;
    private Button b_login;
    private Button b_signup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setElements();
        setActions();
    }

    private void setElements() {
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        b_login = (Button) findViewById(R.id.b_login);
        b_signup = (Button) findViewById(R.id.b_signup);

    }

    private void setActions(){
        b_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*if (!validate()) {
                    return;
                }*/
                b_login.setEnabled(false);

                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Autenticando ...");
                progressDialog.show();

                final String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                // TODO: Need to save on the shared preferences the user and its type once I m able to receive it.
                new android.os.Handler().postDelayed(new Runnable() {
                    public void run() {
                        b_login.setEnabled(true);
                        if (email.equals("1")){
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        }
                        progressDialog.dismiss();
                    }
                }, 1000);

            }
        });

        b_signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

    }

    public boolean validate() {
        boolean valid = true;
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("Por favor, colocar un email válido");
            valid = false;
        } else {
            et_email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            et_password.setError("La contraseña debe tener entre 4 y 10 caracteres alfanuméricos");
            valid = false;
        } else {
            et_password.setError(null);
        }

        return valid;
    }
}