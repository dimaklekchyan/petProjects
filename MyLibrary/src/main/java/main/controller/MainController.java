package main.controller;

import main.model.Book;
import main.model.User;
import main.repository.BookRepository;
import main.repository.UserRepository;
import main.repository.auxiliaryRepository.BookWhichUserFinishedRepository;
import main.repository.auxiliaryRepository.BookWhichUserIsReadingRepository;
import main.repository.auxiliaryRepository.BookWhichUserWantToReadRepository;
import main.repository.auxiliaryRepository.UsersBookRepository;
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

        List<Book> booksWhichUserWantToRead = userRepository.findByUsername(user.getUsername()).getBooksWhichUserWantToRead();
        List<Book> booksWhichUserIsReading = userRepository.findByUsername(user.getUsername()).getBooksWhichUserIsReading();
        List<Book> booksWhichUserFinished = userRepository.findByUsername(user.getUsername()).getBooksWhichUserFinished();


        if((title != null && !title.isEmpty()) || (author != null && !author.isEmpty())){
            booksWhichUserWantToRead = bookWhichUserWantToReadRepository.findByTitleOrAuthor(title, author, user.getId());
            booksWhichUserIsReading = bookWhichUserIsReadingRepository.findByTitleOrAuthor(title, author, user.getId());
            booksWhichUserFinished = bookWhichUserFinishedRepository.findByTitleOrAuthor(title, author, user.getId());
        }

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
            @RequestParam String newList,
            Model model){
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
