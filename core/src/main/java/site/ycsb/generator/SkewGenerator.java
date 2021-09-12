package site.ycsb.generator;

import java.util.concurrent.ThreadLocalRandom;

public class SkewGenerator extends NumberGenerator {
  private final long lb, ub, interval;

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

    if( first > second ) {
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
