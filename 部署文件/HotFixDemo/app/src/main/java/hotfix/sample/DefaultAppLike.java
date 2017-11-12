package hotfix.sample;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dx168.patchsdk.Listener;
import com.dx168.patchsdk.PatchManager;
import com.dx168.patchsdk.app.SampleApplicationLike;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import hotfix.util.Util;


/**
 * Created by dongyuangui on 2017/8/28.
 */

@DefaultLifeCycle(application = "hotfix.sample.com.BaseApplication",//通过注解，由tinker自动生成MyApplication
        flags = ShareConstants.TINKER_ENABLE_ALL,                 //tinkerFlags
        loaderClass = "com.tencent.tinker.loader.TinkerLoader",   //loaderClassName, 我们这里使用默认
        loadVerifyFlag = false)

public class DefaultAppLike extends SampleApplicationLike {
    private static final String TAG = "DefaultAppLike";

    public DefaultAppLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String appId = "20170907163813557-7443";
        String appSecret = "a94728234232444db6f65b6d18971536";
        // 设置设备id，或者null
        String deviceId = "aabbcc";
        String channel = Util.getApplicationMetaData("HOTFIX_CHANNEL", getApplication());

        PatchManager.getInstance().init(getApplication(), "https://192.168.1.7:8443/hotfix-apis/",
                appId, appSecret, deviceId, channel);
//        PatchManager.getInstance().init(getApplication(), "http://192.168.3.16:8080/hotfix-apis/",
//                appId, appSecret, null, null);

        PatchManager.getInstance().register(new Listener() {
            @Override
            public void onQuerySuccess(String response) {
                Log.d(TAG, "onQuerySuccess response=" + response);
            }

            @Override
            public void onQueryFailure(Throwable e) {
                Log.d(TAG, "onQueryFailure e=" + e);
            }

            @Override
            public void onDownloadSuccess(String path) {
                Log.d(TAG, "onDownloadSuccess path=" + path);
            }

            @Override
            public void onDownloadFailure(Throwable e) {
                Log.d(TAG, "onDownloadFailure e=" + e);
            }

            @Override
            public void onPatchSuccess() {
                Log.d(TAG, "onPatchSuccess");
            }

            @Override
            public void onPatchFailure() {
                Log.d(TAG, "onPatchFailure");
            }

            @Override
            public void onLoadSuccess() {
                Log.d(TAG, "onLoadSuccess");
            }

            @Override
            public void onLoadFailure() {
                Log.d(TAG, "onLoadFailure");
            }
        });
        PatchManager.getInstance().queryAndPatch();
    }
}
