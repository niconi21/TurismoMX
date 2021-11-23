package com.niconi21.turismoargentina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;

public class BuscarActivity extends AppCompatActivity {
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        this._establecerItems();
    }

    private void _establecerItems() {
        this.toolbar = (MaterialToolbar) findViewById(R.id.topAppBarBusqueda);
        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }
}