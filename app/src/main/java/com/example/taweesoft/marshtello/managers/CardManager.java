package com.example.taweesoft.marshtello.managers;

import android.content.Context;

import com.example.taweesoft.marshtello.models.Card;
import com.example.taweesoft.marshtello.models.CardList;
import com.example.taweesoft.marshtello.models.Comment;
import com.example.taweesoft.marshtello.utils.Constants;

import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Manage anything about CardList,Card,Comment.
 * Created by TAWEESOFT on 3/6/16 AD.
 */
public class CardManager {

    /**
     * Add card into cardList.
     * And Save to Realm immediately.
     * @param context
     * @param cardList
     * @param card
     */
    public static void addCard(Context context , final CardList cardList, final Card card){
        Realm realm = Realm.getInstance(context);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                cardList.getCards().add(card);
                Collections.sort(cardList.getCards(), new CardComparator());
            }
        });
    }

    /**
     * Edit card details.
     * @param context
     * @param card
     * @param name
     * @param detail
     * @param tag
     */
    public static void editCard(Context context , final Card card , final String name,final String detail,final int tag){
        Realm realm = Realm.getInstance(context);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                card.setName(name);
                card.setDetail(detail);
                card.setTag(tag);
            }
        });
    }

    /**
     * Sort all cards in cardList.
     * @param context
     * @param cardList
     */
    public static void sortCard(Context context , final CardList cardList){
        Realm.getInstance(context).executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Collections.sort(cardList.getCards(), new CardComparator());
            }
        });
    }

    /**
     * Set cardList's name.
     * @param context
     * @param cardList
     * @param name
     */
    public static void setCardListName(Context context , final CardList cardList , final String name){
        Realm.getInstance(context).executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                cardList.setName(name);
            }
        });
    }

    public static void addCardList(Context context, final CardList cardList){
        Realm.getInstance(context).executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Constants.cardLists.add(cardList);
            }
        });
    }

    public static void addComment(Context context , final Card card , final Comment comment){
        Realm.getInstance(context).executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                card.getComments().add(comment);
            }
        });
    }

    public static void removeComment(Context context, final RealmList<Comment> comments ,final int position){
        Realm.getInstance(context).executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                comments.remove(position);
            }
        });
    }

    public static void removeCard(Context context , final RealmList<Card> cards , final int position){
        Realm.getInstance(context).executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                cards.remove(position);
            }
        });
    }

    /**
     * Remove CardList
     */
    public static void removeCardList(Context context , final RealmList<CardList> cardLists , final int position){
        Constants.fragmentList.remove(position);
        Realm.getInstance(context).executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                cardLists.remove(position);
            }
        });
    }

    /**
     * Clear all cards in a CardList
     */
    public static void clearAllCard(Context context , final CardList cardList){
        Realm.getInstance(context).executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                cardList.getCards().clear();
            }
        });
    }

    /**
     * Rename card list.
     */
    public static void renameCardList(Context context , final CardList cardList , final String name){
        Realm.getInstance(context).executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                cardList.setName(name);
            }
        });
    }
}
