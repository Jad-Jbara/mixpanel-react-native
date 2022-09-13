package com.mixpanel.reactnative;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Dynamic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Arrays;

public class MixpanelReactNativeModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext mReactContext;

    public MixpanelReactNativeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.mReactContext = reactContext;
    }

    @Override
    public String getName() {
        return "MixpanelReactNative";
    }


    @ReactMethod
    public void initialize(String token, boolean trackAutomaticEvents, boolean optOutTrackingDefault, ReadableMap metadata, String serverURL, Promise promise) throws JSONException {
        JSONObject mixpanelProperties = ReactNativeHelper.reactToJSON(metadata);
        AutomaticProperties.setAutomaticProperties(mixpanelProperties);
        MixpanelAPI.getInstance(this.mReactContext, token, optOutTrackingDefault, mixpanelProperties, null, trackAutomaticEvents);
        instance.setServerURL(serverURL);
        promise.resolve(null);
    }

    @ReactMethod
    public void setServerURL(final String token, final boolean trackAutomaticEvents, final String serverURL, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.setServerURL(serverURL);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void setUseIpAddressForGeolocation(final String token, final boolean trackAutomaticEvents, boolean useIpAddressForGeolocation, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.setUseIpAddressForGeolocation(useIpAddressForGeolocation);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void setLoggingEnabled(final String token, final boolean trackAutomaticEvents, boolean enableLogging, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.setEnableLogging(enableLogging);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void hasOptedOutTracking(final String token, final boolean trackAutomaticEvents, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            promise.resolve(instance.hasOptedOutTracking());
        }
    }

    @ReactMethod
    public void optInTracking(final String token, final boolean trackAutomaticEvents, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.optInTracking();
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void optOutTracking(final String token, final boolean trackAutomaticEvents, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.optOutTracking();
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void identify(final String token, final boolean trackAutomaticEvents, final String distinctId, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.identify(distinctId);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void getDistinctId(final String token, final boolean trackAutomaticEvents, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            promise.resolve(instance.getDistinctId());
        }
    }

    @ReactMethod
    public void getDeviceId(final String token, final boolean trackAutomaticEvents, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            promise.resolve(instance.getAnonymousId());
        }
    }

    @ReactMethod
    public void track(final String token, final boolean trackAutomaticEvents, final String eventName, ReadableMap properties, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            JSONObject eventProperties = ReactNativeHelper.reactToJSON(properties);
            AutomaticProperties.appendLibraryProperties(eventProperties);
            instance.track(eventName, eventProperties);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void registerSuperProperties(final String token, final boolean trackAutomaticEvents, ReadableMap properties, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            JSONObject superProperties = ReactNativeHelper.reactToJSON(properties);
            instance.registerSuperProperties(superProperties);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void registerSuperPropertiesOnce(final String token, final boolean trackAutomaticEvents, ReadableMap properties, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            JSONObject superProperties = ReactNativeHelper.reactToJSON(properties);
            instance.registerSuperPropertiesOnce(superProperties);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void unregisterSuperProperty(final String token, final boolean trackAutomaticEvents, String superPropertyName, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.unregisterSuperProperty(superPropertyName);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void union(final String token, final boolean trackAutomaticEvents, String name, ReadableArray value, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            JSONArray propertyValue = ReactNativeHelper.reactToJSON(value);
            instance.getPeople().union(name, propertyValue);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void getSuperProperties(final String token, final boolean trackAutomaticEvents, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            promise.resolve(ReactNativeHelper.convertJsonToMap(instance.getSuperProperties()));
        }
    }

    @ReactMethod
    public void clearSuperProperties(final String token, final boolean trackAutomaticEvents, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.clearSuperProperties();
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void alias(final String token, final boolean trackAutomaticEvents, String alias, String original, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.alias(alias, original);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void reset(final String token, final boolean trackAutomaticEvents, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.reset();
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void flush(final String token, final boolean trackAutomaticEvents, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.flush();
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void timeEvent(final String token, final boolean trackAutomaticEvents, final String eventName, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.timeEvent(eventName);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void eventElapsedTime(final String token, final boolean trackAutomaticEvents, final String eventName, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            promise.resolve(instance.eventElapsedTime(eventName));
        }
    }

    @ReactMethod
    public void set(final String token, final boolean trackAutomaticEvents, ReadableMap properties, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            JSONObject sendProperties = ReactNativeHelper.reactToJSON(properties);
            AutomaticProperties.appendLibraryProperties(sendProperties);
            instance.getPeople().set(sendProperties);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void unset(final String token, final boolean trackAutomaticEvents, String propertyName, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.getPeople().unset(propertyName);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void setOnce(final String token, final boolean trackAutomaticEvents, ReadableMap properties, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            JSONObject sendProperties = ReactNativeHelper.reactToJSON(properties);
            AutomaticProperties.appendLibraryProperties(sendProperties);
            instance.getPeople().setOnce(sendProperties);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void trackCharge(final String token, final boolean trackAutomaticEvents, double charge, ReadableMap properties, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            JSONObject transactionValue = ReactNativeHelper.reactToJSON(properties);
            instance.getPeople().trackCharge(charge, transactionValue);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void clearCharges(final String token, final boolean trackAutomaticEvents, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.getPeople().clearCharges();
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void increment(final String token, final boolean trackAutomaticEvents, ReadableMap properties, Promise promise) {
        Map incrementProperties = ReactNativeHelper.toMap(properties);
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.getPeople().increment(incrementProperties);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void append(final String token, final boolean trackAutomaticEvents, String name, Dynamic value, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.getPeople().append(name, ReactNativeHelper.dynamicToObject(value));
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void deleteUser(final String token, final boolean trackAutomaticEvents, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.getPeople().deleteUser();
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void remove(final String token, final boolean trackAutomaticEvents, String name, Dynamic value, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.getPeople().remove(name, ReactNativeHelper.dynamicToObject(value));
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void trackWithGroups(final String token, final boolean trackAutomaticEvents, String eventName, ReadableMap properties, ReadableMap groups, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            Map eventProperties = ReactNativeHelper.toMap(properties);
            Map eventGroups = ReactNativeHelper.toMap(groups);
            instance.trackWithGroups(eventName, eventProperties, eventGroups);
            promise.resolve(null);
        }
    }


    @ReactMethod
    public void setGroup(final String token, final boolean trackAutomaticEvents, String groupKey, Dynamic groupID, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.setGroup(groupKey, ReactNativeHelper.dynamicToObject(groupID));
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void setGroups(final String token, final boolean trackAutomaticEvents, String groupKey, ReadableArray groupIDs, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.setGroup(groupKey, Arrays.asList(ReactNativeHelper.toArray(groupIDs)));
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void addGroup(final String token, final boolean trackAutomaticEvents, String groupKey, Dynamic groupID, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.addGroup(groupKey, ReactNativeHelper.dynamicToObject(groupID));
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void removeGroup(final String token, final boolean trackAutomaticEvents, String groupKey, Dynamic groupID, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.removeGroup(groupKey, ReactNativeHelper.dynamicToObject(groupID));
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void deleteGroup(final String token, final boolean trackAutomaticEvents, String groupKey, Dynamic groupID, Promise promise) {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.getGroup(groupKey, ReactNativeHelper.dynamicToObject(groupID)).deleteGroup();
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void groupSetProperties(final String token, final boolean trackAutomaticEvents, String groupKey, Dynamic groupID, ReadableMap properties, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            JSONObject sendProperties = ReactNativeHelper.reactToJSON(properties);
            instance.getGroup(groupKey, ReactNativeHelper.dynamicToObject(groupID)).set(sendProperties);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void groupSetPropertyOnce(final String token, final boolean trackAutomaticEvents, String groupKey, Dynamic groupID, ReadableMap properties, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            JSONObject sendProperties = ReactNativeHelper.reactToJSON(properties);
            instance.getGroup(groupKey, ReactNativeHelper.dynamicToObject(groupID)).setOnce(sendProperties);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void groupUnsetProperty(final String token, final boolean trackAutomaticEvents, String groupKey, Dynamic groupID, String propertyName, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.getGroup(groupKey, ReactNativeHelper.dynamicToObject(groupID)).unset(propertyName);
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void groupRemovePropertyValue(final String token, final boolean trackAutomaticEvents, String groupKey, Dynamic groupID, String name, Dynamic value, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            instance.getGroup(groupKey, ReactNativeHelper.dynamicToObject(groupID)).remove(name, ReactNativeHelper.dynamicToObject(value));
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void groupUnionProperty(final String token, final boolean trackAutomaticEvents, String groupKey, Dynamic groupID, String name, ReadableArray values, Promise promise) throws JSONException {
        MixpanelAPI instance = MixpanelAPI.getInstance(this.mReactContext, token, trackAutomaticEvents);
        synchronized (instance) {
            JSONArray arrayValues = ReactNativeHelper.reactToJSON(values);
            instance.getGroup(groupKey, ReactNativeHelper.dynamicToObject(groupID)).union(name, arrayValues);
            promise.resolve(null);
        }
    }
}
