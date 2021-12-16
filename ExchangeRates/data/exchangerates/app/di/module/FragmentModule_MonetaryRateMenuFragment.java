package com.example.exchangerates.app.di.module;

import com.example.exchangerates.presentation.fragment.monetaryFragment.MonetaryRateMenuFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents = FragmentModule_MonetaryRateMenuFragment.MonetaryRateMenuFragmentSubcomponent.class
)
public abstract class FragmentModule_MonetaryRateMenuFragment {
  private FragmentModule_MonetaryRateMenuFragment() {}

  @Binds
  @IntoMap
  @ClassKey(MonetaryRateMenuFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      MonetaryRateMenuFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface MonetaryRateMenuFragmentSubcomponent
      extends AndroidInjector<MonetaryRateMenuFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MonetaryRateMenuFragment> {}
  }
}
