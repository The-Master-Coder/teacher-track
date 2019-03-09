package com.factrack.forms;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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
import com.factrack.containers.studentFormData;
import com.factrack.containers.teacherFormData;
import com.factrack.login.LoginActivity;
import com.factrack.studentBottomNavigation.StudentBottomNav;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StreamDownloadTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class studentForm extends AppCompatActivity {

    private EditText name, email, mobileNo, roomNo, rollNo;
    private Spinner hostel;
    private Button btnUpload, btnSubmit;
    private static final int CAMERA_REQUEST = 1888;
    private static final int GALLERY_REQUEST = 2;

    DatabaseReference root, student;
    private String userId;
    private String Name, Email, MobileNo, RoomNo, Hostel;

    String generatedFilePath="default", path;
    Bitmap photo;
    Uri selectedImage;
    StorageReference ref;

    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        rollNo = findViewById(R.id.rollNo);
        roomNo = findViewById(R.id.roomNo);
        hostel = findViewById(R.id.hostel);
        mobileNo = findViewById(R.id.mobileNo);

        btnUpload = findViewById(R.id.uploadImage);
        btnSubmit = findViewById(R.id.submit);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        hostel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Your code here

                Hostel = String.valueOf(hostel.getSelectedItem());
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                Hostel = "Not selected";
                return;
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(studentForm.this);
                alertDialog.setTitle("Confirm.....");
                alertDialog.setMessage("Do you wanna save data ?");
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                        //saveData();
                        uploadImage();
                    }
                });

                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });

    }


    /*public void saveData() {
        final String student_name = name.getText().toString().trim();
        final String student_email = email.getText().toString().trim();
        final String student_hostel = String.valueOf(hostel.getSelectedItem());
        final String student_rollNo = rollNo.getText().toString().trim();
        final String student_roomNo = roomNo.getText().toString().trim();
        final String student_mobileNo = mobileNo.getText().toString().trim();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        uploadImage();

        generatedFilePath = getURL();
        final String student_imageLink = generatedFilePath;

        studentFormData student_info = new studentFormData(student_name,student_email, student_rollNo,
                student_mobileNo, student_hostel, student_roomNo, student_imageLink);
        root = FirebaseDatabase.getInstance().getReference();

        student = root.child("student");
        student.child(userId).setValue(student_info);

    }*/

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

    private void uploadImage() {


        final String student_name = name.getText().toString().trim();
        final String student_email = email.getText().toString().trim();
        final String student_hostel = String.valueOf(hostel.getSelectedItem());
        final String student_rollNo = rollNo.getText().toString().trim();
        final String student_roomNo = roomNo.getText().toString().trim();
        final String student_mobileNo = mobileNo.getText().toString().trim();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        if(selectedImage != null)
        {
            ref = storageReference.child("students").child(userId);

            ref.putFile(selectedImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    // getting image uri and converting into string
                                    Uri downloadUrl = uri;
                                    generatedFilePath = downloadUrl.toString();

                                    final String student_imageLink = generatedFilePath;

                                    studentFormData student_info = new studentFormData(student_name,student_email, student_rollNo,
                                            student_mobileNo, student_hostel, student_roomNo, student_imageLink);
                                    root = FirebaseDatabase.getInstance().getReference();

                                    student = root.child("student");
                                    student.child(userId).setValue(student_info);

                                    Toast.makeText(studentForm.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(studentForm.this, StudentBottomNav.class));
                                    finish();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //progressDialog.dismiss();
                            Toast.makeText(studentForm.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            Toast.makeText(this, "SelectedImage is null", Toast.LENGTH_SHORT).show();
        }

    }

    private String getURL(){

        StorageReference ref2 = storageReference.child("students").child(userId);
        ref2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                generatedFilePath = uri.toString();
                Toast.makeText(studentForm.this, "URL OF IMAGE : "+generatedFilePath, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
        return path;
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
                Toast.makeText(this, "Image is Settt "+selectedImage.toString(), Toast.LENGTH_SHORT).show();
                carImage.setImageBitmap(bitmap);
                //selectedImage = getImageUri(getApplicationContext(), bitmap);

            } catch (IOException e) {
                Log.i("TAG", "Some exception " + e);
            }
        }
    }
}
