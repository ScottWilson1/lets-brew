package com.example.scott.beerdiary2;

/**
 * Created by Scott on 6/21/17.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.BeerViewHolder>{

    Context context;
    static RecyclerView recycler;

    public RecyclerAdapter(Context context, RecyclerView recycler) {
        this.context = context;
        beers = PersistenceUtil.loadBeers(context);
        this.recycler = recycler;
    }

    private static ArrayList<Beer> beers;

    public static class BeerViewHolder extends RecyclerView.ViewHolder {

        private TextView mItemName;
        private TextView mItemType;
        private TextView mItemAbv;
        private TextView mItemDate;
        private Beer mBeer;
        private ImageButton mDeleteButton;
        private RecyclerAdapter adapter;
        private Context mContext;

        private static final String BEER_KEY = "BEER";

        public BeerViewHolder(View itemView, RecyclerAdapter adapter, Context context) {
            super(itemView);

            mContext = context;
            mItemName = (TextView) itemView.findViewById(R.id.item_name);
            mItemType = (TextView) itemView.findViewById(R.id.item_type);
            //mItemAbv = (TextView) itemView.findViewById(R.id.item_abv);
            mItemDate = (TextView) itemView.findViewById(R.id.item_date);
            mDeleteButton = (ImageButton) itemView.findViewById(R.id.delete_button);
            this.adapter = adapter;
        }

        public void bindBeer(Beer beer, final int position) {
            mBeer = beer;
            mItemName.setText(beer.getName());
            String[] beerStyles = mContext.getResources().getStringArray(R.array.beer_styles);
            mItemType.setText(beerStyles[beer.getStyle()]);
            //mItemAbv.setText(beer.getAbv());
            mItemDate.setText(beer.getBrewDate());
            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PersistenceUtil.deleteBeer(itemView.getContext(), position);
                    beers = PersistenceUtil.loadBeers(itemView.getContext());
                    recycler.removeViewAt(position);
                    //adapter.notifyItemRemoved(position);
                    //adapter.notifyItemRangeChanged(position, beers.size());
                    adapter.notifyDataSetChanged();
                    recycler.invalidate();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = itemView.getContext();
                    Intent showBeerIntent = new Intent(context, FormActivity.class);
                    showBeerIntent.putExtra("POSITION", position);
                    context.startActivity(showBeerIntent);
                }
            });
        }

    }

    @Override
    public RecyclerAdapter.BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_row, parent, false);
        return new BeerViewHolder(inflatedView, this, parent.getContext());
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.BeerViewHolder holder, int position) {
        Beer itemBeer = beers.get(position);
        holder.bindBeer(itemBeer, position);
    }

    @Override
    public int getItemCount() {
        return beers.size();
    }
}
