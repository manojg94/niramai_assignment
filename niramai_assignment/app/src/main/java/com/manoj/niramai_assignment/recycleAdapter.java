package com.manoj.niramai_assignment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manoj.niramai_assignment.pojo.DataModel;
import com.manoj.niramai_assignment.pojo.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.MyViewHolder>
        implements Filterable {

    private ContactsAdapterListener listener;
    private List<DataModel> datalist;
    private OnItemClickListener clickListener;

    private List<DataModel> contactListFiltered;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //initialize the layout and return the view with layout
        final View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_list_row, viewGroup, false);
        return new MyViewHolder(itemView, clickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        //main logic or business logic
        final DataModel dataModel = contactListFiltered.get(position);
        myViewHolder.title.setText("Project Name : " + dataModel.getProjectname());
        myViewHolder.genre.setText("Company Name : " + dataModel.getCompanyname());
        myViewHolder.desc.setText("Short desc : " + dataModel.getShortdesc());
        myViewHolder.year.setText(dataModel.getCreationdate());
    }

    @Override
    public int getItemCount() {
        //count of the items of the recycler view
        return contactListFiltered.size();
    }


    //viewholderclass to initialize ui components with the help of view or on clicck listener
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, year, genre, desc;
        private DataModel movie;
        private OnItemClickListener clickListener;

        //add iterface to this constructor
        public MyViewHolder(View view, OnItemClickListener clickListener) {
            super(view);
            this.clickListener = clickListener;
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
            desc = (TextView) view.findViewById(R.id.desc);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onContactSelected(contactListFiltered.get(getAdapterPosition()));
        }
    }
    //first initialize movelist as list then alt+insert to create a constructor where the data is
    //passed from activity to this adapter


    public recycleAdapter(List<DataModel> datalist, ContactsAdapterListener clickListener) {
        this.datalist = datalist;
        this.listener = clickListener;
        contactListFiltered = datalist;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = datalist;
                } else {
                    List<DataModel> filteredList = new ArrayList<>();
                    for (DataModel row : datalist) {

                        // name match condition. this might differ depending on your requirement
                        if (row.getProjectname().toLowerCase().contains(charString.toLowerCase()) || row.getCompanyname().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<DataModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    public interface ContactsAdapterListener {
        void onContactSelected(DataModel contact);
    }
}
