import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../user/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn = new BehaviorSubject<boolean>(false);
  private authHeader!: HttpHeaders;

  constructor(public http: HttpClient) { }

  get isLoggedIn(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  logout() {
    this.loggedIn.next(false);
  }

  login() {
    this.loggedIn.next(true);
  }

  getHeaders(): HttpHeaders {
    return this.authHeader;
  }

  signIn(user: User): Observable<String> {
    const headers = new HttpHeaders(user ? {
      authorization : 'Basic ' + btoa(user.username + ':' + user.password)
    }  : { });
      this.authHeader = headers;

  //  return this.http.post('http://localhost:8080/petclinic/user/signIn', user, {headers: headers, responseType:'text'})
  return this.http.post('http://18.222.162.102:8181/petclinic/user/signIn', user, {headers: headers, responseType:'text'})
  }

  /*
  authenticate(credentials: any, callback: any) {
    const headers = new HttpHeaders(credentials ? {
      authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    }  : {});

    this.http.get('http://localhost:8080/petclinic/user/authenticate', {headers: headers}).subscribe(response => {
      if (response.hasOwnProperty('name')) {
        console.log(response);
        this.authenticated = true;
      } else {
        this.authenticated = false;
      }
      return callback && callback();
    });
  }
*/
  /*
  authenticate(user: User): Observable<String> {
    const headers = new HttpHeaders(user ? {
      authorization : 'Basic ' + btoa(user.username + ':' + user.password)
    } : { });

    return this.http.get('http://localhost:8080/petclinic/user/hey', {headers: headers, responseType:'text'})
  }
*/
}  
