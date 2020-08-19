/*
 * Copyright 2017 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.kayenta.configbin.service;

import com.squareup.okhttp.RequestBody;
import retrofit.client.Response;
import retrofit.http.*;

public interface ConfigBinRemoteService {

  @GET("/browse/{ownerApp}/{configType}?pageSize=1000&showDeleted=false")
  String list(
      @Path("ownerApp") String ownerApp,
      @Path("configType") String configType,
      @Query("nextPageId") String nextPageId);

  @DELETE("/{ownerApp}/{configType}/{configName}?user=kayenta&comment=spinnaker")
  Response delete(
      @Path("ownerApp") String ownerApp,
      @Path("configType") String configType,
      @Path("configName") String configName);

  @GET("/{ownerApp}/{configType}/{configName}")
  String get(
      @Path("ownerApp") String ownerApp,
      @Path("configType") String configType,
      @Path("configName") String configName);

  @POST("/{ownerApp}/{configType}/{configName}?overwrite=true&user=kayenta&comment=spinnaker")
  Response post(
      @Path("ownerApp") String ownerApp,
      @Path("configType") String configType,
      @Path("configName") String configName,
      @Body RequestBody config);
}
