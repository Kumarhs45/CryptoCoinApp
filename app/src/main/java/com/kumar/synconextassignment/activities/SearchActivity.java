package com.kumar.synconextassignment.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kumar.synconextassignment.R;
import com.kumar.synconextassignment.adapter.CryptoDataAdapter;
import com.kumar.synconextassignment.network.Api;
import com.kumar.synconextassignment.pojo.CryptoData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    EditText editTextSearch;
    RecyclerView recyclerView;
    List<CryptoData> mySearchList;
    CryptoDataAdapter myAdapter;
    RelativeLayout progressBar;
    ImageView navigation_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search_layout);

        recyclerView = (RecyclerView) findViewById(R.id.listView_in_search);
        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        progressBar = (RelativeLayout) findViewById(R.id.progressBar);
        navigation_icon = (ImageView) findViewById(R.id.navigation_icon);


        mySearchList = new ArrayList<>();

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        navigation_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.super.onBackPressed();
            }
        });

        getDataFromServer();
    }

    private void filter(String text) {

        ArrayList<CryptoData> filterList = new ArrayList<>();
        for (CryptoData item : mySearchList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item);
            }
        }
        myAdapter.filteredList(filterList);
    }

    private void getDataFromServer() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.SEARCH_API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) { // got profiles from server
                        Log.d("response", "--" + response);
                        progressBar.setVisibility(View.GONE);

                        getSearchDetailsFromServer(response);  // parsing response to list using array list
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SearchActivity.this, "Please Check Your connectivity", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(SearchActivity.this).add(stringRequest);
    }

    private void getSearchDetailsFromServer(String response) {

        try {
            //converting the string to json array object
            JSONArray array = new JSONArray(response);
            Log.d("array", "--" + array.length());
            Log.d("response", "--" + response.toString());
            for (int i = 0; i < array.length(); i++) {

                JSONObject jsonObject = array.getJSONObject(i);// parsing json array to each element json object
                // parsing all ids from json object to string//
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
//
//                mySearchList.add(new CryptoData(id, symbol, name, image, current_price, market_cap, market_cap_rank,
//                        fully_diluted_valuation, total_volume, high_24h, low_24h, price_change_24h,
//                        price_change_percentage_24h, market_cap_change_24h, market_cap_change_percentage_24h,
//                        circulating_supply, total_supply, max_supply, ath, ath_change_percentage, ath_date, atl,
//                        atl_change_percentage, atl_date, roi, last_updated)); // calling matches pojo class constructor and storing object instances in list
                mySearchList.add(coinData);
                myAdapter = new CryptoDataAdapter(mySearchList,SearchActivity.this );  // passing matches adapter constructor
            }
            myAdapter = new CryptoDataAdapter( mySearchList,SearchActivity.this);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(myAdapter);

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }


}