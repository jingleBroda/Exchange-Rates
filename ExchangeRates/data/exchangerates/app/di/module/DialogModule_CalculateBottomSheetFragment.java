package com.example.exchangerates.app.di.module;

import com.example.exchangerates.presentation.dialog.calculateBottomFragment.CalculateBottomSheetFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents =
      DialogModule_CalculateBottomSheetFragment.CalculateBottomSheetFragmentSubcomponent.class
)
public abstract class DialogModule_CalculateBottomSheetFragment {
  private DialogModule_CalculateBottomSheetFragment() {}

  @Binds
  @IntoMap
  @ClassKey(CalculateBottomSheetFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      CalculateBottomSheetFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface CalculateBottomSheetFragmentSubcomponent
      extends AndroidInjector<CalculateBottomSheetFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<CalculateBottomSheetFragment> {}
  }
}
