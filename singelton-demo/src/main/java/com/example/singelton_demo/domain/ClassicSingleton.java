package com.example.singelton_demo.domain;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Classic thread-safe Singleton using the "holder" pattern.
 * Real-life idea: a global in-memory counter (e.g., ticket/invoice sequence).
 */
public final class ClassicSingleton {            // 'final' so no subclasses can break the singleton
    private static final AtomicLong COUNTER = new AtomicLong(0); // Shared counter state (one per JVM)
    private final long instanceId;               // Store identity to prove it's the same instance

    private ClassicSingleton() {                 // Private constructor prevents external instantiation
        this.instanceId = System.identityHashCode(this); // Unique number tied to this object instance
    }

    // Lazy-loaded, thread-safe singleton holder (JVM guarantees class init is thread-safe)
    private static class Holder {
        private static final ClassicSingleton INSTANCE = new ClassicSingleton(); // Created on first access
    }

    public static ClassicSingleton getInstance() { // Global access point
        return Holder.INSTANCE;                  // Returns the single instance
    }

    public long next() {                         // Business method: increment and return sequence
        return COUNTER.incrementAndGet();        // Atomic, so safe for concurrent requests
    }

    public long getInstanceId() {                // Helper to show it's one instance across calls
        return instanceId;
    }
}