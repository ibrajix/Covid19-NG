package fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.snappmi.covid19_ng.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import adapters.RecyclerViewAdapter;
import models.States;

public class CasesFragment extends Fragment {

   /* private RecyclerView recyclerView;
    private List<States> statesList;*/

   private RelativeLayout sort_section;
   private LinearLayout state_cases_section;
   private TextView state_name;
   private TextView cases_in_state;
   private RecyclerView recyclerView;
   private List<States> statesList;
   private FirebaseFirestore db;
   private ImageView naija_flag;

   private String new_url;
   private String ng_url;

    private TextView confirmed_cases, recovered_cases, death_cases, today_confirmed_cases, today_critical_cases, today_death_cases;
    private ProgressBar loading;
    private ProgressBar loading2;
    private SwipeRefreshLayout swipeRefreshLayout;

    public CasesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_cases, container, false);

       sort_section = root.findViewById(R.id.sortSection);
       statesList = new ArrayList<>();
       state_name = root.findViewById(R.id.stateName);
       cases_in_state = root.findViewById(R.id.caseInState);
       recyclerView = root.findViewById(R.id.statesCasesRecyclerView);
       db = FirebaseFirestore.getInstance();
       naija_flag = root.findViewById(R.id.naijaFlag);
       confirmed_cases = root.findViewById(R.id.confirmedCasesNumber);
       recovered_cases = root.findViewById(R.id.recoveredCasesNumber);
       death_cases = root.findViewById(R.id.deathCasesNumber);
       today_confirmed_cases = root.findViewById(R.id.todayConfirmedNumber);
       today_critical_cases = root.findViewById(R.id.todayCriticalNumber);
       today_death_cases = root.findViewById(R.id.todayDeathNumber);
       loading = root.findViewById(R.id.progressBar);
       loading2 = root.findViewById(R.id.progressBar2);
       swipeRefreshLayout = root.findViewById(R.id.swipeRefresh);


       RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), statesList);

       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

       recyclerView.setAdapter(adapter);

       sort_section.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openOptionsDialog();
           }
       });

        if(!checkNetwork(getContext())){
            Toast toast = Toast.makeText(getContext(), "Connect to the internet then swipe up to display LIVE data", Toast.LENGTH_LONG);
            toast.show();
            toast.setGravity(Gravity.TOP, 0, 140);
            confirmed_cases.setText("N/A");
            recovered_cases.setText("N/A");
            death_cases.setText("N/A");
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //do something here
                if(checkNetwork(getContext())){
                    getCasesStates();
                    getNaijaData();
                    swipeRefreshLayout.setRefreshing(false);
                    statesList.clear();
                }
                else{
                    Toast toast = Toast.makeText(getContext(), "Connect to the internet then swipe up to display LIVE data", Toast.LENGTH_LONG);
                    toast.show();
                    toast.setGravity(Gravity.TOP, 0, 140);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        Glide.with(getContext()).load("https://disease.sh/assets/img/flags/ng.png").override(150, 150).into(naija_flag);

        loading.setVisibility(View.VISIBLE);
        loading2.setVisibility(View.VISIBLE);


        getCasesStates();

        getNaijaData();

        return root;
    }

    private void openOptionsDialog(){
        final String[] options = {"States (A-Z)", "Most cases"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Sort By");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if ("States (A-Z)".equals(options[which])) {
                    Toast.makeText(getContext(), "I will sort the states alphabetically", Toast.LENGTH_SHORT).show();

                }
                if("Most cases".equals(options[which])) {
                    Toast.makeText(getContext(), "I will sort based on number of cases, highest - lowest", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
    }


    private void getNgData(){

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ng_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String cc = jsonObject.getString("cases");
                    String rc = jsonObject.getString("recovered");
                    String dc = jsonObject.getString("deaths");
                    String tc = jsonObject.getString("todayCases");
                    String tcc = jsonObject.getString("critical");
                    String td = jsonObject.getString("todayDeaths");

                    int pcc = Integer.parseInt(cc);
                    int prc = Integer.parseInt(rc);
                    int pdc = Integer.parseInt(dc);
                    int ptc = Integer.parseInt(tc);
                    int ptcc = Integer.parseInt(tcc);
                    int ptd = Integer.parseInt(td);

                    String confirmed_cases_formatted = String.format(Locale.US,"%,d", pcc);
                    String recovered_cases_formatted = String.format(Locale.US,"%,d", prc);
                    String death_cases_formatted = String.format(Locale.US,"%,d", pdc);
                    String today_cases_formatted = String.format(Locale.US,"%,d", ptc);
                    String today_cases_critical_formatted = String.format(Locale.US,"%,d", ptcc);
                    String today_deaths_formatted = String.format(Locale.US,"%,d", ptd);

                    confirmed_cases.setText(confirmed_cases_formatted);
                    recovered_cases.setText(recovered_cases_formatted);
                    death_cases.setText(death_cases_formatted);
                    today_confirmed_cases.setText(today_cases_formatted);
                    today_critical_cases.setText(today_cases_critical_formatted);
                    today_death_cases.setText(today_deaths_formatted);

                    loading2.setVisibility(View.GONE);

                } catch (JSONException e) {
                    Log.e("INFOOO", response);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error response", error.toString());
            }
        });

        requestQueue.add(stringRequest);
    }

    private void getData(){

        /*String new_url = "https://covidnigeria.herokuapp.com/api";*/

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, new_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    loading.setVisibility(View.GONE);
                    JSONObject jsonObject = new JSONObject(response);

                    String data  = jsonObject.getString("data");

                    JSONObject jsonObject2 = new JSONObject(data);

                    JSONArray jsonArray = jsonObject2.getJSONArray("states");

                    int array_size = jsonArray.length();

                    int i;

                    for (i=0; i < array_size; i++){

                        States states_cases = new States();
                        JSONObject jsonObject3 = jsonArray.getJSONObject(i);
                        String states = jsonObject3.getString("state");
                        String confirmedCases = jsonObject3.getString("confirmedCases");

                        int pcc = Integer.parseInt(confirmedCases);
                        String confirmed_cases_formatted = String.format(Locale.US,"%,d", pcc);

                        states_cases.setState_name(states);
                        states_cases.setState_cases(confirmed_cases_formatted);

                        statesList.add(states_cases);

                        Log.e("show states", states);
                    }

                } catch (JSONException e) {
                    Log.e("INFOOO", e.toString());
                    e.printStackTrace();
                }
                setRecyclerView(statesList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error response", error.toString());
            }
        });

        requestQueue.add(stringRequest);
    }

    private void setRecyclerView(List<States> statesList){
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getContext(), statesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myAdapter);
    }


    private void getCasesStates(){
        DocumentReference docRef = db.collection("endpoint").document("ng_url");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        new_url = document.getString("states");
                        Log.e("New Url", new_url);
                        getData();
                    } else {
                        Log.d("Tag", "No such document");
                        Log.e("Tag", "failed");
                    }

                } else {
                    Log.d("Tag", "get failed with ", task.getException());

                }
            }

        });
    }

    private void getNaijaData(){
        DocumentReference docRef2 = db.collection("endpoint").document("country_url");
        docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        ng_url = document.getString("ng");
                        Log.e("New Url", ng_url);
                        getNgData();
                    } else {
                        Log.d("Tag", "No such document");
                        Log.e("Tag", "failed");
                    }

                } else {
                    Log.d("Tag", "get failed with ", task.getException());

                }
            }

        });
    }

    public boolean checkNetwork(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean netInfo = cm.isActiveNetworkMetered();

        if (netInfo) {
            return true;
        }
        return false;
    }

}
