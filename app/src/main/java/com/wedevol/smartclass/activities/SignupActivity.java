package com.wedevol.smartclass.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.wedevol.smartclass.R;

import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    private EditText et_name;
    private EditText et_lastname;
    private EditText et_phone;
    private EditText et_email;
    private EditText et_password;
    private Button b_signup;
    private Spinner sp_type;
    private Spinner sp_course;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Crear cuenta");
        actionBar.setDisplayHomeAsUpEnabled(true);

        setElements();
        setActions();
    }



    private void setElements() {
         et_name = (EditText) findViewById(R.id.et_name);
         et_lastname = (EditText) findViewById(R.id.et_lastname);
         et_phone = (EditText) findViewById(R.id.et_phone);
         et_email = (EditText) findViewById(R.id.et_email);
         et_password = (EditText) findViewById(R.id.et_password);
         b_signup = (Button) findViewById(R.id.b_signup);
         sp_type = (Spinner) findViewById(R.id.sp_type);
         sp_course = (Spinner) findViewById(R.id.sp_course);
    }

    private void setActions() {
        sp_type.setVisibility(View.VISIBLE);
        sp_course.setVisibility(View.VISIBLE);

        List<String> typeArray =  new ArrayList<>();
        typeArray.add("¿Alumno o asesor?");
        typeArray.add("Alumno");
        typeArray.add("Asesor");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, typeArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type.setAdapter(adapter);

        List<String> courseArray =  new ArrayList<String>();
        courseArray.add("Selecciona un curso");
        courseArray.add("Cálculo 1");
        courseArray.add("Cálculo 2");
        courseArray.add("Física 1");
        courseArray.add("Química 1");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseArray);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_course.setAdapter(adapter2);

        b_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
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

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            //onSignupFailed();
            //return;
        }

        b_signup.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creando cuenta ...");
        progressDialog.show();

        String name = et_name.getText().toString();
        String lastName = et_lastname.getText().toString();
        String phone = et_phone.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        // TODO: Implement your own signup logic here

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        //onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 1000);
    }


    public void onSignupSuccess() {
        b_signup.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Registro incorrecto", Toast.LENGTH_LONG).show();

        b_signup.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = et_name.getText().toString();
        String lastName = et_lastname.getText().toString();
        String phone = et_phone.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            et_name.setError("por lo menos 3 caracteres");
            valid = false;
        } else {
            et_name.setError(null);
        }

        if (lastName.isEmpty() || lastName.length() < 3) {
            et_lastname.setError("por lo menos 3 caracteres");
            valid = false;
        } else {
            et_lastname.setError(null);
        }

        if (phone.isEmpty() || phone.length() < 5) {
            et_phone.setError("por lo menos 5 dígitos");
            valid = false;
        } else {
            et_phone.setError(null);
        }

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