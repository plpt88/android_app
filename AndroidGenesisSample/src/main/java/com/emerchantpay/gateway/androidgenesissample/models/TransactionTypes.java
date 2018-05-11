package com.emerchantpay.gateway.androidgenesissample.models;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TransactionTypes {

    private HashMap<String, String> transactionTypesMap;

    private String displayName;
    private String value;

    public TransactionTypes() {

    }

    public TransactionTypes(String displayName, String value) {
        this.displayName = displayName;
        this.value = value;
    }

    public static TransactionTypes authorize = new TransactionTypes("Authorize", "authorize");
    public static TransactionTypes authorize3d = new TransactionTypes("Authorize 3D", "authorize3d");
    public static TransactionTypes sale = new TransactionTypes("Sale", "sale");
    public static TransactionTypes sale3d = new TransactionTypes("Sale 3D", "sale3d");
    public static TransactionTypes paysafecard = new TransactionTypes("Paysafecard", "paysafecard");

    // Add Transaction types
    protected Map<String, String> addTransactionTypes() throws IllegalAccessException {

        transactionTypesMap = new HashMap<String, String>();

        Field[] fields = this.getClass().getDeclaredFields();

        for (Field f: fields) {
            if (Modifier.isStatic(f.getModifiers())) {
                transactionTypesMap.put(((TransactionTypes) f.get(this)).getDisplayName(),
                        ((TransactionTypes) f.get(this)).getValue());
            }
        }

        return transactionTypesMap;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getValue() {
        return value;
    }

    // Get Transaction type names list
    public ArrayList<String> getListNames() throws IllegalAccessException {
        addTransactionTypes();

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.addAll(transactionTypesMap.keySet());

        return sortArrayList(arrayList);
    }

    // Get Transaction type values list
    public ArrayList<String> getListValues() throws IllegalAccessException {
        addTransactionTypes();

        ArrayList<String> arrayList = new ArrayList<String>();

        for (String value: transactionTypesMap.keySet()) {
            arrayList.add(transactionTypesMap.get(value));
        }

        return sortArrayList(arrayList);
    }

    // Sort Array list
    protected ArrayList<String> sortArrayList(ArrayList<String> list) {
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String value2, String value1) {
                return  value2.compareTo(value1);
            }
        });

        return list;
    }
}
