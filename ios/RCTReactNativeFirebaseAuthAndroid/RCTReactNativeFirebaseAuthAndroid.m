#import "RCTReactNativeFirebaseAuthAndroid.h"

@implementation ReactNativeFirebaseAuthAndroid

RCT_EXPORT_MODULE();

RCT_REMAP_METHOD(reactNativeFirebaseAuthAndroid,
                 resolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject)
{
    resolve(@"Hello World!");
}

@end
