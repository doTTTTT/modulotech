package com.dot.modulotech.utils.contactgetter;

import android.util.Log;

import com.dot.modulotech.models.ContactModel;
import com.dot.modulotech.views.main.contacts.ContactViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ContactGetter {
    private static final String TAG = ContactGetter.class.getSimpleName();

    private static ContactGetter contactGetter = null;

    private boolean isRequestDone = false;
    private List<ContactModel> listContacts = new ArrayList<>();
    private ContactModel me;

    private enum Type {
        CONTACTS,
        ME
    }

    private ContactGetter(){}

    public static ContactGetter getInstance(){
        if (contactGetter == null) {
            contactGetter = new ContactGetter();
        }
        return contactGetter;
    }

    public void getMe(Callback<ContactModel> callback){
        if (callback != null) {
            if (isRequestDone) {
                callback.onSuccess(me);
            } else {
                executeRequest(callback, Type.ME);
            }
        }
    }

    public void getList(Callback<List<ContactModel>> callback) {
        if (callback != null) {
            if (isRequestDone) {
                callback.onSuccess(listContacts);
            } else {
                executeRequest(callback, Type.CONTACTS);
            }
        }
    }

    private void executeRequest(final Callback callback, final Type type) {
        try {
            new GetContact(new URL("http://www.storage42.com/contacts.json"), new ContactCallback() {
                @Override
                public void onError(String second) {
                    if (callback != null) { callback.onError(second); }
                }

                @Override
                public void onSucces(String first) {
                    parseResponse(first);

                    switch (type) {
                        case CONTACTS:
                            callback.onSuccess(listContacts);
                            break;
                        case ME:
                            callback.onSuccess(me);
                            break;
                    }
                }
            }).execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void parseResponse(String response) {
        try {
            JSONObject object = new JSONObject(response);

            if (object.has("contacts")) {
                parseContacts(object.getJSONArray("contacts"));
            }
            if (object.has("me")) {
                me = parseContact(object.getJSONObject("me"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseContacts(JSONArray contacts) throws JSONException{
        listContacts.clear();
        for (int i = 0; i < contacts.length(); i++) {
            listContacts.add(parseContact((JSONObject) contacts.get(i)));
        }
    }

    private ContactModel parseContact(JSONObject jsonObject) throws JSONException {
        ContactModel model = new ContactModel();

        model.Gender = jsonObject.has("gender") ? jsonObject.getString("gender") : "";
        if (jsonObject.has("name")) {
            JSONObject name = jsonObject.getJSONObject("name");

            model.FirstName = name.has("first") ? name.getString("first") : "";
            model.LastName = name.has("last") ? name.getString("last") : "";
            model.Title = name.has("title") ? name.getString("title") : "";
        }
        if (jsonObject.has("location")) {
            JSONObject location = jsonObject.getJSONObject("location");

            model.Street = location.has("street") ? location.getString("street") : "";
            model.City = location.has("city") ? location.getString("city") : "";
            model.State = location.has("state") ? location.getString("state") : "";
            model.Zip = location.has("zip") ? location.getString("zip") : "";
        }
        model.Email = jsonObject.has("email") ? jsonObject.getString("email") : "";
        model.UserName = jsonObject.has("username") ? jsonObject.getString("username") : "";
        model.Password = jsonObject.has("password") ? jsonObject.getString("password") : "";
        model.Phone = jsonObject.has("phone") ? jsonObject.getString("phone") : "";
        model.Cell = jsonObject.has("cell") ? jsonObject.getString("cell") : "";
        model.SSN = jsonObject.has("SSN") ? jsonObject.getString("SSN") : "";
        if (jsonObject.has("picture")) {
            JSONObject picture = jsonObject.getJSONObject("picture");

            model.LargePic = picture.has("large") ? picture.getString("large") : "";
            model.MeduimPic = picture.has("medium") ? picture.getString("medium") : "";
            model.Thumbnail = picture.has("thumbnail") ? picture.getString("thumbnail") : "";
        }

        return model;
    }

    public interface Callback<T> {
        void onSuccess(T success);

        void onError(String error);
    }
}
