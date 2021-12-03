package com.niconi21.turismoargentina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.google.android.material.appbar.MaterialToolbar;
import com.niconi21.turismoargentina.services.PublicacionService;

public class BuscarActivity extends AppCompatActivity {
    private MaterialToolbar _toolbar;
    private RecyclerView _listaPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        this._establecerItems();
        super.setSupportActionBar(_toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        this.onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private void _establecerItems() {
        this._toolbar = findViewById(R.id.topAppBarBusqueda);
        this._listaPost = findViewById(R.id.postBuscar);
        _toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_busqueda, menu);

        MenuItem searchViewItem = menu.findItem(R.id.menu_busqueda_item);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setQueryHint(getString(R.string.search_hint));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                View view = getWindow().getDecorView().getRootView();
                PublicacionService publicacionService = new PublicacionService(getApplicationContext(),view);
                publicacionService.obtenerPublicacionesFiltro(query,_listaPost);
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }




}