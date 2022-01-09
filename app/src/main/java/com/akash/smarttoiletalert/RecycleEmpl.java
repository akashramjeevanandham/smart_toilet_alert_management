
package com.akash.smarttoiletalert;

        import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class RecycleEmpl  extends RecyclerView.Adapter<RecycleEmpl.ViewHolder> {


    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    private Context context;
    public ArrayList<String> str;
    public List<Member> mem = new ArrayList<>();
    DatabaseReference databaseReference,databaseReference1,databaseReference2;
    FirebaseDatabase firebaseDatabase;
    String phoneNo;
    String message;
    String TNAME;
    String NM;
    String status;


    public RecycleEmpl(Context context) {
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.listview), parent, false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(mem.get(position).getName());
        holder.phone.setText(mem.get(position).getPhoneno());
        holder.time.setText(mem.get(position).getTime());


    }



    @Override
    public int getItemCount() {
        return mem.size();
    }

    public void setdet(List<Member> mem) {
        this.mem = mem;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name,phone_txt,phone,time,time_txt;
        private TextView status;
        private ImageView ok,no;
        private CardView card;



        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name=itemView.findViewById(R.id.emplname);

            phone_txt=itemView.findViewById(R.id.Methane_txt);
            phone=itemView.findViewById(R.id.phone_val);

            card=itemView.findViewById(R.id.cardview2);
            time=itemView.findViewById(R.id.time_val);
            time_txt=itemView.findViewById(R.id.time_txt);



        }
    }
}



