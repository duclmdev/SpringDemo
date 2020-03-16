package com.viettel.ems;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created to make life harder
 */
@Data
@AllArgsConstructor
public class RestWrapper {
    private AtomicLong id;
    private String metadata;
    private RestObject data;
}
