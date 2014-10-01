package json.benchmarks;

import java.util.concurrent.TimeUnit;

import json.Jsonifier;
import json.JsonifierIndyFactory;
import json.ReflectionJsonifierFactory;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
// Hotspot seems to do all its optimizations pretty quickly, so let's save some time.
@Fork(value = 1, warmups = 0)
@Measurement(iterations=5)
@Warmup(iterations = 5)
public class JsonBenchmark {
  private Jsonifier<Bean> handBeanMarshaller = new BeanJsonifier();
  private Jsonifier<Bean> reflectionBeanMarshaller = ReflectionJsonifierFactory.createReflectionJsonifier(Bean.class);
  private Jsonifier<Bean> indyBeanMarshaller = JsonifierIndyFactory.createReflectionMarshaller(Bean.class);

  private Jsonifier<BiggerBean> handBiggerBeanMarshaller = new BiggerBeanJsonifier();
  private Jsonifier<BiggerBean> dumpBiggerBeanMarshaller = BiggerBeanJsonifierDump.makeJsonifier();
  private Jsonifier<BiggerBean> reflectionBiggerBeanMarshaller = ReflectionJsonifierFactory.createReflectionJsonifier(BiggerBean.class);
  private Jsonifier<BiggerBean> indyBiggerBeanMarshaller = JsonifierIndyFactory.createReflectionMarshaller(BiggerBean.class);

  @Benchmark
  public String beanReflection() throws Exception {
    return reflectionBeanMarshaller.marshal(getBean());
  }

  @Benchmark
  public String beanHandCoded() throws Exception {
    return handBeanMarshaller.marshal(getBean());
  }

  @Benchmark
  public String beanFactoryIndy() throws Exception {
    return indyBeanMarshaller.marshal(getBean());
  }

  @Benchmark
  public String biggerBeanReflection() throws Exception {
    return reflectionBiggerBeanMarshaller.marshal(getBiggerBean());
  }

  @Benchmark
  public String biggerBeanHandCoded() throws Exception {
    return handBiggerBeanMarshaller.marshal(getBiggerBean());
  }

  @Benchmark
  public String biggerBeanHandDump() throws Exception {
    return dumpBiggerBeanMarshaller.marshal(getBiggerBean());
  }


  @Benchmark
  public String biggerBeanFactoryIndy() throws Exception {
    return indyBiggerBeanMarshaller.marshal(getBiggerBean());
  }

  public static void main(String[] args) throws Exception {
    new JsonBenchmark().showJson();
  }

  private void showJson() throws Exception {
    System.out.println(beanHandCoded());
    System.out.println(beanReflection());
    System.out.println(beanFactoryIndy());
    System.out.println();
    System.out.println(biggerBeanHandCoded());
    System.out.println(biggerBeanReflection());
    System.out.println(biggerBeanFactoryIndy());
  }

  private Bean[] beans = {
    // for reasons I do not yet understand, if we don't have at least one instance of Bean where at least one of
    // the two field-accessed values are null, then our benchmark runs over three times slower.
    new Bean(null, "goodbye"),
    new Bean("hello", "goodbye"),
    new Bean("up", "down"),
  };

  private BiggerBean[] biggerBeans = {
    // for reasons I do not yet understand, if we don't have at least one instance of BiggerBean where at least one of
    // the two field-accessed values are null, then our benchmark runs over three times slower.
    new BiggerBean("hello", "goodbye", null, "bye"),

    new BiggerBean("hello", "hi", "goodbye", "bye"),
    new BiggerBean("the", "quick", "brown", "fox"),
    new BiggerBean("jumped", "over", "the", "lazy"),
    new BiggerBean("one", "two", "three", "four"),
    new BiggerBean("john", "paul", "george", "ringo"),
    new BiggerBean("summer", "fall", "winter", "guice"),
  };

  int index = 0;
  private Bean getBean() {
    return beans[index++ % beans.length];
  }

  int biggerIndex = 0;
  private BiggerBean getBiggerBean() {
    return biggerBeans[biggerIndex++ % biggerBeans.length];
  }
}
