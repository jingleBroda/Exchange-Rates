// Generated by Dagger (https://dagger.dev).
package com.example.exchangerates.app.di.module;

import com.example.exchangerates.domain.repository.RepositoryExchangeRates;
import com.example.exchangerates.domain.usecase.MakeDbSpecificDataUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class RepositoryModule_MakeDbSpecificUseCaseFactory implements Factory<MakeDbSpecificDataUseCase> {
  private final RepositoryModule module;

  private final Provider<RepositoryExchangeRates> repositoryProvider;

  public RepositoryModule_MakeDbSpecificUseCaseFactory(RepositoryModule module,
      Provider<RepositoryExchangeRates> repositoryProvider) {
    this.module = module;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public MakeDbSpecificDataUseCase get() {
    return makeDbSpecificUseCase(module, repositoryProvider.get());
  }

  public static RepositoryModule_MakeDbSpecificUseCaseFactory create(RepositoryModule module,
      Provider<RepositoryExchangeRates> repositoryProvider) {
    return new RepositoryModule_MakeDbSpecificUseCaseFactory(module, repositoryProvider);
  }

  public static MakeDbSpecificDataUseCase makeDbSpecificUseCase(RepositoryModule instance,
      RepositoryExchangeRates repository) {
    return Preconditions.checkNotNullFromProvides(instance.makeDbSpecificUseCase(repository));
  }
}