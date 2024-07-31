import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BookRoutingModule } from './book-routing.module';
import { MainComponent } from './pages/main/main.component';
import { MenuComponent } from './components/menu/menu.component';
import { BooksListComponent } from './pages/books-list/books-list.component';
import { BookCardComponent } from './components/book-card/book-card.component';


@NgModule({
  declarations: [
    MainComponent,
    MenuComponent,
    BooksListComponent,
    BookCardComponent
  ],
  imports: [
    CommonModule,
    BookRoutingModule
  ]
})
export class BookModule { }
