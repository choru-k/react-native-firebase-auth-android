import React from 'react-native';

const ReactNativeFirebaseAuthAndroid = React.NativeModules.ReactNativeFirebaseAuthAndroid;

export default {
  reactNativeFirebaseAuthAndroid: () => {
    return ReactNativeFirebaseAuthAndroid.reactNativeFirebaseAuthAndroid();
  },
};
