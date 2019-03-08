package com.factrack.forms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.factrack.R;
import com.factrack.containers.teacherFormData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class teacherForm extends AppCompatActivity {

    DatabaseReference root, faculty;
    private String userId;
    private EditText name, email, designation, address, mobileNo, officeNo, homepage;
    private Button btnUpload, btnGetSchedule;
    private Spinner department;
    private static final int CAMERA_REQUEST = 1888;
    private static final int GALLERY_REQUEST = 2;
    private String Department;
    String generatedFilePath;
    Bitmap photo;
    Uri selectedImage;

    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        designation = findViewById(R.id.designation);
        department = findViewById(R.id.department);
        address = findViewById(R.id.address);
        mobileNo = findViewById(R.id.mobileNo);
        officeNo =  findViewById(R.id.officeNo);
        homepage = findViewById(R.id.homepage);

        btnUpload = findViewById(R.id.uploadImage);
        btnGetSchedule = findViewById(R.id.getSchedule);


        //department.setOnItemSelectedListener(new addScheduleItemSelectedListener());

        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Your code here

                Department = String.valueOf(department.getSelectedItem());
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                Department = "Not selected";
                return;
            }
        });


        btnGetSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(teacherForm.this, getSchedule.class));
            }
        });


    }

    public void saveData() {
        final String teacher_name = name.getText().toString().trim();
        final String teacher_email = email.getText().toString().trim();
        final String teacher_department = String.valueOf(department.getSelectedItem());
        final String teacher_designation = designation.getText().toString().trim();
        final String teacher_address = address.getText().toString().trim();
        final String teacher_mobileNo = mobileNo.getText().toString().trim();
        final String teacher_officeNo = officeNo.getText().toString().trim();
        final String teacher_homepage = homepage.getText().toString().trim();
        final String teacher_imageLink = "";

        uploadImage();

        teacherFormData teacher_info = new teacherFormData(teacher_name, teacher_email, teacher_designation, teacher_department, teacher_mobileNo, teacher_officeNo, teacher_address, teacher_homepage, teacher_imageLink);
        root = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        faculty = root.child("faculty");
        faculty.child(userId).setValue(teacher_info);

    }

    public void getImg(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private String uploadImage() {


        if(selectedImage != null)
        {

            final StorageReference ref = storageReference.child("faculty").child(userId);

            ref.putFile(selectedImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //progressDialog.dismiss();
                            generatedFilePath = ref.getDownloadUrl().toString();
                            Toast.makeText(teacherForm.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //progressDialog.dismiss();
                            Toast.makeText(teacherForm.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

            return generatedFilePath;
        } else {
            Toast.makeText(this, "SelectedImage is null", Toast.LENGTH_SHORT).show();
            return "";
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView Imview = (ImageView) findViewById(R.id.view_image);
            Imview.setImageBitmap(imageBitmap);

            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            selectedImage = getImageUri(getApplicationContext(), imageBitmap);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            Toast.makeText(StudentForm.this,"Here "+ getRealPathFromURI(selectedImage), Toast.LENGTH_LONG).show();
        }
        else */
        if(resultCode == Activity.RESULT_OK && requestCode == GALLERY_REQUEST) {
            selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ImageView carImage = (ImageView) findViewById(R.id.view_image);
                carImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                Log.i("TAG", "Some exception " + e);
            }
        }
    }
}
