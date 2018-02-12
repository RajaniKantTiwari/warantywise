package com.app.warantywise.injector.component;



import com.app.warantywise.injector.module.CommonModule;
import com.app.warantywise.injector.scope.PerActivity;
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



}
