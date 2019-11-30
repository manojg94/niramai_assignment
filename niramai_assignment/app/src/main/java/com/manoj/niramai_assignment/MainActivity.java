package com.manoj.niramai_assignment;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manoj.niramai_assignment.pojo.DataModel;
import com.manoj.niramai_assignment.pojo.OnItemClickListener;
import com.manoj.niramai_assignment.pojo.PaginationListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.manoj.niramai_assignment.pojo.PaginationListener.PAGE_START;


public class MainActivity extends AppCompatActivity implements  SearchView.OnQueryTextListener, SearchView.OnCloseListener, MenuItem.OnActionExpandListener, recycleAdapter.ContactsAdapterListener {

    private RecyclerView recyclerView;
    private recycleAdapter adapter;
    private List<DataModel> datalist = new ArrayList<>();
    ProgressDialog  progressbar;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;
    public SearchView  searchView;
    List<DataModel> filteroutput = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
          progressbar = ProgressDialog.show(MainActivity.this, "",
                "Loading  Please wait...", true);



        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new recycleAdapter(datalist, this);

        recyclerView.setAdapter(adapter);

        prepareData(currentPage);

        recyclerView.addOnScrollListener(new PaginationListener((LinearLayoutManager) mLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;

                progressbar.show();
                prepareData(currentPage);
                Toast.makeText(MainActivity.this, "Loading page "+currentPage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.search);
          searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setIconifiedByDefault(true);

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchMenuItem.setOnActionExpandListener(this);



        return true;
    }

    private void prepareData(int currentPage) {

        List<DataModel> movies = DataModel.createMovies(adapter.getItemCount());
        //In this you can check filter is working or not its jst sample
        movies.add(new DataModel("testproject","shortdesc","longdesc",
                "testcompany","date"));

        datalist.addAll(movies);
        isLoading=false;
        progressbar.dismiss();
        adapter.notifyDataSetChanged();
    }




    @Override
    public boolean onQueryTextSubmit(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
//        filteroutput.clear();
//        createMovies1(s);
        adapter.getFilter().filter(s);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        adapter.getFilter().filter(s);

        return false;
    }
    @Override
    public boolean onClose() {
        Log.d("menu","close");
        finish();
        startActivity(getIntent());
        return false;
    }
    @Override
    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        Log.d("menu","close");
        finish();
        startActivity(getIntent());
        return true;
    }


    @Override
    public void onContactSelected(DataModel contact) {
      //  Toast.makeText(getApplicationContext(), "Selected: " + contact.getCompanyname() + ", " + contact.getProjectname(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);



            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST", (Serializable) contact);
            intent.putExtra("BUNDLE", args);

            startActivity(intent);

    }
}

