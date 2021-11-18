package com.niconi21.turismoargentina.pages.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.tools.Mensajes;
import com.niconi21.turismoargentina.tools.Navegacion;

public class RegistroFragment extends Fragment {


    public RegistroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this._navegarcionFragment(view);
        Button btnRegistro = view.findViewById(R.id.btnRegistrarseRegistro);
        Mensajes.MensajeSnackBar(view, btnRegistro,"Registro exitoso", Snackbar.LENGTH_LONG);

    }

    private void _navegarcionFragment(View view) {
        NavController navigator = Navigation.findNavController(view);
        Button btnLogin = view.findViewById(R.id.btnLoginRegistro);
        Navegacion.setNavegacion(navigator, btnLogin, R.id.loginFragment);

    }
}