package fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.snappmi.covid19_ng.R;

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
       /* recyclerView = root.findViewById(R.id.recyclerView);
        statesList = new ArrayList<>();*/


       sort_section = root.findViewById(R.id.sortSection);

       sort_section.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openOptionsDialog();
           }
       });

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
}
