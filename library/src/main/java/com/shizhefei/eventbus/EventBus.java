/**
 * Copyright 2015 shizhefei（LuckyJayce）
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shizhefei.eventbus;

import android.app.Activity;

import java.util.WeakHashMap;

public class EventBus {

    private static EventHandler eventPoster;

    private static EventHandler getDefault() {
        if (eventPoster == null) {
            synchronized (EventBus.class) {
                if (eventPoster == null) {
                    eventPoster = new EventHandler();
                }
            }
        }
        return eventPoster;
    }

    public static <EVENT extends IEvent> EVENT get(Class<EVENT> service) {
        return getDefault().get(service);
    }

    public static <EVENT extends IEvent> EVENT get(Class<EVENT> service, boolean sticky) {
        return getDefault().get(service, sticky);
    }

    public static void removeStickyEvent(Class<? extends IEvent> service) {
        getDefault().removeStickyEvent(service);
    }

    public static void removeAllStickyEvent() {
        getDefault().removeAllStickyEvent();
    }

    /**
     * 注册这个对象的所有event接口
     *
     * @param event
     */

    public static void register(IEvent event) {
        getDefault().register(event);
    }

    /**
     * 注销掉这个对象的有event接口
     *
     * @param event
     */

    public static void unregister(IEvent event) {
        getDefault().unregister(event);
    }

    public static boolean isRegister(IEvent event) {
        return getDefault().isRegister(event);
    }

    /**
     * 获取Activity内通信的EventHandler
     *
     * @param activity
     * @return
     */
    public static synchronized IEventHandler withActivity(Activity activity) {
        EventHandler eventBusImp = eventBusImpMap.get(activity);
        if (eventBusImp == null) {
            eventBusImp = new EventHandler();
            eventBusImpMap.put(activity, eventBusImp);
        }
        return eventBusImp;
    }


    private static WeakHashMap<Activity, EventHandler> eventBusImpMap = new WeakHashMap<>();

}
