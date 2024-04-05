package com.example.eventregistration;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

//import com.example.eventregistration.OnSpinerItemClick;
//import com.example.eventregistration.SpinnerDialog;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class CaptureFragment extends Fragment {
    View view;
    private EditText nameT,emailT,companyT,pnumberT,designationT;

    private Button buttonSubmit;
    private Button addSubmit;
    private Button confirmSubmit;
    private ImageView search_img;
    ArrayList<String> items=new ArrayList<>();
//    private SpinnerDialog spinnerDialog;
    //private SpinnerDialog spinnerDialog;
    private JSONArray item_visitors;
    private ArrayList<String>full_names;
    private ArrayList<String>company_name;
    private ArrayList<String>email;
    private ArrayList<String>pnumber;
    private ArrayList<String>designation;
    private Timer timer;

    private ListView listview;

    private ArrayAdapter<String> adapter;

    private List<String> allItems;
    private List<String> displayedItems;
    DatabaseReference db, nameref, Cref, PNref, DesRef, Eref;
    SendSmsManager smsManager;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        view = inflater.inflate(R.layout.capturefragment, container, false);
        


        nameT = view.findViewById(R.id.nameT);
        emailT = view.findViewById(R.id.emailT);
        companyT = view.findViewById(R.id.companyT);
        pnumberT = view.findViewById(R.id.pnumberT);
        designationT = view.findViewById(R.id.designationT);
        addSubmit = view.findViewById(R.id.buttonNew);
        confirmSubmit = view.findViewById(R.id.buttonConfirm);

        addSubmit.setOnClickListener(v -> post());
        confirmSubmit.setOnClickListener(v -> post());

        Log.d("CaptureFragment", "View initialized successfully");
        db= FirebaseDatabase.getInstance().getReference();
        nameref=db.child("Name");
        Cref=db.child("Company");
        Eref=db.child("Email");
        PNref=db.child("PhoneNumber");
        DesRef=db.child("Designation");

        return view;
    }

    //
    private void post()
    {
        // Log.d("debugging", "post: ");
        if(nameT.getText().toString().equals(""))
        {
            nameT.setError("This field is required");
            nameT.requestFocus();
        }
        else if(companyT.getText().toString().equals(""))
        {
            companyT.setError("This field is required");
            companyT.requestFocus();
        }
        else if(emailT.getText().toString().equals(""))
        {
            emailT.setError("This field is required");
            emailT.requestFocus();
        }
        else if(pnumberT.getText().toString().equals(""))
        {
            pnumberT.setError("This field is required");
            pnumberT.requestFocus();
        }
        else if(designationT.getText().toString().equals(""))
        {
            designationT.setError("This field is required");
            designationT.requestFocus();
        }
        else
        {
            final String name = nameT.getText().toString().trim().toUpperCase();
            final String company = companyT.getText().toString().trim().toUpperCase();
            final String email = emailT.getText().toString().trim();
            final String pnumber = pnumberT.getText().toString().trim();
            final String designation = designationT.getText().toString().trim();

            sendToDB(name,company,email,pnumber,designation);
            Toast.makeText(getContext(), "InsertedSuccessfully", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "post:"+name +company +email +pnumber+designation);

        }
    }

    private void sendToDB(String name, String company, String email, String pnumber, String designation) {

        nameref.setValue(name);
        Cref.setValue(company);
        Eref.setValue(email);
        PNref.setValue(pnumber);
        DesRef.setValue(designation);


    }

}


