package com.kumar.synconextassignment.tabfragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kumar.synconextassignment.R;
import com.kumar.synconextassignment.activities.MainActivity;
import com.kumar.synconextassignment.adapter.CryptoDataAdapter;
import com.kumar.synconextassignment.network.Api;
import com.kumar.synconextassignment.pojo.CryptoData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllCoinsTab extends Fragment {

    private RelativeLayout progressBar;
    private RecyclerView dataRecyclerView;
    private NestedScrollView nestedSV;
    int page = 1; //total pages=5
    int limitPerPage = 20; //data per page
    List<CryptoData> cryptoDataList;
    CryptoDataAdapter adapter;

    public AllCoinsTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coins_list,container,false);

        progressBar = (RelativeLayout) view.findViewById(R.id.progressBar);
        dataRecyclerView = (RecyclerView) view.findViewById(R.id.dataRecyclerView);
        nestedSV = view.findViewById(R.id.idNestedSV);
        cryptoDataList = new ArrayList<>();

        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // on scroll change we are checking when users scroll as bottom.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.
                    page++;
                    progressBar.setVisibility(View.VISIBLE);
                    getDataFromServer(limitPerPage,page );
//                    getDataFromAPI(page, limit);
                }
            }
        });
        getDataFromServer(limitPerPage,page );
return view;
    }
    private void getDataFromServer(int perPage, int pageNo) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.getData(perPage, pageNo),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) { // got profiles from server
                        Log.d("response", "--" + response);
                        progressBar.setVisibility(View.GONE);

                        parseTheData(response);  // parsing response to list using array list
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Please Check Your connectivity", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(getContext()).add(stringRequest);
    }
    private void parseTheData(String response) {

        try {
            //converting the string to json array object
            JSONArray array = new JSONArray(response);
            Log.d("array", "--" + array.length());
            Log.d("response", "--" + response.toString());
            for (int i = 0; i < array.length(); i++) {

                JSONObject jsonObject = array.getJSONObject(i);// parsing json array to each element json object
                CryptoData coinData = new CryptoData();
                // parsing all ids from json object to string//
                coinData.setId(jsonObject.getString("id"));
                coinData.setSymbol(jsonObject.getString("symbol"));
                coinData.setName(jsonObject.getString("name"));
                coinData.setImage(jsonObject.getString("image"));
                coinData.setCurrent_price(jsonObject.getString("current_price"));
                coinData.setMarket_cap(jsonObject.getString("market_cap"));
                coinData.setMarket_cap_rank(jsonObject.getString("market_cap_rank"));
                coinData.setFully_diluted_valuation(jsonObject.getString("fully_diluted_valuation"));
                coinData.setTotal_volume(jsonObject.getString("total_volume"));
                coinData.setHigh_24h(jsonObject.getString("high_24h"));
                coinData.setLow_24h(jsonObject.getString("low_24h"));
                coinData.setPrice_change_24h(jsonObject.getString("price_change_24h"));
                coinData.setPrice_change_percentage_24h(jsonObject.getString("price_change_percentage_24h"));
                coinData.setMarket_cap_change_24h(jsonObject.getString("market_cap_change_24h"));
                coinData.setMarket_cap_change_percentage_24h(jsonObject.getString("market_cap_change_percentage_24h"));
                coinData.setCirculating_supply(jsonObject.getString("circulating_supply"));
                coinData.setTotal_supply(jsonObject.getString("total_supply"));
                coinData.setMax_supply(jsonObject.getString("max_supply"));
                coinData.setAth(jsonObject.getString("ath"));
                coinData.setAth_change_percentage(jsonObject.getString("ath_change_percentage"));
                coinData.setAth_date(jsonObject.getString("ath_date"));
                coinData.setAtl(jsonObject.getString("atl"));
                coinData.setAtl_change_percentage(jsonObject.getString("atl_change_percentage"));
                coinData.setAtl_date(jsonObject.getString("atl_date"));
                coinData.setRoi(jsonObject.getString("roi"));
                coinData.setLast_updated(jsonObject.getString("last_updated"));

//                String id = jsonObject.getString("id");
//                String symbol = jsonObject.getString("symbol");
//                String name = jsonObject.getString("name");
//                String image = jsonObject.getString("image");
//                String current_price = jsonObject.getString("current_price");
//                String market_cap = jsonObject.getString("market_cap");
//                String market_cap_rank = jsonObject.getString("market_cap_rank");
//                String fully_diluted_valuation = jsonObject.getString("fully_diluted_valuation");
//                String total_volume = jsonObject.getString("total_volume");
//                String high_24h = jsonObject.getString("high_24h");
//                String low_24h = jsonObject.getString("low_24h");
//                String price_change_24h = jsonObject.getString("price_change_24h");
//                String price_change_percentage_24h = jsonObject.getString("price_change_percentage_24h");
//                String market_cap_change_24h = jsonObject.getString("market_cap_change_24h");
//                String market_cap_change_percentage_24h = jsonObject.getString("market_cap_change_percentage_24h");
//                String circulating_supply = jsonObject.getString("circulating_supply");
//                String total_supply = jsonObject.getString("total_supply");
//                String max_supply = jsonObject.getString("max_supply");
//                String ath = jsonObject.getString("ath");
//                String ath_change_percentage = jsonObject.getString("ath_change_percentage");
//                String ath_date = jsonObject.getString("ath_date");
//                String atl = jsonObject.getString("atl");
//                String atl_change_percentage = jsonObject.getString("atl_change_percentage");
//                String atl_date = jsonObject.getString("atl_date");
//                String roi = jsonObject.getString("roi");
//                String last_updated = jsonObject.getString("last_updated");

//                cryptoDataList.add(new CryptoData(id, symbol, name, image, current_price, market_cap, market_cap_rank,
//                        fully_diluted_valuation, total_volume, high_24h, low_24h, price_change_24h,
//                        price_change_percentage_24h, market_cap_change_24h,market_cap_change_percentage_24h,
//                        circulating_supply,total_supply,max_supply,ath,ath_change_percentage,ath_date,atl,
//                        atl_change_percentage,atl_date,roi,last_updated)); // calling matches pojo class constructor and storing object instances in list
                cryptoDataList.add(coinData);
                adapter = new CryptoDataAdapter(cryptoDataList, getContext());  // passing matches adapter constructor
            }
            dataRecyclerView.setHasFixedSize(true);
            dataRecyclerView.setNestedScrollingEnabled(false);
            dataRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            dataRecyclerView.setAdapter(adapter);  // settingv adapter to recycler view

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

}