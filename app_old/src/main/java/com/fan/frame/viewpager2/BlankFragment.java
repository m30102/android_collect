package com.fan.frame.viewpager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.FileInputStream;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class BlankFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        Bundle bundle = getArguments();
        String name = bundle.getString("name");
        textView.setText(name);
        textView.setTextSize(20);

        return textView;
    }

    public static BlankFragment getInstances(String name){
        BlankFragment blankFragment = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        blankFragment.setArguments(bundle);
        return  blankFragment;
    }

}
