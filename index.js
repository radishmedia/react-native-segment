"use strict";

const RNSegmentBridge = require("react-native").NativeModules.RNSegment;
console.log('.........................');
console.log(require('react-native').NativeModules);
console.log(RNSegmentBridge);
console.log('.........................');

class RNSegment {
  static setup(writeKey) {
    RNSegmentBridge.setup(writeKey);
  }

  static identify(userId, traits, options) {
    RNSegmentBridge.identify(userId, traits, options);
  }

  static screen(name, properties, options) {
    RNSegmentBridge.screen(name, properties, options);
  }
  static track(event, properties, options) {
    RNSegmentBridge.track(event, properties, options);
  }
}

module.exports = RNSegment;
