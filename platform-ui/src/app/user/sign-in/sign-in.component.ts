import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/_services/user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Token } from 'src/app/_model/token.model';
import { User } from 'src/app/_model/user.model';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.sass']
})
export class SignInComponent implements OnInit {
  token: Token;

  constructor(private userService: UserService) { }

  ngOnInit() { }

  onSubmit(username: string, password: string): void {
    this.userService.initialiseVariables();

    // TODO: Display a different message when web-services-authentication is down:
    // Browser and Concole
    const user: User = { username: username, password: password };
    this.userService.userAuthentication(user).subscribe(
      (data: Token) => {
        if (data == null) {
          console.log('User: ' + username + ' - Invalid username or password!');
          this.userService.isLoginError = true;
        } else {
          this.userService.signin(data);
        }
      },
      (err: HttpErrorResponse) => {
        this.userService.isLoginError = true;
      }
    );
  }

}
