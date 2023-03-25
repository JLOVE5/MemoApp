package com.example.memoapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class memoAdapter extends RecyclerView.Adapter<memoAdapter.MyViewHolder> {
private Context context;
private ArrayList Memo_id, Memo_title, Memo_content, Memo_date, Memo_importance;




memoAdapter(Context context, ArrayList Memo_id, ArrayList Memo_title, ArrayList Memo_content, ArrayList Memo_date,
            ArrayList Memo_importance){
    this.context = context;
    this.Memo_id = Memo_id;
    this.Memo_title = Memo_title;
    this.Memo_content = Memo_content;
    this.Memo_date = Memo_date;
    this.Memo_importance = Memo_importance;
}


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


    holder.memoTitle.setText(String.valueOf(Memo_title.get(position)));
    holder.memoDate.setText(String.valueOf(Memo_date.get(position)));
    holder.memoImportance.setText(String.valueOf(Memo_importance.get(position)));
    holder.mainLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("id",String.valueOf(Memo_id.get(position)));
            intent.putExtra("title",String.valueOf(Memo_title.get(position)));
            intent.putExtra("date",String.valueOf(Memo_date.get(position)));
            intent.putExtra("importance",String.valueOf(Memo_importance.get(position)));
            intent.putExtra("content",String.valueOf(Memo_content.get(position)));
            context.startActivity(intent);

        }
    });


    }

    @Override
    public int getItemCount() {
        return Memo_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView memoTitle, memoDate, memoImportance;
    LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           memoTitle = itemView.findViewById(R.id.textmemoTitle);
           memoDate = itemView.findViewById(R.id.textmemoDate);
           memoImportance = itemView.findViewById(R.id.textMemoImportance);
           mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

}
