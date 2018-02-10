package com.app.warantywise.injector.component;


import com.app.warantywise.injector.module.ApplicationModule;
import com.app.warantywise.injector.module.NetworkModule;
import com.app.warantywise.injector.scope.PerApplication;
import com.app.warantywise.network.Repository;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@PerApplication
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    Repository repository();
}
