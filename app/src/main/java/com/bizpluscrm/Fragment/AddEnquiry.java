package com.bizpluscrm.Fragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.bizpluscrm.Activity.MainPage;
import com.bizpluscrm.Extra.DetectConnection;
import com.bizpluscrm.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;


public class AddEnquiry extends Fragment {

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_enquiry, container, false);
        ButterKnife.bind(this, view);
        MainPage.title.setText("");

        return view;

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);

        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerView);
        TextView close = dialog.findViewById(R.id.close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }

    @OnClick({R.id.next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                ((MainPage) getActivity()).loadFragment(new AddFollowUp(), true);
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