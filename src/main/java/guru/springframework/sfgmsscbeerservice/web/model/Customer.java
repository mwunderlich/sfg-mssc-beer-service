/*
 * Copyright (c) by censhare AG
 */
package guru.springframework.sfgmsscbeerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Plain customer POJO
 *
 * @author Martin Wunderlich
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    private UUID id;
    private String customerName;
}
