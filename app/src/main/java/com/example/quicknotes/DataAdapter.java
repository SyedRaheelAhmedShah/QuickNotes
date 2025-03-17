package com.example.quicknotes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.Viewholder> {

        Context context;
        ArrayList<DataModel> arrayList = new ArrayList<DataModel>();
    public DataAdapter(Context context, ArrayList<DataModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ui_display_design, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

    holder.titledesign.setText(arrayList.get(position).getTitle());
    holder.Descriptiondesign.setText(arrayList.get(position).getDescription());

    holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
            new AlertDialog.Builder(context).
                    setIcon(R.drawable.alert).
                    setTitle("Delete Entry").
                    setMessage("Are you sure ?").
                    setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DBconnection dBconnection=new DBconnection(context);

                            String id =String.valueOf(arrayList.get(position).getId());
                            dBconnection.deletedata(id);
                            dialog.dismiss();
                            context.startActivity(new Intent(context,MainActivity.class));
                        }
                    }).
                    setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        }
                    }).show();
            return true;
        }
    });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Note: method signature returns void
                Intent intent2 = new Intent(context, update.class);
                intent2.putExtra("title", arrayList.get(position).getTitle());
                intent2.putExtra("Description", arrayList.get(position).getDescription());
                intent2.putExtra("id", arrayList.get(position).getId());
                context.startActivity(intent2);
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class Viewholder extends RecyclerView.ViewHolder{
        TextView titledesign,Descriptiondesign;
        CardView cardView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            titledesign=itemView.findViewById(R.id.titledesign);
            Descriptiondesign=itemView.findViewById(R.id.Descriptiondesign);
            cardView=itemView.findViewById(R.id.cardview);
        }
    }
}
