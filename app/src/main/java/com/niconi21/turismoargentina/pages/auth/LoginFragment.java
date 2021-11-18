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


public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this._navegacionFragment(view);
        this._login(view);

    }

    private void _navegacionFragment(View view) {

        Button btnRegistro = view.findViewById(R.id.btnRegistroLogin);
        Button btnForgot = view.findViewById(R.id.btnForgotLogin);
        NavController navigation = Navigation.findNavController(view);

        Navegacion.setNavegacion(navigation, btnRegistro, R.id.registroFragment);
        Navegacion.setNavegacion(navigation, btnForgot, R.id.forgotFragment);
    }

    private void _login(View view) {
        NavController navigation = Navigation.findNavController(view);
        Button btnLogin = view.findViewById(R.id.btnIniciarSesionLogin);
        Mensajes.MensajeSnackBar(view, btnLogin, "Login exitoso", Snackbar.LENGTH_LONG);
        Navegacion.setNavegacion(navigation,btnLogin, R.id.usuarioActivity);
    }

}