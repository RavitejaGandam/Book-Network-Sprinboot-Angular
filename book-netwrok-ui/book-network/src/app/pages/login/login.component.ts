import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';
import { AuthenticationRequest } from 'src/app/services/models';
import { AuthenticationService } from 'src/app/services/services';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  authRequest : AuthenticationRequest={email:' ',password:''};
  errorMsg : Array<string>=[];

  constructor(
    private router : Router,
    private authService : AuthenticationService
  ){

  }

  login(){
    this.errorMsg = [];
    this.authService.authenticate(
      {
        body:this.authRequest
      }
    ).subscribe({
      next:()=>{
        this.router.navigate(["books"]);
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }
  register(){
    this.router.navigate(["register"]);
  }
}
