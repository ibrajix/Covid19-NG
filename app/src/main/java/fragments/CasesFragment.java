package fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.snappmi.covid19_ng.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import models.States;

public class CasesFragment extends Fragment {

   /* private RecyclerView recyclerView;
    private List<States> statesList;*/

   private RelativeLayout sort_section;

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

       sort_section.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openOptionsDialog();
           }
       });

       getData();

        return root;
    }

    public void openOptionsDialog(){
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

    public void getData(){
        String new_url = "https://covidnigeria.herokuapp.com/api";

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, new_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("states");

                    String cc = jsonObject.getString("cases");

                    Log.e("testurl", cc);

                   /* progressBar.setVisibility(View.GONE);
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
                    today_death_cases.setText(today_deaths_formatted);*/
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

}
