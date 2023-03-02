package com.example.springlogindemo.controller;

import com.example.springlogindemo.auth.AppUserService;
import com.example.springlogindemo.auth.RegistrationService;
import com.example.springlogindemo.model.AppUser;
import com.example.springlogindemo.model.ToDo;
import com.example.springlogindemo.model.ToDoRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ToDoController {

    @NonNull
    private ToDoRepository toDoRepository;

    @NonNull
    private AppUserService userDetailsService;

    @NonNull
    private RegistrationService registrationService;


    @GetMapping(path={"/", "/home", "/todolist"})
    public String TodoList(
            Model model,
            Principal principal
    ) {
        AppUser owner = (AppUser) userDetailsService.loadUserByUsername(principal.getName());
        List<ToDo> todos = toDoRepository.findByOwner(owner);
        model.addAttribute("TodoList", todos);

        return "todo_list";
    }

    @GetMapping("/todo")
    public String addToDo(
            Model model
    ) {
        model.addAttribute("newtodo", new ToDoForm());
        return "todo_form";
    }

    @PostMapping("/todo")
    public String saveToDo(
            @ModelAttribute("newtodo")
            @Validated
            ToDoForm todo,
            BindingResult bind,
            Principal principal

    ) {
        if(bind.hasErrors()){
        return "todo_form";
    }
        AppUser appUser = (AppUser) userDetailsService.loadUserByUsername(principal.getName());
        ToDo entity = new ToDo(todo.getDescription());
        entity.setDeadline(todo.getDeadline());
        entity.setDone(false);
        entity.setOwner(appUser);

        toDoRepository.save(entity);

        return "redirect:/home";
    }

    @PostMapping("todo_delete")
    public String deleteToDo(
            @RequestParam("todo_id") Long id,
            Principal user
    ) {
        AppUser appUser = (AppUser) userDetailsService.loadUserByUsername(user.getName());
        toDoRepository.deleteByIdAndOwner(id, appUser);
        return "redirect:/home";
    }

    @PostMapping("todo_done")
    public String updateToDoDone(
            @RequestParam("todo_id") Long id,
            Principal user
    ) {
        AppUser appUser = (AppUser) userDetailsService.loadUserByUsername(user.getName());

        toDoRepository.updateDoneByIdAndOwner(!toDoRepository.getById(id).getDone(), id, appUser);

        return "redirect:/home";
    }

        @GetMapping("/registration")
        public String newRegistration(Model model) {

        model.addAttribute("newregistration", new RegistrationForm());
        return "/registration_form";
        }

        @PostMapping("/registration")
        public String saveRegistration (
                @ModelAttribute("newregistration") RegistrationForm registrationForm
        ) {
            registrationService.register(registrationForm);

            return "redirect:/home";
        }

}
