package com.example.eventregistration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationFragment extends Fragment {
    View view;
    private RecyclerView recyclerView;
    //private RecordsListAdapter myJobsAdapter;
    private List<RecordsList> listItems;
    private ProgressBar progressbar;
    private TextView emailTV;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.registrationfragment, container, false);
        listItems = new ArrayList<>();
        progressbar = view.findViewById(R.id.progressbar);
        emailTV = view.findViewById(R.id.emailTV);
        //emailTV.setText("("+SharedPrefManager.getInstance(getContext()).getKeyUserEmail()+")");
        recyclerView = view.findViewById(R.id.recyclerViewPosts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        loadRecyclerView();
        return view;

    }
    private void loadRecyclerView()
    {
        progressbar.setVisibility(View.VISIBLE);
    }



}
