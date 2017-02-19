package com.wedevol.smartclass.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.soundcloud.android.crop.Crop;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.models.User;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.UtilMethods;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.client.Response;
import retrofit.mime.TypedFile;

/** Created by paolo on 1/27/17.*/
public class EditProfileActivity  extends AppCompatActivity {
    private FloatingActionButton fab_finish_edit_profile;
    private boolean isInstructor;
    private User user;
    private TextView tv_user_university;
    private TextView tv_user_faculty;
    private RestClient restClient;
    private Activity self;
    private EditText tv_user_profile_number;
    private ImageView iv_user_profile_photo;
    private int universityId;
    private int facultyId;
    private String mPhotoLocationPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setupElements();
        setupActions();
    }

    private void setupElements() {
        self = this;
        restClient = new RestClient(this);
        isInstructor = SharedPreferencesManager.getInstance(this).getUserType();
        user = SharedPreferencesManager.getInstance(this).getUserInfo();

        iv_user_profile_photo = (ImageView)  findViewById(R.id.iv_user_profile_photo);
        TextView tv_user_level = (TextView) findViewById(R.id.tv_user_level);
        ProgressBar pb_user_progress = (ProgressBar) findViewById(R.id.pb_user_progress);
        TextView tv_user_counselled_time = (TextView) findViewById(R.id.tv_user_counselled_time);
        TextView tv_user_profile_type = (TextView) findViewById(R.id.tv_user_profile_type);
        TextView tv_user_profile_email = (TextView) findViewById(R.id.tv_user_profile_email);

        tv_user_profile_number = (EditText) findViewById(R.id.et_user_profile_number);
        tv_user_faculty = (TextView) findViewById(R.id.tv_user_faculty);
        tv_user_university = (TextView) findViewById(R.id.tv_user_university);
        fab_finish_edit_profile = (FloatingActionButton) findViewById(R.id.fab_finish_edit_profile);

        UtilMethods.setPhoto(this, iv_user_profile_photo, user.getProfilePictureUrl(), Constants.USER_PHOTO);
        tv_user_level.setText("Nivel "+ user.getLevel());
        pb_user_progress.setProgress(((Double)user.getRating()).intValue());
        tv_user_counselled_time.setText(user.getTotalHours() + " hrs");
        tv_user_profile_type.setText(isInstructor?"Instructor":"Alumno");
        tv_user_profile_number.setText(user.getPhone());
        tv_user_profile_email.setText(user.getEmail());

        tv_user_faculty.setText(user.getFacultyName());
        tv_user_university.setText(user.getUniversityName());

        universityId = user.getUniversityId();
        facultyId = user.getFacultyId();
    }

    private void setupActions() {
        fab_finish_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phone = tv_user_profile_number.getText().toString();

                if(phone.isEmpty()){
                    Toast.makeText(self, "El telefono no puede estar vacio", Toast.LENGTH_SHORT).show();
                    return;
                }

                user.setUniversityId(universityId == -1 ? user.getUniversityId(): universityId);
                user.setFacultyId(facultyId == -1 ? user.getFacultyId(): facultyId);
                user.setPhone(phone);
                user.setPassword(SharedPreferencesManager.getInstance(self).getUserTruePassword());

                if(isInstructor){
                    restClient.getWebservices().updateInstructor(user.getAccessToken(), user.getId(), user.toJsonUpdate(), new IClassCallback<JsonObject>(self){
                        @Override
                        public void success(JsonObject jsonObject, Response response) {
                            super.success(jsonObject, response);
                            setResult(Activity.RESULT_OK);
                            relogin();
                        }
                    });
                }else{
                    restClient.getWebservices().updateStudent(user.getAccessToken(), user.getId(), user.toJsonUpdate(), new IClassCallback<JsonObject>(self){
                        @Override
                        public void success(JsonObject jsonObject, Response response) {
                            super.success(jsonObject, response);
                            setResult(Activity.RESULT_OK);
                            relogin();
                        }
                    });
                }
            }
        });

        tv_user_faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(self, ListFacultyActivity.class);
                intent.putExtra(Constants.BUNDLE_UNIVERSITY_ID, universityId);
                startActivityForResult(intent, Constants.CHOOSEN_FACULTY);
            }
        });

        tv_user_university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(self, ListUniversityActivity.class);
                startActivityForResult(intent, Constants.CHOOSEN_UNIVERSITY);
            }
        });

        iv_user_profile_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPhotoOptions();
            }
        });
    }

    private void relogin() {
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance(self);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", sharedPreferencesManager.getUserInfo().getEmail());
        jsonObject.addProperty("password", sharedPreferencesManager.getUserTruePassword());

        if(isInstructor) {
            restClient.getWebservices().authInstructor("", jsonObject, new IClassCallback<JsonObject>(self) {
                @Override
                public void success(JsonObject jsonObject, Response response) {
                    super.success(jsonObject, response);
                    updateUser(jsonObject);
                    self.finish();
                }
            });
        }else{
            restClient.getWebservices().authStudent("", jsonObject, new IClassCallback<JsonObject>(self) {
                @Override
                public void success(JsonObject jsonObject, Response response) {
                    super.success(jsonObject, response);
                    updateUser(jsonObject);
                    self.finish();
                }
            });
        }
    }

    private void updateUser(JsonObject jsonObject) {
        Gson gson = new Gson();
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance(self);
        sharedPreferencesManager.saveUser("", gson.toJson(jsonObject));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((resultCode == Activity.RESULT_OK)){
            if((requestCode == Constants.CHOOSEN_UNIVERSITY)) {
                String universityName = data.getStringExtra(Constants.BUNDLE_UNIVERSITY_NAME);
                universityId = data.getIntExtra(Constants.BUNDLE_UNIVERSITY_ID, user.getUniversityId());
                tv_user_university.setText(universityName);

            }

            if((requestCode == Constants.CHOOSEN_FACULTY)) {
                facultyId = -1;
                String facultyName = data.getStringExtra(Constants.BUNDLE_FACULTY_NAME);
                facultyId = data.getIntExtra(Constants.BUNDLE_FACULTY_ID, user.getFacultyId());
                tv_user_faculty.setText(facultyName);
            }

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

                UtilMethods.setPhoto(this, iv_user_profile_photo, mPhotoLocationPath, Constants.USER_PHOTO);

                //TODO uploadStudentPhoto
                if(!isInstructor){
                    TypedFile typedImageFile = new TypedFile("image/jpeg", file);
                    restClient.getWebservices().uploadStudentPhoto(user.getAccessToken(), user.getId(), typedImageFile,
                            new IClassCallback<JsonObject>(self) {
                                @Override
                                public void success(JsonObject jsonObject, Response response) {
                                    super.success(jsonObject, response);
                                }
                            }
                    );
                }

            }
        } else {
            mPhotoLocationPath = null;

        }
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
            File mDestinationFile = UtilMethods.getImageFile();

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

}