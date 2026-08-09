package com.vttish.minescrap.core;

import com.vttish.minescrap.api.MinescrapBotConfig;
import com.vttish.minescrap.core.entity.EntityModule;
import dagger.BindsInstance;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        EntityModule.class
})
public interface MinescrapBotComponent {
    DefaultMinescrapBot getBot();

    @Component.Factory
    interface Factory {
        MinescrapBotComponent create(@BindsInstance MinescrapBotConfig config);
    }
}
