package web.controller;


import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


import java.util.List;
@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

    private final UserService userService;

    public Controller(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/")
    public String showUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("users", user);
        return "new-user";
    }

    @PostMapping("/")
    public String addUser(@ModelAttribute("users")  User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("users", userService.getUserById(id));
        return "show_user";
    }

    @GetMapping("/{id}/replacement")
    public String replacement(Model model, @PathVariable("id") long id) {
        model.addAttribute("users", userService.getUserById(id));
        return "update";
    }

    @PatchMapping ("/{id}")
    public String update(@ModelAttribute("users") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/update";
        }
        userService.updateUser( user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/";

    }

}