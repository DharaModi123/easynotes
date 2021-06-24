package com.example.easynotes.service;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.Content;
import com.example.easynotes.repository.NoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    ModelMapper mapper;


    public List<Note> getAllNotes()
    {
        List<Note> notes= new ArrayList<>();
        return noteRepository.findAll();

    }

    public Content getNote(Long noteId)
    {
       // return noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        return noteRepository.findByNative(noteId);
    }

     public Note getNote(String title)
    {
        return noteRepository.findByTitle(title).orElseThrow(() -> new ResourceNotFoundException("Note", "title", title));
    }

     public Content getNoteByContent(String content)
    {
        return noteRepository.findByContent(content);
    }

    /*
    public Note getNoteByIdAndTitle(Long noteId, String title)
    {
        return noteRepository.findByIdAndTitle(noteId,title);
    }*/


    public Note addNote(Note note) {

        noteRepository.save(note);
        return note;
    }


   /*public void deleteNote(Long noteId) {
        //notes.removeIf(t -> t.getId().equals(id));
           noteRepository.deleteById(noteId);
    }*/


    public Note updateNote(Note note) {

        return noteRepository.save(note);
    }

    public void deleteNote(Note note) {

        noteRepository.deleteById(note.getId());
    }
}
