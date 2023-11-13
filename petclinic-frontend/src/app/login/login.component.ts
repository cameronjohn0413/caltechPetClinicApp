import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';
import { User } from '../user/user';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  loginUser! : User;

  constructor(public formBuilder: FormBuilder, public authService: AuthService, public router: Router) { }

  ngOnInit(): void {
      this.loginForm = this.formBuilder.group({
        username: [],
        password: []
      });
  }

  
  get username() : string {
    return this.loginForm.get('username')!.value;
  }

  get password() : string {
    return this.loginForm.get('password')!.value;
  }
  
 // credentials = { username: this.username, password: this.password };

  login() {
    this.loginUser = this.loginForm.value;
    
    this.authService.signIn(this.loginUser).subscribe({
      next: (result: any) => {
        console.log(result);
        console.log(this.loginUser.username);
        if (result == this.loginUser.username) {
          sessionStorage.setItem('user', result);
     //     this.authService.login();
          console.log("Login Component Login method result");
          this.router.navigate(["home"]);
        } else {
          alert("Login Authentication Failed");
        }
      },
      error: (error: any) => {
        console.log(error)
      },
      complete: () => {
        console.log("SignIn Authentication Success");
      }
    })
 //   this.authLogin(this.loginUser);
    this.loginForm.reset();
  }

/*
  authLogin(lgUser: User) {
    this.authService.authenticate(lgUser, () => {
      this.router.navigateByUrl('/home');
  });
  return false;
  }
*/

}
