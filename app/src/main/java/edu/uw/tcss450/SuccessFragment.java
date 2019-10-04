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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SuccessFragment.OnSuccessFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SuccessFragment extends Fragment {

    private OnSuccessFragmentInteractionListener mListener;

    public SuccessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_success, container, false);

        if (getArguments() != null) {
             Serializable userObject = getArguments().getSerializable("user");

             Credentials user = (Credentials) userObject;
             String userEmail = user.getEmail();

             TextView emailText = view.findViewById(R.id.success_email_text);
             emailText.setText(userEmail);
        }

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSuccessFragmentInteractionListener) {
            mListener = (OnSuccessFragmentInteractionListener) context;
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
    public interface OnSuccessFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSuccessFragmentInteraction(Uri uri);
    }
}
