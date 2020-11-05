package main.service;

import main.model.Book;
import main.model.User;
import main.model.usersBook.BookWhichUserFinished;
import main.model.usersBook.BookWhichUserIsReading;
import main.model.usersBook.BookWhichUserWantToRead;
import main.model.usersBook.UsersBookKey;
import main.repository.BookRepository;
import main.repository.UserRepository;
import main.repository.usersBook.BookWhichUserFinishedRepository;
import main.repository.usersBook.BookWhichUserIsReadingRepository;
import main.repository.usersBook.BookWhichUserWantToReadRepository;
import main.repository.usersBook.UsersBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferService {

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

    @Transactional
    public void transferToAnotherList(Book book, User user, String oldList, String newList){
        removeFromList(book, user, oldList);
        saveToList(book, user, newList);
    }

    public void saveToList(Book book, User user, String newList){
        switch (newList){
            case "finished":
                bookWhichUserFinishedRepository.save(new BookWhichUserFinished(new UsersBookKey(book, user)));
                break;
            case "isReading":
                bookWhichUserIsReadingRepository.save(new BookWhichUserIsReading(new UsersBookKey(book, user)));
                break;
            case "wantToRead":
                bookWhichUserWantToReadRepository.save(new BookWhichUserWantToRead(new UsersBookKey(book, user)));
                break;
        }
    }

    public void removeFromList(Book book, User user, String oldList){
        switch(oldList){
            case "finished":
                bookWhichUserFinishedRepository.delete(new BookWhichUserFinished(new UsersBookKey(book, user)));
                break;
            case "isReading":
                bookWhichUserIsReadingRepository.delete(new BookWhichUserIsReading(new UsersBookKey(book, user)));
                break;
            case "wantToRead":
                bookWhichUserWantToReadRepository.delete(new BookWhichUserWantToRead(new UsersBookKey(book, user)));
                break;
            default:
                break;
        }
    }
}
