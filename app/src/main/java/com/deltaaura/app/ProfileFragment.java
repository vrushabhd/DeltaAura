package com.deltaaura.app;


import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    TextView uniqueid;
    FloatingActionButton fab_plus,fab_fb,fab_insta,fab_linkedin;
    private Uri imageUri,photo;
    private String DEFAULT_IMAGE_URL ="https://picsum.photos/200";
    Animation Fabopen,FabClose,FabRClockwise,FabRantclockwise;
    String name,mail;
    Button  buttonsave,uidBtn;
    ImageView imageView;
    ProgressBar progressBar;
    private int REQUEST_IMAGE_CAPTURE = 100;//track the callback
    private EditText editTextname;
    private TextView textEmail, textPhone,textnotverified,emailverified,phoneverified,phonenotverified;
    private FirebaseUser currenUser = FirebaseAuth.getInstance().getCurrentUser();
    boolean isOpen =false;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
         phonenotverified = view.findViewById(R.id.phone_not_verified);
//         uidBtn= view.findViewById(R.id.uidbtn);
        uniqueid =view.findViewById(R.id.uniqueid);
        phoneverified = view.findViewById(R.id.phoneverified);
        emailverified =view.findViewById(R.id.emailverified);
        buttonsave = view.findViewById(R.id.button_save);
        editTextname = view.findViewById(R.id.edit_text_name);
        textPhone = view.findViewById(R.id.text_phone);
        textEmail = view.findViewById(R.id.text_email);
        progressBar = view.findViewById(R.id.progressbar);
        imageView = view.findViewById(R.id.image_view);
        textnotverified =view.findViewById(R.id.text_not_verified);
         fab_plus =view.findViewById(R.id.fab);
         fab_fb = view.findViewById(R.id.fabfb);
         fab_insta =view.findViewById(R.id.fabinsta);
         fab_linkedin =view.findViewById(R.id.fablinkedin);
         //Now initializing Animations
         Fabopen = AnimationUtils.loadAnimation(getActivity(),R.anim.fab_open);
         FabClose =AnimationUtils.loadAnimation(getActivity(),R.anim.fab_close);
         FabRClockwise =AnimationUtils.loadAnimation(getActivity(),R.anim.rotate_clockwise);
         FabRantclockwise =AnimationUtils.loadAnimation(getActivity(),R.anim.rotate_anticlockwise);
        textPhone.setText("Add the number");

//        ObjectAnimator colorAnim = ObjectAnimator.ofInt(myButton, "textColor", Color.BLACK, Color.TRANSPARENT);
//        colorAnim.setDuration(1000);
//        colorAnim.setEvaluator(new ArgbEvaluator());
//        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
//        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
//        colorAnim.start();















//        if(currenUser.getPhoneNumber().isEmpty()){
//
//            textPhone.setText("Add the number");
//
//
//
//        }else{
//
//            textPhone.setText(currenUser.getPhoneNumber());
//
//
//
//        }

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
                 editTextname.setText(name);

textPhone.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
                    if(currenUser.getPhoneNumber() ==null) {//if already verified you will not be redirected to verification page.

                        NavDirections action = ProfileFragmentDirections.actionVerifyPhone();
                        Navigation.findNavController(v).navigate(action);
                    }
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
//        fragmentTransaction.replace(R.id.fragment,new VerifyPhoneFragment());
//        fragmentTransaction.addToBackStack("verifyfragment");
//        fragmentTransaction.commit();





    }
});
        if (currenUser != null) {

            Glide.with(this)
                    .load(currenUser.getPhotoUrl())
                    .into(imageView);
            mail = currenUser.getEmail();
            editTextname.setText(currenUser.getDisplayName());
            textEmail.setText(currenUser.getEmail());
            imageUri = currenUser.getPhotoUrl();
            if(textPhone ==null){

          textPhone.setText("Add Number");



            }else{

                textPhone.setText(currenUser.getPhoneNumber());
            }






        }

        textEmail.setText(mail);


        if(currenUser.isEmailVerified()){
            textnotverified.setVisibility(View.GONE);
            emailverified.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "Explore The Navigation Menu on the Left..", Toast.LENGTH_SHORT).show();

        }else{

            textnotverified.setVisibility(View.VISIBLE);
            emailverified.setVisibility(View.GONE);



        }

