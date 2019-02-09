package org.springDataJpaOneToMany;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springDataJpaOneToMany.onedirectional.OnedirectionalOneToManyApplication;
import org.springDataJpaOneToMany.onedirectional.entity.Book;
import org.springDataJpaOneToMany.onedirectional.entity.Genre;
import org.springDataJpaOneToMany.onedirectional.repository.BookRepository;
import org.springDataJpaOneToMany.onedirectional.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=OnedirectionalOneToManyApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OnedirectionalTests {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	GenreRepository genreRepository;

	@Test
	public void test1JpaCreate() {
		//Insert
		Stream.of(new Genre("Приключения"), new Genre("Фантастика"), new Genre("Детектив"))
				.forEach(obj -> {
					genreRepository.save(obj);
				});
		List<Genre> genres = genreRepository.findAll();
		assertThat(genres != null).isTrue();
		assertThat(genres.size() == 3).isTrue();

		Stream.of(new Book("Том Сойер", genreRepository.findByName("Приключения")),
				new Book("Незнайка", genreRepository.findByName("Приключения")),
				new Book("Игра Эндера", genreRepository.findByName("Фантастика")),
				new Book("Игра престолов", genreRepository.findByName("Фантастика")),
				new Book("Шерлок Холмс", genreRepository.findByName("Детектив")),
				new Book("Убийство в Восточном экспрессе", genreRepository.findByName("Детектив"))


		)
				.forEach(obj -> {
					bookRepository.save(obj);
				});
		List<Book> books = bookRepository.findAll();
		assertThat(books != null).isTrue();
		Assert.assertEquals(6, books.size());


	}

	@Test
	public void test2JpaFind() {
		//Select
		Book book = bookRepository.findOneByName("Игра Эндера");
		assertThat(book.getName()).isEqualTo("Игра Эндера");
		assertThat(book.getGenre().getName()).isEqualTo("Фантастика");
	}


	@Test
	public void test3JpaChange() {
		//Update
		Book book = bookRepository.findOneByName("Игра Эндера");
		book.setName("Ender’s Game, 1985");

		Genre genre = new Genre("Научно-фантастический роман");
		genreRepository.save(genre);

		book.setGenre(genre);
		bookRepository.save(book);

		Book changedBook = bookRepository.findOneByName("Ender’s Game, 1985");
		assertThat(changedBook).isEqualTo(book);
	}

	@Test
	public void test4JpaDelete() {
		List<Book> booksForDelete = bookRepository.findAll();
		bookRepository.deleteAll(booksForDelete);
		Assert.assertEquals(0, bookRepository.findAll().size());

		List<Genre> genresForDelete = genreRepository.findAll();
		genreRepository.deleteAll(genresForDelete);
		Assert.assertEquals(0, genreRepository.findAll().size());
	}


}