package com.mzw.rabbitmq.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jonathan Meng
 * @date 29/04/2019
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueueMessage {
    private String batchId;
    private String message;
}
