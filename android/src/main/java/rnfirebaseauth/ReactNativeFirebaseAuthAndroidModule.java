package rnfirebaseauth;

import android.content.Context;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthProvider;

class ReactNativeFirebaseAuthAndroidModule extends ReactContextBaseJavaModule {
    private Context context;
    private FirebaseAuth mAuth;
    private FirebaseUser user;


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
    public void createUserWithEmailAndPassword(final String email, final String password, final Callback onSuccess, final Callback onFail) {
        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = task.getResult().getUser();
                            userCallback(onSuccess);
                        }else{
                            onFail.invoke(task.getException().toString());
                        }
                    }
                });
    }

    @ReactMethod
    public void signInWithEmailAndPassword(final String email, final String password, final Callback onSuccess, final Callback onFail) {
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = task.getResult().getUser();
                            userCallback(onSuccess);
                        }else{
                            onFail.invoke(task.getException().toString());
                        }
                    }
                });
    }

    @ReactMethod
    public void getCurrentUser(final Callback onSuccess, final Callback onFail) {
        user = mAuth.getCurrentUser();
        if(user == null){
            onFail.invoke("not logged in");
        }else{
            userCallback(onSuccess);
        }
    }

    @ReactMethod
    public void sendPasswordResetEmail(String email, final Callback onSuccess, final Callback onFail) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            onSuccess.invoke("complete");
                        }else{
                            onFail.invoke(task.getException().toString());
                        }
                    }
                });
    }

    @ReactMethod
    public void googleLogin(String IdToken, final Callback onSuccess, final Callback onFail) {
        AuthCredential credential = GoogleAuthProvider.getCredential(IdToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = task.getResult().getUser();
                            userCallback(onSuccess);
                        }else{
                            onFail.invoke(task.getException().toString());
                        }
                    }
                });
    }

    @ReactMethod
    public void facebookLogin(String Token, final Callback onSuccess, final Callback onFail) {
        AuthCredential credential = FacebookAuthProvider.getCredential(Token);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = task.getResult().getUser();
                            userCallback(onSuccess);
                        }else{
                            onFail.invoke(task.getException().toString());
                        }
                    }
                });
    }

    public void userCallback(final Callback onSuccess) {

        user.getToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
            @Override
            public void onComplete(@NonNull Task<GetTokenResult> task) {
                WritableMap userMap = Arguments.createMap();

                userMap.putString("token", task.getResult().getToken());
                userMap.putString("email", user.getEmail());
                userMap.putString("uid", user.getUid());
                userMap.putString("provider", user.getProviderId());

                onSuccess.invoke(userMap);
            }
        });

    }
}
