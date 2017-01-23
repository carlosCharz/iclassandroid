package com.wedevol.smartclass.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {
    private static final int REQUEST_SIGNUP = 1;
    private EditText et_email;
    private EditText et_password;
    private Button b_login;
    private Button b_signup;
    private Switch s_instructor_student;
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
        s_instructor_student = (Switch) findViewById(R.id.s_instructor_student);
    }

    private void setActions(){
        b_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!validate()) {
                    return;
                }

                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Autenticando ...");
                progressDialog.show();

                final String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("email", email);
                jsonObject.addProperty("password", password);

                if(isInstructor) {
                    restClient.getWebservices().authInstructor("", jsonObject, new IClassCallback<JsonObject>(self) {
                        @Override
                        public void success(JsonObject jsonObject, Response response) {
                            super.success(jsonObject, response);
                            login(jsonObject, progressDialog);
                        }
                    });
                }else{
                    restClient.getWebservices().authStudent("", jsonObject, new IClassCallback<JsonObject>(self) {
                        @Override
                        public void success(JsonObject jsonObject, Response response) {
                            super.success(jsonObject, response);
                            login(jsonObject, progressDialog);
                        }
                    });
                }
            }
        });

        b_signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        s_instructor_student.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isInstructor = !isInstructor;
                String message;
                if(isInstructor){ message = "Instructor";} else message = "Alumno";
                s_instructor_student.setText(message);
            }
        });
    }

    private void login(JsonObject jsonObject, ProgressDialog progressDialog) {
        Gson gson = new Gson();
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance(self);
        sharedPreferencesManager.saveUser("", gson.toJson(jsonObject));
        sharedPreferencesManager.saveUserType(isInstructor);

        Intent intent = new Intent(self, HomeActivity.class);
        intent.putExtra(Constants.BUNDLE_INSTRUCTOR, isInstructor);
        startActivity(intent);

        progressDialog.dismiss();
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

        if (password.isEmpty() || password.length() < 6) {
            et_password.setError("La contraseña debe tener minimo 6 caracteres alfanuméricos");
            valid = false;
        } else {
            et_password.setError(null);
        }
        return valid;
    }
}