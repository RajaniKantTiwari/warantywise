package com.app.warantywise;

import android.support.multidex.MultiDexApplication;

import com.app.warantywise.injector.component.ApplicationComponent;
import com.app.warantywise.injector.component.DaggerApplicationComponent;
import com.app.warantywise.injector.module.ApplicationModule;
import com.orhanobut.hawk.Hawk;


public class WarantyApplication extends MultiDexApplication {

    private ApplicationComponent applicationComponent;
    public static boolean isDebug=false;
    @Override
    public void onCreate() {
        super.onCreate();
        setOrientation();
        Hawk.init(this).build();
        setUpInjector();
    }

    private void setUpInjector() {
        applicationComponent = DaggerApplicationComponent.builder().
                applicationModule(new ApplicationModule(this)).build();
    }

    private void setOrientation() {
        registerActivityLifecycleCallbacks(new ActivityLifeCycle());
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
