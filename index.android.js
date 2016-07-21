import React from 'react-native';

const ReactNativeFirebaseAuthAndroid = React.NativeModules.ReactNativeFirebaseAuthAndroid;

export default {
  createUserWithEmailAndPassword: (email, pw, onSuccess) => {
    return ReactNativeFirebaseAuthAndroid.createUserWithEmailAndPassword(email, pw, onSuccess);
  },
  signInWithEmailAndPassword: (email, pw, onSuccess) => {
    return ReactNativeFirebaseAuthAndroid.signInWithEmailAndPassword(email, pw, onSuccess);
  },
};
