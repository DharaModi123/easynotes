package com.example.easynotes.dto;


public class NoteDto {
     private Long id;
     private String content;

     public NoteDto()
     {

     }

    public NoteDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {

         this.id=id;
    }

    public String getContent() {

         return content;
    }

    public void setContent(String content) {

         this.content = content;
    }
}
