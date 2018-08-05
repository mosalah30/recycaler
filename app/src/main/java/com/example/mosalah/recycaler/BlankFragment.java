package com.example.mosalah.recycaler;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
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

import org.w3c.dom.Text;

import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements View.OnClickListener {
    private TextInputEditText email, pass;


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment1_blank, container, false);
        Button button = v.findViewById(R.id.button2);
        email = v.findViewById(R.id.editText4);
        pass = v.findViewById(R.id.editText5);

        button.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        login();
        try {
            String e = email.getText().toString();
            String p = pass.getText().toString();
            FirebaseAuth.getInstance().signInWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "You have been successfully registered", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(),MainActivity.class));
                    } else
                        Toast.makeText(getContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(getContext(), "please write your Email & password", Toast.LENGTH_SHORT).show();
        }
    }

    private void login() {
        if ( email.getText().toString().isEmpty() ) {
            email.setError("please type your email address");
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            email.setError("please type your email address");
        }
        if (pass.getText().toString().length()<6) {
            pass.setError("please type your password more then 6 character");
        }

    }
}

