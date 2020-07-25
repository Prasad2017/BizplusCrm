package com.bizpluscrm.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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


public class Home extends Fragment {

    View view;
    public static SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        MainPage.title.setText("");
        swipeRefreshLayout = view.findViewById(R.id.simpleSwipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (DetectConnection.checkInternetConnection(getActivity())) {
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    Toasty.warning(getActivity(), "No Internet Connection", Toasty.LENGTH_SHORT, true);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        return view;

    }

    @OnClick({R.id.enquiryLayout, R.id.quotationLayout, R.id.followLayout, R.id.cmsLayout, R.id.visitLayout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.enquiryLayout:
                ((MainPage) getActivity()).loadFragment(new EnquiryList(), false);
                break;

            case R.id.quotationLayout:
                ((MainPage) getActivity()).loadFragment(new QuatationList(), false);
                break;

            case R.id.followLayout:
                ((MainPage) getActivity()).loadFragment(new AddFollowUp(), true);
                break;

            case R.id.cmsLayout:
                ((MainPage) getActivity()).loadFragment(new AddCms(), true);
                break;

            case R.id.visitLayout:
                ((MainPage) getActivity()).loadFragment(new AddVisit(), true);
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