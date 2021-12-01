package com.niconi21.turismoargentina.tools;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class Validaciones {

    public static Boolean isValid(TextInputLayout input, String msgError) {
        Boolean isEmpty = input.getEditText().getText().toString().isEmpty();
        input.setError(isEmpty ? msgError: null);
        return !isEmpty;
    }

    public static void textChangedListener(TextInputLayout input, String msgError) {
        input.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                input.setError(s.toString().isEmpty() ? msgError : null);
            }
        });
    }

}
