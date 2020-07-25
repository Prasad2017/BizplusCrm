package com.bizpluscrm.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bizpluscrm.Activity.MainPage;
import com.bizpluscrm.Extra.DetectConnection;
import com.bizpluscrm.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;


public class AddFollowUp extends Fragment {

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_follow_up, container, false);
        ButterKnife.bind(this, view);
        MainPage.title.setText("");

        return view;

    }

    @OnClick({R.id.next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:

                break;

        }
    }

    public void onStart() {
        super.onStart();
        Log.e("onStart", "called");
        MainPage.title.setVisibility(View.VISIBLE);
        ((MainPage) getActivity()).lockUnlockDrawer(1);
        MainPage.drawerLayout.closeDrawers();
        if (DetectConnection.checkInternetConnection(getActivity())) {

        } else {
            Toasty.warning(getActivity(), "No Internet Connection", Toasty.LENGTH_SHORT, true).show();
        }
    }
}