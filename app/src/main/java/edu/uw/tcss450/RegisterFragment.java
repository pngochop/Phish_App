package edu.uw.tcss450;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import model.Credentials;


public class RegisterFragment extends Fragment {

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle args) {
        Button registerButton = view.findViewById(R.id.register_register_button);
        registerButton.setOnClickListener(this::succesfulReg);
    }


    public void succesfulReg(View view) {
        //get email from textfield
        EditText email = getView().findViewById(R.id.register_email_text);
        String emailText = email.getText().toString();
        //get password from textfield
        EditText password = getView().findViewById(R.id.register_password1_text);
        String passwordText = password.getText().toString();
        //get password2 from textfield
        EditText password2 = getView().findViewById(R.id.register_password2_text);
        String passwordText2 = password.getText().toString();

        if(emailText.length() == 0 || passwordText.length() == 0 || passwordText2.length() == 0) {
            if (emailText.length() == 0) {
                email.setError("Required");
            }
            if (passwordText.length() == 0) {
                password.setError("Required");
            }
            if (passwordText2.length() == 0) {
                password2.setError("Required");
            }
            //Log.d("error", "empty strings");
            return;
        }

        if(!emailText.contains("@")) {
            email.setError("Not valid email");
            return;
        }

        if(passwordText.length() < 5 || passwordText2.length() < 5) {
            if (passwordText.length() < 5) {
                password.setError("Password must be longer than 5 characters");
            }
            if (passwordText2.length() < 5) {
                password2.setError("Password must be longer than 5 characters");
            }
            return;
        }

        if (view.findViewById(R.id.register_password1_text) != (view.findViewById(R.id.register_password2_text))) {
            Log.d("Password Fault", "Passwords do not match");
            return;
        }

        //build credentials
        Credentials.Builder userInfo = new Credentials.Builder(emailText, passwordText);
        userInfo.addFirstName("default");
        userInfo.addLastName("default");
        userInfo.addUsername("default");
        Credentials userLogin = userInfo.build();
        //end building credentials

        //bundle and set args to pass to fragment
        Bundle args = new Bundle();
        args.putSerializable("user", userLogin);

        NavController nc = Navigation.findNavController(getView());
        if (nc.getCurrentDestination().getId() != R.id.registerFragment) {
            nc.navigateUp();
        }
        nc.navigate(R.id.action_registerFragment_to_successFragment, args);
    }


}
