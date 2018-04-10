package com.app.warantywise.injector.component;



import com.app.warantywise.injector.module.CommonModule;
import com.app.warantywise.injector.scope.PerActivity;
import com.app.warantywise.ui.activity.MyOrderActivity;
import com.app.warantywise.ui.activity.OrderDetailsActivity;
import com.app.warantywise.ui.authentication.AddProductActivity;
import com.app.warantywise.ui.dashboard.user.ProfileFragment;
import com.app.warantywise.ui.authentication.LoginActivity;
import com.app.warantywise.ui.authentication.VerifyAccountActivity;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = CommonModule.class)
public interface CommonComponent {
    void inject(LoginActivity activity);
    void inject(ProfileFragment activity);
    void inject(VerifyAccountActivity activity);
    void inject(AddProductActivity activity);
    void inject(OrderDetailsActivity activity);
    void inject(MyOrderActivity activity);

}
