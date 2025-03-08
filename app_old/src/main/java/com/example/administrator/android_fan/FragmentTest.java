package com.example.administrator.android_fan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;

import com.fan.frame.fragment.MyDialogFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentTest extends FragmentActivity implements View.OnKeyListener {


    Fragment aFm,bFm,cFm;

    RadioGroup.OnCheckedChangeListener rs = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(aFm == null)
                    aFm = new Fragment();
                if(bFm == null)
                    aFm = new Fragment();
                if(cFm == null)
                    aFm = new Fragment();

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);
        ButterKnife.bind(this);
    }
    MyDialogFragment myDialogFragment;
    @OnClick(R.id.dialog_show)
    public void show(View view){
         myDialogFragment = new MyDialogFragment();
//        myDialogFragment.show(getSupportFragmentManager(),"");
        myDialogFragment.show(getFragmentManager(),"");
    }

    @OnClick(R.id.dialog_hide)
    public void hide(View view){
//        getSupportFragmentManager().beginTransaction().hide(myDialogFragment).commit();
        getFragmentManager().beginTransaction().hide(myDialogFragment).commit();
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if (i == KeyEvent.KEYCODE_BACK) {
//            getSupportFragmentManager().beginTransaction().hide(myDialogFragment).commit();
            getFragmentManager().beginTransaction().hide(myDialogFragment).commit();
            return true;
        } else {
            return false;
        }
    }
}
