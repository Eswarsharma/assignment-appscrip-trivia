package trivia.assignment.appscrip.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import trivia.assignment.appscrip.Modal.Quiz_Modal;
import trivia.assignment.appscrip.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    Context context;
    ArrayList list;

    // constructor
    public ListAdapter(Context context, ArrayList list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_for_data, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Quiz_Modal quiz_modal = (Quiz_Modal) list.get(position);

        holder.date.setText("Game " + quiz_modal.getId() + ": " + quiz_modal.getDate() +" - "+ quiz_modal.getTime());
        holder.name.setText("Name : " + quiz_modal.getUserName());
        holder.cricketer.setText(quiz_modal.getBest_cricketer());
        holder.colors.setText(quiz_modal.getColors());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // clear the data
    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    // add data to list
    public void addItems(List<Quiz_Modal> postItems) {
        list.addAll(postItems);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView date, name, cricketer, colors;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            name = itemView.findViewById(R.id.user_name);
            cricketer = itemView.findViewById(R.id.best_Cricketer);
            colors = itemView.findViewById(R.id.colors);
        }
    }
}
