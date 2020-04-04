package serg.chuprin.finances.core.impl.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import serg.chuprin.finances.core.api.di.provider.CoreDependenciesProvider
import serg.chuprin.finances.core.api.di.provider.CoreNavigationProvider
import serg.chuprin.finances.core.api.di.scopes.AppScope
import serg.chuprin.finances.core.impl.di.module.CoreDataSourceModule
import serg.chuprin.finances.core.impl.di.module.CoreGatewaysModule
import serg.chuprin.finances.core.impl.di.module.CoreRepositoriesModule

/**
 * Created by Sergey Chuprin on 02.04.2020.
 */
@AppScope
@Component(
    modules = [
        CoreGatewaysModule::class,
        CoreDataSourceModule::class,
        CoreRepositoriesModule::class
    ],
    // Navigation is implemented in :app module.
    dependencies = [CoreNavigationProvider::class]
)
interface CoreDependenciesComponent : CoreDependenciesProvider {

    companion object {

        private lateinit var component: CoreDependenciesComponent

        fun get(): CoreDependenciesProvider = component

        fun init(context: Context, navigationProvider: CoreNavigationProvider) {
            component = DaggerCoreDependenciesComponent
                .builder()
                .context(context)
                .navigationProvider(navigationProvider)
                .build()
        }

    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun navigationProvider(provider: CoreNavigationProvider): Builder

        fun build(): CoreDependenciesComponent

    }

}