import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookResponse, PageResponseBookResponse } from 'src/app/services/models';
import { BookService } from 'src/app/services/services';

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.scss']
})
export class BooksListComponent implements OnInit {
displayBookDetails($event: BookResponse) {
throw new Error('Method not implemented.');
}
borrowBook($event: BookResponse) {
throw new Error('Method not implemented.');
}
  bookResponse:PageResponseBookResponse={}
  page: number = 0;
  size: number = 5;
  pages = [];
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

  goToFirstPage(){
    this.page = 0;
    this.findAllBooks();
  }

  goToPreviousPage(){
    this.page--;
    this.findAllBooks();
  }
  goToPage(page:number){
    this.page = page;
    this.findAllBooks();
  }

  goToNextPage(){
    this.page++;
    this.findAllBooks();
  }
  goToLastPage(){
    this.page = this.bookResponse.totalPages as number -1;
    this.findAllBooks();
  }
  get isLastPage(){
    return this.page = this.bookResponse.totalPages as number -1;
  }



}
