"use strict";

const RNSegmentBridge = require("react-native").NativeModules.RNSegment;

class RNSegment {
  static setup(writeKey) {
    RNSegmentBridge.setup(writeKey);
  }

  static identify(userId, traits={}, options={}) {
    RNSegmentBridge.identify(userId, traits, options);
  }

  static screen(name, properties={}, options={}) {
    RNSegmentBridge.screen(name, properties, options);
  }
  static track(event, properties={}, options={}) {
    RNSegmentBridge.track(event, properties, options);
  }
}

export default new RNSegment();
