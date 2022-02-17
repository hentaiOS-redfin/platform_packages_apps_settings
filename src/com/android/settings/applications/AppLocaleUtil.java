/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.applications;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.android.settings.R;
import com.android.settingslib.applications.ApplicationsState.AppEntry;

/** This class provides methods that help dealing with per app locale. */
public class AppLocaleUtil {
    private static final String TAG = AppLocaleUtil.class.getSimpleName();

    /**
     * Decides the UI display of per app locale.
     */
    public static boolean canDisplayLocaleUi(Context context, AppEntry app) {
        return !isDisallowedPackage(context, app.info.packageName)
                && !isSignedWithPlatformKey(context, app.info.packageName)
                && app.hasLauncherEntry;
    }

    private static boolean isDisallowedPackage(Context context, String packageName) {
        final String[] disallowedPackages = context.getResources().getStringArray(
                R.array.config_disallowed_app_localeChange_packages);
        for (String disallowedPackage : disallowedPackages) {
            if (packageName.equals(disallowedPackage)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSignedWithPlatformKey(Context context, String packageName) {
        PackageInfo packageInfo = null;
        PackageManager packageManager = context.getPackageManager();
        ActivityManager activityManager = context.getSystemService(ActivityManager.class);
        try {
            packageInfo = packageManager.getPackageInfoAsUser(
                    packageName, /* flags= */ 0,
                    activityManager.getCurrentUser());
        } catch (PackageManager.NameNotFoundException ex) {
            Log.e(TAG, "package not found: " + packageName);
        }
        if (packageInfo == null) {
            return false;
        }
        return packageInfo.applicationInfo.isSignedWithPlatformKey();
    }
}
