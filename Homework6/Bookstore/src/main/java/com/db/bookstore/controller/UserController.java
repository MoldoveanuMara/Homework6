package com.db.bookstore.controller;

import com.db.bookstore.model.Book;
import com.db.bookstore.model.User;

import com.db.bookstore.service.BookService;
import com.db.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    BookService bookService;

    /*
   1. Pentru prima cerinta m-am folosit de o functie pt a seta rolul de admin, functia "setRole" din UserService, si de fiecare
    data cand se va inregistra un user, o sa i se atribuie si rolul de "client"
    */


     /*
     2. Pentru cerinta 2, am adaugat in baza de data, folosind MySql Workbrench, 5 autori si un user.
     */

    /*
    3.Pentru cerinta3, am verificat folosind consola din browser aparitia cookie-urilor.
     */

    /*
    4. Pentru cerinta 4, pentru a afisa "hello, <username>" ar trebui ca username-ul sa fie afisat cu ajutorul cookie-urilor
        M-am gandit sa fac o functie pentru a afla userId-ul
     */


    /*

     */
    
    @GetMapping("/register")
    public ModelAndView getRegisterForm(){
        ModelAndView modelAndView = new ModelAndView("register-form");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView addUser(User user){

        userService.insertUser(user);
        userService.setRole(user);
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView getLoginForm(){
        ModelAndView modelAndView = new ModelAndView("login-form");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView verifyUser(User user, HttpServletResponse response){
        try {
            User user1 = userService.findByUsernameOrEmailAndPassword(user);
            response.addCookie(new Cookie("id", "" + user1.getId()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/dashboard");

        return modelAndView;



    }

    @GetMapping("/dashboard")
    public ModelAndView getDashBoard(@CookieValue("id") String IdCookie){
        int userId = Integer.parseInt(IdCookie);
        ModelAndView modelAndView=new ModelAndView("dashboard");
        User oldUser = userService.getUserById(userId); // user care este deja logat
        List<Book> listOfBooks = bookService.getBooksList();  //pentru a crea lista dcu toate cartile
        modelAndView.addObject("user",oldUser);
        modelAndView.addObject("bookList","listOfBooks");
        return modelAndView;
    }



}
