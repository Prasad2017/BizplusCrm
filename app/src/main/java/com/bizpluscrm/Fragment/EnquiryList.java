package com.bizpluscrm.Fragment;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bizpluscrm.Activity.MainPage;
import com.bizpluscrm.Extra.DetectConnection;
import com.bizpluscrm.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;


public class EnquiryList extends Fragment {

    View view;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_enquiry_list, container, false);
        ButterKnife.bind(this, view);
        MainPage.title.setText("");

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY < oldScrollY) { // up
                    animateNavigation(false);
                }
                if (scrollY > oldScrollY) { // down
                    animateNavigation(true);
                }
            }
        });

        return view;

    }

    boolean isNavigationHide = false;

    private void animateNavigation(final boolean hide) {
        if (isNavigationHide && hide || !isNavigationHide && !hide) return;
        isNavigationHide = hide;
        int moveY = hide ? (2 * MainPage.bottomNavigationView.getHeight()) : 0;
        MainPage.bottomNavigationView.animate().translationY(moveY).setStartDelay(100).setDuration(300).start();
    }

    @OnClick({R.id.addEnquiry})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addEnquiry:
                ((MainPage) getActivity()).loadFragment(new EnquiryForm(), true);
                break;

        }
    }

            public void onStart() {
        super.onStart();
        Log.e("onStart", "called");
        MainPage.title.setVisibility(View.VISIBLE);
        ((MainPage) getActivity()).lockUnlockDrawer(0);
        MainPage.drawerLayout.closeDrawers();
        if (DetectConnection.checkInternetConnection(getActivity())) {

        } else {
            Toasty.warning(getActivity(), "No Internet Connection", Toasty.LENGTH_SHORT, true).show();
        }
    }
}