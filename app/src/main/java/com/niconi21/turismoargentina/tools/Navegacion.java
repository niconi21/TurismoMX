package com.niconi21.turismoargentina.tools;

import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;

public class Navegacion {

    public static void setNavegacion(NavController nav, Button btn, int res) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(res);
            }
        });
    }

    public static void setNavegacion(NavController nav, int res) {
        nav.navigate(res);
        
    }

}
