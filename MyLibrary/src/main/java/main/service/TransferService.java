package main.service;

import main.model.Book;
import main.model.User;
import main.model.BookWhichUserFinished.BookWhichUserFinished;
import main.model.BookWhichUserFinished.BookWhichUserFinishedKey;
import main.model.BookWhichUserIsReading.BookWhichUserIsReading;
import main.model.BookWhichUserIsReading.BookWhichUserIsReadingKey;
import main.model.BookWhichUserWantToRead.BookWhichUserWantToRead;
import main.model.BookWhichUserWantToRead.BookWhichUserWantToReadKey;
import main.repository.BookRepository;
import main.repository.UserRepository;
import main.repository.BookWhichUserFinishedRepository;
import main.repository.BookWhichUserIsReadingRepository;
import main.repository.BookWhichUserWantToReadRepository;
import main.repository.UsersBookRepository;
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
                bookWhichUserFinishedRepository.save(new BookWhichUserFinished(new BookWhichUserFinishedKey(book, user)));
                break;
            case "isReading":
                bookWhichUserIsReadingRepository.save(new BookWhichUserIsReading(new BookWhichUserIsReadingKey(book, user)));
                break;
            case "wantToRead":
                bookWhichUserWantToReadRepository.save(new BookWhichUserWantToRead(new BookWhichUserWantToReadKey(book, user)));
                break;
        }
    }

    public void removeFromList(Book book, User user, String oldList){
        switch(oldList){
            case "finished":
                bookWhichUserFinishedRepository.delete(new BookWhichUserFinished(new BookWhichUserFinishedKey(book, user)));
                break;
            case "isReading":
                bookWhichUserIsReadingRepository.delete(new BookWhichUserIsReading(new BookWhichUserIsReadingKey(book, user)));
                break;
            case "wantToRead":
                bookWhichUserWantToReadRepository.delete(new BookWhichUserWantToRead(new BookWhichUserWantToReadKey(book, user)));
                break;
            default:
                break;
        }
    }
}
