package com.example.taweesoft.marshtello.utils;

import android.content.Context;

import com.example.taweesoft.marshtello.models.CardList;
import com.example.taweesoft.marshtello.models.DataWrapper;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Storage class.
 * Created by TAWEESOFT on 3/1/16 AD.
 */
public class Storage {
    private static Storage storage = null;
    private static Context context;
    /**
     * Get instance of Storage Object.
     * @return
     */
    public static Storage getInstance(Context context1){
        context = context1;
        if(storage == null)
            storage = new Storage();
        return storage;
    }

    /**
     * Save data into internal storage.
     */
    public void saveData(){
        try{
            if(Constants.result.size()>0)
                Constants.result.clear();
        }catch(IllegalStateException ex){}

        Realm realm = Realm.getInstance(context);
        final DataWrapper dataWrapper = new DataWrapper(Constants.cardLists);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(dataWrapper);
            }
        });

    }

    /**
     * Load data from internal storage.
     */
    public void loadData(){
        Realm realm = Realm.getInstance(context);
        RealmResults<DataWrapper> result = realm.where(DataWrapper.class).findAll();
        if(result.size()>0)
            Constants.cardLists = result.get(0).getCardListRealmList();
        Constants.result = result;
    }

    public void removeAllCardList(){
        Realm.getInstance(context).executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Constants.result.get(0).getCardListRealmList().clear();
            }
        });

    }

    /**
     *
     * @param position = card list position
     */
    public void removeCardList(final int position){
        Constants.fragmentList.remove(position);
        Realm.getInstance(context).executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                DataWrapper dataWrapper = Constants.result.get(0);
                RealmList<CardList> cardLists = dataWrapper.getCardListRealmList();
                cardLists.remove(position);
            }
        });

    }

    /**
        @param position = card list position.
     */
    public void removeAllCard(final int position){
        Realm.getInstance(context).executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                DataWrapper dataWrapper = Constants.result.get(0);
                RealmList<CardList> cardLists = dataWrapper.getCardListRealmList();
                cardLists.get(position).getCards().clear();
            }
        });

    }


}
