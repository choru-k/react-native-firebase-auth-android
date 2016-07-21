package rnfirebaseauth;

import android.content.Context;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

class ReactNativeFirebaseAuthAndroidModule extends ReactContextBaseJavaModule {
    private Context context;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Boolean callBackOnlyOne;


    public ReactNativeFirebaseAuthAndroidModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    /**
     * @return the name of this module. This will be the name used to {@code require()} this module
     * from javascript.
     */
    @Override
    public String getName() {
        return "ReactNativeFirebaseAuthAndroid";
    }

    @ReactMethod
    public void createUserWithEmailAndPassword(final String email, final String password, final Callback onLoginSuccess) {
        callBackOnlyOne = true;
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null && callBackOnlyOne) {
                    // User is signed in
                    user.getToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                        @Override
                        public void onComplete(@NonNull Task<GetTokenResult> task) {

                            String token = task.getResult().getToken();

                            if(callBackOnlyOne){
                                mAuth.removeAuthStateListener(mAuthListener);
                                onLoginSuccess.invoke(token);
                                callBackOnlyOne = false;
                            }


                        }
                    });
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {

                                    }else{

                                    }

                                }
                            });

                }
                // ...
            }
        };
        mAuth.addAuthStateListener(mAuthListener);
    }

    @ReactMethod
    public void signInWithEmailAndPassword(final String email, final String password, final Callback onLoginSuccess) {
        callBackOnlyOne = true;
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null && callBackOnlyOne) {
                    // User is signed in
                    user.getToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                        @Override
                        public void onComplete(@NonNull Task<GetTokenResult> task) {

                            String token = task.getResult().getToken();

                            if(callBackOnlyOne){
                                mAuth.removeAuthStateListener(mAuthListener);
                                onLoginSuccess.invoke(token);
                                callBackOnlyOne = false;
                            }


                        }
                    });
                } else {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {

                                    }else{

                                    }

                                }
                            });

                }
                // ...
            }
        };
        mAuth.addAuthStateListener(mAuthListener);
    }
}
