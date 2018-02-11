package com.app.warantywise.injector.component;




import com.app.warantywise.injector.module.DashboardModule;
import com.app.warantywise.injector.scope.PerActivity;
import com.app.warantywise.ui.dashboard.DashBoardActivity;
import com.app.warantywise.ui.dashboard.home.ProductDetailsFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = DashboardModule.class)
public interface DashboardComponent {

    void inject(DashBoardActivity dashBoardActivity);
    void inject(ProductDetailsFragment dashBoardActivity);

}
