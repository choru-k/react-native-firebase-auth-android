# ReactNativeFirebaseAuthAndroid

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
import FBAuth from 'react-native-firebase-auth-android'

FBAuth.createUserWithEmailAndPassword(email, pw, (token)=>{
      this.setState({
        token: token
      })
    })

FBAuth.signInWithEmailAndPassword(email, pw, (token)=>{
      this.setState({
        token: token
      })
    })    
```

## Now only support `createUserWithEmailAndPassword` and `signInWithEmailAndPassword` and support `token` you cannot get userName, uid.