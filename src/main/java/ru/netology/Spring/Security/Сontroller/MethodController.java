package ru.netology.Spring.Security.Сontroller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/method")
public class MethodController {

    @GetMapping("/read")
    @Secured("ROLE_READ")
    public String read() {
        return "вам доступна функция Read";
    }

    @GetMapping("/write")
    @RolesAllowed("WRITE")
    public String write() {
        return "вам доступна функция Write";
    }

    @GetMapping("/delete")
    @Secured("ROLE_DELETE")
    public String delete() {
        return "вам доступна функция Delete";
    }

    @GetMapping("/delete-or-write")
    @PreAuthorize("hasAnyAuthority('DELETE','WRITE')")
    public String deleteOrWrite() {
        return "вам доступна функция Delete или Write";
    }
}
