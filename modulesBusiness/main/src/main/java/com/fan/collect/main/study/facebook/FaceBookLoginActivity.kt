package com.fan.collect.main.study.facebook

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.alibaba.android.arouter.utils.TextUtils
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.fan.collect.base.BaseVBActivity
import com.fan.collect.base.utils.LogHelper
import com.fan.collect.module.main.databinding.ActivityLoginFacebookBinding
import org.json.JSONObject
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


// https://developers.facebook.com/docs/facebook-login/android
// https://github.com/facebook/facebook-android-sdk/tree/main/samples/FBLoginSample
// https://www.jianshu.com/p/0711cab25b43
// https://blog.csdn.net/qq_43540406/article/details/114574661

/**
 * 我的游戏
 * 应用编号 facebook_app_id:1082799829926006
 * fb_login_protocol_scheme
 * 客户端口令 facebook_client_token:46c3c40d21afd8a69317cdc8a2ed49ea
 *
 * keytool -exportcert -alias keystore的别名 -keystore keystore的路径 |openssl sha1 -binary |openssl base64
 * keytool -exportcert -alias name1 -keystore D:\work\android\android_collect\app\keystores\mytest.jks |openssl sha1 -binary |openssl base64
 * 541jcuP3TJGCbgX//5gDLtVf5PM=
 * vNE0rt9Z0VYoamkqZeArBm8u6FU=  //TODO
 *
 * https://blog.csdn.net/qq_43540406/article/details/114574661
 * https://developers.facebook.com/docs/games/acquire/login-for-gaming
 */
class FaceBookLoginActivity : BaseVBActivity<ActivityLoginFacebookBinding>() {
    lateinit var callbackManager: CallbackManager

    override fun initData() {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                    Log.e(TAG, "LoginManager onCancel")
                }

                override fun onError(error: FacebookException) {
                    Log.e(TAG, "LoginManager onError:" + error.message)
                }

                override fun onSuccess(result: LoginResult) {
                    Log.e(TAG, "LoginManager onSuccess:" + result.accessToken.token)

                    /* val request1 = GraphRequest.newMeRequest(result.accessToken,object :GraphRequest.GraphJSONObjectCallback{
                         override fun onCompleted(obj: JSONObject?, response: GraphResponse?) {

                         }
                     })*/
                    val request = GraphRequest.newMeRequest(result.accessToken) { obj, response ->
                        if (obj != null) {
                            val email = obj.optString("email")
                            val first_name = obj.optString("first_name")
                            val last_name = obj.optString("last_name")
                            val uid = result.accessToken.userId;
                            val token = result.accessToken.token
                            Log.e(TAG, "email:" + email)
                            Log.e(TAG, "first_name:" + first_name)
                            Log.e(TAG, "last_name:" + last_name)
                            Log.e(TAG, "uid:" + uid)
                            Log.e(TAG, "token:" + token)
                        } else {
                            Log.e(TAG, "obj is null")
                        }
                    }
                    //这一段也不知道干嘛
                    var parameters = Bundle();
                    parameters.putString(
                        "fields", "id,name,link,gender,birthday,email,picture,locale," +
                                "updated_time,timezone,age_range,first_name,last_name"
                    );
                    request.parameters = parameters
                    request.executeAsync();
                }
            })


//        getKeyHash()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e(TAG, "onActivityResult requestCode:$requestCode resultCode:$resultCode")
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun initView() {
        binding.btnLogin.setOnClickListener {
            val currentAccessToken = AccessToken.getCurrentAccessToken()
            val isLogin = currentAccessToken != null && !currentAccessToken.isExpired
            LogHelper.d(TAG, "isLogin:$isLogin")
            if (isLogin) {
                val token = currentAccessToken?.token;
                LogHelper.d(TAG, "token:$token")
                AccessToken.refreshCurrentAccessTokenAsync(object :
                    AccessToken.AccessTokenRefreshCallback{
                    override fun OnTokenRefreshFailed(exception: FacebookException?) {
                        Log.e(TAG,"OnTokenRefreshFailed:"+exception?.message)
                    }

                    override fun OnTokenRefreshed(accessToken: AccessToken?) {
                        Log.e(TAG,"OnTokenRefreshed:"+accessToken?.token)
                        if(currentAccessToken?.equals(accessToken?.token)!!){
                            Log.e(TAG,"same token")
                        }else{
                            Log.e(TAG,"not same token")
                        }
                    }

                })
            } else {
                LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile"))
            }
        }
    }

    override fun getViewBinding(): ActivityLoginFacebookBinding {
        return ActivityLoginFacebookBinding.inflate(layoutInflater)
    }

    fun getKeyHash() {
        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d(
                    TAG,
                    "KEYHASH------" + Base64.encodeToString(md.digest(), Base64.DEFAULT)
                )
            }
            //
            //vNE0rt9Z0VYoamkqZeArBm8u6FU=
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
    }


}