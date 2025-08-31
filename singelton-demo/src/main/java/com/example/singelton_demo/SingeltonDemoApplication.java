
package com.example.singelton_demo;            // Put this class in the project’s base package.

import org.springframework.boot.SpringApplication;  // Boot launcher class.
import org.springframework.boot.autoconfigure.SpringBootApplication; // Enables auto-config & component scan.

@SpringBootApplication                        // Meta-annotation: @Configuration + @EnableAutoConfiguration + @ComponentScan
public class SingeltonDemoApplication {       // App entry point class (public, so JVM can reach it)

	public static void main(String[] args) {  // Standard Java main method
		SpringApplication.run(                // Bootstraps Spring context, starts embedded Tomcat, etc.
				SingeltonDemoApplication.class,  // Primary source for component scan (this package and subpackages)
				args);                        // Pass through CLI args
	}
}

//http://localhost:8080/singleton/classic/next
//
//http://localhost:8080/singleton/spring/next
//
//http://localhost:8080/singleton/prototype/next
//
//http://localhost:8080/singleton/inspect
