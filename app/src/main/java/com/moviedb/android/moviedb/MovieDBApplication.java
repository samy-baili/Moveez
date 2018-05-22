package com.moviedb.android.moviedb;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MovieDBApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build());
    }
}
