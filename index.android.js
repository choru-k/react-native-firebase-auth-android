import React from 'react-native';

const ReactNativeFirebaseAuthAndroid = React.NativeModules.ReactNativeFirebaseAuthAndroid;

export default {
  createUserWithEmailAndPassword: (email, pw, onSuccess, onFail) => {
    return ReactNativeFirebaseAuthAndroid.createUserWithEmailAndPassword(email, pw, onSuccess, onFail);
  },
  signInWithEmailAndPassword: (email, pw, onSuccess, onFail) => {
    return ReactNativeFirebaseAuthAndroid.signInWithEmailAndPassword(email, pw, onSuccess, onFail);
  },
  getCurrentUser: (onSuccess, onFail) => {
  	return ReactNativeFirebaseAuthAndroid.getCurrentUser(onSuccess, onFail);
  },
  sendPasswordResetEmail: (email, onSuccess, onFail) => {
  	return ReactNativeFirebaseAuthAndroid.sendPasswordResetEmail(email, onSuccess, onFail);
  },
  googleLogin: (IdToken, onSuccess, onFail) => {
  	return ReactNativeFirebaseAuthAndroid.googleLogin(IdToken, onSuccess, onFail);
  },
  facebookLogin: (token, onSuccess, onFail) => {
  	return ReactNativeFirebaseAuthAndroid.facebookLogin(token, onSuccess, onFail);
  }
};
