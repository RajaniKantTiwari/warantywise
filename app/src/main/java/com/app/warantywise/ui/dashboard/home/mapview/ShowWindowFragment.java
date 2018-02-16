/*
package com.app.warantywise.ui.dashboard.home.mapview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.warantywise.R;


public class ShowWindowFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.product_dialog, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle=getArguments();
        ((TextView)view.findViewById(R.id.editTextFormInfoWindow)).setText(bundle.getString("KEY"));
        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Yes", Toast.LENGTH_SHORT).show();
            }
        };

        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "No", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.button2).setOnClickListener(onClickListener);
    }
}
*/
