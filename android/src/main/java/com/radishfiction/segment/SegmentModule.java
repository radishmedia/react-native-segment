package com.radishfiction.segment;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.segment.analytics.Analytics;
import com.segment.analytics.Traits;
import com.segment.analytics.Properties;
import com.segment.analytics.ValueMap;

public class SegmentModule extends ReactContextBaseJavaModule {
  private Analytics analytics;

  public SegmentModule(ReactApplicationContext reactContext) {
    super(reactContext);
    analytics = null;
  }

  @Override
  public String getName() {
    return "RNSegment";
  }

  @ReactMethod
  public void setup(String writeKey, int flushQueueSize) {
    try {
      analytics = new Analytics.Builder(getCurrentActivity(), writeKey)
        //.trackApplicationLifecycleEvents()
        .flushQueueSize(flushQueueSize)
        .build();
      Analytics.setSingletonInstance(analytics);
    } catch (java.lang.IllegalStateException ise) {
    }
  }

  private <T extends ValueMap> void copyElements(ReadableMap map, T target) {
    ReadableMapKeySetIterator it = map.keySetIterator();
    while (it.hasNextKey()) {
      String key = it.nextKey();
      switch (map.getType(key)) {
        case Boolean:
          target.putValue(key, new Boolean(map.getBoolean(key)));
          break;
        case Number:
          target.putValue(key, new Double(map.getDouble(key)));
          break;
        case String:
          target.putValue(key, map.getString(key));
          break;
      }
    }
  }

  @ReactMethod
  public void identifyWithTraits(String userId, ReadableMap traits) {
    if (analytics == null) {
      return;
    }
    Traits _traits = null;
    if (traits != null) {
      _traits = new Traits();
      if (traits.hasKey("Email")) {
        _traits.putEmail(traits.getString("Email"));
      }
      if (traits.hasKey("CreatedAt")) {
        _traits.putCreatedAt(traits.getString("CreatedAt"));
      }
      copyElements(traits, _traits);
    }
    Analytics.with(getCurrentActivity()).identify(userId, _traits, null);
  }

  @ReactMethod
  public void identify(String userId) {
    identifyWithTraits(userId, null);
  }

  @ReactMethod
  public void screenWithProps(String name, ReadableMap properties) {
    if (analytics == null) {
      return;
    }
    Properties _properties = null;
    if (properties != null) {
      _properties = new Properties();
      copyElements(properties, _properties);
    }
    Analytics.with(getCurrentActivity()).screen(null, name, _properties, null);
  }

  @ReactMethod
  public void screen(String name) {
    screenWithProps(name, null);
  }

  @ReactMethod
  public void trackWithProps(String event, ReadableMap properties) {
    if (analytics == null) {
      return;
    }
    Properties _properties = null;
    if (properties != null) {
      _properties = new Properties();
      copyElements(properties, _properties);
    }
    Analytics.with(getCurrentActivity()).track(event, _properties, null);
  }

  @ReactMethod
  public void track(String event) {
    trackWithProps(event, null);
  }
}
