package edu.uw.tcss450;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import model.Credentials;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnLoginFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class LoginFragment extends Fragment {

    private OnLoginFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button registerButton = view.findViewById(R.id.main_register_button);
        Button loginButton = view.findViewById(R.id.main_login_button);

        registerButton.setOnClickListener(this::regClicked);
        loginButton.setOnClickListener(this::loginClicked);

        return view;
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

        mListener.onLoginSuccess(userLogin, null);
    }

    private void regClicked(View view) {
        mListener.onRegisterClicked();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginFragmentInteractionListener) {
            mListener = (OnLoginFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnLoginFragmentInteractionListener {
        // TODO: Update argument type and name
        void onLoginFragmentInteraction(Uri uri);
        void onLoginSuccess(Credentials creds, String jwt);
        void onRegisterClicked();

    }
}
