import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/services';

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.scss']
})
export class ActivateAccountComponent {

  message = '';
  isOkay = true;
  submitted = false;
  constructor(
    private router :Router,
    private authService : AuthenticationService
  ){

  }
  redirectToLogin(){
    this.router.navigate(["login"]);
    
  }
  onCodeCompleted(token:string){
    this.confirmAccount(token);
  }
  private confirmAccount(token:string){
    this.authService.confirm({
      token
    }).subscribe({
      next: () => {
        this.message = "Your account is activated now enjoy all the benefits..";
        this.submitted=true;
        this.isOkay=true;
      },
      error:()=>{
        this.message= "Token expired or invalid";
        this.submitted=true;
        this.isOkay=false;
      }
    })
  }
}
