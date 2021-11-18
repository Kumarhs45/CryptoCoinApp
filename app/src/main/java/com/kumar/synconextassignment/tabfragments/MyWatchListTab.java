package com.kumar.synconextassignment.tabfragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.kumar.synconextassignment.R;
import com.kumar.synconextassignment.pojo.CryptoData;
import com.kumar.synconextassignment.pojo.WatchList;
import com.kumar.synconextassignment.utils.WatchListDataBase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyWatchListTab extends Fragment implements
        AdapterView.OnItemSelectedListener{

LinearLayout ll_itemsInWatchList;
    private WatchListDataBase mDatabaseHelper;
    private List<WatchList> watchLists;
    Spinner currencySpin;
    String[] currency = { "INR", "USD", "EUR"};
    public MyWatchListTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_watchlist,container,false);
        ll_itemsInWatchList = view.findViewById(R.id.ll_itemsInWatchList);
         currencySpin = (Spinner) view.findViewById(R.id.spinner);
        currencySpin.setOnItemSelectedListener(this);
        mDatabaseHelper = new WatchListDataBase(getActivity());

        ArrayAdapter currencyAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,currency);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        currencySpin.setAdapter(currencyAdapter);




        getAllTheWatchListShowNumItems();
        return view;
    }
    private void getAllTheWatchListShowNumItems() {

        ll_itemsInWatchList.removeAllViews();
        watchLists = mDatabaseHelper.getAllTheWatchListData();
        List<CryptoData> dataList = new ArrayList<>();

        for (WatchList watchList : watchLists) {
//            cart.setTotalTax(gstA);
            dataList.add(watchList.getData());

            if (currencySpin.getSelectedItemPosition()==0){
                addList(watchList.getData());

            }else if (currencySpin.getSelectedItemPosition()==1){
                addListInUsd(watchList.getData());
            }else {
                addListInEuro(watchList.getData());
            }
        }






    }
    private void addList(final CryptoData watchList) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View view = layoutInflater.inflate(R.layout.single_watchlist_layout, null);

        ImageView imageView;
        TextView titleTv, subTitleTv, currentPriceTv, marketcapTv;

        imageView = view.findViewById(R.id.imageView);
        titleTv = view.findViewById(R.id.titleTv);
        subTitleTv = view.findViewById(R.id.subTitleTv);
        currentPriceTv = view.findViewById(R.id.currentPriceTv);
        marketcapTv = view.findViewById(R.id.marketcapTv);

        Picasso.with(getContext()).
                load(watchList.getImage()).into(imageView);
        titleTv.setText(watchList.getName());
        subTitleTv.setText(watchList.getSymbol());
        currentPriceTv.setText(getResources().getString(R.string.Rs)+" "+watchList.getCurrent_price());
        marketcapTv.setText(watchList.getPrice_change_24h());
        double marKetCap = Double.parseDouble(watchList.getPrice_change_24h());
        if (marKetCap>0.0){
            marketcapTv.setTextColor(Color.GREEN);
        }else {
            marketcapTv.setTextColor(Color.RED);
        }
        ll_itemsInWatchList.addView(view);

    }

    private void addListInUsd(final CryptoData watchList) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View view = layoutInflater.inflate(R.layout.single_watchlist_layout, null);

        ImageView imageView;
        TextView titleTv, subTitleTv, currentPriceTv, marketcapTv;

        imageView = view.findViewById(R.id.imageView);
        titleTv = view.findViewById(R.id.titleTv);
        subTitleTv = view.findViewById(R.id.subTitleTv);
        currentPriceTv = view.findViewById(R.id.currentPriceTv);
        marketcapTv = view.findViewById(R.id.marketcapTv);

        Picasso.with(getContext()).
                load(watchList.getImage()).into(imageView);
        titleTv.setText(watchList.getName());
        subTitleTv.setText(watchList.getSymbol());
//        currentPriceTv.setText(watchList.getCurrent_price());
        marketcapTv.setText(watchList.getPrice_change_24h());
        double currentRate = Double.parseDouble(String.valueOf(watchList.getCurrent_price()));
        currentPriceTv.setText(getResources().getString(R.string.Rs)+" "+String.valueOf((int)currentRate*74.24));
        double marKetCap = Double.parseDouble(watchList.getPrice_change_24h());
        if (marKetCap>0.0){
           marketcapTv.setTextColor(Color.GREEN);
        }else {
            marketcapTv.setTextColor(Color.RED);
        }

        ll_itemsInWatchList.addView(view);

    }
    private void addListInEuro(final CryptoData watchList) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View view = layoutInflater.inflate(R.layout.single_watchlist_layout, null);

        ImageView imageView;
        TextView titleTv, subTitleTv, currentPriceTv, marketcapTv;

        imageView = view.findViewById(R.id.imageView);
        titleTv = view.findViewById(R.id.titleTv);
        subTitleTv = view.findViewById(R.id.subTitleTv);
        currentPriceTv = view.findViewById(R.id.currentPriceTv);
        marketcapTv = view.findViewById(R.id.marketcapTv);

        Picasso.with(getContext()).
                load(watchList.getImage()).into(imageView);
        titleTv.setText(watchList.getName());
        subTitleTv.setText(watchList.getSymbol());
//        currentPriceTv.setText(watchList.getCurrent_price());
        marketcapTv.setText(watchList.getPrice_change_24h());
        double currentRate = Double.parseDouble(String.valueOf(watchList.getCurrent_price()));
        currentPriceTv.setText(getResources().getString(R.string.Rs)+" "+String.valueOf((int)currentRate*84.14));
        double marKetCap = Double.parseDouble(watchList.getPrice_change_24h());
        if (marKetCap>0.0){
            marketcapTv.setTextColor(Color.GREEN);
        }else {
            marketcapTv.setTextColor(Color.RED);
        }
        ll_itemsInWatchList.addView(view);

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//        Toast.makeText(getContext(),currency[position] , Toast.LENGTH_LONG).show();
        ((TextView) arg0.getChildAt(0)).setTextColor(getResources().getColor(R.color.white));
        getAllTheWatchListShowNumItems();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {

            // Refresh tab data:
            getAllTheWatchListShowNumItems();
            if (getFragmentManager() != null) {

                getFragmentManager()
                        .beginTransaction()
                        .detach(this)
                        .attach(this)
                        .commit();
            }
        }
    }
}