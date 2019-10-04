package edu.uw.tcss450;

import android.content.Context;
import android.net.Uri;
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

public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle args) {
        Button registerButton = view.findViewById(R.id.main_register_button);
        Button loginButton = view.findViewById(R.id.main_login_button);

        registerButton.setOnClickListener(this::regClicked);
        loginButton.setOnClickListener(this::loginClicked);


        //ease of use fillers
        EditText email = getView().findViewById(R.id.main_email_text);
        EditText password = getView().findViewById(R.id.main_password_text);
        email.setText("SuperDuper@gmail.com");
        password.setText("123456");
    }


    private void loginClicked(View view) {
        //get email from textfield
        EditText email = getView().findViewById(R.id.main_email_text);
        String emailText = email.getText().toString();
        //get password from textfield
        EditText password = getView().findViewById(R.id.main_password_text);
        String passwordText = password.getText().toString();


        if(emailText.length() == 0 || passwordText.length() == 0) {
            if (emailText.length() == 0) {
                email.setError("Required");
            }
            if (passwordText.length() == 0) {
                password.setError("Required");
            }
            //Log.d("error", "empty strings");
            return;
        }

        if(!emailText.contains("@")) {
            email.setError("Not valid email");
            return;
        }

        //build credentials
        Credentials.Builder userInfo = new Credentials.Builder(emailText, passwordText);
        userInfo.addFirstName("default");
        userInfo.addLastName("default");
        userInfo.addUsername("default");
        Credentials userLogin = userInfo.build();
        //end building credentials

        Bundle args = new Bundle();
        args.putSerializable("user", userLogin);

        NavController nc = Navigation.findNavController(getView());
        if (nc.getCurrentDestination().getId() != R.id.loginFragment) {
            nc.navigateUp();
        }
        nc.navigate(R.id.action_loginFragment_to_successFragment, args);

    }

    private void regClicked(View view) {
        NavController nc = Navigation.findNavController(getView());
        if (nc.getCurrentDestination().getId() != R.id.loginFragment) {
            nc.navigateUp();
        }
        nc.navigate(R.id.action_loginFragment_to_registerFragment);
    }
}
