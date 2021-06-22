package com.example.easynotes.repository;

import com.example.easynotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> findByTitle(String title);
    Content findByContent(String content);



   @Query(value = "SELECT content FROM notes WHERE id = ?1", nativeQuery = true)
    Content findByNative(long id);


}
