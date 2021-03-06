package com.example.taweesoft.marshtello.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Data Wrapper model.
 * Objective : To wrap all cardlist together and save into Realm Database.
 *             So no need to save card list one by one.
 * Created by TAWEESOFT on 3/1/16 AD.
 */
public class DataWrapper extends RealmObject {
    private RealmList<CardList> cardListRealmList;


    public DataWrapper() {
    }

    public DataWrapper(RealmList<CardList> cardListRealmList) {
        this.cardListRealmList = cardListRealmList;
    }

    public RealmList<CardList> getCardListRealmList() {
        return cardListRealmList;
    }

    public void setCardListRealmList(RealmList<CardList> cardListRealmList) {
        this.cardListRealmList = cardListRealmList;
    }


}
