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
package org.apache.beam.runners.direct;

import java.util.Collection;
import org.apache.beam.runners.direct.DirectRunner.CommittedBundle;
import org.apache.beam.sdk.transforms.AppliedPTransform;
import org.apache.beam.sdk.transforms.PTransform;

/**
 * An executor that schedules and executes {@link AppliedPTransform AppliedPTransforms} for both
 * source and intermediate {@link PTransform PTransforms}.
 */
interface PipelineExecutor {
  /**
   * Starts this executor. The provided collection is the collection of root transforms to
   * initially schedule.
   *
   * @param rootTransforms
   */
  void start(Collection<AppliedPTransform<?, ?, ?>> rootTransforms);

  /**
   * Blocks until the job being executed enters a terminal state. A job is completed after all
   * root {@link AppliedPTransform AppliedPTransforms} have completed, and all
   * {@link CommittedBundle Bundles} have been consumed. Jobs may also terminate abnormally.
   *
   * @throws Throwable whenever an executor thread throws anything, transfers the throwable to the
   *                   waiting thread and rethrows it
   */
  void awaitCompletion() throws Throwable;
}
