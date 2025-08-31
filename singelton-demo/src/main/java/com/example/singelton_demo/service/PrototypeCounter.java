package com.example.singelton_demo.service;   // Service layer

import org.springframework.beans.factory.config.ConfigurableBeanFactory;// Provides SCOPE_PROTOTYPE constant
import org.springframework.context.annotation.Scope;  // Annotation to set bean scope
import org.springframework.stereotype.Component; // Marks as bean (like @Service but generic)
import java.util.concurrent.atomic.AtomicLong;  // Thread-safe counter

/**
 * Prototype-scoped bean. A NEW instance is created each time you request it.
 * Useful when you specifically need fresh state per usage.
 */
@Component                                     // Register as a bean
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)// Change default scope → prototype (new instance per retrieval)
public class PrototypeCounter {
    private final AtomicLong counter = new AtomicLong(0); // Each new instance starts at 0
    private final long instanceId = System.identityHashCode(this); // Different for every new object

    public long next() {                        // Increments this instance's counter only
        return counter.incrementAndGet();
    }

    public long getInstanceId() {               // Expose identity to prove "new each time"
        return instanceId;
    }
}