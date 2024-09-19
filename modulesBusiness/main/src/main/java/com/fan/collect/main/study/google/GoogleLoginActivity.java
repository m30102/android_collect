package com.fan.collect.main.study.google;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.fan.collect.base.BaseVBActivity;
import com.fan.collect.base.utils.LogHelper;
import com.fan.collect.module.main.databinding.ActivityLoginGoogleBinding;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
//import com.google.android.gms.games.GamesSignInClient;
//import com.google.android.gms.games.PlayGames;
//import com.google.android.gms.games.PlayGamesSdk;
//import com.google.android.gms.games.GamesSignInClient;
//import com.google.android.gms.games.PlayGames;
//import com.google.android.gms.games.PlayGamesSdk;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.List;

public class GoogleLoginActivity extends BaseVBActivity<ActivityLoginGoogleBinding> {
    private String TAG = getTAG();
    //com.arkgames.rod
    @Override
    protected void initData() {

    }
    // https://firebase.google.com/docs/auth/android/play-games?hl=zh-cn  filebase
    // https://blog.csdn.net/o279642707/article/details/127079092
    // https://juejin.cn/post/7024375507978289166
    // https://developers.google.com/identity/sign-in/android/start-integrating?hl=zh-cn
    // https://console.cloud.google.com/apis/credentials/oauthclient/663360936834-io9e2s0p5j52mr9edsp1re0vd3cekiec.apps.googleusercontent.com?project=expanded-run-410009
    // https://developer.android.google.cn/games/pgs/android/android-signin?authuser=0&hl=ru
    // https://android.stackexchange.com/questions/252314/how-to-sign-out-of-google-play-games
    // summerfff0211@gmail.com  00000wjdmM
    // google游戏
    private static final int SIGN_LOGIN = 901;
    private GoogleSignInClient mGoogleGameSignInClient;
    private GoogleSignInClient mGoogleNormalSignInClient;
    @Override
    public void initBeforeSetContentView() {
        context = this;
        gameSignInClient();
        normalSignInClient();
//        canlogout();
        FirebaseApp.initializeApp(this);
        Log.e(getTAG(),"初始化file");
        initgooglePlay();
    }

