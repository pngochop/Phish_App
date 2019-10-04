package edu.uw.tcss450;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import model.Credentials;

public class MainActivity extends AppCompatActivity implements
        LoginFragment.OnLoginFragmentInteractionListener,
        RegisterFragment.OnRegisterFragmentInteractionListener,
        SuccessFragment.OnSuccessFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //construct the register fragment
        LoginFragment loginFragment = new LoginFragment();

        //change fragments
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_main_container, loginFragment)
                .addToBackStack(null);
        // Commit the transaction
        transaction.commit();

    }


    @Override
    public void onLoginFragmentInteraction(Uri uri) {
      Log.d("LOG:", "logFrag");
    }

    @Override
    public void onLoginSuccess(Credentials creds, String jwt) {


        //construct the successful login page fragment
        SuccessFragment successFragment = new SuccessFragment();

        //bundle and set args to pass to fragment
        Bundle args = new Bundle();
        args.putSerializable("user", creds);
        successFragment.setArguments(args);

        //change fragments
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_main_container, successFragment)
                .addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onRegisterClicked() {
        //construct the register fragment
        RegisterFragment registerFragment = new RegisterFragment();

        //change fragments
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_main_container, registerFragment)
                .addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }





    @Override
    public void onRegisterFragmentInteraction(Uri uri) {
        Log.d("REG:", "RegFrag");
    }

    @Override
    public void onRegisterSuccess(Credentials creds) {
        //construct the successful login page fragment
        SuccessFragment successFragment = new SuccessFragment();

        //bundle and set args to pass to fragment
        Bundle args = new Bundle();
        args.putSerializable("user", creds);
        successFragment.setArguments(args);

        //change fragments
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_main_container, successFragment)
                .addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }




    @Override
    public void onSuccessFragmentInteraction(Uri uri) {
        Log.d("SUC:", "SuccessFrag");
    }
}
