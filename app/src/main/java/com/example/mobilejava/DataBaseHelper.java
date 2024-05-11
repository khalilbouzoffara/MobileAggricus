package com.example.mobilejava;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String CUSTOMER_FIRST_NAME = "CUSTOMER_FIRST_NAME";
    public static final String CUSTOMER_LAST_NAME = "CUSTOMER_LAST_NAME";
    public static final String CUSTOMER_STATE = "CUSTOMER_STATE";
    public static final String CUSTOMER_DELEG = "CUSTOMER_DELEG";
    public static final String CUSTOMER_PHONE_NUMBER = "CUSTOMER_PHONE_NUMBER";
    public static final String CUSTOMER_COMPANY_NAME = "CUSTOMER_COMPANY_NAME";
    public static final String CUSTOMER_TRADE_MARK = "CUSTOMER_TRADE_MARK";
    public static final String CUSTOMER_CIN = "CUSTOMER_CIN";
    public static final String CUSTOMER_ACTIVITY = "CUSTOMER_ACTIVITY";
    public static final String CUSTOMER_ADRESS = "CUSTOMER_ADRESS";
    public static final String CUSTOMER_CODE_POSTAL = "CUSTOMER_CODE_POSTAL";
    public static final String CUSTOMER_IS_CARD = "CUSTOMER_IS_CARD";
    public static final String CUSTOMER_IS_AGREE = "CUSTOMER_IS_AGREE";
    public static final String CUSTOMER_IS_SUBS1 = "CUSTOMER_IS_SUBS1";
    public static final String CUSTOMER_IS_SUBS2 = "CUSTOMER_IS_SUBS2";
    public static final String CUSTOMER_IS_SUBS3 = "CUSTOMER_IS_SUBS3";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "new_customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTableStatement = " CREATE TABLE " + CUSTOMER_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," + CUSTOMER_FIRST_NAME + " TEXT," + CUSTOMER_LAST_NAME + " TEXT," + CUSTOMER_STATE + " TEXT," + CUSTOMER_DELEG + " TEXT," + CUSTOMER_PHONE_NUMBER + " INT, "
                + CUSTOMER_COMPANY_NAME + " TEXT," + CUSTOMER_TRADE_MARK + " TEXT," + CUSTOMER_CIN + " TEXT," + CUSTOMER_ACTIVITY + " TEXT," + CUSTOMER_ADRESS + " TEXT," + CUSTOMER_CODE_POSTAL + " INT," + CUSTOMER_IS_CARD + " BOOL," + CUSTOMER_IS_AGREE + " BOOL," + CUSTOMER_IS_SUBS1 +" BOOL,"+ CUSTOMER_IS_SUBS2 +" BOOL,"+ CUSTOMER_IS_SUBS3 + " BOOL)";

        db.execSQL(CreateTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean addCustomer(Customer customer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CUSTOMER_FIRST_NAME,customer.getFirstName());
        cv.put(CUSTOMER_LAST_NAME,customer.getLastName());
        cv.put(CUSTOMER_STATE,customer.getState());
        cv.put(CUSTOMER_DELEG,customer.getDelegation());
        cv.put(CUSTOMER_PHONE_NUMBER,customer.getPhoneNum());
        cv.put(CUSTOMER_COMPANY_NAME,customer.getCompanyName());
        cv.put(CUSTOMER_TRADE_MARK,customer.getTradeMark());
        cv.put(CUSTOMER_CIN,customer.getCin());
        cv.put(CUSTOMER_ACTIVITY,customer.getActivity());
        cv.put(CUSTOMER_ADRESS,customer.getAdress());
        cv.put(CUSTOMER_CODE_POSTAL,customer.getCodePostal());
        cv.put(CUSTOMER_IS_CARD,customer.isCard());
        cv.put(CUSTOMER_IS_AGREE,customer.isAgree());
        cv.put(CUSTOMER_IS_SUBS1,customer.isSubs1());
        cv.put(CUSTOMER_IS_SUBS2,customer.isSubs2());
        cv.put(CUSTOMER_IS_SUBS3,customer.isSubs3());

        long insert = db.insert(CUSTOMER_TABLE, null,cv);
        if(insert == -1){
            return false;
        }else {
            return true;
        }

    }
}