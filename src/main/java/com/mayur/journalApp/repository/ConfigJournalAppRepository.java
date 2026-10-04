package com.mayur.journalApp.repository;

import com.mayur.journalApp.entity.ConfigJournalApp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigJournalAppRepository extends JpaRepository<ConfigJournalApp,Long> {

}
