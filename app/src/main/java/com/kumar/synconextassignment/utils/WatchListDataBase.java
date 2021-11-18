package com.kumar.synconextassignment.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.kumar.synconextassignment.pojo.CryptoData;
import com.kumar.synconextassignment.pojo.WatchList;

import java.util.ArrayList;
import java.util.List;

public class WatchListDataBase extends SQLiteOpenHelper {


    private static final int DATBASE_VERSION = 1;
    private static final String DATABASE_NAME = "WatchListDataBase";

    private static final String CART_TABLE = "WatchlistTable";

    private static final String COIN_ID = "CoinId";
    private static final String COIN_NAME = "CoinName";
    private static final String COIN_SYMBOL = "CoinSymbol";
    private static final String COIN_IMAGE = "CoinImage";
    private static final String COIN_PRICE = "CoinPrice";
    private static final String COIN_24H_CHANGE = "CoinChange";


    public WatchListDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_MENU_TABLE = "CREATE TABLE " + CART_TABLE + "("
                + COIN_ID + " TEXT,"
                + COIN_NAME + " TEXT,"
                + COIN_SYMBOL + " TEXT,"
                + COIN_IMAGE + " TEXT,"
                + COIN_PRICE + " TEXT,"
                + COIN_24H_CHANGE + " TEXT" + ")";

        db.execSQL(CREATE_MENU_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + CART_TABLE);

        onCreate(db);
    }

    public void addAnItemToWatchList(WatchList watchListData) {

        SQLiteDatabase database = this.getWritableDatabase();

        CryptoData menu = watchListData.getData();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COIN_ID, menu.getId());
        contentValues.put(COIN_NAME, menu.getName());
        contentValues.put(COIN_SYMBOL, menu.getSymbol() );
        contentValues.put(COIN_IMAGE, menu.getImage() );
        contentValues.put(COIN_PRICE, menu.getCurrent_price()+ "");
        contentValues.put(COIN_24H_CHANGE, menu.getMarket_cap_change_percentage_24h()+ "");

        long row = database.insert(CART_TABLE, null, contentValues);

        if (row != -1) {
            Log.d("watchListData", "Added to cart");
        } else {
            Log.d("watchListData", "Failed to add in cart");
        }
        database.close();
    }

    public boolean checkTheDataInWatchList(String menuId) {

        SQLiteDatabase mDatabase = this.getReadableDatabase();

        String query = "SELECT * FROM " + CART_TABLE + " WHERE " + COIN_ID + " = " + menuId;
        Cursor mCursor = mDatabase.rawQuery(query, null);

        if (mCursor.moveToFirst()) {

            return true;

        }

        return false;
    }

    public List<WatchList> getAllTheWatchListData() {

        SQLiteDatabase mDatabase = this.getReadableDatabase();

        String query = "SELECT * FROM " + CART_TABLE + " ;";

        List<WatchList> cartList = new ArrayList<>();
        Cursor mCursor = mDatabase.rawQuery(query, null);

        if (mCursor.moveToFirst()) {

            do {
                WatchList mCart = new WatchList();

                CryptoData menu = new CryptoData();


                menu.setId(mCursor.getString(mCursor.getColumnIndex(COIN_ID)));
                menu.setName(mCursor.getString(mCursor.getColumnIndex(COIN_NAME)));
                menu.setSymbol(mCursor.getString(mCursor.getColumnIndex(COIN_SYMBOL)));
                menu.setImage(mCursor.getString(mCursor.getColumnIndex(COIN_IMAGE)));
                menu.setCurrent_price(mCursor.getString(mCursor.getColumnIndex(COIN_PRICE)));
                menu.setPrice_change_24h(mCursor.getString(mCursor.getColumnIndex(COIN_24H_CHANGE)));
                mCart.setData(menu);

                cartList.add(mCart);
            } while (mCursor.moveToNext());

        }

        mCursor.close();
        mDatabase.close();
        return cartList;

    }
}
