package com.parita.notification.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "templates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Template {

    @Id
    private Long id;

    private String templateCode;

    private String subject;

    private String body;
}