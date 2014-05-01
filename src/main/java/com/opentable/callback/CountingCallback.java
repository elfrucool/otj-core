/**
 * Copyright (C) 2013 Ness Computing, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.opentable.callback;

import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.concurrent.ThreadSafe;

/**
 * A Callback that throws away all objects but counts them as it does so.
 */
@ThreadSafe
public class CountingCallback implements Callback<Object> {

    private final AtomicLong count = new AtomicLong();

    public long getCount() {
        return count.get();
    }

    @Override
    public void call(Object item) throws Exception {
        count.incrementAndGet();
    }
}