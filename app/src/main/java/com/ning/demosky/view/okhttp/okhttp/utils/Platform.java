/*
 * Copyright (C) 2013 Square, Inc.
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
package com.ning.demosky.view.okhttp.okhttp.utils;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Platform
{
    private static final Platform PLATFORM = findPlatform();

    public static Platform get()
    {
        L.e(PLATFORM.getClass().toString());
        return PLATFORM;
    }

    private static Platform findPlatform()
    {
        try
        {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT != 0)
            {
                Log.e("wy_ex","www1");
                return new Android();
            }
        } catch (ClassNotFoundException ignored)
        {
        }
        return new Platform();
    }

    public Executor defaultCallbackExecutor()
    {
        Log.e("wy_ex","www2");
        return Executors.newCachedThreadPool();
    }

    public void execute(Runnable runnable)
    {
        Log.e("wy_ex","www3");
        defaultCallbackExecutor().execute(runnable);
    }


    static class Android extends Platform
    {
        @Override
        public Executor defaultCallbackExecutor()
        {
            Log.e("wy_ex","www4");
            return new MainThreadExecutor();
        }

        static class MainThreadExecutor implements Executor
        {
            private final Handler handler = new Handler(Looper.getMainLooper());

            @Override
            public void execute(@NonNull Runnable r)
            {
                handler.post(r);
            }
        }
    }


}
