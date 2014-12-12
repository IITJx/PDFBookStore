package com.iitjx.pdfbookstore.dao;

import java.util.*;

import junit.framework.TestCase;

import org.junit.Before;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.iitjx.pdfbookstore.domain.Book;

public class BookDaoTest extends TestCase {
	private BookDao bookDao;
	private Book book;
	private Book book2;
	@Before
	@Override
	protected void setUp() throws Exception {
		book = new Book();
		book.setAuthorName("Author");
		book.setBookName("Book");
		book.setCategory("Category");
		book.setDescription("Sample");
		book.setImageId(1);
		book.setInsertionDate((new Date()).toString());
		book.setISBN("ISBN");
		book.setPdfId(20);
		book.setUploader(1);
		book2 = new Book();
		book2.setAuthorName("Author2");
		book2.setBookName("Book2");
		book2.setCategory("Category");
		book2.setDescription("Sample");
		book2.setImageId(2);
		book2.setInsertionDate((new Date()).toString());
		book2.setISBN("ISBN");
		book2.setPdfId(28);
		book2.setUploader(2);
		bookDao = mock(BookDao.class);
		when(bookDao.getBookBy("bookName","Book")).thenReturn(Arrays.asList(book));
		when(bookDao.insertBook(book)).thenReturn(true);
		when(bookDao.getAllBooks()).thenReturn(Arrays.asList(book,book2));
	}
	
	public void testInsertBook() {
		assertEquals(true,bookDao.insertBook(book));
	}
	
	public void testGetBookBy() {
		Book resultBook = bookDao.getBookBy("bookName", "Book").get(0);
		assertNotNull(resultBook);
		assertEquals(book.getAuthorName(), resultBook.getAuthorName());
		assertEquals(book.getBookName(), resultBook.getBookName());
		assertEquals(book.getCategory(), resultBook.getCategory());
		assertEquals(book.getDescription(), resultBook.getDescription());
		assertEquals(book.getImageId(), resultBook.getImageId());
		assertEquals(book.getPdfId(), resultBook.getPdfId());
		assertEquals(book.getUploader(), resultBook.getUploader());
	}
	
	public void testGetAllBooks() {
		List<Book>list = bookDao.getAllBooks();
		assertNotNull(list);
		assertEquals(2,list.size());
	}

}
