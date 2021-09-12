/**
 * Copyright (c) 2010 Yahoo! Inc. Copyright (c) 2017 YCSB contributors. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License. See accompanying
 * LICENSE file.
 */

package site.ycsb.generator;

import java.util.concurrent.ThreadLocalRandom;

public class SkewGenerator extends NumberGenerator {
  private final long lb, ub, interval;

  /**
   * Roll Twice, Pick Better
   */
  public SkewGenerator(long lb, long ub) {
    this.lb = lb;
    this.ub = ub;
    interval = this.ub - this.lb + 1;
  }

  @Override
  public Number nextValue() {
    //Roll Twice, Pick Better
    long first = Math.abs(ThreadLocalRandom.current().nextLong()) % interval  + lb;
    long second = Math.abs(ThreadLocalRandom.current().nextLong()) % interval  + lb;

    if(first > second) {
      setLastValue(first);
      return first;
    } else {
      setLastValue(second);
      return second;
    }
  }

  @Override
  public double mean() {
    throw new UnsupportedOperationException("Can't compute mean of non-stationary distribution!");
  }
}
