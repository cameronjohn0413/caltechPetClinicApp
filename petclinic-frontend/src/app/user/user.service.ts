import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { User } from './user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl: string = "http://18.222.162.102:8181/petclinic/user";

  constructor(public httpClient: HttpClient, public authService: AuthService) { }

  private loginHeader = this.authService.getHeaders();

  getAllUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.baseUrl + "/admin/getAllUsers", { headers:this.loginHeader });
  }

  createUser(user: User): Observable<any> {
    return this.httpClient.post(this.baseUrl + "/admin/newUser", user, { headers:this.loginHeader, responseType:'text'});
  }

  updateUserAdmin(user: User): Observable<any> {
    return this.httpClient.put(this.baseUrl + "/admin/updatePassword", user, {headers:this.loginHeader, responseType:'text'});
  }

  updateUserSelf(user: User): Observable<any> {
    return this.httpClient.put(this.baseUrl + "/updatePassword", user, {headers:this.loginHeader, responseType:'text'});
  }

  deleteUser(uid: any): Observable<any> {
    return this.httpClient.delete(this.baseUrl + "/admin/deleteUser?aid="+ uid, {headers:this.loginHeader, responseType:'text'});
  }

}
