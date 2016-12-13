/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.beam.runners.dataflow;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for {@link DataflowRunnerInfo}.
 */
public class DataflowRunnerInfoTest {

  @Test
  public void getDataflowRunnerInfo() throws Exception {
    DataflowRunnerInfo info = DataflowRunnerInfo.getDataflowRunnerInfo();

    String version = info.getEnvironmentMajorVersion();
    // Validate major version is a number
    assertTrue(
        String.format("Environment major version number %s is not a number", version),
        version.matches("\\d+"));

    // Validate container images contain gcr.io
    assertThat(
        "batch worker harness container image invalid",
        info.getBatchWorkerHarnessContainerImage(),
        containsString("gcr.io"));
    assertThat(
        "streaming worker harness container image invalid",
        info.getStreamingWorkerHarnessContainerImage(),
        containsString("gcr.io"));
  }
}
