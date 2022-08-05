package com.learningapp.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(length = 1000)
    private String description;

    private String language;

    @ElementCollection
    private List<String> imageUrls;

    @CreationTimestamp
    private LocalDate dateCreated;

    private LocalDateTime datePublished;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Boolean isPublished = Boolean.FALSE;

    @ManyToOne
    private Instructor instructor;

    @ManyToMany
    private List<Student> student;
}
