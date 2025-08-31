package com.example.singelton_demo.web;  // Web (controller) layer package

import com.example.singelton_demo.domain.ClassicSingleton;
import com.example.singelton_demo.service.PrototypeCounter;
import com.example.singelton_demo.service.SingletonCounterService;

import org.springframework.beans.factory.ObjectFactory;     // Lazy/provider access to prototype beans
import org.springframework.web.bind.annotation.GetMapping;  // Map GET endpoints
import org.springframework.web.bind.annotation.RestController; // Mark as REST controller (JSON by default)

import java.util.LinkedHashMap;                 // Deterministic map order in JSON
import java.util.Map;

@RestController                                 // Spring MVC controller returning JSON
public class DemoController {

    private final SingletonCounterService springSingleton;   // Inject the singleton-scoped service
    private final ObjectFactory<PrototypeCounter> prototypeFactory; // Factory to fetch NEW prototype instances

    // Constructor injection (preferred). Spring will provide the beans.
    public DemoController(SingletonCounterService springSingleton,
                          ObjectFactory<PrototypeCounter> prototypeFactory) {
        this.springSingleton = springSingleton; // Assign injected singleton bean
        this.prototypeFactory = prototypeFactory; // Assign provider for prototype beans
    }

    @GetMapping("/singleton/spring/next")       // GET endpoint: test Spring singleton
    public Map<String, Object> springSingletonNext() {
        Map<String, Object> res = new LinkedHashMap<>(); // Response body map
        res.put("type", "spring-singleton");     // Tag so you know what you called
        res.put("value", springSingleton.next()); // Increments the one shared counter
        res.put("instanceId", springSingleton.getInstanceId()); // Same id every request
        return res;                               // Spring converts Map→JSON
    }

    @GetMapping("/singleton/classic/next")       // GET endpoint: test classic Java singleton
    public Map<String, Object> classicSingletonNext() {
        ClassicSingleton s = ClassicSingleton.getInstance(); // Access the global instance
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("type", "classic-singleton");
        res.put("value", s.next());              // Increments the global counter
        res.put("instanceId", s.getInstanceId());// Same id every request
        return res;
    }

    @GetMapping("/singleton/prototype/next")     // GET endpoint: test prototype scope
    public Map<String, Object> prototypeNext() {
        PrototypeCounter p = prototypeFactory.getObject(); // NEW instance every call
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("type", "prototype");
        res.put("value", p.next());              // Starts at 1 for this fresh instance
        res.put("instanceId", p.getInstanceId());// Different id each time you hit the endpoint
        return res;
    }

    @GetMapping("/singleton/inspect")            // GET endpoint: compare identities in one shot
    public Map<String, Object> inspect() {
        ClassicSingleton classic = ClassicSingleton.getInstance(); // The one classic instance
        PrototypeCounter p1 = prototypeFactory.getObject();        // Fresh prototype #1
        PrototypeCounter p2 = prototypeFactory.getObject();        // Fresh prototype #2

        Map<String, Object> res = new LinkedHashMap<>();
        res.put("classicSingleton.instanceId", classic.getInstanceId());     // Stable
        res.put("springSingleton.instanceId", springSingleton.getInstanceId());// Stable
        res.put("prototype.instanceId.first", p1.getInstanceId());           // Different
        res.put("prototype.instanceId.second", p2.getInstanceId());          // Different
        return res;
    }
}