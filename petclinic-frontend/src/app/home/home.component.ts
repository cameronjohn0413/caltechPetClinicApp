import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  flag!: boolean;
  isAdmin!: boolean;
  
  constructor(public authService: AuthService) { }

  

  ngOnInit(): void {
    this.authService.login();
      this.authService.isLoggedIn.subscribe({
        next: (result: any) => {
          this.flag = result;
          console.log(result);
        },
        error: (error:any) => {
          console.log(error);
        },
        complete:() => {
          console.log("Completed from Home Component AuthService isLoggedIn Subscribe");
        }
      })
      if (sessionStorage.getItem('user') == 'admin') {
        this.isAdmin = true;
      }
  }

}