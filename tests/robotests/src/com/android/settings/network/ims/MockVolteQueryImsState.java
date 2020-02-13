/*
 * Copyright (C) 2020 The Android Open Source Project
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

package com.android.settings.network.ims;

import android.content.Context;

import com.android.ims.ImsManager;



/**
 * Controller class for mock VoLte status
 */
public class MockVolteQueryImsState extends VolteQueryImsState {

    private Boolean mIsTtyOnVolteEnabled;
    private Boolean mIsProvisionedOnDevice;
    private Boolean mIsEnabledByUser;

    /**
     * Constructor
     *
     * @param context {@link Context}
     * @param subId subscription's id
     */
    public MockVolteQueryImsState(Context context, int subId) {
        super(context, subId);
    }

    public ImsManager getImsManager(int subId) {
        return super.getImsManager(subId);
    }

    public void setIsTtyOnVolteEnabled(boolean enabled) {
        mIsTtyOnVolteEnabled = enabled;
    }

    @Override
    boolean isTtyOnVolteEnabled(int subId) {
        if (mIsTtyOnVolteEnabled != null) {
            return mIsTtyOnVolteEnabled;
        }
        return super.isTtyOnVolteEnabled(subId);
    }

    public void setIsProvisionedOnDevice(boolean isProvisioned) {
        mIsProvisionedOnDevice = isProvisioned;
    }

    @Override
    boolean isProvisionedOnDevice(int subId) {
        if (mIsProvisionedOnDevice != null) {
            return mIsProvisionedOnDevice;
        }
        return super.isProvisionedOnDevice(subId);
    }

    public void setIsEnabledByUser(boolean enabled) {
        mIsEnabledByUser = enabled;
    }

    @Override
    boolean isEnabledByUser(int subId) {
        if (mIsEnabledByUser != null) {
            return mIsEnabledByUser;
        }
        return super.isEnabledByUser(subId);
    }

}
