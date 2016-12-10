package com.wedevol.smartclass.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.soundcloud.android.crop.Crop;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.utils.UtilMethods;
import com.wedevol.smartclass.utils.interfaces.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignupActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_lastname;
    private EditText et_phone;
    private EditText et_email;
    private EditText et_password;
    private Button b_signup;
    private Spinner sp_type;
    private Spinner sp_course;
    private CircleImageView civ_profile_photo;
    private ImageView iv_toolbar_back;
    private File mDestinationFile;
    private String mPhotoLocationPath;
    private final int CAMERA_REQUEST_CODE = 1;
    private final int GALLERY_REQUEST_CODE = 2;
    private Activity self;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setElements();
        setActions();
    }

    private void setElements() {
        self = this;
        TextView tv_detail_title = (TextView) findViewById(R.id.tv_detail_title);
        ImageView iv_toolbar_actual_screen = (ImageView) findViewById(R.id.iv_toolbar_actual_screen);
        tv_detail_title.setText("Crear cuenta");
        iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_face_black));

        et_name = (EditText) findViewById(R.id.et_name);
        et_lastname = (EditText) findViewById(R.id.et_lastname);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        b_signup = (Button) findViewById(R.id.b_signup);
        sp_type = (Spinner) findViewById(R.id.sp_type);
        sp_course = (Spinner) findViewById(R.id.sp_course);
        sp_type.setVisibility(View.VISIBLE);
        sp_course.setVisibility(View.VISIBLE);
        civ_profile_photo = (CircleImageView) findViewById(R.id.civ_profile_photo);

        iv_toolbar_back = (ImageView) findViewById(R.id.iv_toolbar_back);

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
    }

    private void setActions() {
        //for the event on the right drawable
        et_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (et_password.getRight() - et_password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        if(et_password.getTransformationMethod()==null){
                            et_password.setTransformationMethod(new PasswordTransformationMethod());
                        }else{
                            et_password.setTransformationMethod(null);
                        }
                        return true;
                    }
                }
                return false;
            }
        });

        civ_profile_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPhotoOptions();
            }
        });

        b_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                new android.os.Handler().postDelayed(new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        b_signup.setEnabled(true);
                        setResult(RESULT_OK, null);
                        finish();
                        //onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 1000);
            }
        });

        iv_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.finish();
            }
        });
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

    protected void showPhotoOptions() {
        List<String> options = Arrays.asList(getString(R.string.take_photo),
                getString(R.string.choose_from_gallery));

        UtilMethods.createSelectionDialog(this, getString(R.string.choose_option),
                new ArrayList<Object>(options), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                invokeDeviceCamera();
                                break;
                            case 1:
                                invokePhotoGallery();
                                break;
                        }
                    }
                });
    }

    private void invokeDeviceCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (cameraIntent.resolveActivity(this.getPackageManager()) != null) {
            mDestinationFile = UtilMethods.getImageFile();

            if (mDestinationFile != null) {
                mPhotoLocationPath = mDestinationFile.getAbsolutePath();
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mDestinationFile));
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
            }

        }
    }

    private void invokePhotoGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        if (galleryIntent.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK ) {
            if (requestCode == GALLERY_REQUEST_CODE) {
                mPhotoLocationPath = UtilMethods.getGalleryImagePath(this, data.getData());
            }
            if (requestCode == GALLERY_REQUEST_CODE || requestCode == CAMERA_REQUEST_CODE) {
                File file = UtilMethods.getImageFile();
                if (file != null) {
                    Uri photoUri = Uri.fromFile(new File(mPhotoLocationPath));
                    Crop.of(photoUri, Uri.fromFile(file)).asSquare().start(this);
                    mPhotoLocationPath = file.getAbsolutePath();
                }
                UtilMethods.setPhoto(this, civ_profile_photo, mPhotoLocationPath, Constants.USER_PHOTO);
            } else if (requestCode == Crop.REQUEST_CROP) {
                //Result from cropping. Do nothing
            }
        }else{
            mPhotoLocationPath = null;
        }
    }
}