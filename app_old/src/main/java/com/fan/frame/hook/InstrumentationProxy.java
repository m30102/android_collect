package com.fan.frame.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import com.fan.frame.utils.FLogger;

import java.lang.reflect.Method;

public class InstrumentationProxy extends Instrumentation {

    Instrumentation instrumentation;
    public InstrumentationProxy(Instrumentation instrumentation){
        this.instrumentation = instrumentation;
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
                                            Intent intent, int requestCode, Bundle options){
        FLogger.d("hook成功 who:"+who);
        try {
            Method execStartActivity = Instrumentation.class.getDeclaredMethod("execStartActivity", Context.class, IBinder.class, IBinder.class,
                    Activity.class, Intent.class, int.class, Bundle.class
            );
            return (ActivityResult) execStartActivity.invoke(instrumentation,who,contextThread,token,target,intent,requestCode,options);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
