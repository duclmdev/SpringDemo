package com.viettel.ems.fm.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class RestObjectController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public RestWrapper greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        long l = counter.incrementAndGet();
        RestObject object = new RestObject(l, String.format(template, name));
        return new RestWrapper(counter, name, object);
    }
}
