package com.feng.meter;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.stereotype.Component;

@Component
public class MyMeter implements MeterBinder {

    public Counter counter1;
    public Counter counter2;
    public Counter counter3;

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        this.counter1 = Counter.builder("com.feng.test").tag("name","feng").description("aaa").register(meterRegistry);
        this.counter2 = Counter.builder("com.feng.test").tag("name","feng").description("bbb").register(meterRegistry);
        this.counter3 = Counter.builder("com.feng.test").tag("name","feng").description("ccc").register(meterRegistry);

        Gauge.builder("gauge feng", Math::random).tag("a","100").register(meterRegistry);
    }
}
