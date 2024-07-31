import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './pages/main/main.component';
import { BooksListComponent } from './pages/books-list/books-list.component';


const routes: Routes = [
  {
    path:"",
    component:MainComponent,
    children:[
      {
        path:"",
        component:BooksListComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BookRoutingModule { }
