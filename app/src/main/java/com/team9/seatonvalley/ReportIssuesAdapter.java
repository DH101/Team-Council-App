package com.team9.seatonvalley;

/**
 * @Author: Dean Hunter, Student Number: 16027456
 * @Since: 10/04/2018
 *
 * This class sets up each item for the report issue list. This includes adding a card view for each
 * item of the report issue list as well as an icon, title, description for each issue card view
 * as well as a button for each card view to take the user to the next activity.
 * Extends RecyclerView.Adapter<ReportIssueAdapter.ViewHolder> as a base class for the adapter.
 * This provides binding from the data set to views displayed on a recycler view.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class ReportIssuesAdapter extends RecyclerView.Adapter<ReportIssuesAdapter.ViewHolder>{

    // Private fields to ensure not accessed outside class

    // Tag to represent time of discovery
    private static final String TAG = "ReportIssueRecyclerView";

    // An empty list of ReportIssue type to contain issues
    private ArrayList<ReportIssue> reportIssues;

    // To store the activity context
    private Context context;

    /**
     * Class to describe the item view and metadata within the recycler view
     * Extends  RecyclerView.ViewHolder to do this
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{

        // Fields for each item view

        // Card view for the issue in the item view
        CardView cardView;

        // Image view for icon of issue in the item view
        ImageView reportIssueIcon;

        // Text view for issue title in the item view
        TextView reportIssueTitle;

        // Text view for issue description in the item view
        TextView reportIssueDescription;

        // Button for the card view in the item view
        Button reportIssueButton;

        /**
         * Constructor to create item view
         */
        private ViewHolder(View itemView) {

            super(itemView);

            // Assigns the card view widget with id of report_issue_card_view
            cardView = itemView.findViewById(R.id.report_issue_card_view);

            // Assigns the image view widget with id of report_issue_icon
            this.reportIssueIcon = itemView.findViewById(R.id.report_issue_icon);

            // Assigns the text view widget with id of report_issue_title
            this.reportIssueTitle = itemView.findViewById(R.id.report_issue_title);

            // Assigns the text view widget with id of report_issue_description
            this.reportIssueDescription = itemView.findViewById(R.id.
                    report_issue_description);

            // Assigns the button widget with id of report_issue_button
            this.reportIssueButton = itemView.findViewById(R.id.report_issue_button);

        }

    }

    /**
     * Constructor to create report issue adapter, including the list of issues
     * and activity context.
     */
    ReportIssuesAdapter(Context context, ArrayList<ReportIssue> reportIssues) {

        this.reportIssues = reportIssues;
        this.context = context;

    }

    /**
     * Creates new RecyclerView.ViewHolder to represent an item. Makes use of parent
     * where the new view is added and the view type of the new view.
     */
    @Override
    public ReportIssuesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // describes the UI layout for the item.
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.listitem_report_it, parent, false);
        // Instantiates item view in is place withing recycler view.
        return new ReportIssuesAdapter.ViewHolder(view);

    }


    /** To fill the created View Holder with the data to represent the report issue
     *  and its intent.
     */
    @Override
    public void onBindViewHolder(ReportIssuesAdapter.ViewHolder reportIssueViewHolder, int position){

        // Show in Logcat onBindViewHolder has been called.
        Log.d(TAG, "onBindViewHolder: called.");

        // Assign the position parameter to a final int variable.
        final int reportIssuePosition = position;

        // Set the icon in the View Holder to the drawable with the icon resource id at the position
        // the user chose.
        reportIssueViewHolder.reportIssueIcon.setImageResource(reportIssues.get(position).iconResourceId);

        // Set the title in the View Holder to the title at the position
        // the user chose.
        reportIssueViewHolder.reportIssueTitle.setText(reportIssues
                .get(position).title);
        // Set the description in the View Holder to the description at the position
        // the user chose.
        reportIssueViewHolder.reportIssueDescription.setText(reportIssues
                .get(position).description);

        // Set an onclick listener to the card view so when clicked, show in logcat the view
        // has been clicked, make a toast appear at the bottom of the application of the title of
        // the issue clicked and intent to the report issue form
        reportIssueViewHolder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context, ReportIssueFormActivity.class);
                String reportIssueTitle = reportIssues.get(reportIssuePosition).title;
                intent.putExtra("report_issue_title", reportIssueTitle);
                context.startActivity(intent);

            }
        });

        // Same as above but adds functionality to the button in the view.
        reportIssueViewHolder.reportIssueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: clicked on button");

                Intent intent = new Intent(context, ReportIssueFormActivity.class);
                String reportIssueTitle = reportIssues.get(reportIssuePosition).title;
                intent.putExtra("report_issue_title", reportIssueTitle);
                context.startActivity(intent);
            }
        });
    }


    /**
     * get the size of the issues list.
     */
    @Override
    public int getItemCount() {
        return reportIssues.size();
    }

}