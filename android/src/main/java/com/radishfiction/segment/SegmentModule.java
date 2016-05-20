import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class SegmentModule extends ReactContextBaseJavaModule {
  public SegmentModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "RNSegment";
  }

  @ReactMethod
  public void setup(String writeKey) {
    Analytics analytics = new Anlytics.Builder(context, writeKey)
      .trackApplicationLifecycleEvents()
      .build();
    Analytics.setSingletonInstance(analytics);
  }

  private <T> void copyElements(ReadableMap map, T target) {
    Iterator it = map.keySetIterator();
    while (it.hasNextKey()) {
      String key = it.nextKey();
      switch (map.getType(key)) {
        case "Boolean":
          target.putValue(traits.getBoolean(key));
          break;
        case "Number": // TODO: What about Double?
          target.putValue(traits.getInt(key));
          break;
        case "String":
          target.putValue(traits.getString(key));
          break;
      }
    }
  }

  @ReactMethod
  public void identity(String userId, ReadableMap traits, ReadableMap options) {
    Traits _traits = new Traits();
    if (traits.hasKey("Email")) {
      _traits.putEmail(traits.getString("Email"));
    }
    if (traits.hasKey("CreatedAt")) {
      _traits.putCreatedAt(traits.getString("CreatedAt"));
    }
    copyElements(traits, _traits);
    Analytics.with(context).identify(userId, _traits, null);
  }

  @ReactMethod
  public void screen(String category, String name, ReadableMap properties, ReadableMap options) {
    Properties _props = new Properties();
    copyElements(properties, _properties);
    Analytics.with(context).screen(category, name, _properties, null);
  }

  @ReactMethod
  public void track(String event, ReadableMap properties, ReadableMap options) {
    Properties _props = new Properties();
    copyElements(properties, _properties);
    Analytics.with(context).track(event, _properties, null);
  }
}
