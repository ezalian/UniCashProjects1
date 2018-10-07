package com.example.nitro5.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Pay extends AppCompatActivity {
    Button btn_scan;
    TextView textViewShowResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        textViewShowResult = (TextView)findViewById(R.id.txtview_show_result);
        btn_scan = (Button) findViewById(R.id.btn_scan);

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(Pay.this);
                scanIntegrator.setPrompt("Scan a barcode");
                scanIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                scanIntegrator.setCameraId(0);
                scanIntegrator.setOrientationLocked(false);
                scanIntegrator.setBarcodeImageEnabled(true);
                scanIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult (int requestCode,int resultCode, Intent data) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, requestCode, data) ;
        if (scanningResult!=null) {
            String contents = data.getStringExtra("SCAN_RESULT");
            String format = data.getStringExtra("SCAN_RESULT_FORMAT");

            textViewShowResult.setText("Wallet ID -" +contents+ " Format-" +format);


        }
    }
}
