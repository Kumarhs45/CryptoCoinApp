package com.kumar.synconextassignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kumar.synconextassignment.R;
import com.kumar.synconextassignment.activities.DetailsActivity;
import com.kumar.synconextassignment.pojo.CryptoData;
import com.kumar.synconextassignment.pojo.WatchList;
import com.kumar.synconextassignment.utils.Keys;
import com.kumar.synconextassignment.utils.WatchListDataBase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CryptoDataAdapter extends RecyclerView.Adapter<CryptoDataAdapter.ViewHolder> {

    private List<CryptoData> parseItems;
    private Context context;
    private WatchListDataBase mDatabaseHelper;

    public CryptoDataAdapter(List<CryptoData> parseItems, Context context) {
        this.parseItems = parseItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CryptoDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_single_data, parent, false);
        return new CryptoDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoDataAdapter.ViewHolder holder, int position) {
        CryptoData parseItem = parseItems.get(position);
        holder.titleTv.setText(parseItem.getName());
        holder.subTitleTv.setText(parseItem.getSymbol());
        holder.currentPriceTv.setText(context.getResources().getString(R.string.Rs)+" "+parseItem.getCurrent_price());
        holder.marketcapTv.setText(parseItem.getMarket_cap_change_percentage_24h());


        Picasso.with(context)
                .load((parseItem.getImage().isEmpty()) ? "Hello" : parseItem.getImage())
                .resize(300, 300)
                .into(holder.imageView);

        double marKetCap = Double.parseDouble(parseItem.getMarket_cap_change_percentage_24h());
        if (marKetCap>0.0){
            holder.marketcapTv.setTextColor(Color.GREEN);
        }else {
            holder.marketcapTv.setTextColor(Color.RED);
        }

        holder.linear_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showBottomSheetDialog(parseItem);


            }
        });
    }

    private void showBottomSheetDialog(CryptoData position) {
        mDatabaseHelper = new WatchListDataBase(context);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
        RelativeLayout viewDetailsLayout = bottomSheetDialog.findViewById(R.id.viewDetailsLayout);
        LinearLayout watchlistLayout = bottomSheetDialog.findViewById(R.id.watchlistLayout);
        ImageView coinImage = bottomSheetDialog.findViewById(R.id.coinImage);
        TextView coinTitle = bottomSheetDialog.findViewById(R.id.coinTitle);
        TextView coinPrice = bottomSheetDialog.findViewById(R.id.coinPrice);

        coinTitle.setText(position.getName());
        coinPrice.setText(context.getResources().getString(R.string.Rs)+" "+position.getCurrent_price());

        Picasso.with(context)
                .load((position.getImage().isEmpty()) ? "Hello" : position.getImage())
                .resize(300, 300)
                .into(coinImage);
        viewDetailsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                Intent mIntent = new Intent(context, DetailsActivity.class);
                mIntent.putExtra(Keys.ITEM_SELECTED_KEY, position);
                context.startActivity(mIntent);
            }
        });
        watchlistLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
//                if (mDatabaseHelper.checkTheDataInWatchList(position.getId())) {
//
//                } else {
                    WatchList watchList = new WatchList();
                    watchList.setData(position);
                    mDatabaseHelper.addAnItemToWatchList(watchList);
                    Toast.makeText(context, position.getName()+" Added to watchlist", Toast.LENGTH_SHORT).show();
//                }

            }
        });
        bottomSheetDialog.show();
    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }
    public void filteredList(ArrayList<CryptoData> filterList) {
        parseItems = filterList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linear_background;
        ImageView imageView;
        TextView titleTv, subTitleTv, currentPriceTv, marketcapTv;

        public ViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
            titleTv = view.findViewById(R.id.titleTv);
            subTitleTv = view.findViewById(R.id.subTitleTv);
            currentPriceTv = view.findViewById(R.id.currentPriceTv);
            marketcapTv = view.findViewById(R.id.marketcapTv);
            linear_background = view.findViewById(R.id.linear_background);

        }

    }

}
