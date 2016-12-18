package com.wedevol.smartclass.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.JsonArray;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {
    private static final int REQUEST_SIGNUP = 1;
    private EditText et_email;
    private EditText et_password;
    private Button b_login;
    private Button b_signup;
    private ToggleButton tb_instructor_student;
    private boolean isInstructor = true;
    private RestClient restClient;
    private Activity self;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setElements();
        setActions();
    }

    private void setElements() {
        restClient = new RestClient(this);
        self = this;

        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        b_login = (Button) findViewById(R.id.b_login);
        b_signup = (Button) findViewById(R.id.b_signup);
        tb_instructor_student = (ToggleButton) findViewById(R.id.tb_instructor_student);

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

                restClient.getWebservices().getInstructor("",1,new IClassCallback<JsonArray>(self) {
                    @Override
                    public void success(JsonArray jsonObject, Response response) {
                        super.success(jsonObject, response);
                        Log.d("The response format", jsonObject.toString());
                    }
                });

                /**
                 TODO Por alguna razon me esta devolviendo un array para un get unico...
                restClient.getWebservices().getInstructor("",1,new IClassCallback<JsonObject>(self) {
                    @Override
                    public void success(JsonObject jsonObject, Response response) {
                        super.success(jsonObject, response);
                        //-12.103676, -76.948572 Our house location
                        Log.d("The response format", jsonObject.toString());
                    }
                });
                */

                // TODO: Need to save on the shared preferences the user and its type once I m able to receive it.
                /*new android.os.Handler().postDelayed(new Runnable() {
                    public void run() {
                        b_login.setEnabled(true);

                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra(Constants.BUNDLE_INSTRUCTOR, isInstructor);
                        startActivity(intent);

                        progressDialog.dismiss();
                    }
                }, 1000);*/
            }
        });

        b_signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        tb_instructor_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isInstructor = !isInstructor;
                String message;
                if(isInstructor){ message = "Entrando como instructor";} else message = "Entrando como alumno";
                Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
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