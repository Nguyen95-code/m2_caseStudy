package com.codegym.cms.controller;

import com.codegym.cms.model.Note;
import com.codegym.cms.model.NoteType;
import com.codegym.cms.service.NoteService;
import com.codegym.cms.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class NoteController {
    @Autowired
    NoteService noteService;
    @Autowired
    NoteTypeService noteTypeService;

    @ModelAttribute("noteTypes")
    public Iterable<NoteType> noteTypes(){
        return noteTypeService.findAll();
    }

    @GetMapping("/notes")
    public ModelAndView listNotes(@RequestParam("s") Optional<String> s, @PageableDefault(value = 3) Pageable pageable, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("/note/list");
        Page<Note> notes;
        if(s.isPresent()){
            notes = noteService.findAllByTitleContaining(s.get(), pageable);
            modelAndView.addObject("titleSearch", s.get());
        } else {
            notes = noteService.findAll(pageable);
        }
        List<Integer> notePages = noteService.getNumberPage(notes);
        modelAndView.addObject("notes", notes);
        modelAndView.addObject("notePages", notePages);

        if (request.getParameter("message")!= null){
            modelAndView.addObject("message",request.getParameter("message"));
        }
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }

    @GetMapping("/create-note")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("note", new Note());
        return modelAndView;
    }
    @PostMapping("/create-note")
    public ModelAndView saveNote(@ModelAttribute("note") Note note){
        noteService.save(note);
        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("note", new Note());
        modelAndView.addObject("message", "New note created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-note/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Note note = noteService.findById(id);
        if(note != null) {
            ModelAndView modelAndView = new ModelAndView("/note/edit");
            modelAndView.addObject("note", note);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }
    @PostMapping("/edit-note")
    public ModelAndView updateNote(@ModelAttribute("note") Note note){
        noteService.save(note);
        ModelAndView modelAndView = new ModelAndView("/note/edit");
        modelAndView.addObject("note", note);
        modelAndView.addObject("message", "note updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-note/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Note note = noteService.findById(id);
        if(note != null) {
            ModelAndView modelAndView = new ModelAndView("/note/delete");
            modelAndView.addObject("note", note);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-note")
    public String deleteNote(@ModelAttribute("note") Note note){
        noteService.remove(note.getId());
        return "redirect:notes";
    }

    @GetMapping("view-note/{id}")
    public ModelAndView viewNoteType(@PathVariable("id") Long id){
        Note note = noteService.findById(id);
        if (note!=null){
            ModelAndView modelAndView=new ModelAndView("/note/view");
            modelAndView.addObject("note", note);
            return modelAndView;
        }else {
            ModelAndView modelAndView=new ModelAndView("/error.404");
            return modelAndView;
        }
    }
}
