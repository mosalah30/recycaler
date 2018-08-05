package com.example.mosalah.recycaler;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment implements View.OnClickListener {
    private EditText name, pass, email;

    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        name = view.findViewById(R.id.B2_name);
        pass = view.findViewById(R.id.B2_password);
        email = view.findViewById(R.id.B2_email);
        Button re = view.findViewById(R.id.B2_register);
        Button sign = view.findViewById(R.id.B2_sign);
        re.setOnClickListener(this);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main1Activity.fragmentManager.beginTransaction().replace(R.id.Active, new BlankFragment())
                        .addToBackStack(null).commit();
            }
        });
        return view;

    }

    @Override
    public void onClick(View v) {

                try {
                    final String n = name.getText().toString();
                    String p = pass.getText().toString();
                    String e = email.getText().toString();
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getContext(),"You have been successfully registered",Toast.LENGTH_LONG).show();
                            }else{
                                if (task.getException().getMessage().equals("The email address is already in use by another account.")){
                                    Toast.makeText(getContext(), "The email address is already in use by another account.", Toast.LENGTH_SHORT).show();
                                }

                            }
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            UserProfileChangeRequest u = new UserProfileChangeRequest.Builder().setDisplayName(n).build();
                            firebaseUser.updateProfile(u);
                        }
                    });

                } catch (Exception e) {
                    Toast.makeText(getContext(), "please write your information", Toast.LENGTH_SHORT).show();
                }

            }
        }



