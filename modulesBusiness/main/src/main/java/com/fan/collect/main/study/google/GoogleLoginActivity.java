package com.fan.collect.main.study.google;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.ProductDetailsResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.fan.collect.base.BaseVBActivity;
import com.fan.collect.base.utils.LogHelper;
import com.fan.collect.module.main.databinding.ActivityLoginGoogleBinding;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.games.Games;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GoogleLoginActivity extends BaseVBActivity<ActivityLoginGoogleBinding> {
    //com.arkgames.rod
    @Override
    protected void initData() {

    }

// https://juejin.cn/post/7024375507978289166
    // https://developers.google.com/identity/sign-in/android/start-integrating?hl=zh-cn
    private static final int SIGN_LOGIN = 901;
    private GoogleSignInClient mGoogleSignInClient;
    @Override
    public void initBeforeSetContentView() {
        context = this;
        signInClient();
        initgooglePlay();

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

    private void signInClient() {
        if (mGoogleSignInClient == null) {

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions
                    .DEFAULT_GAMES_SIGN_IN)
//                    .requestScopes(Games.SCOPE_GAMES)
                    .requestEmail()
                    .requestServerAuthCode("663360936834-054egccvfuqjtlas7ci09hes2iqc98oc.apps.googleusercontent.com")
                    .build();

          /*  GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions
                    .DEFAULT_SIGN_IN)
                    .requestEmail()
//                    .requestIdToken("663360936834-io9e2s0p5j52mr9edsp1re0vd3cekiec.apps.googleusercontent.com")//android
                    .requestIdToken("663360936834-054egccvfuqjtlas7ci09hes2iqc98oc.apps.googleusercontent.com")//web
//                    .requestServerAuthCode
                    .build();*/
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        }
        GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this);
        LogHelper.i(getTAG(),"lastSignedInAccount:"+lastSignedInAccount);
    }

    @Override
    protected void initView() {


        binding.btnLogin.setOnClickListener(v -> {
            startActivityForResult(mGoogleSignInClient.getSignInIntent(), SIGN_LOGIN);
        });
        binding.btnPay.setVisibility(View.GONE);
        binding.btnPay.setOnClickListener(v -> {
                toGooglePay();
        });
        binding.btnLogout.setOnClickListener(v -> {
            logout();
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
        mGoogleSignInClient.signOut().addOnCompleteListener(this, task -> {
            LogHelper.e(getTAG(),"task:"+task == null?" null":task.getResult()+"");
            Toast.makeText(context,"登出成功",Toast.LENGTH_SHORT).show();
        });
        /*GoogleSignInClient signInClient = GoogleSignIn.getClient(context, GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        signInClient.signOut().addOnCompleteListener(this, task -> {
            LogHelper.e(getTAG(),"task:"+task == null?" null":task.getResult()+"");
            Toast.makeText(context,"登出成功",Toast.LENGTH_SHORT).show();
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case SIGN_LOGIN:
                LogHelper.e(getTAG(),"onActivityResult");
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                if (task == null) {
                    LogHelper.e(getTAG(),"task == null");
                }
                try {

//                    GoogleSignInResult signInResultFromIntent = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//                    GoogleSignInAccount account2 = signInResultFromIntent.getSignInAccount();
//                    LogHelper.e(getTAG(),"account2 Id:" + account2.getId() + "|Email:" + account2.getEmail() + "|IdToken:" + account2.getIdToken());

//                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    LogHelper.e(getTAG(),"account Id:" + account.getId() + "|Email:" + account.getEmail() + "|IdToken:" + account.getIdToken());
                    Toast.makeText(context,"登录成功"+account.getEmail(),Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    LogHelper.e(getTAG()
                            ,"ApiException:" + e.getMessage());
                }

                break;
        }
    }

    @NonNull
    @Override
    protected ActivityLoginGoogleBinding getViewBinding() {
        return ActivityLoginGoogleBinding.inflate(getLayoutInflater());
    }
}
