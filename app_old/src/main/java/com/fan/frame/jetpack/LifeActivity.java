package com.fan.frame.jetpack;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.administrator.android_fan.R;
import com.fan.frame.retrofit.RetroTest;
import com.fan.frame.utils.FLogger;

import java.util.Random;

// https://github.com/KunMinX/Jetpack-MVVM-Best-Practice
//https://www.jianshu.com/p/2a2c4a817ada
//https://www.jianshu.com/p/6a19424e5c62
public class LifeActivity extends AppCompatActivity  {
    private static String TAG = "LifeActivity";



    Random random = new Random();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);



        // Lifecycle
        getLifecycle().addObserver(new MyObserver());

        // liveData
        TextView textView1 = findViewById(R.id.content1);
        MutableLiveData<String> data = new MutableLiveData<>();
        findViewById(R.id.btn_change1).setOnClickListener(v->{
            data.setValue("李四"+random.nextInt(10));
        });
        // 可以有多个observer
        Observer ob = new Observer<String>(){

            @Override
            public void onChanged(String s) {
                textView1.setText(s);
            }
        };
         /**
          * 可以添加多次和多个
          * //        Observer<String> ob = s -> textView1.setText(s);
          *         data.observe(this ,ob);
          *         data.observe(this ,s -> FLogger.d(TAG,"呵呵呵"));
          *         data.observe(this ,ob);
          * //        data.observe(this ,s -> textView1.setText(s));
          *         data.observe(this ,s -> FLogger.d(TAG,"哈哈哈哈"));
         * */



        /*MutableLiveData<Integer> intData = new MutableLiveData<>();
        LiveData<String> stringLiveData = Transformations.map(intData, new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                return "";
            }
        });
        stringLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });*/
        //
        /*MutableLiveData<Integer> intData = new MutableLiveData<>();
        LiveData<String> mapLiveData = Transformations.map(intData, input -> input + "___");// 转换liveData,integer换为string。 观察转换后的mapLiveData就行了
        mapLiveData.observe(this,s->{
            FLogger.e("mapLiveData:"+s);
        });
        findViewById(R.id.btn_change2).setOnClickListener((v)->{
            // intData变化会让mapLiveData observe到
            intData.setValue(random.nextInt(10));
        });*/

        MutableLiveData<String> source2 = new MutableLiveData<>();
        LiveData<Integer> boolLiveData = Transformations.switchMap(source2, new Function<String, LiveData<Integer>>() {
            @Override
            public LiveData<Integer> apply(String input) {
                // 其他地方产生的liveData,每次都是新的
                MutableLiveData<Integer> liveData = new MutableLiveData<>();
                int i = random.nextInt(100);
                liveData.setValue(i );
                FLogger.d("liveData:"+liveData.hashCode()+" ___"+i);//638175064
                return liveData;
            }
        });
        // 其他地方产生的liveData观察其他地方产生的liveData的数据
        boolLiveData.observe(this,o->{
            FLogger.d("observe:"+o);
        });
        FLogger.d("boolLiveData:"+boolLiveData.hashCode());//657029159 和switchMap产生的不是同一个
        findViewById(R.id.btn_change2).setOnClickListener((v)->{
            source2.setValue("");
        });

//        Transformations.switchMap(source2,input -> new MutableLiveData<Boolean>());//转换liveData,integer换为Object

        //MediatorLiveData 可以addSource多个Observer然后绑定一个observe

        // ViewModle
     /*   TextView textView2 = findViewById(R.id.content2);
        MyViewModel MyViewModel1 = new ViewModelProvider(this).get(MyViewModel.class);
        MyViewModel MyViewModel2 = new ViewModelProvider(this).get(MyViewModel.class);
        // 旋转屏幕后viewmodel不消失
        textView2.setText("name:王二 "+" age:"+MyViewModel1.age);
        MyViewModel1.getName().observe(this,s -> { textView2.setText(s);});
        findViewById(R.id.btn_change2).setOnClickListener(v->{
            // 同一个viewmodel
            FLogger.d(TAG,(MyViewModel1==MyViewModel2) +"MyViewModel1:"+MyViewModel1.hashCode()+" MyViewModel2:"+MyViewModel2.hashCode());
            MyViewModel1.age = random.nextInt(20);
            MyViewModel1.getName().setValue("name:王二 "+" age:"+MyViewModel1.age);
        });

        findViewById(R.id.liveDataBus).setOnClickListener( v-> ActivityUtils.startActivity(LiveDataBusActivityA.class));*/
    }


    

    class MyObserver implements LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart(){
            FLogger.d(TAG,"onStart");
        }
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void onResume(){
            FLogger.d(TAG,"onResume");
        }
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestory(){
            FLogger.d(TAG,"onDestory");
        }
    }
}


