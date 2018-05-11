package com.emerchantpay.gateway.androidgenesissample.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.emerchantpay.gateway.androidgenesissample.R;
import com.emerchantpay.gateway.androidgenesissample.models.TransactionTypes;
import com.emerchantpay.gateway.genesisandroid.api.constants.IntentExtras;

public class MainActivity extends Activity {

    private TextView privacyTextView;
    private TextView termsandconditionsTextView;

    private ListView transactionTypesListView;

    private TransactionTypes transactionTypes = new TransactionTypes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadView();
    }

    protected void loadView() {
        privacyTextView = (TextView) findViewById(R.id.privacyPolicyLabel);
        termsandconditionsTextView = (TextView) findViewById(R.id.termsandconditionsLabel);

        transactionTypesListView = (ListView) findViewById(R.id.transactionTypesListView);

        privacyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources()
                        .getString(R.string.privacy_policy_url)));
                startActivity(browserIntent);
            }
        });

        termsandconditionsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getResources()
                        .getString(R.string.terms_and_conditions_url)));
                startActivity(browserIntent);
            }
        });

        // Load  transaction types to ListView
        ArrayAdapter<String> listAdapter = null;
        try {
            listAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, transactionTypes.getListNames());
        } catch (IllegalAccessException e) {
            Log.e("Illigal Exception", e.toString());
        }

        transactionTypesListView.setAdapter(listAdapter);

        Intent intentTransactionDetails = new Intent(this, TransactionDetailsActivity.class);

        transactionTypesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String itemValue = null;
                try {
                    itemValue = transactionTypes.getListValues().get(i);
                    
                    intentTransactionDetails.putExtra(IntentExtras.EXTRA_TRANSACTION_TYPE, itemValue);
                    startActivity(intentTransactionDetails);
                } catch (IllegalAccessException e) {
                    Log.e("Illigal Exception", e.toString());
                }
            }
        });
    }
}
