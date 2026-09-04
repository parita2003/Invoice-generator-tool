package com.parita.notification.repository;

import com.parita.notification.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemplateRepository
        extends JpaRepository<Template, Long> {

    Optional<Template> findByTemplateCode(
            String templateCode);
}