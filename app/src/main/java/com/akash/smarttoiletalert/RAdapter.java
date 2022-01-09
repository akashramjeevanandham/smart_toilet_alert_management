package com.akash.smarttoiletalert;

import android.content.Context;
import android.os.Build;
import android.telephony.SmsManager;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class RAdapter extends RecyclerView.Adapter<RAdapter.ViewHolder> {


    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    private Context context;
    public ArrayList<String> str;
    public List<Toilet> toilet = new ArrayList<>();
    DatabaseReference databaseReference,databaseReference1,databaseReference2;
    FirebaseDatabase firebaseDatabase;
    String phoneNo;
    String message;
    String TNAME;
    String NM;
    String status;


    public RAdapter(Context context) {
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.recycle), parent, false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(toilet.get(position).getToiletnm());
        holder.methane_val.setText(toilet.get(position).getMethanevalue());
        TNAME = toilet.get(position).getToiletnm();
        holder.ir_val.setText(toilet.get(position).getIrvalue());

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference1 = firebaseDatabase.getInstance("https://smart-alert-toilet-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("ToiletManagementSystem");
        databaseReference2=databaseReference1.child("Toilet").child(TNAME);



        if (toilet.get(position).checkstatus()) {
            TransitionManager.beginDelayedTransition(holder.card);
            holder.no.setVisibility(View.GONE);
            holder.ok.setVisibility(View.VISIBLE);
            databaseReference2.child("status").setValue("0");
        }
        else {
            TransitionManager.beginDelayedTransition(holder.card);
            holder.ok.setVisibility(View.GONE);
            holder.no.setVisibility(View.VISIBLE);
           status= toilet.get(position).getStatus();

                   if(status.equals("0"))
                       {
                           firebaseDatabase = FirebaseDatabase.getInstance();
                           databaseReference = firebaseDatabase.getInstance("https://smart-alert-toilet-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("ToiletManagementSystem");
                           DatabaseReference databaseReference1 = databaseReference.child("EMPLYOEE");
                           databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                               @Override
                               public void onDataChange(@NonNull DataSnapshot snapshot) {

                                   //Member member=snapshot.getValue(Member.class);
    //                check=snapshot.getValue(Member.class);
    //                String value = snapshot.getValue(String.class);
                                   Calendar calendar = Calendar.getInstance();
                                   SimpleDateFormat sdf = new SimpleDateFormat("hh");
                                   String currentDateandTime = sdf.format(new Date());
    //                SimpleDateFormat mdformat = new SimpleDateFormat("HH");
    //                String strDate = mdformat.format(calendar.getTime());
                                   String st;
                                   List<Member> Userlist = new ArrayList<>();
                                   // Result will be holded Here
                                   for (DataSnapshot item : snapshot.getChildren()) {
                                       Member t = item.getValue(Member.class);
                                       int i = Integer.parseInt(t.getTime());
                                       int j = Integer.parseInt(currentDateandTime);
                                       if (i == j) {
                                           phoneNo = t.getPhoneno();
                                           NM=t.getName();

                                           message = "From manager\n\nHi!! "+NM+" Please clean the "+TNAME+" toilet as soon as possible.Thankyou";


                                           try {
                                               SmsManager smsManager = SmsManager.getDefault();
                                               smsManager.sendTextMessage(phoneNo, null, message, null, null);
                                               Toast.makeText(context, "Message Sent", Toast.LENGTH_LONG).show();
                                           } catch (Exception e) {
                                               Toast.makeText(context, "Some files are Empty", Toast.LENGTH_LONG).show();
                                           }





                                       }
                                   }
                               }



                               @Override
                               public void onCancelled(@NonNull DatabaseError error) {

                                   Toast.makeText(context, "Fail to get data.", Toast.LENGTH_SHORT).show();

                               }
                           });


                       databaseReference2.child("status").setValue("1");

                   }






            }



    }












    @Override
    public int getItemCount() {
        return toilet.size();
    }

    public void setToilet(List<Toilet> toilet) {
        this.toilet = toilet;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name,methane_txt,ir_txt,methane_val,ir_val;
        private TextView status;
        private ImageView ok,no;
        private CardView card;



        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name=itemView.findViewById(R.id.toiletname);
           ir_txt=itemView.findViewById(R.id.Ir_txt);
            ir_val=itemView.findViewById(R.id.Ir_val);
            methane_txt=itemView.findViewById(R.id.Methane_txt);
            methane_val=itemView.findViewById(R.id.Methane_val);
            status=itemView.findViewById(R.id.satus_txt);
            no=itemView.findViewById(R.id.status_im);
            ok=itemView.findViewById(R.id.status_im2);

            card=itemView.findViewById(R.id.cardview);







        }
    }
}



