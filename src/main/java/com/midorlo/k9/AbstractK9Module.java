package com.midorlo.k9;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;

/**
 * <p>Describes every module's main class.</p>
 *
 * <p>Let's face it. Even for libraries, having a main class massively eases developments.
 * Developers _will_ create one. Leaders can only ever decide if they will committed.</p>
 *
 * <p>So, instead of fighting that, let's utilize it. We actively offer an entry point per module.
 * In return, we can:
 *
 * <ul>
 *      <li>Lock down its invocation by aspect</li>
 *          <li>Have a decent place to collect a module metamap</li>
 * </ul>
 * </p>
 */
@Slf4j
public abstract class AbstractK9Module implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        log.info("Loaded module {}", getClass().getName());
    }
}
