package com.app.warantywise.injector.module;

import com.app.warantywise.WarantyApplication;
import com.app.warantywise.injector.scope.PerApplication;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {
    private WarantyApplication application;

    public ApplicationModule(WarantyApplication application) {
        this.application = application;
    }
    @Provides
    @PerApplication
    public WarantyApplication provideApplication() {
        return application;
    }
}