    private GoogleApiClient mGoogleApiClient;
// v2 https://developers.google.cn/games/services/android/signin
    private void canlogout() {

     /*   PlayGamesSdk.initialize(this);
        GamesSignInClient gamesSignInClient = PlayGames.getGamesSignInClient(this);
        gamesSignInClient.isAuthenticated().addOnCompleteListener(command -> {
          });
        gamesSignInClient.signIn();
        gamesSignInClient.
*/




        //  https://www.androidp1.com/en/file_1185-dw_apk.html  war dragons 逆向 GIDSignInShim
        this.mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        Log.e(getTAG(),"onConnected:");
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        Log.e(getTAG(),"onConnectionSuspended:"+i);
                    }
                })
                .addOnConnectionFailedListener(connectionResult -> {
                    Log.e(getTAG(),"addOnConnectionFailedListener:"+connectionResult.toString());
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                        .requestEmail().requestId().requestProfile()
                                .build()).build();
        OptionalPendingResult<GoogleSignInResult> googleSignInResultOptionalPendingResult = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        GoogleSignInResult googleSignInResult = googleSignInResultOptionalPendingResult.get();
        mGoogleApiClient.connect();
    }

    private Context context;
    private BillingClient billingClient;
    private void initgooglePlay() {
        billingClient = BillingClient.newBuilder(context)
                .setListener((billingResult, list) -> {

                })
                .enablePendingPurchases()
                .build();
        if(billingClient!=null){
            billingClient.startConnection(new BillingClientStateListener() {
                @Override
                public void onBillingSetupFinished(BillingResult billingResult) {
                    Log.d(getTAG(),"onBillingSetupFinished,"+billingResult);
                    if (billingResult.getResponseCode() ==  BillingClient.BillingResponseCode.OK) {


                    }
                }
                @Override
                public void onBillingServiceDisconnected() {
                    Log.d(getTAG(),"onBillingServiceDisconnected");
                }
            });
        }

    }
    boolean isGameSign = false;
    boolean isGameSignOld = false;

    private void gameSignInClient() {
        /**
         <meta-data android:name="com.google.android.gms.games.APP_ID" android:value="@string/google_play_app_id"/>
         <meta-data android:name="com.google.android.gms.version" android:value="@integer/google_play_services_version"/>
         */
        if (mGoogleGameSignInClient == null) {

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions
                    .DEFAULT_GAMES_SIGN_IN)
//                    .requestScopes(Games.SCOPE_GAMES)
                    .requestScopes(Games.SCOPE_GAMES_SNAPSHOTS)
                    .requestEmail()
//                    .requestServerAuthCode("663360936834-054egccvfuqjtlas7ci09hes2iqc98oc.apps.googleusercontent.com")//com.arkgames.rod
//                    .requestServerAuthCode("833529546451-us9l6mfucc6lu0tegrjhgqk365pam5sh.apps.googleusercontent.com")
//                    .requestIdToken("833529546451-us9l6mfucc6lu0tegrjhgqk365pam5sh.apps.googleusercontent.com")
                    .build();
            mGoogleGameSignInClient = GoogleSignIn.getClient(this, gso);

        }
        GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this);
        LogHelper.i(getTAG(),"lastSignedInAccount:"+lastSignedInAccount);
    }

    private void normalSignInClient(){
        if (mGoogleNormalSignInClient == null) {

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions
                    .DEFAULT_SIGN_IN)
                    .requestEmail()
//                    .requestServerAuthCode("663360936834-054egccvfuqjtlas7ci09hes2iqc98oc.apps.googleusercontent.com")
//                    .requestServerAuthCode("833529546451-us9l6mfucc6lu0tegrjhgqk365pam5sh.apps.googleusercontent.com")
//                    .requestIdToken("833529546451-us9l6mfucc6lu0tegrjhgqk365pam5sh.apps.googleusercontent.com")
                    .build();
            mGoogleNormalSignInClient = GoogleSignIn.getClient(this, gso);
        }
        GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this);
        LogHelper.i(getTAG(),"lastSignedInAccount:"+lastSignedInAccount);
    }

    @Override
    protected void initView() {

        binding.btnLoginNormal.setOnClickListener(v -> {
            isGameSign = false;
            startActivityForResult(mGoogleNormalSignInClient.getSignInIntent(), SIGN_LOGIN);
        });
        binding.btnLoginGame.setOnClickListener(v -> {
            isGameSign = true;
            startActivityForResult(mGoogleGameSignInClient.getSignInIntent(), SIGN_LOGIN);
        });
        binding.btnLoginGamev2.setOnClickListener(v -> {
            isGameSign = true;
            isGameSignOld =true;
            startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient), SIGN_LOGIN);
        });
        binding.btnPay.setVisibility(View.GONE);
        binding.btnPay.setOnClickListener(v -> {
                toGooglePay();
        });
        binding.btnLogout.setOnClickListener(v -> {
            logout();
        });
        binding.btnUmp.setOnClickListener(v -> {
            startActivity(new Intent(this, GoogleUmpActivity.class));
        });
    }

    private void toGooglePay() {

        String productId ="4390_1_1";
        //"com.testgame.099";
        List<QueryProductDetailsParams.Product> productList= new ArrayList<>();
        productList.add(QueryProductDetailsParams.Product.newBuilder()
                .setProductId(productId)
                .setProductType(BillingClient.ProductType.INAPP)
                .build());
        QueryProductDetailsParams queryProductDetailsParams = QueryProductDetailsParams.newBuilder().
                setProductList(productList)
                .build();
        billingClient.queryProductDetailsAsync(queryProductDetailsParams, (billingResult, productDetailsList) -> {
            Log.d(getTAG(),"billingResult，，，:"+billingResult +productDetailsList);
            if(productDetailsList!=null&&productDetailsList.size()>0){
                ProductDetails productDetails=productDetailsList.get(0);
                List<BillingFlowParams.ProductDetailsParams> productDetailsParamsList= new ArrayList<>();
                productDetailsParamsList.add(BillingFlowParams.ProductDetailsParams.newBuilder()
                        .setProductDetails(productDetails)
                        .build());
                BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                        .setProductDetailsParamsList(productDetailsParamsList)
                        .build();
                billingClient.launchBillingFlow((Activity) context, billingFlowParams);
            }
        });
    }
    // 系统->google->google应用的设置->关联应用
    public void logout(){
        if(isGameSign){

            if(isGameSignOld){
               Auth.GoogleSignInApi.signOut(this.mGoogleApiClient).setResultCallback(status -> {
                    LogHelper.e(getTAG(),"signOut:"+status.toString());
                   Toast.makeText(context,"游戏模式 登出成功old",Toast.LENGTH_SHORT).show();
                });
            }else{
                GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
                        GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
                // google play game - 设置 - 您的数据 - 更改游戏账号 -> 每次都询问 - > 或者使用指定的账号
                signInClient.signOut().addOnCompleteListener(this, task -> {
                    LogHelper.e(getTAG(),"task:"+task == null?" null":task.getResult()+"");
                    Toast.makeText(context,"游戏模式 登出成功",Toast.LENGTH_SHORT).show();
                });
            }
        }else{
            mGoogleNormalSignInClient.signOut().addOnCompleteListener(this, task -> {
                LogHelper.e(getTAG(),"task:"+task == null?" null":task.getResult()+"");
                Toast.makeText(context,"普通模式 登出成功",Toast.LENGTH_SHORT).show();
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case SIGN_LOGIN:
                LogHelper.e(getTAG(),"onActivityResult");
                Task<GoogleSignInAccount> taskc = GoogleSignIn.getSignedInAccountFromIntent(data);
                if (taskc == null) {
                    LogHelper.e(getTAG(),"task == null");
                }
                try {

                    GoogleSignInResult signInResultFromIntent = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                    boolean success = signInResultFromIntent.isSuccess();
                    Status status = signInResultFromIntent.getStatus();// https://www.jianshu.com/p/7fcebce03af5 PlayGamesServices log
                    LogHelper.e(TAG,"success:"+success+" status:"+status.toString());
//                    GoogleSignInAccount account2 = signInResultFromIntent.getSignInAccount();
//                    LogHelper.e(getTAG(),"account2 Id:" + account2.getId() + "|Email:" + account2.getEmail() + "|IdToken:" + account2.getIdToken());

                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    LogHelper.e(getTAG(),"account Id:" + account.getId() + "|Email:" + account.getEmail() + "|IdToken:" + account.getIdToken());
                    Toast.makeText(context,"登录成功"+account.getEmail(),Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    LogHelper.e(getTAG()
                            ,"ApiException:" + e.toString());
                }

                break;
        }
    }

    /**
     *
     * 12501: 点击外部取消登录
     * @return
     */
    @NonNull
    @Override
    protected ActivityLoginGoogleBinding getViewBinding() {
        return ActivityLoginGoogleBinding.inflate(getLayoutInflater());
    }
}
