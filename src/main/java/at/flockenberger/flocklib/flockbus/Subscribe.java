package at.flockenberger.flocklib.flockbus;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <h1>Subscribe</h1><br>
 * The {@link Subscribe} annotation marks a method of any class to be registered
 * in the {@link FlockBus}.<br>
 * 
 * @author Florian Wagner
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Subscribe
{}
