package com.niconi21.turismoargentina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.niconi21.turismoargentina.tools.Navegacion;
import com.niconi21.turismoargentina.tools.Permisos;

public class UsuarioActivity extends AppCompatActivity {
    private NavHostFragment _navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        Permisos.permisoCamaraStorage(getApplicationContext(), this);

        this._navegacion();
    }


    private void _navegacion() {
        this._navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        this._bottomNavigationView();
        this._materialToolbar();
    }

    private void _bottomNavigationView() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_usuario);
        NavigationUI.setupWithNavController(bottomNavigationView, this._navHostFragment.getNavController());
    }

    private void _materialToolbar() {
        MaterialToolbar toolbar = (MaterialToolbar) findViewById(R.id.topAppBar);
        toolbar.setOnMenuItemClickListener(item -> {
            NavController navigation = this._navHostFragment.getNavController();
            switch (item.getItemId()) {
                case R.id.menu_inicio_favoritos:
                    Navegacion.setNavegacion(navigation, R.id.menu_inicio_favoritos);
                    return true;
            }
            return false;
        });
    }

}