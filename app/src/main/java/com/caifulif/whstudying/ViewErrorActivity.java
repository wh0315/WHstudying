package com.caifulif.whstudying;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

public class ViewErrorActivity extends Activity implements View.OnClickListener {

    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_error);

        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
        findViewById(R.id.buttonError).setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonError:
                //TODO implement
                break;
        }
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title")
                .setMessage("Dialog Content")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }





}
