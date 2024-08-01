import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {
  BookResponse,
  PageResponseBookResponse,
} from 'src/app/services/models';
import { BookService } from 'src/app/services/services';

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.scss'],
})
export class BooksListComponent implements OnInit {
  bookResponse: PageResponseBookResponse = {};
  page: number = 0;
  size: number = 5;
  pages = [];
  message = ' ';
  level: string = 'success';
  constructor(private booksService: BookService, private router: Router) {}
  ngOnInit(): void {
    this.findAllBooks();
    throw new Error('Method not implemented.');
  }
  findAllBooks() {
    this.booksService
      .findAllBooks({
        page: this.page,
        size: this.size,
      })
      .subscribe({
        next: (books) => {
          this.bookResponse = books;
          console.log('ok');
        },
      });
    throw new Error('Method not implemented.');
  }

  goToFirstPage() {
    this.page = 0;
    this.findAllBooks();
  }

  goToPreviousPage() {
    this.page--;
    this.findAllBooks();
  }
  goToPage(page: number) {
    this.page = page;
    this.findAllBooks();
  }

  goToNextPage() {
    this.page++;
    this.findAllBooks();
  }
  goToLastPage() {
    this.page = (this.bookResponse.totalPages as number) - 1;
    this.findAllBooks();
  }
  get isLastPage() {
    return (this.page = (this.bookResponse.totalPages as number) - 1);
  }

  displayBookDetails($event: BookResponse) {
    throw new Error('Method not implemented.');
  }
  borrowBook(book: BookResponse) {
    this.message = ' ';
    this.booksService
      .borrowBook({
        'book-id': book.id as number,
      })
      .subscribe({
        next: () => {
          this.level = 'success';
          this.message = 'Book borrowed successfully';
        },
        error: (error) => {
          this.level = 'error';
          this.message = error.error.error;
        },
      });
  }
}
