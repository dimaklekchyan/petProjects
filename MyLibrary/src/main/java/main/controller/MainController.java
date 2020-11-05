package main.controller;

import main.model.Book;
import main.model.User;
import main.repository.*;
import main.repository.usersBook.BookWhichUserFinishedRepository;
import main.repository.usersBook.BookWhichUserIsReadingRepository;
import main.repository.usersBook.BookWhichUserWantToReadRepository;
import main.repository.usersBook.UsersBookRepository;
import main.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UsersBookRepository usersBookRepository;
    @Autowired
    private BookWhichUserIsReadingRepository bookWhichUserIsReadingRepository;
    @Autowired
    private BookWhichUserFinishedRepository bookWhichUserFinishedRepository;
    @Autowired
    private BookWhichUserWantToReadRepository bookWhichUserWantToReadRepository;
    @Autowired
    private TransferService transferService;


    @GetMapping("/")
    public String greeting(Map<String, Object> model){
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            Model model){

        List<Book> booksWhichUserWantToRead = bookWhichUserWantToReadRepository.findByTitleOrAuthor(title, author, user.getId());
        List<Book> booksWhichUserIsReading = bookWhichUserIsReadingRepository.findByTitleOrAuthor(title, author, user.getId());
        List<Book> booksWhichUserFinished = bookWhichUserFinishedRepository.findByTitleOrAuthor(title, author, user.getId());

        model.addAttribute("wantToRead", booksWhichUserWantToRead);
        model.addAttribute("isReading", booksWhichUserIsReading);
        model.addAttribute("finished", booksWhichUserFinished);
        return "main";
    }

    @PostMapping("/main/{id}")
    public String update(
            @AuthenticationPrincipal User user,
            @RequestParam int id,
            @RequestParam String oldList,
            @RequestParam String newList){
        Book book = bookRepository.findById(id);
        transferService.transferToAnotherList(book, user, oldList, newList);
        return "redirect:/main";
    }

    @DeleteMapping("/main/{id}")
    public String delete(
            @AuthenticationPrincipal User user,
            @RequestParam int id,
            @RequestParam String oldList
            ){
        Book book = bookRepository.findById(id);
        transferService.removeFromList(book, user, oldList);
        return "redirect:/main";
    }
}
