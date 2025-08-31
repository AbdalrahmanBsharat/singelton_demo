package com.example.singelton_demo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;


/**
 * Spring-managed singleton (default scope).
 * Real-life: a process-wide sequence generator, config cache, logger, etc.
 */
@Service                                        // Registers this as a bean in the Spring context (scope = singleton by default)
public class SingletonCounterService {
    private final AtomicLong counter = new AtomicLong(0);  // Each bean has its own counter field
    private final long instanceId = System.identityHashCode(this); // Expose identity for demo

    public long next() {                         // Public method called by controller
        return counter.incrementAndGet();        // Increment shared state inside this single bean
    }

    public long getInstanceId() {                // For debugging/demo
        return instanceId;
    }
}