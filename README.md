# ReactNativeFirebaseAuthAndroid

## firebase setup
goto firebase console.
[https://console.firebase.google.com](https://console.firebase.google.com) 
add android in your project and follow the steps from there.


## Install

`npm install --save 'react-native-firebase-auth-android`

`rnpm link 'react-native-firebase-auth-android`

### If you use react-native 0.29
After `rnpm link` edit MainApplication.java file. 

in MainApplication.java
```java
import java.util.Arrays;
import java.util.List;

import rnfirebaseauth.ReactNativeFirebaseAuthAndroidPackage; // <- add this


.
.
.


protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
              new ReactNativeFirebaseAuthAndroidPackage() // <- add this
      );
    }
```


## Usage
```js
import FirebaseAuth from 'react-native-firebase-auth-android'

FirebaseAuth.createUserWithEmailAndPassword(email, pw, (user)=>{
      console.log(user)
    }, (err) => {
      console.log(err)
    })

FirebaseAuth.signInWithEmailAndPassword(email, pw, (user)=>{
      console.log(user)
    }, (err) => {
      console.log(err)
    })
FirebaseAuth.getCurrentUser((user)=>{
      console.log(user)
    }, (err) => {
      console.log(err)
    })
FirebaseAuth.sendPasswordResetEmail(email, (result)=>{
      console.log(result)
    }, (err) => {
      console.log(err)
    })
FirebaseAuth.googleLogin(googleIdToken, (user)=>{
      console.log(user)
    }, (err) => {
      console.log(err)
    })
FirebaseAuth.facebookLogin(facebookToken, (user)=>{
      console.log(user)
    }, (err) => {
      console.log(err)
    })            
```
```js
user = {
  token: String
  email: String,
  uid: String, // not save this in local. 
  provider: String,
}
```
