package com.mayur.journalApp.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Data // includes getter, setter and other annotations as well
public class JournalEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}

//Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields. Will also generate setters for all non-final fields, as well as a constructor (except that no constructor will be generated if any explicitly written constructors already exist).
//Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.
//Complete documentation is found at the project lombok features page for @Data  .
//        See Also:
//Getter, Setter, RequiredArgsConstructor, ToString, EqualsAndHashCode, Value
