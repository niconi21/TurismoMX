package com.niconi21.turismoargentina.tools;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.niconi21.turismoargentina.BuscarActivity;
import com.niconi21.turismoargentina.R;

public class Navegacion {

    public static void setNavegacion(@NonNull NavController nav, @NonNull Button btn, @NonNull int res) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(res);
            }
        });
    }

    public static void setNavegacion(@NonNull NavController nav, @NonNull int res) {
        nav.navigate(res);
    }

    public static void configBottomNavigationView(@NonNull Activity activity, @NonNull NavHostFragment navHostFragment) {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) activity.findViewById(R.id.bottom_navigation_usuario);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
    }

    public static void configMaterialToolbarInicio(@NonNull Activity activity, @NonNull NavHostFragment navHostFragment) {
        MaterialToolbar toolbar = (MaterialToolbar) activity.findViewById(R.id.topAppBar);
        toolbar.setOnMenuItemClickListener(item -> {
            NavController navigation = navHostFragment.getNavController();
            switch (item.getItemId()) {
                case R.id.menu_inicio_favoritos:
                    setNavegacion(navigation, R.id.menu_inicio_favoritos);
                    return true;
                case R.id.menu_inicio_buscar:
                    Intent intent = new Intent(activity, BuscarActivity.class);
                    activity.startActivity(intent);
            }
            return false;
        });
    }
}
