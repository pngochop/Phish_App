package edu.uw.tcss450;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
 * {@link RegisterFragment.OnRegisterFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class RegisterFragment extends Fragment {

    private OnRegisterFragmentInteractionListener mListener;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        Button registerButton = view.findViewById(R.id.register_register_button);
        registerButton.setOnClickListener(this::succesfulReg);

        return view;
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

        mListener.onRegisterSuccess(userLogin);
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterFragmentInteractionListener) {
            mListener = (OnRegisterFragmentInteractionListener) context;
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
    public interface OnRegisterFragmentInteractionListener {
        // TODO: Update argument type and name
        void onRegisterFragmentInteraction(Uri uri);
        void onRegisterSuccess(Credentials creds);
    }
}
