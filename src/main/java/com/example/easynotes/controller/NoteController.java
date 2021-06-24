package com.example.easynotes.controller;

import com.example.easynotes.dto.NoteDto;
import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.Content;
import com.example.easynotes.repository.NoteRepository;
import com.example.easynotes.service.NoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteService noteService;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    ModelMapper modelMapper;

    // Get All Notes
    @GetMapping("/notes")
    public List<NoteDto> getAllNotes() {

        //return noteService.getAllNotes();

        return noteService.getAllNotes().stream().map(note -> modelMapper.map(note, NoteDto.class))
                .collect(Collectors.toList());
    }

    // Create a new Note
    /* @PostMapping("/notes")
    public void createNote(@Valid @RequestBody Note note) {

        noteService.addNote(note);
    } */


    @PostMapping("/notes")
    public ResponseEntity<NoteDto> addNote(@RequestBody NoteDto noteDto) {

        // convert DTO to entity
        Note noteRequest = modelMapper.map(noteDto, Note.class);

        Note note = noteService.addNote(noteRequest);

        // convert entity to DTO
        NoteDto noteResponse = modelMapper.map(note, NoteDto.class);

        return new ResponseEntity<NoteDto>(noteResponse, HttpStatus.CREATED);
    }



    // Get a Single Note
    /*@GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long noteId) {
        //return noteService.getNote(noteId);
    } */



    @GetMapping("/notes/{id}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable(name = "id") Long noteId) {

        //convert entity to DTO
        Content note = noteService.getNote(noteId);

        NoteDto noteResponse = modelMapper.map(note,NoteDto.class);
        return ResponseEntity.ok().body(noteResponse);

    }

    //Get a Note By Id And Title
    /*
    @GetMapping("/notes/findByIdAndTitle/{id}")
    public Note getNoteByIdAndTitle(@PathVariable(value = "id") Long noteId,@PathVariable(value="title") String title) {
        return noteService.getNoteByIdAndTitle(noteId,title);
    }*/

    //Get a Note By Title
     @GetMapping("/notes/search/{title}")
    public Note getNoteByTitle(@PathVariable(value = "title") String title) {
        return noteService.getNote(title);

    }

    //Find a Note By Content
    @GetMapping("/notes/findByContent/{content}")
    public Content getNoteByContent(@PathVariable(value = "content") String content) {
        return noteService.getNoteByContent(content);

    }

    //Find content by id
    @GetMapping("/notes/findByNative/{id}")
    public Content getNoteByContent(@PathVariable(value = "id") Long noteId) {
        return noteService.getNote(noteId);

    }



    // Update a Note
    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long noteId,
                           @Valid @RequestBody Note noteDetails) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteService.updateNote(note);
        return updatedNote;
    }

    // Delete a Note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteService.deleteNote(note);

        return ResponseEntity.ok().build();
    }
}