package main.controller;

import main.model.Book;
import main.model.User;
import main.model.usersBook.UsersBook;
import main.model.usersBook.UsersBookKey;
import main.repository.BookRepository;
import main.repository.UserRepository;
import main.repository.UsersBookRepository;
import main.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UsersBookRepository usersBookRepository;
    @Autowired
    private TransferService transferService;

    @GetMapping()
    public String searchBooks(@RequestParam(required = false) String title,
                              @RequestParam(required = false) String author,
                              Model model){
        if(title != null){title = title.trim();}
        if(author != null){author = author.trim();}
        Iterable<Book> books = bookRepository.findByTitleOrAuthor(title, author);
        model.addAttribute("books", books);
        return "/search";
    }

    @PostMapping("/{id}")
    public String addBookToAnyList(@AuthenticationPrincipal User user,
                                   @RequestParam int id,
                                   @RequestParam String newList,
                                   Model model){
        Book book = bookRepository.findById(id);
        if(!usersBookRepository.existsById(new UsersBookKey(book, user))){
            usersBookRepository.save(new UsersBook(new UsersBookKey(book, user)));
            transferService.saveToList(book, user, newList);
        } else {
            model.addAttribute("message", "Book has already been added.");
        }
        return "/search";
    }
}
