// Generated by Dagger (https://dagger.dev).
package com.example.exchangerates.app.di.component;

import androidx.lifecycle.ViewModel;
import com.example.exchangerates.app.App;
import com.example.exchangerates.app.di.module.DialogModule_CalculateBottomSheetFragment;
import com.example.exchangerates.app.di.module.FragmentModule_MonetaryRateMenuFragment;
import com.example.exchangerates.app.di.module.RepositoryModule;
import com.example.exchangerates.app.di.module.RepositoryModule_MakeApiUseCaseFactory;
import com.example.exchangerates.app.di.module.RepositoryModule_MakeDbDataUseCaseFactory;
import com.example.exchangerates.app.di.module.RepositoryModule_MakeDbSpecificUseCaseFactory;
import com.example.exchangerates.app.di.module.RepositoryModule_MakeDeleteSingeDataInfoDbUseCaseFactory;
import com.example.exchangerates.app.di.module.RepositoryModule_MakeRepositoryFactory;
import com.example.exchangerates.app.di.module.RepositoryModule_MakeSingleInsertDbDataUseCaseFactory;
import com.example.exchangerates.app.di.module.RetrofiteModule;
import com.example.exchangerates.app.di.module.RetrofiteModule_CreateRetrofiteFactory;
import com.example.exchangerates.app.di.module.RetrofiteModule_CreateRetrofiteServiceFactory;
import com.example.exchangerates.app.di.module.RoomModule;
import com.example.exchangerates.app.di.module.RoomModule_CreateDBFactory;
import com.example.exchangerates.app.di.module.RoomModule_GetDAOFactory;
import com.example.exchangerates.app.di.utils.ViewModelFactory;
import com.example.exchangerates.app.di.utils.ViewModelFactory_Factory;
import com.example.exchangerates.data.retrofite.RetrofiteService;
import com.example.exchangerates.data.room.ExchangeRatesRoomDB;
import com.example.exchangerates.data.room.ExchangeRatesRoomDao;
import com.example.exchangerates.domain.repository.RepositoryExchangeRates;
import com.example.exchangerates.domain.usecase.MakeApiUseCaseRx;
import com.example.exchangerates.domain.usecase.MakeDbDataUseCase;
import com.example.exchangerates.domain.usecase.MakeDbSpecificDataUseCase;
import com.example.exchangerates.domain.usecase.MakeDeleteSingeDataInfoDbUseCase;
import com.example.exchangerates.domain.usecase.MakeSingleInsertDbDataUseCase;
import com.example.exchangerates.presentation.dialog.calculateBottomFragment.BottomSheetViewModel;
import com.example.exchangerates.presentation.dialog.calculateBottomFragment.BottomSheetViewModel_Factory;
import com.example.exchangerates.presentation.dialog.calculateBottomFragment.CalculateBottomSheetFragment;
import com.example.exchangerates.presentation.dialog.calculateBottomFragment.CalculateBottomSheetFragment_MembersInjector;
import com.example.exchangerates.presentation.fragment.monetaryFragment.MonetaryRateMenuFragment;
import com.example.exchangerates.presentation.fragment.monetaryFragment.MonetaryRateMenuFragmentViewModel;
import com.example.exchangerates.presentation.fragment.monetaryFragment.MonetaryRateMenuFragmentViewModel_Factory;
import com.example.exchangerates.presentation.fragment.monetaryFragment.MonetaryRateMenuFragment_MembersInjector;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication_MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.DispatchingAndroidInjector_Factory;
import dagger.android.support.DaggerFragment_MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.MapBuilder;
import dagger.internal.MapProviderFactory;
import dagger.internal.Preconditions;
import java.util.Collections;
import java.util.Map;
import javax.inject.Provider;
import retrofit2.Retrofit;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerAppComponent implements AppComponent {
  private final DaggerAppComponent appComponent = this;

  private Provider<FragmentModule_MonetaryRateMenuFragment.MonetaryRateMenuFragmentSubcomponent.Factory> monetaryRateMenuFragmentSubcomponentFactoryProvider;

  private Provider<DialogModule_CalculateBottomSheetFragment.CalculateBottomSheetFragmentSubcomponent.Factory> calculateBottomSheetFragmentSubcomponentFactoryProvider;

  private Provider<App> bindContextProvider;

  private Provider<ExchangeRatesRoomDB> createDBProvider;

  private Provider<ExchangeRatesRoomDao> getDAOProvider;

  private Provider<Retrofit> createRetrofiteProvider;

  private Provider<RetrofiteService> createRetrofiteServiceProvider;

  private Provider<RepositoryExchangeRates> makeRepositoryProvider;

  private Provider<MakeApiUseCase> makeApiUseCaseProvider;

  private Provider<MakeDbDataUseCase> makeDbDataUseCaseProvider;

  private Provider<MonetaryRateMenuFragmentViewModel> monetaryRateMenuFragmentViewModelProvider;

  private Provider<MakeDbSpecificDataUseCase> makeDbSpecificUseCaseProvider;

  private Provider<MakeSingleInsertDbDataUseCase> makeSingleInsertDbDataUseCaseProvider;

  private Provider<MakeDeleteSingeDataInfoDbUseCase> makeDeleteSingeDataInfoDbUseCaseProvider;

  private Provider<BottomSheetViewModel> bottomSheetViewModelProvider;

  private Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> mapOfClassOfAndProviderOfViewModelProvider;

  private Provider<ViewModelFactory> viewModelFactoryProvider;

  private DaggerAppComponent(RetrofiteModule retrofiteModuleParam, RoomModule roomModuleParam,
      RepositoryModule repositoryModuleParam, App bindContextParam) {

    initialize(retrofiteModuleParam, roomModuleParam, repositoryModuleParam, bindContextParam);

  }

  public static AppComponent.Builder builder() {
    return new Builder();
  }

  private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> mapOfClassOfAndProviderOfAndroidInjectorFactoryOf(
      ) {
    return MapBuilder.<Class<?>, Provider<AndroidInjector.Factory<?>>>newMapBuilder(2).put(MonetaryRateMenuFragment.class, (Provider) monetaryRateMenuFragmentSubcomponentFactoryProvider).put(CalculateBottomSheetFragment.class, (Provider) calculateBottomSheetFragmentSubcomponentFactoryProvider).build();
  }

  private DispatchingAndroidInjector<Object> dispatchingAndroidInjectorOfObject() {
    return DispatchingAndroidInjector_Factory.newInstance(mapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), Collections.<String, Provider<AndroidInjector.Factory<?>>>emptyMap());
  }

  @SuppressWarnings("unchecked")
  private void initialize(final RetrofiteModule retrofiteModuleParam,
      final RoomModule roomModuleParam, final RepositoryModule repositoryModuleParam,
      final App bindContextParam) {
    this.monetaryRateMenuFragmentSubcomponentFactoryProvider = new Provider<FragmentModule_MonetaryRateMenuFragment.MonetaryRateMenuFragmentSubcomponent.Factory>() {
      @Override
      public FragmentModule_MonetaryRateMenuFragment.MonetaryRateMenuFragmentSubcomponent.Factory get(
          ) {
        return new MonetaryRateMenuFragmentSubcomponentFactory(appComponent);
      }
    };
    this.calculateBottomSheetFragmentSubcomponentFactoryProvider = new Provider<DialogModule_CalculateBottomSheetFragment.CalculateBottomSheetFragmentSubcomponent.Factory>() {
      @Override
      public DialogModule_CalculateBottomSheetFragment.CalculateBottomSheetFragmentSubcomponent.Factory get(
          ) {
        return new CalculateBottomSheetFragmentSubcomponentFactory(appComponent);
      }
    };
    this.bindContextProvider = InstanceFactory.create(bindContextParam);
    this.createDBProvider = DoubleCheck.provider(RoomModule_CreateDBFactory.create(roomModuleParam, bindContextProvider));
    this.getDAOProvider = DoubleCheck.provider(RoomModule_GetDAOFactory.create(roomModuleParam, createDBProvider));
    this.createRetrofiteProvider = DoubleCheck.provider(RetrofiteModule_CreateRetrofiteFactory.create(retrofiteModuleParam));
    this.createRetrofiteServiceProvider = DoubleCheck.provider(RetrofiteModule_CreateRetrofiteServiceFactory.create(retrofiteModuleParam, createRetrofiteProvider));
    this.makeRepositoryProvider = DoubleCheck.provider(RepositoryModule_MakeRepositoryFactory.create(repositoryModuleParam, getDAOProvider, createRetrofiteServiceProvider));
    this.makeApiUseCaseProvider = RepositoryModule_MakeApiUseCaseFactory.create(repositoryModuleParam, makeRepositoryProvider);
    this.makeDbDataUseCaseProvider = RepositoryModule_MakeDbDataUseCaseFactory.create(repositoryModuleParam, makeRepositoryProvider);
    this.monetaryRateMenuFragmentViewModelProvider = MonetaryRateMenuFragmentViewModel_Factory.create(makeApiUseCaseProvider, makeDbDataUseCaseProvider);
    this.makeDbSpecificUseCaseProvider = RepositoryModule_MakeDbSpecificUseCaseFactory.create(repositoryModuleParam, makeRepositoryProvider);
    this.makeSingleInsertDbDataUseCaseProvider = RepositoryModule_MakeSingleInsertDbDataUseCaseFactory.create(repositoryModuleParam, makeRepositoryProvider);
    this.makeDeleteSingeDataInfoDbUseCaseProvider = RepositoryModule_MakeDeleteSingeDataInfoDbUseCaseFactory.create(repositoryModuleParam, makeRepositoryProvider);
    this.bottomSheetViewModelProvider = BottomSheetViewModel_Factory.create(makeDbSpecificUseCaseProvider, makeSingleInsertDbDataUseCaseProvider, makeDeleteSingeDataInfoDbUseCaseProvider);
    this.mapOfClassOfAndProviderOfViewModelProvider = MapProviderFactory.<Class<? extends ViewModel>, ViewModel>builder(2).put(MonetaryRateMenuFragmentViewModel.class, (Provider) monetaryRateMenuFragmentViewModelProvider).put(BottomSheetViewModel.class, (Provider) bottomSheetViewModelProvider).build();
    this.viewModelFactoryProvider = DoubleCheck.provider(ViewModelFactory_Factory.create(mapOfClassOfAndProviderOfViewModelProvider));
  }

  @Override
  public void inject(App app) {
    injectApp(app);
  }

  private App injectApp(App instance) {
    DaggerApplication_MembersInjector.injectAndroidInjector(instance, dispatchingAndroidInjectorOfObject());
    return instance;
  }

  private static final class Builder implements AppComponent.Builder {
    private App bindContext;

    @Override
    public Builder bindContext(App app) {
      this.bindContext = Preconditions.checkNotNull(app);
      return this;
    }

    @Override
    public AppComponent build() {
      Preconditions.checkBuilderRequirement(bindContext, App.class);
      return new DaggerAppComponent(new RetrofiteModule(), new RoomModule(), new RepositoryModule(), bindContext);
    }
  }

  private static final class MonetaryRateMenuFragmentSubcomponentFactory implements FragmentModule_MonetaryRateMenuFragment.MonetaryRateMenuFragmentSubcomponent.Factory {
    private final DaggerAppComponent appComponent;

    private MonetaryRateMenuFragmentSubcomponentFactory(DaggerAppComponent appComponent) {
      this.appComponent = appComponent;
    }

    @Override
    public FragmentModule_MonetaryRateMenuFragment.MonetaryRateMenuFragmentSubcomponent create(
        MonetaryRateMenuFragment arg0) {
      Preconditions.checkNotNull(arg0);
      return new MonetaryRateMenuFragmentSubcomponentImpl(appComponent, arg0);
    }
  }

  private static final class CalculateBottomSheetFragmentSubcomponentFactory implements DialogModule_CalculateBottomSheetFragment.CalculateBottomSheetFragmentSubcomponent.Factory {
    private final DaggerAppComponent appComponent;

    private CalculateBottomSheetFragmentSubcomponentFactory(DaggerAppComponent appComponent) {
      this.appComponent = appComponent;
    }

    @Override
    public DialogModule_CalculateBottomSheetFragment.CalculateBottomSheetFragmentSubcomponent create(
        CalculateBottomSheetFragment arg0) {
      Preconditions.checkNotNull(arg0);
      return new CalculateBottomSheetFragmentSubcomponentImpl(appComponent, arg0);
    }
  }

  private static final class MonetaryRateMenuFragmentSubcomponentImpl implements FragmentModule_MonetaryRateMenuFragment.MonetaryRateMenuFragmentSubcomponent {
    private final DaggerAppComponent appComponent;

    private final MonetaryRateMenuFragmentSubcomponentImpl monetaryRateMenuFragmentSubcomponentImpl = this;

    private MonetaryRateMenuFragmentSubcomponentImpl(DaggerAppComponent appComponent,
        MonetaryRateMenuFragment arg0Param) {
      this.appComponent = appComponent;


    }

    @Override
    public void inject(MonetaryRateMenuFragment arg0) {
      injectMonetaryRateMenuFragment(arg0);
    }

    private MonetaryRateMenuFragment injectMonetaryRateMenuFragment(
        MonetaryRateMenuFragment instance) {
      DaggerFragment_MembersInjector.injectAndroidInjector(instance, appComponent.dispatchingAndroidInjectorOfObject());
      MonetaryRateMenuFragment_MembersInjector.injectViewModelFactory(instance, appComponent.viewModelFactoryProvider.get());
      return instance;
    }
  }

  private static final class CalculateBottomSheetFragmentSubcomponentImpl implements DialogModule_CalculateBottomSheetFragment.CalculateBottomSheetFragmentSubcomponent {
    private final DaggerAppComponent appComponent;

    private final CalculateBottomSheetFragmentSubcomponentImpl calculateBottomSheetFragmentSubcomponentImpl = this;

    private CalculateBottomSheetFragmentSubcomponentImpl(DaggerAppComponent appComponent,
        CalculateBottomSheetFragment arg0Param) {
      this.appComponent = appComponent;


    }

    @Override
    public void inject(CalculateBottomSheetFragment arg0) {
      injectCalculateBottomSheetFragment(arg0);
    }

    private CalculateBottomSheetFragment injectCalculateBottomSheetFragment(
        CalculateBottomSheetFragment instance) {
      CalculateBottomSheetFragment_MembersInjector.injectViewModelFactory(instance, appComponent.viewModelFactoryProvider.get());
      CalculateBottomSheetFragment_MembersInjector.injectAndroidInjector(instance, appComponent.dispatchingAndroidInjectorOfObject());
      return instance;
    }
  }
}
