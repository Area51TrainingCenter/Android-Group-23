package com.jcodee.tema03_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Author: johannfjs
 * Date: 21/5/16
 * Time: 11:30
 */
public class DetalleActivity extends AppCompatActivity {
    private TextView lblDescripcion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        lblDescripcion = (TextView) findViewById(R.id.lblDescripcion);

        if (getIntent().hasExtra("descripcion")) {
            String descripcion = getIntent().getStringExtra("descripcion");
            lblDescripcion.setText(descripcion);
        }
    }
}