if(currenUser.getPhoneNumber()!=null && textPhone!=null){
    phonenotverified.setVisibility(View.GONE);
    phoneverified.setVisibility(View.VISIBLE);
}else{
    phoneverified.setVisibility(View.GONE);
    phonenotverified.setVisibility(View.VISIBLE);

}





        textnotverified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currenUser!=null){
                    if(!currenUser.isEmailVerified()) {
                            sendEmailVerification();
                    }
                }
            }
        });



        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // progressBar.setVisibility(View.VISIBLE);
                name =editTextname.getText().toString().trim();
                mail = textEmail.getText().toString().trim();

                if(name.isEmpty()){

                    editTextname.setError("name is Empty");
                    editTextname.requestFocus();
                    return;


                }
              else{
                         progressBar.setVisibility(View.VISIBLE);
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()

                            .setDisplayName(name)
                            .build();

                    user.updateProfile(request)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getActivity().getApplicationContext(), "Updated name succesfully", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity().getApplicationContext(), "Name not updated...", Toast.LENGTH_SHORT).show();
                                }
                            });
            }



            }
        });



//        if(currenUser.isEmailVerified() && currenUser.getPhoneNumber()!=null){
//
//
//            uidBtn.setVisibility(View.VISIBLE);
//
//
//        }else{
//
//
//
//            uidBtn.setVisibility(View.GONE);
//
//
//        }



    fab_plus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
             if(isOpen){

                 fab_fb.startAnimation(FabClose);
                 fab_insta.startAnimation(FabClose);
                 fab_linkedin.startAnimation(FabClose);
                 fab_plus.startAnimation(FabRantclockwise);
                 fab_fb.setClickable(false);
                 fab_insta.setClickable(false);
                 fab_linkedin.setClickable(false);
                 isOpen =false;


             }
             else{

                fab_fb.startAnimation(Fabopen);
                fab_insta.startAnimation(Fabopen);
                fab_linkedin.startAnimation(Fabopen);
                 fab_plus.startAnimation(FabRClockwise);
                 fab_fb.setClickable(true);
                 fab_insta.setClickable(true);
                 fab_linkedin.setClickable(true);
                 isOpen =true;

             }
        }
    });
        fab_fb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                Intent facebookIntent = getOpenFacebookIntent(getActivity());
                startActivity(facebookIntent);

            }
        });
        fab_insta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                Intent instagramIntent = getOpenInstagramIntent(getActivity());
                startActivity(instagramIntent);

            }
        });
        fab_linkedin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                Intent linkdinIntent = getOpenLinkdinIntent(getActivity());
                startActivity(linkdinIntent);
            }
        });

if(currenUser!=null){

//    uidBtn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            MaterialAlertDialogBuilder a_builder =  new MaterialAlertDialogBuilder(getActivity());
//            a_builder.setMessage("Show your unique ID to get your food \uD83C\uDF54 \uD83C\uDF5F \uD83C\uDF55  :\n"+currenUser.getUid()).setCancelable(false)
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.cancel();
//
//                        }
//                    })
//              .show();
//
//
//        }
//    });


    uniqueid.setText("UID :"+currenUser.getUid());




}



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"clicked",Toast.LENGTH_SHORT).show();
                takePictureIntent();
            }
        });



    }


    private void takePictureIntent() {

   Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      if(takePictureIntent.resolveActivity(getActivity().getPackageManager())!=null){

          startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);



      }



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

      if(requestCode == REQUEST_IMAGE_CAPTURE &&  resultCode==RESULT_OK){

         Bundle extras = data.getExtras();
          Bitmap imgbitmap =(Bitmap)extras.get("data");
          imageView.setImageBitmap(imgbitmap);
            progressBar.setVisibility(View.VISIBLE);
             handleupload(imgbitmap);






      }



    }

    private void handleupload(Bitmap imgbitmap) {


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FirebaseStorage storage = FirebaseStorage.getInstance();//create a reference
        if (currenUser != null) {
            final StorageReference storageRef = storage.getReference().child("profilepics/" + FirebaseAuth.getInstance().getCurrentUser().getUid());
            imgbitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();
            final UploadTask uploadTask = storageRef.putBytes(data);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressBar.setVisibility(View.INVISIBLE);
//
                    getDownloadUrl(storageRef);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "onFailure: ", e.getCause());
                }
            });


        }


    }
    private void getDownloadUrl(StorageReference reference) {
        reference.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d(TAG, "onSuccess: " + uri);
                        setUserProfileUrl(uri);
                    }
                });
    }

    private void setUserProfileUrl(Uri uri) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .setDisplayName(name)
                .build();

        user.updateProfile(request)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity().getApplicationContext(), "Updated succesfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity().getApplicationContext(), "Profile image failed...", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    //    private void uploadImageSaveUri(final Bitmap imgbitmap) {
