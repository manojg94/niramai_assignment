package com.manoj.niramai_assignment.pojo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationListener extends RecyclerView.OnScrollListener {

    public static final int PAGE_START = 1;

    @NonNull
    private LinearLayoutManager layoutManager;

    /**
     * Set scrolling threshold here (for now i'm assuming 10 item in one page)
     */
    private static int PAGE_SIZE = 10;

    public PaginationListener(@NonNull LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        Log.d("pafination","isLoading"+isLoading());
        Log.d("pafination","isLastPage"+isLastPage());
        if (!isLoading() && !isLastPage()) {
      //  if ( !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= PAGE_SIZE) {
                Log.d("pafination","totalItemCount"+totalItemCount);
                Log.d("pafination","PAGE_SIZE"+PAGE_SIZE);
                //PAGE_SIZE=PAGE_SIZE+totalItemCount;
                loadMoreItems();
            }
        }
    }

    protected abstract void loadMoreItems();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}
