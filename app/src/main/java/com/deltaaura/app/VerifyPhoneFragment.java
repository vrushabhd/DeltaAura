package com.deltaaura.app;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyPhoneFragment extends Fragment {
    private String mverificationId = null;
    LinearLayout linearLayoutPhone, linearLayoutVerify;
    Button butttonverify, buttonsend;
    String phone;
    EditText editTextcode;
    com.hbb20.CountryCodePicker countrycode;
    private EditText phonenumber;

    public VerifyPhoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_verify_phone, container, false);

        linearLayoutPhone = view.findViewById(R.id.layoutPhone);
        linearLayoutVerify = view.findViewById(R.id.layoutVerification);
        butttonverify = view.findViewById(R.id.button_verify);
        buttonsend = view.findViewById(R.id.button_send_verification);
        phonenumber = view.findViewById(R.id.edit_text_phone);
        countrycode = view.findViewById(R.id.ccp);
        editTextcode = view.findViewById(R.id.edit_text_code);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//
        linearLayoutPhone.setVisibility(View.VISIBLE);
        linearLayoutVerify.setVisibility(View.GONE);

        buttonsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phone = phonenumber.getText().toString().trim();
                if (phone.isEmpty() || phone.length() != 10) {

                    phonenumber.setError("Enter a valid phone number");
                    phonenumber.requestFocus();
                    return;

                }
                String phoneNumber = "+" + countrycode.getSelectedCountryCode() + phone;

                PhoneAuthProvider.getInstance()
                        .verifyPhoneNumber(
                                phoneNumber,
                                60,
                                TimeUnit.SECONDS,
                                requireActivity(),
                                phoneAuthCallBacks
                        );
                linearLayoutPhone.setVisibility(View.GONE);
                linearLayoutVerify.setVisibility(View.VISIBLE);



            }
        });
        butttonverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = editTextcode.getText().toString().trim();
                if (code.isEmpty()) {
                    editTextcode.setError("Code required");
                    editTextcode.requestFocus();
                    return;


                }

                if (mverificationId != null) {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mverificationId, code);

                    addPhoneNumber(credential);


                }


            }
        });

    }


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks phoneAuthCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


        @Override //using this function automatically the user will get verified.
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            if (phoneAuthCredential != null) {

                addPhoneNumber(phoneAuthCredential);


            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override //when user need to manually input the verification code
        public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken Token) {
            mverificationId = verificationId;

            super.onCodeSent(verificationId, Token);
        }
    };


    private void addPhoneNumber(PhoneAuthCredential phoneAuthCredential) {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseAuth.getInstance()
                    .getCurrentUser()
                    .updatePhoneNumber(phoneAuthCredential)
                 .addOnSuccessListener(new OnSuccessListener<Void>() {
                                           @Override
                                           public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity().getApplicationContext(), "Phone Updated", Toast.LENGTH_SHORT).show();
                                               NavDirections action =VerifyPhoneFragmentDirections.actionPhoneVerified();
                                               Navigation.findNavController(butttonverify).navigate(action);

//                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                                fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
//                                fragmentTransaction.remove(new VerifyPhoneFragment());
//                                fragmentTransaction.commit();


                            }
                                           }
                                       )
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                     Toast.makeText(getActivity().getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();

                            NavDirections action =VerifyPhoneFragmentDirections.actionPhoneVerified();
                            Navigation.findNavController(butttonverify).navigate(action);


                        }
                    });




//                        @Override
//                        public void onSuccess(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                Toast.makeText(getActivity().getApplicationContext(), "Phone Updated", Toast.LENGTH_SHORT).show();
//
//                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                                fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
//                                fragmentTransaction.replace(R.id.phoneFragment, new ProfileFragment());
//                                fragmentTransaction.commit();
//
//
//                            } else {
//
//                                Toast.makeText(getActivity().getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
//
//
//                            }
//
//
//                        }
//                    });


        }


    }




}
