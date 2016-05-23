#import "RNSegment.h"
#import "SEGAnalytics.h"

@implementation RNSegment {
}

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(setup:(NSString* )writeKey flushQueueSize:(int)flushQueueSize)
{
  SEGAnalyticsConfiguration *configuration = [SEGAnalyticsConfiguration configurationWithWriteKey:writeKey];
  configuration.flushAt = flushQueueSize;
  configuration.shouldUseLocationServices = NO;
  [SEGAnalytics setupWithConfiguration:configuration];
}

RCT_EXPORT_METHOD(identify:(NSString *)userId traits:(NSDictionary *)traits options:(NSDictionary *)options)
{
  [[SEGAnalytics sharedAnalytics] identify:userId traits:traits options:options];
}

RCT_EXPORT_METHOD(identify:(NSString *)userId traits:(NSDictionary *)traits)
{
  [[SEGAnalytics sharedAnalytics] identify:userId traits:traits];
}

RCT_EXPORT_METHOD(identify:(NSString *)userId)
{
  [[SEGAnalytics sharedAnalytics] identify:userId];
}

RCT_EXPORT_METHOD(screen:(NSString *)name properties:(NSDictionary *)properties options:(NSDictionary *)options)
{
  [[SEGAnalytics sharedAnalytics] screen:name properties:properties options:options];
}

RCT_EXPORT_METHOD(screen:(NSString *)name properties:(NSDictionary *)properties)
{
  [[SEGAnalytics sharedAnalytics] screen:name properties:properties];
}

RCT_EXPORT_METHOD(screen:(NSString *)name)
{
  [[SEGAnalytics sharedAnalytics] screen:name];
}

RCT_EXPORT_METHOD(track:(NSString *)event properties:(NSDictionary *)properties options:(NSDictionary *)options)
{
  [[SEGAnalytics sharedAnalytics] track:event properties:properties options:options];
}

RCT_EXPORT_METHOD(track:(NSString *)event properties:(NSDictionary *)properties)
{
  [[SEGAnalytics sharedAnalytics] track:event properties:properties];
}

RCT_EXPORT_METHOD(track:(NSString *)event)
{
  [[SEGAnalytics sharedAnalytics] track:event];
}

@end
