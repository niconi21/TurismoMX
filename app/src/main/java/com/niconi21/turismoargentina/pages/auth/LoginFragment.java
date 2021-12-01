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
import com.google.android.material.textfield.TextInputLayout;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.services.AuthServices;
import com.niconi21.turismoargentina.tools.Mensajes;
import com.niconi21.turismoargentina.tools.Navegacion;
import com.niconi21.turismoargentina.tools.Validaciones;


public class LoginFragment extends Fragment {

    private TextInputLayout _correo;
    private TextInputLayout _clave;

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
        this._declararElementos(view);
        this._login(view);

    }

    private void _navegacionFragment(View view) {

        Button btnRegistro = view.findViewById(R.id.btnRegistroLogin);
        Button btnForgot = view.findViewById(R.id.btnForgotLogin);
        NavController navigation = Navigation.findNavController(view);

        Navegacion.setNavegacion(navigation, btnRegistro, R.id.registroFragment);
        Navegacion.setNavegacion(navigation, btnForgot, R.id.forgotFragment);
    }

    private void _declararElementos(View view) {
        this._correo = view.findViewById(R.id.tfCorreoLogin);
        this._clave = view.findViewById(R.id.tfClaveLogin);
        Validaciones.textChangedListener(this._correo, getString(R.string.mgsErrorCorreo));
        Validaciones.textChangedListener(this._clave, getString(R.string.mgsErrorClave));
    }


    private void _login(View view) {
        NavController navigation = Navigation.findNavController(view);
        Button btnLogin = view.findViewById(R.id.btnIniciarSesionLogin);
        btnLogin.setOnClickListener(v -> {
            AuthServices auth = new AuthServices(this.getContext(), view);
            Boolean isValidEmail = Validaciones.isValid(this._correo, getString(R.string.mgsErrorCorreo));
            Boolean isValidPassword = Validaciones.isValid(this._clave, getString(R.string.mgsErrorClave));
            if (isValidEmail && isValidPassword) {
                String correo = this._correo.getEditText().getText().toString();
                String clave = this._clave.getEditText().getText().toString();
                auth.Login(correo, clave, navigation);
            } else {
                Mensajes.MensajeSnackBar(view, getString(R.string.mgsErrorGeneral), Snackbar.LENGTH_SHORT);
            }
        });
    }

}