import { Component } from '@angular/core';
import { AuthenticationRequest } from 'src/app/services/models';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  authRequest : AuthenticationRequest={email:' ',password:''};
  errorMsg : Array<string>=[];

  login(){

  }
  register(){
    
  }
}
