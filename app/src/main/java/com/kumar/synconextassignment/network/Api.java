package com.kumar.synconextassignment.network;

public class Api {

    public static final String SEARCH_API = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=inr&order=market_cap_desc&per_page=100&page=1&sparkline=false";

    public static String getData(int perPage, int pageNo) {

        return "https://api.coingecko.com/api/v3/coins/markets?vs_currency=inr&order=market_cap_desc&per_page="+perPage+"&page="+pageNo+"&sparkline=false";

    }

}