//
//    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        FirebaseStorage storage =FirebaseStorage.getInstance();//create a reference
//        if(currenUser!=null) {
//            final StorageReference storageRef = storage.getReference().child("profilepics/" +  FirebaseAuth.getInstance().getCurrentUser().getUid());
//                 imgbitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
//                 byte[] data = baos.toByteArray();
//            final UploadTask uploadTask = storageRef.putBytes(data);
//                 progressBar.setVisibility(View.VISIBLE);
//
//            uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                    progressBar.setVisibility(View.GONE);
//                    if(uploadTask.isSuccessful()){
//                           storageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                               @Override
//                               public void onComplete(@NonNull Task<Uri> task) {
//                                    if(task.getResult()!= null){
//
//                                        imageUri = task.getResult();
//                                        imageView.setImageBitmap(imgbitmap);
//
//
//                                    }
//                               }
//                           });
//
//
//                    }else{
//
//                        uploadTask.getException();
//
//
//
//
//
//
//
//
//
//
//
//                    }
//
//
//
//
//
//
//
//
//                }
//            });
//
//        }
//
//    }
//
//    public void getUserProfile() {
//        // [START get_user_profile]
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            // Name, email address, and profile photo Url
//            String name = user.getDisplayName();
//            String email = user.getEmail();
//            Uri photoUrl = user.getPhotoUrl();
//
//            // Check if user's email is verified
//            boolean emailVerified = user.isEmailVerified();
//        }
//    }
//
//            public void getProviderData() {
//                // [START get_provider_data]
//                currenUser = FirebaseAuth.getInstance().getCurrentUser();
//                if (currenUser != null) {
//                    for (UserInfo profile : currenUser.getProviderData()) {
//                        // Id of the provider (ex: google.com)
//                        String providerId = profile.getProviderId();
//
//                        // UID specific to the provider
//                        String uid = profile.getUid();
//
//                        // Name, email address, and profile photo Url
//                       String  name = profile.getDisplayName();
//                      String   email = profile.getEmail();
//                      Uri   photoUrl = profile.getPhotoUrl();
//                    }
//                }
//                // [END get_provider_data]
//            }
//
//
//
//
//
//    public void updateProfile() {
//        // [START update_profile]
//   //     FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                .setDisplayName(name)
//               .setPhotoUri(photo)
//                .build();
//                    progressBar.setVisibility(View.VISIBLE);
//       currenUser.updateProfile(profileUpdates)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        progressBar.setVisibility(View.INVISIBLE);
//
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "User profile updated.");
//                            Toast.makeText(getActivity().getApplicationContext(), "User Profile Updated ", Toast.LENGTH_SHORT).show();
//
//                        }else{
//
//                            Toast.makeText(getActivity().getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
//        // [END update_profile]
//    }
    public void sendEmailVerification() {
        // [START send_email_verification]

            currenUser.sendEmailVerification()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getActivity(), "Email sent", Toast.LENGTH_SHORT).show();
                            FirebaseAuth.getInstance().signOut();
                            getActivity().finish();

                            Intent i = new Intent(getActivity(), LoginActivity.class);
                            startActivity(i);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            // [END send_email_verification]
        }

    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/deltatheinnovators/")); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/deltatheinnovators/")); //catches and opens a url to the desired page
        }
    }



    public static Intent getOpenLinkdinIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.linkedin.android", 0); //Checks if Linkdin is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.linkedin.com/company/thedelta/")); //Trys to make intent with Linkdin's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.linkedin.com/company/thedelta/")); //catches and opens a url to the desired page
        }
    }



    public static Intent getOpenInstagramIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.instagram.android", 0); //Checks if Instagram is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/deltatheinnovators/")); //Trys to make intent with Instagram's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/deltatheinnovators/")); //catches and opens a url to the desired page
        }
    }



//    public static Intent getDriveIntent(Context context) {
//
//        try {
//            context.getPackageManager()
//                    .getPackageInfo("com.google.android.apps.docs ", 0); //Checks if Drive is even installed.
//            return new Intent(Intent.ACTION_VIEW,
//                    Uri.parse("https://drive.google.com/file/d/1O-5d4SPNUnfq_HLTb5IgUT8k8tMYm7Uy/view")); //Trys to make intent with Drive URI
//        } catch (Exception e) {
//            return new Intent(Intent.ACTION_VIEW,
//                    Uri.parse("https://drive.google.com/file/d/1O-5d4SPNUnfq_HLTb5IgUT8k8tMYm7Uy/view")); //catches and opens a url to the desired page
//        }
//    }




}
//  startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));