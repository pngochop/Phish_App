package edu.uw.tcss450;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

import model.Credentials;


public class SuccessFragment extends Fragment {

    public SuccessFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_success, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle args) {
        if (getArguments() != null) {
            Serializable userObject = getArguments().getSerializable("user");

            Credentials user = (Credentials) userObject;
            String userEmail = user.getEmail();

            TextView emailText = view.findViewById(R.id.success_email_text);
            emailText.setText(userEmail);
        }
    }
}
