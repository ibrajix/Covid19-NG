package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.snappmi.covid19_ng.R;

import java.util.List;

import models.States;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List <States> statesList;

    public RecyclerViewAdapter(Context context, List<States> statesList) {
        this.context = context;
        this.statesList = statesList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.state_cases_content, parent, false);
        return new RecyclerViewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        /*Glide.with(context).load(statesList.get(position).getState_cases()).into(holder.imageView);*/
        holder.state_name.setText(statesList.get(position).getState_name());
    }

    @Override
    public int getItemCount() {
        return statesList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView state_name;
        TextView state_cases;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            state_name = itemView.findViewById(R.id.states_name);
            state_cases = itemView.findViewById(R.id.states_cases);

        }

    }
}
