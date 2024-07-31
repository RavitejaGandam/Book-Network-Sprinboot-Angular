import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PageResponseBookResponse } from 'src/app/services/models';
import { BookService } from 'src/app/services/services';

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.scss']
})
export class BooksListComponent implements OnInit {
  bookResponse:PageResponseBookResponse={}
  page: number = 0;
  size: number = 5;
  constructor(
    private booksService: BookService,
    private router: Router
  ){

  }
  ngOnInit(): void {
    this.findAllBooks();
    throw new Error('Method not implemented.');
  }
  findAllBooks() {
    this.booksService.findAllBooks(
      {
      page:this.page,
      size:this.size
    }
    ).subscribe(
      {
        next:(books)=>{
          this.bookResponse=books;
          console.log('ok');  
        }
      }
    )
    throw new Error('Method not implemented.');
  }



}
