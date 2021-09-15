package site.ycsb.generator;

import java.util.HashMap;

public class SkewTester {
  public static void main(String[] args) {
    HashMap<Long, Long> h = new HashMap(100);

    SkewGenerator sg = new SkewGenerator(1,100);

    for (int i = 0; i < 100; i++) {
      Long k = (Long) sg.nextValue();
      Long v = h.get(k);
      if(v == null) {
        h.put((Long) sg.nextValue(), 1L);
      } else {
        h.put((Long) sg.nextValue(),v+1);  
      }

    }


  }
}
