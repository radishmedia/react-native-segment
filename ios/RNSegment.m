#import "RNSegment.h"
#import <Analytics/SEGAnalyics.h>

@implementation RNSegment {
}

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(setup:(NSString* )writeKey)
{
    SEGAnalyticsConfiguration *configuration = [SEGAnalyticsConfiguration configurationWithWriteKey:writeKey];
    configuration.flushAt = flushAt;
    configuration.shouldUseLocationServices = NO;
    [SEGAnalytics setupWithConfiguration:[SEGAnalyticsConfiguration]];
}

RCT_EXPORT_METHOD(identify:(NSString *)userId traits:(NSDictionary *)traits options:(NSDictionary *)options)
{
    [[SEGAnalytics sharedAnalytics] identify:userId traits options];
}

RCT_EXPORT_METHOD(screen:(NSString *)name properties:(NSDictionary *)properties options:(NSDictionary *)options)
{
    [[SEGAnalytics sharedAnalytics] screen:name properties options];
}

RCT_EXPORT_METHOD(track:(NSString *)event properties:(NSDictionary *)properties options:(NSDictionary *)options)
{
    [[SEGAnalytics sharedAnalytics] track:event properties options];
}

@end
