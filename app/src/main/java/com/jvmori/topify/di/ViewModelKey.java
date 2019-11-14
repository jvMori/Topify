package com.jvmori.topify.di;


import androidx.lifecycle.ViewModel;
import dagger.MapKey;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;

import java.lang.annotation.*;

@MapKey
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}
