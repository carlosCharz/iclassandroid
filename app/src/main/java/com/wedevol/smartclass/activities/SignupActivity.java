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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.soundcloud.android.crop.Crop;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.models.Instructor;
import com.wedevol.smartclass.models.Student;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.UtilMethods;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit.client.Response;

public class SignupActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_lastname;
    private EditText et_phone;
    private EditText et_email;
    private EditText et_password;
    private Button b_signup;
    private Spinner sp_type;
    private TextView tv_university;
    private TextView tv_faculty;
    private TextView tv_course;
    private CircleImageView civ_profile_photo;
    private ImageView iv_toolbar_back;
    private File mDestinationFile;
    private String mPhotoLocationPath;
    private Activity self;
    private RestClient restClient;
    private int courseId = -1;
    private int universityId = -1;
    private int facultyId = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setElements();
        setActions();
    }

    private void setElements() {
        self = this;
        restClient = new RestClient();

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
        tv_university = (TextView) findViewById(R.id.tv_university);
        tv_faculty = (TextView) findViewById(R.id.tv_faculty);
        tv_course = (TextView) findViewById(R.id.tv_course);
        civ_profile_photo = (CircleImageView) findViewById(R.id.civ_profile_photo);
        iv_toolbar_back = (ImageView) findViewById(R.id.iv_toolbar_back);

        List<String> typeArray =  new ArrayList<>();
        typeArray.add("¿Alumno o asesor?");
        typeArray.add("Alumno");
        typeArray.add("Asesor");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, typeArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_type.setAdapter(adapter);


    }

    private void setActions() {
        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position == 2){
                    tv_course.setVisibility(View.GONE);
                } else {
                    tv_course.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

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
                    return;
                }

                final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Creando cuenta ...");
                progressDialog.show();

                String type = (String) sp_type.getSelectedItem();
                String university = tv_university.getText().toString();

                //current attributes
                String name = et_name.getText().toString();
                String lastName = et_lastname.getText().toString();
                String phone = et_phone.getText().toString();
                final String email = et_email.getText().toString();
                final String password = et_password.getText().toString();

                if(type.equals("Alumno")){
                    Student student = new Student();
                    student.setFirstname(name);
                    student.setLastname(lastName);
                    student.setPhone(phone);
                    student.setEmail(email);
                    student.setUniversity(university);
                    student.setUniversityId(universityId);
                    student.setFacultyId(facultyId);
                    student.setPassword(password);
                    student.setFcmToken(FirebaseInstanceId.getInstance().getToken());
                    JsonObject studentObject = student.toJson();
                    studentObject.addProperty("courseId", courseId);

                    try {
                        restClient.getWebservices().newStudent("", studentObject, new IClassCallback<JsonObject>(self){
                            @Override
                            public void success(JsonObject jsonObject, Response response) {
                                super.success(jsonObject, response);
                                setResult(RESULT_OK, null);
                                progressDialog.dismiss();
                                login(email, password, false);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    Instructor instructor = new Instructor();
                    instructor.setFirstname(name);
                    instructor.setLastname(lastName);
                    instructor.setPhone(phone);
                    instructor.setEmail(email);
                    instructor.setPassword(password);
                    instructor.setUniversityId(universityId);
                    instructor.setFacultyId(facultyId);
                    instructor.setFcmToken(FirebaseInstanceId.getInstance().getToken());
                    JsonObject instructorObject = instructor.toJson();

                    restClient.getWebservices().newInstructor("", instructorObject, new IClassCallback<JsonObject>(self){
                        @Override
                        public void success(JsonObject jsonObject, Response response) {
                            super.success(jsonObject, response);
                            setResult(RESULT_OK, null);
                            progressDialog.dismiss();
                            login(email, password, true);
                        }
                    });
                }
            }
        });

        iv_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.finish();
            }
        });

        tv_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(self, ListCoursesActivity.class);
                startActivityForResult(intent, Constants.CHOOSEN_COURSE);
            }
        });

        tv_university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(self, ListUniversityActivity.class);
                startActivityForResult(intent, Constants.CHOOSEN_UNIVERSITY);
            }
        });

        tv_faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(universityId != -1) {
                    Intent intent = new Intent(self, ListFacultyActivity.class);
                    startActivityForResult(intent, Constants.CHOOSEN_FACULTY);
                }else{
                    Toast.makeText(self, "Necesitas escoger una universidad", Toast.LENGTH_SHORT).show();
                }
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
        String courseName = tv_course.getText().toString();
        String selectedUserType= sp_type.getSelectedItem().toString();
        String university = tv_university.getText().toString();
        String faculty = tv_faculty.getText().toString();

        if(selectedUserType.equals("¿Alumno o asesor?")){
            valid = false;
            Toast.makeText(this, "Debe elegir ser asesor o alumno", Toast.LENGTH_SHORT).show();
        }

        if (name.isEmpty() || name.length() < 3) {
            et_name.setError("El nombre deberia tener por lo menos 3 caracteres");
            valid = false;
        } else {
            et_name.setError(null);
        }

        if (lastName.isEmpty() || lastName.length() < 3) {
            et_lastname.setError("El apellido deberia tener por lo menos 3 caracteres");
            valid = false;
        } else {
            et_lastname.setError(null);
        }

        if (phone.isEmpty() || phone.length() < 7) {
            et_phone.setError("El telefono es de por lo menos 7 dígitos");
            valid = false;
        } else {
            et_phone.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("Colocar un email válido");
            valid = false;
        } else {
            et_email.setError(null);
        }

        if (password.isEmpty() || password.length() <= 6 || password.length() >= 20) {
            et_password.setError("La contrasenha debe tener minimo 6 o maximo 20 caracteres");
            valid = false;
        } else {
            et_password.setError(null);
        }

        if(selectedUserType.equals("Alumno") && courseName.isEmpty()){
            tv_course.setError("Debe elegir un curso gratuito");
            valid = false;
        }else{
            tv_course.setError(null);
        }

        if(university.isEmpty()){
            tv_university.setError("Debe elegir una universidad");
            valid = false;
        }else{
            tv_university.setError(null);
        }

        if(faculty.isEmpty()){
            tv_faculty.setError("Debe elegir una facultad");
            valid = false;
        }else{
            tv_faculty.setError(null);
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
                startActivityForResult(cameraIntent, Constants.CAMERA_REQUEST_CODE);
            }

        }
    }

    private void invokePhotoGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        if (galleryIntent.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(galleryIntent, Constants.GALLERY_REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK ) {
            if (requestCode == Constants.GALLERY_REQUEST_CODE) {
                mPhotoLocationPath = UtilMethods.getGalleryImagePath(this, data.getData());
            }

            if (requestCode == Constants.GALLERY_REQUEST_CODE || requestCode == Constants.CAMERA_REQUEST_CODE) {
                File file = UtilMethods.getImageFile();
                if (file != null) {
                    Uri photoUri = Uri.fromFile(new File(mPhotoLocationPath));
                    Crop.of(photoUri, Uri.fromFile(file)).asSquare().start(this);
                    mPhotoLocationPath = file.getAbsolutePath();
                }
                UtilMethods.setPhoto(this, civ_profile_photo, mPhotoLocationPath, Constants.USER_PHOTO);
            } //else if (requestCode == Crop.REQUEST_CROP) {
                //Result from cropping. Do nothing
            //}

            if((requestCode == Constants.CHOOSEN_COURSE)) {
                String courseName = data.getStringExtra(Constants.BUNDLE_COURSE_NAME);
                courseId = data.getIntExtra(Constants.BUNDLE_COURSE_ID, -1);
                tv_course.setText(courseName);
            }

            if((requestCode == Constants.CHOOSEN_FACULTY)) {
                String facultyName = data.getStringExtra(Constants.BUNDLE_FACULTY_NAME);
                facultyId = data.getIntExtra(Constants.BUNDLE_FACULTY_ID, -1);
                tv_faculty.setText(facultyName);
            }

            if((requestCode == Constants.CHOOSEN_UNIVERSITY)) {
                String universityName = data.getStringExtra(Constants.BUNDLE_UNIVERSITY_NAME);
                universityId = data.getIntExtra(Constants.BUNDLE_UNIVERSITY_ID, -1);
                tv_university.setText(universityName);
            }
        }else{
            mPhotoLocationPath = null;
        }
    }

    private void login(String email, String password, final boolean isInstructor) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", password);

        if(isInstructor) {
            restClient.getWebservices().authInstructor("", jsonObject, new IClassCallback<JsonObject>(self) {
                @Override
                public void success(JsonObject jsonObject, Response response) {
                    super.success(jsonObject, response);
                    startHome(jsonObject, true);
                }
            });
        }else{
            restClient.getWebservices().authStudent("", jsonObject, new IClassCallback<JsonObject>(self) {
                @Override
                public void success(JsonObject jsonObject, Response response) {
                    super.success(jsonObject, response);
                    startHome(jsonObject, false);
                }
            });
        }
    }

    public void startHome(JsonObject jsonObject, boolean isInstructor){
        Gson gson = new Gson();
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance(self);
        sharedPreferencesManager.saveUser("", gson.toJson(jsonObject));
        sharedPreferencesManager.saveUserType(isInstructor);

        Intent intent = new Intent(self, HomeActivity.class);
        intent.putExtra(Constants.BUNDLE_INSTRUCTOR, isInstructor);
        startActivity(intent);
        finish();
    }
}