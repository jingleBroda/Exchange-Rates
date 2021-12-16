// Generated by Dagger (https://dagger.dev).
package com.example.exchangerates.app.di.module;

import com.example.exchangerates.app.App;
import com.example.exchangerates.data.room.ExchangeRatesRoomDB;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class RoomModule_CreateDBFactory implements Factory<ExchangeRatesRoomDB> {
  private final RoomModule module;

  private final Provider<App> appProvider;

  public RoomModule_CreateDBFactory(RoomModule module, Provider<App> appProvider) {
    this.module = module;
    this.appProvider = appProvider;
  }

  @Override
  public ExchangeRatesRoomDB get() {
    return createDB(module, appProvider.get());
  }

  public static RoomModule_CreateDBFactory create(RoomModule module, Provider<App> appProvider) {
    return new RoomModule_CreateDBFactory(module, appProvider);
  }

  public static ExchangeRatesRoomDB createDB(RoomModule instance, App app) {
    return Preconditions.checkNotNullFromProvides(instance.createDB(app));
  }
}