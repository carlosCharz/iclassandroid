package com.wedevol.smartclass.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.fragments.zothermomentaneous.StudentProfileActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

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
                login();
            }
        });

        b_signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                signup();
            }
        });

    }

    public void signup() {
        // Start the Signup activity
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivityForResult(intent, REQUEST_SIGNUP);
    }


    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            //onLoginFailed();
            //return;
        }

        b_login.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Autenticando ...");
        progressDialog.show();

        final String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        //onLoginSuccess();
                        // onLoginFailed();
                        signInWithoutLogin(email);
                        progressDialog.dismiss();
                    }
                }, 1000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
                //this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void signInWithoutLogin(String email){
        b_login.setEnabled(true);
        if (email.equals("1")){
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }

    }

    public void onLoginSuccess() {
        b_login.setEnabled(true);
        Intent intent = new Intent(getApplicationContext(), StudentProfileActivity.class);
        startActivity(intent);
        //finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Inicio de sesión incorrecto", Toast.LENGTH_LONG).show();

        b_login.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("colocar un email válido");
            valid = false;
        } else {
            et_email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            et_password.setError("entre 4 y 10 caracteres alfanuméricos");
            valid = false;
        } else {
            et_password.setError(null);
        }

        return valid;
    }
}