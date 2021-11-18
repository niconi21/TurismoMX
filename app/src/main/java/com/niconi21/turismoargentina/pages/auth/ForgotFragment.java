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

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ForgotFragment extends Fragment {

    public ForgotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this._navegacionFragment(view);
        Button btnReestablecer = view.findViewById(R.id.btnReestablecerForgot);
        Mensajes.MensajeSnackBar(view,btnReestablecer, "Contraseña reestablecida", Snackbar.LENGTH_LONG);
    }

    private void _navegacionFragment(View view) {
        NavController navigation = Navigation.findNavController(view);
        Button btnLogin = view.findViewById(R.id.btnLoginForgot);
        Navegacion.setNavegacion(navigation, btnLogin, R.id.loginFragment);
    }
}