package com.team9.seatonvalley;

/**
 * @Author: Andreea Stirbu, Student Number: 160483710
 * @Since: 25/03/2018
 *
 *  Class that connects and populates the Issue's CardView template
 */

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;
import static android.util.TypedValue.TYPE_STRING;

public class FuturesAdapter extends RecyclerView.Adapter<FuturesAdapter.MyHolder> {

    //Fields
    private List<Future> issuesList;
    private Context context;

    //Constructor
    public FuturesAdapter(List<Future> issuesList, Context context) {
        this.issuesList = issuesList;
        this.context = context;
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_future, parent,false);
        MyHolder myHolder = new MyHolder(view);

        view.setLayoutParams(new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
        ));

        return myHolder;
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        //Get the current object from the issuesList
        final Future currentIssue = issuesList.get(position);

        //set the Views content
        holder.future_title.setText(currentIssue.getTitle());
        holder.future_description.setText(currentIssue.getDescription());
        holder.setImage(context, currentIssue.getImage());

        //OnClickListener added to the displayButton that sends a String(representing the PDF resource) to the FututrePDFViewer activity
        holder.displayPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FuturePDFViewer.class);
                intent.putExtra("PDF Link", currentIssue.getPDF());
                context.startActivity(intent);
            }
        });
        holder.future_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FuturePDFViewer.class);
                intent.putExtra("PDF Link", currentIssue.getPDF());
                context.startActivity(intent);
            }
        });
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(issuesList.size()==0){

                arr = 0;

            }
            else{

                arr = issuesList.size();
            }

        }catch (Exception e){



        }
        return arr;
    }

    /**
     * Class that identify the required Views that needs to be populated
     */
    class MyHolder extends RecyclerView.ViewHolder{

        // Provide a reference to the views for each data item
        private CardView future_card_view;
        private TextView future_title, future_description;
        private ImageView future_image;
        private Button displayPDF;

        public MyHolder(View itemView) {
            super(itemView);

            //references to the XML views
            future_card_view = itemView.findViewById(R.id.futures_card_view);
            future_title = itemView.findViewById(R.id.title);
            future_description = itemView.findViewById(R.id.description);
            future_image = itemView.findViewById(R.id.image);
            displayPDF = itemView.findViewById(R.id.pdfDisplay);

            //Allows the "description" text to auto-size
            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(future_description,5, 10, 2, TYPE_STRING);
        }

        //Set an ImageView
        public void setImage(Context context, String image){
            future_image = itemView.findViewById(R.id.image);
            Picasso.with(context).load(image).into(future_image);
        }
    }
}

