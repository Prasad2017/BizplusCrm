package com.bizpluscrm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import com.andreabaccega.widget.FormEditText;
import com.bizpluscrm.R;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity {

    @BindViews({R.id.email, R.id.password})
    List<FormEditText> formEditTexts;
    @BindView(R.id.loginLinearLayout)
    RelativeLayout loginLinearLayout;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String userName;
    private RelativeLayout login_button;
    private CardView login_button_card_view;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        login_button = findViewById(R.id.login_button);
        login_button_card_view = findViewById(R.id.login_button_card_view);
        inputChange();
        formEditTexts.get(0).setSelection(formEditTexts.get(0).getText().toString().length());
        formEditTexts.get(1).setSelection(formEditTexts.get(1).getText().toString().length());

        loginLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });

        File file = new File("data/data/com.mlmecommerce/shared_prefs/user.xml");
        if (file.exists()) {
            Intent intent = new Intent(Login.this, MainPage.class);
            startActivity(intent);
            finish();
        }

    }

    protected void hideKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @OnClick({R.id.login_button_card_view, R.id.login_button, R.id.forgotPassword, R.id.signIn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button_card_view:
            case R.id.login_button:
                if (formEditTexts.get(0).testValidity() && formEditTexts.get(1).testValidity()) {

                    Intent forgotIntent = new Intent(Login.this, MainPage.class);
                    startActivity(forgotIntent);
                    finishAffinity();

                }
                break;

            case R.id.forgotPassword:

                break;

        }
    }

    @SuppressLint("ResourceType")
    private void loginButtonStyle() {
        if (formEditTexts.get(0).getText().length() > 9 && formEditTexts.get(1).getText().length() > 7) {
            if (!login_button.isFocusable()) {
                login_button.setFocusable(true);
                login_button.setClickable(true);
                login_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorAccent)));
                TypedValue outValue = new TypedValue();
                getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                login_button.setBackgroundResource(outValue.resourceId);
            }
        } else {
            if (login_button.isFocusable()) {
                login_button.setFocusable(false);
                login_button.setClickable(false);
                login_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCardViewBackground)));
                login_button.setBackgroundResource(0);
            }
        }
    }

    private void inputChange() {

        formEditTexts.get(0).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                loginButtonStyle();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                loginButtonStyle();
            }
        });

        formEditTexts.get(1).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                loginButtonStyle();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                loginButtonStyle();
            }
        });
    }

}