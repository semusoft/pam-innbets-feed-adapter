package com.pam.sportradar.innbets.kafka;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Transaction {
    private String transactionId;
    private Timestamp timestamp;
    private String action;
    private String info;
}
